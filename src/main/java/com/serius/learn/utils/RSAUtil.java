package com.serius.learn.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


/**
 * Created by cdtanwei1 on Mar 9, 2018
 */
public class RSAUtil {

	
	private static String RSA = "RSA";  
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
     
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
 
    /** 
     * 随机生成RSA密钥对(默认密钥长度为1024) 
     *  
     * @return 
     */  
    public static KeyPair generateRSAKeyPair()  
    {  
        return generateRSAKeyPair(1024);  
    }  
  
    /** 
     * 随机生成RSA密钥对 
     *  
     * @param keyLength 
     *            密钥长度，范围：512～2048<br> 
     *            一般1024 
     * @return 
     */  
    public static KeyPair generateRSAKeyPair(int keyLength)  
    {  
        try  
        {  
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);  
            kpg.initialize(keyLength);  
            return kpg.genKeyPair();  
        } catch (NoSuchAlgorithmException e)  
        {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 用公钥加密 <br> 
     * 每次加密的字节数，不能超过密钥的长度值减去11 
     *  
     * @param data 
     *            需加密数据的byte数据 
     * @param pubKey 
     *            公钥 
     * @return 加密后的byte型数据 
     */  
    public static byte[] encryptData(byte[] data, PublicKey publicKey)  
    {  
        try  
        {  
            Cipher cipher = Cipher.getInstance(RSA);  
            // 编码前设定编码方式及密钥  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            // 传入编码数据并返回编码结果  
            return cipher.doFinal(data);  
        } catch (Exception e)  
        {  
            return null;  
        }  
    }  
    /**
     * <p>
     * 公钥分段加密
     * </p>
     * 
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    /**
     * <p>
     * 私钥分段加密
     * </p>
     * 
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    /**
     * <p>
     * 公钥分段解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
    /**
     * <P>
     * 私钥分段解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) {
		try {
			byte[] keyBytes = Base64Util.decode(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet,
							MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen
							- offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			return null;
		}
	}
    /** 
     * 用私钥解密 
     *  
     * @param encryptedData 
     *            经过encryptedData()加密返回的byte数据 
     * @param privateKey 
     *            私钥 
     * @return 
     */  
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)  
    {  
        try  
        {  
            Cipher cipher = Cipher.getInstance(RSA);  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            return cipher.doFinal(encryptedData);  
        } catch (Exception e)  
        {  
            return null;  
        }  
    }  
    
    
    /** 
     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法 
     *  
     * @param keyBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     */  
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,  
            InvalidKeySpecException  
    {  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);  
        PublicKey publicKey = keyFactory.generatePublic(keySpec);  
        return publicKey;  
    }  
  
    /** 
     * 通过私钥byte[]将公钥还原，适用于RSA算法 
     *  
     * @param keyBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     */  
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,  
            InvalidKeySpecException  
    {  
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);  
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);  
        return privateKey;  
    }  
  
    /** 
     * 使用N、e值还原公钥 
     *  
     * @param modulus 
     * @param publicExponent 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     */  
    public static PublicKey getPublicKey(String modulus, String publicExponent)  
            throws NoSuchAlgorithmException, InvalidKeySpecException  
    {  
        BigInteger bigIntModulus = new BigInteger(modulus);  
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);  
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);  
        PublicKey publicKey = keyFactory.generatePublic(keySpec);  
        return publicKey;  
    }  
  
    /** 
     * 使用N、d值还原私钥 
     *  
     * @param modulus 
     * @param privateExponent 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     */  
    public static PrivateKey getPrivateKey(String modulus, String privateExponent)  
            throws NoSuchAlgorithmException, InvalidKeySpecException  
    {  
        BigInteger bigIntModulus = new BigInteger(modulus);  
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);  
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);  
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);  
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);  
        return privateKey;  
    }  
  
    /** 
     * 从字符串中加载公钥 
     *  
     * @param publicKeyStr 
     *            公钥数据字符串 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception  
    {  
        try  
        {  
            byte[] buffer = Base64Util.decode(publicKeyStr);  
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e)  
        {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e)  
        {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e)  
        {  
            throw new Exception("公钥数据为空");  
        }  
    }  
  
    /** 
     * 从字符串中加载私钥<br> 
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。 
     *  
     * @param privateKeyStr 
     * @return 
     * @throws Exception 
     */  
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception  
    {  
        try  
        {  
            byte[] buffer = Base64Util.decode(privateKeyStr);  
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e)  
        {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e)  
        {  
            throw new Exception("私钥非法");  
        } catch (NullPointerException e)  
        {  
            throw new Exception("私钥数据为空");  
        }  
    }  
  
    /** 
     * 从文件中输入流中加载公钥 
     *  
     * @param in 
     *            公钥输入流 
     * @throws Exception 
     *             加载公钥时产生的异常 
     */  
    public static PublicKey loadPublicKey(InputStream in) throws Exception  
    {  
        try  
        {  
            return loadPublicKey(readKey(in));  
        } catch (IOException e)  
        {  
            throw new Exception("公钥数据流读取错误");  
        } catch (NullPointerException e)  
        {  
            throw new Exception("公钥输入流为空");  
        }  
    }  
  
    /** 
     * 从文件中加载私钥 
     *  
     * @param keyFileName 
     *            私钥文件名 
     * @return 是否成功 
     * @throws Exception 
     */  
    public static PrivateKey loadPrivateKey(InputStream in) throws Exception  
    {  
        try  
        {  
            return loadPrivateKey(readKey(in));  
        } catch (IOException e)  
        {  
            throw new Exception("私钥数据读取错误");  
        } catch (NullPointerException e)  
        {  
            throw new Exception("私钥输入流为空");  
        }  
    }  
    /** 
     * 读取密钥信息 
     *  
     * @param in 
     * @return 
     * @throws IOException 
     */  
    private static String readKey(InputStream in) throws IOException  
    {  
        BufferedReader br = new BufferedReader(new InputStreamReader(in));  
        String readLine = null;  
        StringBuilder sb = new StringBuilder();  
        while ((readLine = br.readLine()) != null)  
        {  
            if (readLine.charAt(0) == '-')  
            {  
                continue;  
            } else  
            {  
                sb.append(readLine);  
                sb.append('\r');  
            }  
        }  
  
        return sb.toString();  
    }
    /**
     * @Description: RSA-privateKey-签名
     * @author 
     * @param privateKey
     * @param content
     * @return 返回字节数组
     * @throws Exception
     */
    public static byte[] sign(RSAPrivateKey privateKey, byte[] content) throws Exception {
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(content);
            byte[] signResult = signature.sign();
            return signResult;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("RSA-privateKey-签名异常");
        }
    }
    /**
     * @Description: RSA-privateKey-签名
     * @author 
     * @param privateKey
     * @param contentBase64
     *            【验证签名原文-base64编码字符串】
     * @return 返回base64字符串
     * @throws Exception
     */
    public static String signBase64(RSAPrivateKey privateKey, String contentBase64) throws Exception {
        byte[] content = Base64Util.decode(contentBase64);
        byte[] signResult = sign(privateKey, content);
        return Base64Util.encode(signResult);
    }
    /**
     * @Description: RSA-publicKey-验证签名
     * @author 
     * @param publicKey
     * @param content
     *            【签名原文-字节数组】
     * @param sign
     *            【待验证签名-字节数组】
     * @return 签名结果
     * @throws Exception
     */
    public static boolean verify(RSAPublicKey publicKey, byte[] content, byte[] sign) throws Exception {
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKey);
            signature.update(content);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("RSA-publicKey-验证签名异常");
        }
    }
    /**
     * @Description: RSA-publicKey-验证签名
     * @author 
     * @param publicKey
     * @param contentBase64
     *            【签名原文-base64编码字符串】
     * @param signBase64
     *            【待验证签名-base64编码字符串】
     * @return 签名结果
     * @throws Exception
     */
    public static boolean verifyBase64(RSAPublicKey publicKey, String contentBase64, String signBase64) throws Exception {
        byte[] content = Base64Util.decode(contentBase64);
        byte[] sign = Base64Util.decode(signBase64);
        return verify(publicKey, content, sign);
    }
    /** 
     * 打印公钥信息 
     *  
     * @param publicKey 
     */  
    public static void printPublicKeyInfo(PublicKey publicKey)  
    {  
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;  
        System.out.println("----------RSAPublicKey----------");  
        System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());  
        System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());  
        System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());  
        System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());  
    }  
  
    public static void printPrivateKeyInfo(PrivateKey privateKey)  
    {  
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;  
        System.out.println("----------RSAPrivateKey ----------");  
        System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());  
        System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());  
        System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());  
        System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());  
  
    }  
    
    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
    	return Base64Util.encode(key);
    }
    
    //获得公钥
    public static String getPublicKey(KeyPair keyPair) throws Exception {
        //编码返回字符串
        return encryptBASE64(keyPair.getPublic().getEncoded());
    }

    //获得私钥
    public static String getPrivateKey(KeyPair keyPair) throws Exception {
        //编码返回字符串
        return encryptBASE64(keyPair.getPrivate().getEncoded());
    }
    
    public static void main(String[] args) throws Exception {
//		KeyPair par = generateRSAKeyPair();
//		System.out.println("private:" + getPrivateKey(par));
//		System.out.println("---------------");
//		System.out.println("public:" + getPublicKey(par));
		byte[] enStr = encryptByPublicKey("yhvqZ4Y%2BQyEQfZJl1NMiY3hYa8W6OMdLFXB0uUAHNyx3C52GwQs2h0gJmMlkmNHYvwsnWnJbZXeEhlgGbMBLocyQqhKrfbyxIxrbyjLtKMcLxCF4R%2BKNr5FOgRmMYBX3QhpVKlLsmVWUuV%2BsoIr%2BgTs7b1inyCd%2FQPicH14SKdlkymbFIYNdf%2FiQc24GK2wsz5dnLaUgLNSxiI%2FqNs0gLuNfBq7SD4bC0GVcCPr2n6j5fBfNjUm2lmpsXIEH0uK3Vm7Rrf9MZypyEw12QPGoA79iNl5YWniQiMiNi9PvIFhC9qOkjSsfYrJtfJOahMTUB6QjNpkuPV59ZGCjGWuAO08%3D".getBytes(), 
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo/10gg7VsTam8qTEkzRRT8T6VNcyPBEd/alEHiFMC6yRBZNqLr/qCuRBPu+TZ+UjdzGYSDMpoI//kEauVYaIHWHfhAB/xzUkmz4HJwLaaNGbaR1qmjupzAsR2ZSr6pl4df/jnlxHOXizw0Zk7rFSQtbddWNVpez+s7I3JoCJ5BwIDAQAB");
		String en = Base64Util.encode(enStr);
		System.out.println(URLEncoder.encode(en, "utf-8"));
		byte[] deStr = decryptByPrivateKey(Base64Util.decode(en), "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKj/XSCDtWxNqbypMSTNFFPxPpU1zI8ER39qUQeIUwLrJEFk2ouv+oK5EE+75Nn5SN3MZhIMymgj/+QRq5VhogdYd+EAH/HNSSbPgcnAtpo0ZtpHWqaO6nMCxHZlKvqmXh1/+OeXEc5eLPDRmTusVJC1t11Y1Wl7P6zsjcmgInkHAgMBAAECgYAb135TNf+0qRLWtxUWmAbKUYta77MUuMLCthRFZmsupuihMWlj7AkccjrCuBTa6Fvd1qfWP281OwEUZOjcwmn8AG463/VpcBRRNBtwHuKC7N2dIgbOpy7etWK8hqm8L/qFhUhpjfP1Y1/atxO+ShMMfzRN5iUoHfshsOAtXOQL4QJBAPOPdD0cu+sDubemo0TCOB+V8C15qeljDNbwcuBZbkhIZYwbcDM5WhJbfciN+ZiDt9bb3A/5wMMrD368ULAUvRECQQCxoQCRNtGBrmiaFNsT7C8SUE9ULxKOHUxSKDwbFpKQDKAtAix7BaNUWR4VPnCgAblmpqb/PHxUg98VJRfLh7SXAkAj6U/rPtRaV4dHk9NY7QYXaiUqn1i1J7yZ6IQXnjgLglkuqlEFzHwCXukR7BUULr+pd2tgE6QfYrb7Kcr/217xAkBaqKrqcskhYAvhQG3EUFKCDV0/wTq+L/PpJsHurWmmfdsuvaJIM8t13Ho/K3w+HkMrQjmPoaZvWFL+rszjQ2MHAkEAo2hrx4rILBHhhD+fx2MwfG9E6ftYJNOWSiWddLrRnjHMEPiugcv8OAbwVfVSQ+RwuAAl2u1X5jkRzv7oXVHn0w==");
		String de = new String(deStr);
		System.out.println(de);
	}

}
