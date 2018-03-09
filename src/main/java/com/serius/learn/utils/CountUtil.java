package com.serius.learn.utils;

import java.util.HashMap;

/**
 * Created by cdtanwei1 on Mar 9, 2018
 */
public class CountUtil {
	
	/**
     * 计数器
     * @param map
     * @param key
     */
    public static void counter (HashMap<String, MutableInteger> map, String key){  
    	 MutableInteger initValue = new MutableInteger(1);  
         // 利用 HashMap 的put方法弹出旧值的特性  
         MutableInteger oldValue = map.put(key, initValue);  
         if(oldValue != null){  
             initValue.setVal(oldValue.getVal() + 1);  
         }
    } 
    
    
    /**
     * create by tanweia on Jun 24, 2017
     * 可变Integer
     */
    public static class MutableInteger {
    	private int val; 
    	
        public MutableInteger(int val){  
            this.val = val;  
        }  
        
        public int getVal() {
    		return val;
    	}


    	public void setVal(int val) {
    		this.val = val;
    	}


    	public String toString() {  
            return Integer.toString(val);  
        }
    }
}
