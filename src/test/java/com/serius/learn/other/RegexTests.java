package com.serius.learn.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.serius.learn.utils.RegexUtil;

public class RegexTests {
	
	@Test
	public void urlTest() {
		String url = "";
		System.out.println(RegexUtil.IsUrl(url));
	}
	
	@Test
	public void mesureTest() {
		String mesure = "455*592";
		System.out.println(RegexUtil.isMesure(mesure));
	}
	
	@Test
	public void regexTest1() {
		String regex = "^[a-zA-Z0-9，,!！\\u4e00-\\u9fa5]{0,50}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("。");
		System.out.println(matcher.matches());	
	}
	
	@Test
	public void regexTest2() {
		String regex = "^[1-9][0-9]{1,4}\\*[1-9][0-9]{1,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("90500*100");
		System.out.println(matcher.matches());
	}
	
	@Test
	public void regexTest3() {
		String regex = "^[1-9][0-9]{0,4}\\*[1-9][0-9]{0,4}\\*[1-9][0-9]{1,4}\\*[1-9][0-9]{1,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("2*2*200*200");
		System.out.println(matcher.matches());
	}
	
	@Test
	public void regexTest4() {
		String regex = "^(0|([1-9][0-9]{1,4}))\\*(0|([1-9][0-9]{1,4}))\\*[1-9][0-9]{1,4}\\*[1-9][0-9]{1,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("0*10000*54654*4545");
		System.out.println(matcher.matches());
	}
	
	@Test
	public void regexTest5() {
		String url = "";
		System.out.println(RegexUtil.IsUrl(url));
	}
	
	@Test
	public void regexTest6() {
		String regex = "^[a-zA-Z0-9，。！：；、？【】（）“”￥——《》\\u4e00-\\u9fa5]{4,50}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("！中？字符￥试s967【】：；。“的”《》（的）sdf，字符测试sdf，sdf78967”《》");
		System.out.println(matcher.matches());	
	}
	
	@Test
	public void regexTest7() {
		String regex = "[\\s\\S]*_[\\s\\S]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("1045678_1789_45");
		System.out.println(matcher.matches());
	}
}
