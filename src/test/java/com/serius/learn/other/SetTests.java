package com.serius.learn.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SetTests {
	
	@Test
	public void arrayTest() {
		String[] array = {null, null};
		for (String string : array) {
			System.out.println(string);
		}
		Map<String, String> map = new HashMap<>();
	}
	
	
	@Test
	public void removeTest() {
		Set<Integer> set = new HashSet<>();
		set.add(0);
		set.remove(-1);
		System.out.println(set.size());
	}
	
	@Test
	public void paramTest() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		int t = 10;
		change(list, t);
		System.out.println(list);
		System.out.println(t);
	}
	
	private void change(List<Integer> list, int t) {
		List<Integer> lastList = new ArrayList<>();
		lastList.add(3);
		lastList.add(4);
		list = lastList;
		t = 11;
		System.out.println(list);
		System.out.println(t);
	}
}
