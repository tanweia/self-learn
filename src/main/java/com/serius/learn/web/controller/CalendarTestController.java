package com.serius.learn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("calendar")
public class CalendarTestController {
	
	
	@RequestMapping("/test")
	public String myTest(){
		return "dataAnalyze/calendarTest";
	}
	
	@RequestMapping("/echo")
	@ResponseBody
	public String echo(Integer startTime, Integer endTime, Integer timeType){
		System.out.println("timeType:" + timeType + "\r\n" + "startTime:" + startTime + "\r\n" + "endTime:" + endTime + "\r\n");
		return "just demo!";
	}
}
