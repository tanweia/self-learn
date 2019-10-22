package com.serius.learn.other;

import java.io.FileInputStream;

import org.junit.Test;

import com.serius.learn.exception.BusinessException;

public class TryCatchFinallyTest {
	
	@Test
	public void nestTest() {
		try {
			FileInputStream fis = null;
			try {
				 fis = new FileInputStream("");
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
	
	@Test
	public void nestTest2() {
		try {
			finallyTest();
			System.out.println("444");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void exceptionTest3() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void finallyTest(){	
		try {
			System.out.println("111");
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println("222");
			throw new BusinessException("my test");
		} finally{
			System.out.println("333");
		}
	}
	
}
