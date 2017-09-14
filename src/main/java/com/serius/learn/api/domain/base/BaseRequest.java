package com.serius.learn.api.domain.base;

import java.io.Serializable;

public class BaseRequest implements Serializable{

	private static final long serialVersionUID = 2625837100810151496L;
	
	// 调用链路唯一标识号
	private String sessionId;
	
	// 通用扩展信息
	private ExtraGeneralInfo extraGeneralInfo;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public ExtraGeneralInfo getExtraGeneralInfo() {
		return extraGeneralInfo;
	}
	public void setExtraGeneralInfo(ExtraGeneralInfo extraGeneralInfo) {
		this.extraGeneralInfo = extraGeneralInfo;
	}
}
