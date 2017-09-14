package com.serius.learn.api.domain.base;

import java.io.Serializable;

public class BaseResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 调用链路唯一标识号
	private String sessionId;
	
	// 结果：flse失败；true 成功
	private boolean success;
	
	// 错误码
	private String code;
	
	// 错误描述
	private String desc;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public boolean isFailed() {
		return !success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
