package com.serius.learn.other;

import org.junit.Test;

public class HashTests {
	
	@Test
	public void hashcodeTest() {
		int j = 0;
		while(j < 100) {
			long start = System.currentTimeMillis();
			String key = "";
			int i = 0;
			while (i < 100000) {
				int t = Math.abs(key.hashCode()) % 2;
				i ++;
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
			j++;
		}
		
	}
}
