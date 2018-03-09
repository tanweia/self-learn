package com.serius.learn.other;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class MapTests {
	
	
	@Test
	public void linkedMapTest() {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> linkedMap = new LinkedHashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		linkedMap.put("1", "1");
		linkedMap.put("2", "2");
		linkedMap.put("3", "3");
		linkedMap.put("4", "4");
		linkedMap.put("5", "5");
		System.out.println(map.values());
		System.out.println(linkedMap.values());
	}
}
