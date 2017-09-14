package com.serius.learn.api.domain.result;

import java.io.Serializable;

public class DemoResDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String demoRes;

	public String getDemoRes() {
		return demoRes;
	}

	public void setDemoRes(String demoRes) {
		this.demoRes = demoRes;
	}
}
