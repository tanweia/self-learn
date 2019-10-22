package com.serius.learn.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;


/**
 * create by serius on Jun 8, 2017
 */
public class Base64Util {
	private static Logger log = Logger.getLogger(Base64Util.class);
	private static Base64 base64 = new Base64();

	
	/**
	 * 指定编码格式序列化字符串
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String encode(String str, String charsetName) {
		String encodeSql = null;
		try {
			byte[] enbytes = base64.encode(str.getBytes(charsetName));
			encodeSql = new String(enbytes);
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		}
		return encodeSql;
	}

	/**
	 * 序列化字节数组
	 * @param pArray
	 * @return
	 */
	public static String encode(byte[] pArray) {
		byte[] enbytes = base64.encode(pArray);
		String encodeSql = new String(enbytes);
		return encodeSql;
	}

	/**
	 * 指定编码格式反序列化
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String decode(String str, String charsetName) {
		// 解码
		byte[] debytes = base64.decode(str.getBytes());
		String decodeSql = null;
		try {
			decodeSql = new String(debytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		}
		return decodeSql;
	}

	/**
	 * 反序列化成字节数组
	 * @param str
	 * @return
	 */
	public static byte[] decode(String str) {
		// 解码
		byte[] debytes = base64.decode(str.getBytes());
		return debytes;
	}
	
}
