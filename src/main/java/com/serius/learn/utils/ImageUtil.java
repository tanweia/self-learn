package com.serius.learn.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by serius on Mar 9, 2018
 */
public class ImageUtil {
	/**
     * base64字符串转化成图片 
     * @param imgStr
     * @return
     */
    public static File base642image(String imgStr, String filePath) {   
		try {
			// Base64解码
			String b64encoded = imgStr.substring(imgStr.indexOf("base64,") + "base64,".length(), imgStr.length()); // 解码
			byte[] b = Base64Util.decode(b64encoded);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpg图片
			return FileUtil.byte2fileByNio(b, filePath);
		} catch (Exception e) {
			return null;
		}
    }  
    
    /** 
     * 检测图片路径是否为真实有效的路径方法 
     * @param src 
     * @return 
     */  
    public static boolean checkBase64Image(String src){  
        //使用正则表达式，排除img标签src属性值已经是base64编码的情况  
        Pattern pattern = Pattern.compile("^data:image/(png|gif|jpg|jpeg|bmp|tif|psd|ICO|\\*);base64,.*");  
        Matcher matcher = pattern.matcher(src);  
        if(matcher.matches()){
        	return true; 
        }
        return false;  
    }
    
    /** 
     * 检测图片路径是否为真实有效的路径方法 
     * @param src 
     * @return 
     */  
    public static boolean checkImage(String src){  
        //使用正则表达式，排除img标签src属性值已经是base64编码的情况  
        Pattern pattern = Pattern.compile("^data:image/(png|gif|jpg|jpeg|bmp|tif|psd|ICO);base64,.*");  
        Matcher matcher = pattern.matcher(src);  
        if(matcher.matches()){
        	return false; 
        }
        //排除src路径并非图片格式的情况  
        pattern=Pattern.compile("^.*[.](png|gif|jpg|jpeg|bmp|tif|psd|ICO)$");  
        matcher = pattern.matcher(src);  
        if(!matcher.matches()){
        	 return false; 
        }
        //排除图片路径不存在的情况  
        File file = new File(src);  
        if(!file.exists()){
        	return false;  
        }
        return true;  
    }
}
