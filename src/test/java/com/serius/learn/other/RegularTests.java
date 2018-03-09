package com.serius.learn.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegularTests {
	
	@Test
	public void test1() {
		String str = "我只是测试一下";
		String regEx = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{4,30}$";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher matcher = pattern.matcher(str);
	    System.out.println(matcher.matches());
	}
}
