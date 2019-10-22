package com.serius.learn.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类型转换工具类
 * Created by serius on Mar 9, 2018
 */
public class ConvertUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);
	
	/**
	 * 类型转换，如果T对象中有与S对象一样变量名的属性，则将S对象中的属性值复制到T对象中
	 * @param target
	 * @param source
	 * @return
	 */
	public static<T,S> T convert(T target,S source){
		try {
			//获取目标对象的Class
			Class<?> ct = target.getClass();
			Field[] fields = ct.getDeclaredFields();
			//获取来源对象的Class
			Class<?> cs = source.getClass();
			Field[] fieldss = cs.getDeclaredFields();
			for(Field field:fields){
				//获取
				String fieldName = field.getName();
				for(Field f:fieldss){
					String fName = f.getName();
					//如果目标对象的属性名和来源对象的属性名相同，则赋值
					if(fieldName.toLowerCase().equals(fName.toLowerCase())){
						//设置private变量可以设置值
						field.setAccessible(true);
						
						//将属性的首字母转换为大写
						String firstLetter = fieldName.substring(0, 1).toUpperCase();
						//获取set和get的方法名
						String setMethodName = "set" + firstLetter + fieldName.substring(1);
						String getMethodName = "get" + firstLetter + fName.substring(1);
						
						//获取方法对象,set方法需要传入参数类型
						Method setMethod = ct.getMethod(setMethodName, new Class[]{field.getType()});
						Method getMethod = cs.getMethod(getMethodName, new Class[]{});
						
						//调用get方法获取旧对象的值
						Object value = getMethod.invoke(source, new Object[]{});
						//调用set方法为新对象赋值
						setMethod.invoke(target, new Object[]{value});
					}
				}
			}
		} catch (Exception e) {
			logger.error("类型转换失败", e);
		} 
		return target;
	}
}
