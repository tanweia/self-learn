package com.serius.learn.api.domain.base;

import java.io.Serializable;

public class ExtraGeneralInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String demo;

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
}
