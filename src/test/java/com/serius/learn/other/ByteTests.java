package com.serius.learn.other;

import java.io.IOException;

import org.junit.Test;

public class ByteTests {
	
	@Test
	public void byteTest() throws IOException {
		String test = "mytest";
		byte[] arr = test.getBytes();
		String base64 = getBase64(arr);
		System.out.println(new String(base64ToByteArray(base64)));
	}
	
	@Test
	public void _OStr2Str() {
		/*String _OStr = "\347\262\276\345\207\206\346\240\207\351\242\2302";*/
		/*Iterator it = _OStr.
		while(it.hasNext()) {
			System.out.println(it.next());
		}*/
		/*_OStr = StringEscapeUtils.unescapeJava(_OStr);
		System.out.println(_OStr.charAt(1)); 
		System.out.println(_OStr);*/
		/*_OStr = _OStr.replaceAll("\\", "\\\\");
		System.out.println(_OStr);*/
	}
	
	
	@Test
	public void byteEqualTest() {
		byte t = 2;
		Integer t2 = 3;
		System.out.println(t == t2);
	}
	
	@Test
	public void switchTest() {
	}
	
    
    
    @Test
    public void byteTest1() {
    	byte[] t = null;
    	System.out.println((byte[])t);
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
	
	public static String _8to16(String str) {
		String[] arr = str.split("\\\\");
		String hex = "";
		for (String string : arr) {
			Integer tmp = Integer.valueOf(string, 8);
			String tess = Integer.toHexString(tmp);
			hex += tess;
		}
		return hex;
	}
	
	
	public static String hexStringToString(String s) {
		
		
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	
	@SuppressWarnings("restriction")
	public static String getBase64(byte[] ba) {
        if (ba == null)
            return null;
        return (new sun.misc.BASE64Encoder()).encode(ba);
    }

    /**
     * base64 解码为 byte[]
     * 
     * @param String base64
     * @return byte[]
     * @throws IOException
     * */
    @SuppressWarnings("restriction")
	public static byte[] base64ToByteArray(String base64) throws IOException {
        if (base64 == null)
            return null;
        return (new sun.misc.BASE64Decoder()).decodeBuffer(base64);
    }
}
