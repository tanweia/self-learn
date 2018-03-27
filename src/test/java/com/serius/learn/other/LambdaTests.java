package com.serius.learn.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class LambdaTests {
	
	@Test
	public void filterTest1() {
		List<String> list = Arrays.asList("1","2");
		List<String> newList = list.stream().filter(s -> StringUtils.equals("1", s)).collect(Collectors.toList());
		System.out.println(newList);
	}
	
	@Test
	public void forEach1Test() {
		List<String> list = Arrays.asList("1","2","3");
		List<String> newList = list.stream().map(s -> s).collect(Collectors.toList());
		System.out.println(newList);
	}
	
	@Test
	public void forEach2Test() {
		List<String> list = new ArrayList<String>();
		List<String> newList = list.stream().map(s -> s).collect(Collectors.toList());
		System.out.println(newList);
	}
	
	@Test
	public void forEach3Test() {
		List<String> list = null;
		List<String> newList = list.stream().map(s -> s).collect(Collectors.toList());
		System.out.println(newList);
	}
	
}
