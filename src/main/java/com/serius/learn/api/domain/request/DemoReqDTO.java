package com.serius.learn.api.domain.request;

import java.io.Serializable;

public class DemoReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String demoReq;

	public String getDemoReq() {
		return demoReq;
	}

	public void setDemoReq(String demoReq) {
		this.demoReq = demoReq;
	}
}
