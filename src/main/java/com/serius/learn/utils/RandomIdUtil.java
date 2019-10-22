package com.serius.learn.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by serius on Mar 9, 2018
 */
public class RandomIdUtil {
	
	/**
	 * 创建指定数量的随机字符串
	 * @param numberFlag 是否是数字
	 * @param length
	 * @return
	 */
	public static String generateVerifyCode(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
	
	/** 
	 * Title: generateSN 获取Sn编号，Sn编号为24位且唯一
	 * Description:  
	 * @return
	 */
	public static String generateSN() {
		return generateVerifyCode(true, 11) + System.currentTimeMillis();
	}
	
	/**
     * 生成随机数
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
	
	
	/**
	 * 生成唯一ID
	 * @return
	 */
	public static String createSid() {
		return UUID.randomUUID().toString();
	}
}
