package com.serius.learn.other;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTests {
	private static final Logger logger = LoggerFactory.getLogger(LogTests.class);
	
	
	@Test
	public void nullTest() {
		System.out.println("test->" + null);
	}
	
	@Test
	public void doubleTest() {
		double test = 10.4678;
		BigDecimal result = BigDecimal.valueOf(10.4678);
		for (int i = 0; i < 20; i++) {
			result = result.multiply(BigDecimal.valueOf(test));
			System.out.println(result);
			result.setScale(8, BigDecimal.ROUND_HALF_UP);
			if(i == 19){
				double t = result.doubleValue();
		        System.out.println(t);
			}
		}
		
	}
}
