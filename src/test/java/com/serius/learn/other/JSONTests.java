package com.serius.learn.other;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class JSONTests {
	
	@Test
	public void jsonTest() {
		String tt = "[{\"attribute\":{\"education\":\"1,2,3,4\",\"userGender\":\"2\",\"ageGroup\":\"1,2,3,4,5,6\",\"cityId\":\"78\",\"provinceId\":\"2\",\"plus\":\"1\"},\"behavior\":{\"relationType\":\"1\",\"behavior4\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]}],\"behavior3\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]}],\"behavior2\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]}],\"behavior1\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]}]}},{\"attribute\":{\"education\":\"1\",\"userGender\":\"2\",\"ageGroup\":\"1\",\"cityId\":\"78\",\"provinceId\":\"2\",\"plus\":\"1\"},\"behavior\":{\"relationType\":\"1\",\"behavior4\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]},{\"dimensionType\":\"4\",\"brandName\":\"\"}],\"behavior3\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]},{\"dimensionType\":\"4\",\"brandName\":\"\"}],\"behavior2\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]},{\"dimensionType\":\"4\",\"brandName\":\"\"}],\"behavior1\":[{\"dimensionType\":\"1\",\"data\":[\"15126#15127#15132\",\"15126#15127#15132\"]},{\"dimensionType\":\"4\",\"brandName\":\"\"}]}}]";
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
