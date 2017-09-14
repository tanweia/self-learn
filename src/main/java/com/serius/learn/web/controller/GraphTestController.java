package com.serius.learn.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("graph")
public class GraphTestController {
	
	@RequestMapping("/test")
	public String myTest() {
		return "dataAnalyze/graphTest";
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public Map<String, Object> getData() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mytest> list = new ArrayList<GraphTestController.Mytest>();
		
		Mytest t1 = new Mytest();
		t1.setTime(20170826);
		t1.setCount(100L);
		Mytest t2 = new Mytest();
		t2.setTime(20170827);
		t2.setCount(200L);
		Mytest t3 = new Mytest();
		t3.setTime(20170828);
		t3.setCount(150L);
		Mytest t4 = new Mytest();
		t4.setTime(20170829);
		t4.setCount(400L);
		Mytest t5 = new Mytest();
		t5.setTime(20170830);
		t5.setCount(460L);
		Mytest t6 = new Mytest();
		t6.setTime(20170831);
		t6.setCount(600L);
		Mytest t7 = new Mytest();
		t7.setTime(20170901);
		t7.setCount(96L);
		
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		list.add(t7);
		Long total = 2006L;
		
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	
	public static class Mytest {
		private Integer time;
		private Long count;
		
		public Integer getTime() {
			return time;
		}
		public void setTime(Integer time) {
			this.time = time;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
	}
}


