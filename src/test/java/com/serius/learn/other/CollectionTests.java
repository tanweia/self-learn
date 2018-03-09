package com.serius.learn.other;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionTests {
	
	@Test
	public void arrayTest() {
		List<String> list = new ArrayList<String>();
		System.out.println(list.getClass().isArray());
	}
	
}
