package com.serius.learn.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.serius.learn.utils.RegexUtil;

public class RegexTests {
	
	@Test
	public void urlTest() {
		String url = "http://clickc.admaster.com.cn/c/a108738,b2588680,c2160,i0,m101,8a2,8b2,0a$(h_MMAOS),0c$(h_MMAIMEI),0d$(h_MMAANDROIDID),n$(h_MMAMAC),o$(h_MMAOPENUDID),z$(h_MMAIDFA),f$(h_MMAIP),t$(m_TIMESTAMP),x$(h_MMAAKEY),y$(h_MMAANAME),h";
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
		String url = "http://clk.gentags.net/nck/iv-38996/st-1645/cr-2/oi-1281930/or-6568/adv-403/pcon-0/t_ip=__IP__&imei=__IMEI__&mac=__MAC__&mac1=__MAC1__&androidid=__AndroidID__&androidid1=__AndroidID1__&aaid=__AAID__&udid=__UDID__&idfa=__IDFA__&openudid=__OpenUDID__&duid=__DUID__&os=__OS__&ts=__TS__/";
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
