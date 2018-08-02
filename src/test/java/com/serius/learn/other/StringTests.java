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
		String test = "http://xxx.xxx.xx&to=1";
		System.out.println(test.split("&to=")[1]);
	}
	
	@Test
	public void indexTest2() {
		String test = "http://img30.360buyimg.com/test/jfs/t7/274/580651897/22698/322fba56/5adec95dNa73a02f3.jpg";
		int index = test.indexOf("/jfs/");
		System.out.println(test.substring(index));
	}
	
	@Test
	public void replaceTest() {
		String test = "123434hhaa113";
		System.out.println(test.replaceAll("h", "o"));
		System.out.println(test);
	}
	
	@Test
	public void replaceTest1() {
		String test = "dfdsffd\t";
		System.out.println(test.replaceAll("\\\\t", "").trim() + "" + "---");
		System.out.println("----");
//		System.out.println(test);
	public void replaceTableTest() {
		String test = "  测	试 测\ts\r\n	  测 ";
		System.out.println(test);
		System.out.println(test.replaceAll("\t", "").replaceAll("\r", "").replaceAll("\n", "").trim());
	}
	
	@Test
	public void formatTest() {
		String test = "测试%s测试";
		System.out.println(String.format(test, "000"));
	}
	
	@Test
	public void containsTest() {
		String test = "http://img30.360buyimg.com/tg/tetst=http://www.baidu.com";
		System.out.println(test.split("http://img30.360buyimg.com/tg/")[1]);
	}
	
	@Test
	public void spiltTest() {
		String test = "http://www.baidu.com";
		System.out.println(test.split("to=").length);
		System.out.println(test);
	}
	
	@Test
	public void spiltTest2() {
		String test = "是|否";
		System.out.println(test.split("\\|").length);
		System.out.println(test.split("\\|")[2]);
	}
	
	@Test
	public void subStrTest() {
		long materialId = 0;
		String materialIdStr = String.valueOf(materialId);
		int len = materialIdStr.length();
		if (len >= 5) {
			materialIdStr = materialIdStr.substring(0, len - 2);
			materialId = Long.parseLong(materialIdStr);
		}
		System.out.println(materialId);
	}
}
