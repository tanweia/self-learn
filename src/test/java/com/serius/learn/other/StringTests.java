package com.serius.learn.other;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
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
	public void intTest() {
		int skuNum = 10;
		long value = 12;
		System.out.println((long)(value % skuNum));
	}
	
	@Test
	public void subTest() {
		String test = "2018091910";
		System.out.println(test.substring(0, 8));
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
	}
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
//		System.out.println(test.split("\\|")[2]);
		System.out.println(test);
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
	
	@Test
	public void spiltTest3() {
		long start = System.currentTimeMillis();
		int i = 0;
		while (i < 100000) {
			String sourceId = "tmp-1234-3456-123456";
			sourceId.split("-");
			i ++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public void spiltTest4() {
		long start = System.currentTimeMillis();
		int i = 0;
		while (i < 100000) {
			String sourceId = "tmp-1234-3456-123456";
			StringTokenizer token = new StringTokenizer(sourceId, "-");
			i ++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public void spiltTestall() {
		int i = 0;
		while (i < 100) {
			spiltTest6();
			i ++;
		}
	}
	
	@Test
	public void spiltTest5() {
		long start = System.currentTimeMillis();
		int i = 0;
		while (i < 10000) {
			String test = "12345-20180828_true_*_*";
			test.split("-");
			i ++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public void spiltTest6() {
		long start = System.currentTimeMillis();
		int i = 0;
		while (i < 10000) {
			String test = "201-1-12345-20180828_true_*_*";
			test.split("-");
			i ++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public void dateTest() {
		int j = 0;
		while(j < 1000) {
			long start = System.currentTimeMillis();
			int i = 0;
			while(i < 10000) {
				
				 LocalDateTime curDateTime = LocalDateTime.now();
			     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");
			     curDateTime.format(dateTimeFormatter);
			     System.out.println(curDateTime.format(dateTimeFormatter));
			     
			     i ++;
			}
			long end = System.currentTimeMillis();
			 System.out.println(end - start);
			 j++;
		}
	}
	
	@Test
	public void str2IntTest() {
		int j = 0;
		while(j < 1000) {
			long start = System.currentTimeMillis();
			int i = 0;
			while(i < 10000) {
				
				 String t = "20180919";
				 int date = Integer.parseInt(t);
//				 System.out.println(date);
			     //System.out.println(curDateTime.format(dateTimeFormatter));
			     
			     i ++;
			}
			long end = System.currentTimeMillis();
			 System.out.println(end - start);
			 j++;
		}
	}
	
	@Test
	public void replaceTest2() {
		int j = 0;
		while(j < 1000) {
			long start = System.currentTimeMillis();
			int i = 0;
			while(i < 10000) {
				
				 String t = "2018-09-19";
				 t.replaceAll("-", "");
//				 System.out.println(t.replaceAll("-", ""));
//				 System.out.println(date);
			     //System.out.println(curDateTime.format(dateTimeFormatter));
			     
			     i ++;
			}
			long end = System.currentTimeMillis();
			 System.out.println(end - start);
			 j++;
		}
	}
	
	@Test
	public void equalTest() {
		int j = 0;
		while(j < 1000) {
			long start = System.currentTimeMillis();
			int i = 0;
			while(i < 10000) {
				
				 String t = "2018-09-19";
				 String t1 = "2018-09-18";
				 if (!StringUtils.equals(t, t1)) {
//					System.out.println(false);
				 }
//				 t.replaceAll("-", "");
//				 System.out.println(t.replaceAll("-", ""));
//				 System.out.println(date);
			     //System.out.println(curDateTime.format(dateTimeFormatter));
			     
			     i ++;
			}
			long end = System.currentTimeMillis();
			 System.out.println(end - start);
			 j++;
		}
	}
	
	@Test
	public void str2intTest() {
		String dd = "09";
		System.out.println(Integer.parseInt(dd));
	}
	
	@Test
	public void spiltTest7() {
		String t = "123\t456\t789";
		String[] array = t.split("\t");
		System.out.println(array[0]);
	}
}
