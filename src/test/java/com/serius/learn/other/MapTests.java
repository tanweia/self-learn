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
	
	@Test
	public void putAllTest() {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> hMap = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		hMap.put("3", "c");
		hMap.put("4", "d");
		Map<String, String> res = new HashMap<String, String>();
		res.putAll(map);
		res.putAll(hMap);
		System.out.println(res.keySet());
		System.out.println(res.values());
		System.out.println(res.get("3"));
	}
}
