package com.serius.learn.other;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.assertj.core.util.DateUtil;
import org.junit.Test;

public class DateTests {
	
	
	@Test
	public void test1() {
		Date date = DateUtil.parse("2018-02-01");
		System.out.println(date.toString());
	}
	
	@Test
	public void test2() {
		Date date = new Date();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse("2018-03-28 23:59:59", f);
		Instant instant = date.toInstant();
		System.out.println(instant);
		ZoneId zone = ZoneId.systemDefault();
		System.out.println(zone);
		LocalDateTime tmp = LocalDateTime.ofInstant(instant, zone);
//		return localDateTime.compareTo(tmp);
	}
}
