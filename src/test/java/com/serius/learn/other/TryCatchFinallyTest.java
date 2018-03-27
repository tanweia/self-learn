package com.serius.learn.other;

import java.io.FileInputStream;

import org.junit.Test;

public class TryCatchFinallyTest {
	
	@Test
	public void nestTest() {
		try {
			FileInputStream fis = null;
			try {
				 fis = new FileInputStream("E:/工作资料/谭伟工作计划.xlsx");
			} finally {
				fis.close();
				System.out.println("11111");
			}
			System.out.println("222222");
			String test = null;
			System.out.println(test.toCharArray());
		} catch (Exception e) {
			System.out.println("33333333");
		}
		System.out.println("4444444444");
		return;
	}
}	
