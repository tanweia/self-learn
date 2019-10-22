package com.serius.learn.util;

import java.io.IOException;

import org.junit.Test;

import com.serius.learn.utils.HttpUtil;

public class UtilTests {
	
	@Test
	public void httpPostTest() throws IOException {
		String serverUrl = "";
		String jsonParam = "";
		String result = HttpUtil.doPost2(serverUrl);
		//String result = HttpUtil.doGet(serverUrl);
		System.out.println(result);
	}
	
	@Test
	public void ttTest() {
		String url = "";
		System.out.println("结果：" + HttpUtil.doGet(url));
	}
	
}
