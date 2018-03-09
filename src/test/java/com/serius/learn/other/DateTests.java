package com.serius.learn.other;

import java.util.Date;

import org.assertj.core.util.DateUtil;
import org.junit.Test;

public class DateTests {
	
	
	@Test
	public void test1() {
		Date date = DateUtil.parse("2018-02-01");
		System.out.println(date.toString());
	}
}
