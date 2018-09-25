package com.serius.learn.other;

import org.junit.Test;

public class ByteTests {
	
	@Test
	public void byteEqualTest() {
		byte t = 2;
		Integer t2 = 3;
		System.out.println(t == t2);
	}
	
	@Test
	public void switchTest() {
	}
	
    public static String toStringHex2(String s) {
	       byte[] baKeyword = new byte[s.length() / 2];
	       for (int i = 0; i < baKeyword.length; i++) {
	        try {
	         baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
	           i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	         e.printStackTrace();
	        }
	       }
	       try {
	        s = new String(baKeyword, "utf-8");// UTF-16le:Not
	       } catch (Exception e1) {
	        e1.printStackTrace();
	       }
	       return s;
	    }
}
