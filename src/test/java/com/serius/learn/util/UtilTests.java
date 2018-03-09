package com.serius.learn.util;

import org.junit.Test;

import com.serius.learn.utils.HttpUtil;

public class UtilTests {
	
	@Test
	public void httpPostTest() {
		String serverUrl = "http://xxx.xxx.xx";
		String jsonParam = "{\"id\":12}";
		String result = HttpUtil.post(serverUrl, jsonParam);
		System.out.println(result);
	}
	
}
