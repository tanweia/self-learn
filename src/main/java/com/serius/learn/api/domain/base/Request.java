package com.serius.learn.api.domain.base;

public class Request<T> extends BaseRequest{
	
	private static final long serialVersionUID = 1L;
	
	// 请求数据：基本类型包装类对象/常规可序列化对象
	private T dto;

	public Request(){

	}

	public Request(T dto){
		this.dto = dto;
	}

	public static <T> Request<T> create() {
		Request<T> result = new Request<T>();
		return result;
	}

	public Request<T> sessionId(String sid){
		this.setSessionId(sid);
		return this;
	}

	public Request<T> dto(T dto){
		this.dto = dto;
		return this;
	}

	public T getDto() {
		return dto;
	}

	public void setDto(T dto) {
		this.dto = dto;
	}
}
