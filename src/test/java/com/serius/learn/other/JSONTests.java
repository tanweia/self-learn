package com.serius.learn.other;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JSONTests {
	
	@Test
	public void jsonTest() {
		String tt = "";
		com.alibaba.fastjson.JSONArray array = JSON.parseArray(tt);
		System.out.println(array.toJSONString());
	}
	
	@Test
	public void jsonArrayTest() {
		JSONArray jsonarray = new JSONArray();
		jsonarray.add("1");
		jsonarray.add("2");
		jsonarray.add("3");
		System.out.println(jsonarray.toJSONString());
		jsonarray.set(1, "4");
		System.out.println(jsonarray.toJSONString());
	}
}
