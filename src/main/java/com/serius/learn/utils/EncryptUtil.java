package com.serius.learn.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by serius on Mar 9, 2018
 */
public class EncryptUtil {
	public static final String UTF8 = "UTF-8";
	
	/**
	 * md5加密
	 * @Title: encryptMD5
	 * @param strInput
	 * @return
	 */
	public static String md5(String strInput, String charset) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strInput.getBytes(charset));
			byte b[] = md.digest();
			buf = new StringBuffer(b.length * 2);
			for (int i = 0; i < b.length; i++) {
				if (((int) b[i] & 0xff) < 0x10) { // & 0xff转换无符号整型
					buf.append("0");
				}
				// buf.append(Long.toString((int) b[i] & 0xff,
				// 16));//转换16进制,下方法同
				buf.append(Long.toHexString((int) b[i] & 0xff));
			}
		} catch (Exception ex) {
			return null;
		}
		return buf.toString();
	}
	
	/**
	 * sha1加密
	 * @param decript
	 * @return
	 */
	public static String sha1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/** 
     * 字符串转换unicode 
     */  
    public static String string2Unicode(String string) {  
       
        StringBuffer unicode = new StringBuffer();  
       
        for (int i = 0; i < string.length(); i++) {  
       
            // 取出每一个字符  
            char c = string.charAt(i);  
       
            // 转换为unicode  
            unicode.append("\\u" + Integer.toHexString(c));  
        }  
       
        return unicode.toString();  
    } 
    
    
    /**
     * 转UTF-8编码
     * @param value
     * @param sourceCharsetName
     * @return
     */
    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取sha1二进制数组
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes(UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    /**
     * 获取md5二进制数组
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes(UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }
    
    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
