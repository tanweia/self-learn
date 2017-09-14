package com.serius.learn.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect("dataAnalyze/graphTest"); 
	}
}
