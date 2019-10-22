package com.serius.learn.other;

import org.junit.Test;

public class HashCodeTests {
	
	@Test
	public void test() {
		int i = 0;
		while (i < 100) {
			toHashCodeTest();
			i ++;
		}
	}
	
	@Test
	public void toHashCodeTest() {
		long start = System.currentTimeMillis();
		int i = 1;
		while (i < 10000) {
			String deviceId = "";
			boolean flag = Math.abs(deviceId.hashCode()) % 2 == 0 ? true : false;
			i ++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
}
