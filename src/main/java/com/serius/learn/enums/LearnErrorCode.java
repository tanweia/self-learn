package com.serius.learn.enums;

/**
 * create by tanweia on Jun 8, 2017
 * 错误码
 */
public enum LearnErrorCode {
	UNKNOWN_ERROR("LEARN-000", "未知错误"),
	PARAM_ERROR("LEARN-001", "请求参数错误或缺失"),
	
	SYSTEM_ERROR("LEARN-999", "系统繁忙，请稍后再试"),
	;

	private String code;		// 错误码
	private String message;		// 错误描述

	LearnErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
