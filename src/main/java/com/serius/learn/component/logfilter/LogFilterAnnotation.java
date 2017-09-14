package com.serius.learn.component.logfilter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogFilterAnnotation {
	
	/**
	 * 是否打印日志
	 */
	boolean print() default true;
	
	/**
	 * 需要脱敏的参数
	 */
	String[] sensitiveparams() default {};
	
	/**
	 * 值过长，需要截取的参数
	 */
	String[] largeparams() default {};
}
