package com.serius.learn.web.fillter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter{
	
	// 需要排除的页面    
	private String[] excludedPageArray;   
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		if (excludedPageArray != null && excludedPageArray.length != 0) {
			for (String page : excludedPageArray) {//判断是否在过滤url之外    
				if(((HttpServletRequest) request).getServletPath().equals(page)){ 
					request.setAttribute("isExcluded", true);
					break;
				}
			}   

		}
		
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response); 
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public String[] getExcludedPageArray() {
		return excludedPageArray;
	}

	public void setExcludedPageArray(String[] excludedPageArray) {
		this.excludedPageArray = excludedPageArray;
	}
}
