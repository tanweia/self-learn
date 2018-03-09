package com.serius.learn.other;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringTests {
	
	@Test
	public void stringSubTest() {
		String str = "0101011";
		char[] charArray = str.toCharArray();
		List<String> list = new ArrayList<String>();
		for (char item : charArray) {
			list.add(String.valueOf(item));
		}
		System.out.println(list.get(1));
		System.out.println(str.substring(1, 2));
	}
	
	@Test
	public void joinTest() {
		String[] arrStr=new String[]{"a","b","c"};
		System.out.println(String.join("-", arrStr));
	}
	
	@Test
	public void charTest() {
		String deliveryHours = "000000000000000000000000";
		char[] hourArray = deliveryHours.toCharArray();
		int index = 3;
		hourArray[index] = '1';
		System.out.println(String.valueOf(hourArray));
	}
	
	@Test
	public void indexTest() {
		String test = "http://xxx.xxx.xx";
		System.out.println(test.split("&to=")[1]);
	}
}
