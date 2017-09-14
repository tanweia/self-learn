package com.serius.learn.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.serius.learn.web.fillter.XssFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean XssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(xssFilter());
		registration.addUrlPatterns("/*");
		registration.setOrder(1);
		return registration;
	} 

	@Bean
	public Filter xssFilter() {
		XssFilter xssFilter = new XssFilter();
		xssFilter.setExcludedPageArray(null); // 需要过滤的页面
		return xssFilter;
	}
}
