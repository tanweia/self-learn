package com.serius.learn.other;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTests {
	private static final Logger logger = LoggerFactory.getLogger(LogTests.class);
	
	
	@Test
	public void nullTest() {
		System.out.println("test->" + null);
	}
}
