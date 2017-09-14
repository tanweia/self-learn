package com.serius.learn.api.domain.base;

import java.util.List;
import java.util.Map;

public class Result<T> extends BaseResult{
	
	private static final long serialVersionUID = 1L;


	// 返回数据：基本类型包装类对象/常规可序列化对象
	private T dto;

	public static <T> Result<T> create() {
		Result<T> result = new Result<T>();
		result.setSuccess(false);
		return result;
	}

	public Result<T> success(){
		success(null);
		return this;
	}

	public Result<T> success(T dto){
		this.setSuccess(true);
		this.dto = dto;
		return this;
	}

	public Result<T> fail(String code,String desc){
		this.setSuccess(false);
		this.setCode(code);
		this.setDesc(desc);
		return this;
	}

	public Result<T> fail(String code){
		fail(code, null);
		return this;
	}

	public Result<T> code(String code){
		this.setCode(code);
		return this;
	}

	public Result<T> desc(String description){
		this.setDesc(description);
		return this;
	}

	public Result<T> sessionId(String sessionId){
		this.setSessionId(sessionId);
		return this;
	}

	public Result<T> data(T dto){
		this.dto = dto;
		return this;
	}

	public T getDto() {
		return dto;
	}

	public void setDto(T dto) {
		this.dto = dto;
	}

	/**
	 * 是否成功返回并带回数据
	 * @return
	 */
	public boolean isFailedOrDataEmpty(){
		return isFailed() || isDataEmpty();
	}

	/**
	 * 数据是否为空<br/>
	 * 如果是对象,则判断是否为空（NULL）<br/>
	 * 如果是字符串,则判断是为空串（NULL,""）<br/>
	 * 如果是List对象,则判断是否为空列表（isEmpty)<br/>
	 * 如果是Map对象,则判断是否为空Map(isEmpty)<br/>
	 * 如果是Array数组，则判断是否为空数组(length == 0)
	 * @return
	 */
	public boolean isDataEmpty(){
		if(dto == null){
			return true;
		}

		if(dto instanceof String){
			String str = (String) dto;
			return str.trim().equals("");
		}else if(dto instanceof List){
			@SuppressWarnings("rawtypes")
			List list = (List) dto;
			return list.isEmpty();
		}else if(dto instanceof Map){
			@SuppressWarnings("unchecked")
			Map<Object,Object> map = (Map<Object,Object>) dto;
			return map.isEmpty();
		}else if(dto instanceof Object[]){
			Object[] array = (Object[]) dto;
			return array.length == 0;
		}
		return false;
	}

}
