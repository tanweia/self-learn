package com.serius.learn.other;

import java.util.Random;

import org.junit.Test;

public class RandomTests {
	
	@Test
	public void randomTest() {
		Random rand = new Random();
		while(true) {
			int randNum = rand.nextInt(8);
			System.out.println(randNum);
		}
		
	}
	
}
