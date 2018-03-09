package com.serius.learn.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	/*
	 * 
	 * 实现了ApplicationContextAware 接口，必须实现该方法；
	 * 
	 * 通过传递applicationContext参数初始化成员变量applicationContext
	 */

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

}
