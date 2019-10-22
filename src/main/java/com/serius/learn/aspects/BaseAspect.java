package com.serius.learn.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.serius.learn.api.domain.base.Request;
import com.serius.learn.api.domain.base.Result;
import com.serius.learn.component.jsr303.JSR303CheckException;
import com.serius.learn.component.jsr303.JSR303Checker;
import com.serius.learn.component.logfilter.LogFilter;
import com.serius.learn.component.logfilter.LogFilterAnnotation;
import com.serius.learn.enums.LearnErrorCode;



/**
 * create by serius on Jun 8, 2017
 * 参数检测 + dubbo接口出入参日志打印
 */
@Service
@Aspect
public class BaseAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAspect.class);
	
	@Autowired
	LogFilter logFilter;

	@Pointcut("execution(* com.serius.learn.service.*.*.*(..))")
	private void serviceMethod() {

	}

	@SuppressWarnings("unchecked")
	@Around("serviceMethod()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature signature = (MethodSignature)point.getSignature();
		Object object = point.getTarget();
		Method method = object.getClass().getDeclaredMethod(signature.getName(), signature.getMethod().getParameterTypes());
		String simpleName = object.getClass().getSimpleName();
		String methodName = method.getName();
		
		boolean print = true;
		String[] sensitiveParams = {};
		String[] largeParams = {};
		LogFilterAnnotation annotation = method.getAnnotation(LogFilterAnnotation.class);
		if(annotation != null){ // 方法级别优先生效
			print = annotation.print();
			sensitiveParams = annotation.sensitiveparams();
			largeParams = annotation.largeparams();
		} else{ // 方法级别无特殊配置，则取bean级别
			annotation = object.getClass().getAnnotation(LogFilterAnnotation.class);
			if(annotation != null){
				print = annotation.print();
				sensitiveParams = annotation.sensitiveparams();
				largeParams = annotation.largeparams();
			}
		}
		
		Object[] params = point.getArgs();
		try {
			if(params!= null && params.length > 0){
				//打印入参
				if(print){
					logger.info(simpleName + "." + methodName + "-" + "requestJson==>\n{}", logFilter.toJSON(params[0], largeParams, sensitiveParams));
				}
				
				//参数检测
				try {
					Request<Object> request = (Request<Object>)params[0];
					if(request.getDto() != null){
						JSR303Checker.check(request.getDto());
					}
				} catch (JSR303CheckException e) {
					String[] message = e.getMessage().split(",");
					Result<Object> result = new Result<Object>();
					result.setSuccess(false);
					result.setCode(LearnErrorCode.PARAM_ERROR.getCode());
					result.setDesc(LearnErrorCode.PARAM_ERROR.getMessage() +  "：" + message[1]);
					if(print){
						logger.info(simpleName + "." + methodName + "-" + "resultJson==>\n{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
					}
					return result;
				}
			}
			
			//打印出参
			Object response = point.proceed();
			if(print){
				logger.info(simpleName + "." + methodName + "-" + "resultJson==>\n{}", logFilter.toJSON(response, largeParams, sensitiveParams));
			}
			return response;
		} catch (Exception e) {
			logger.error("未知异常", e);
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setCode(LearnErrorCode.UNKNOWN_ERROR.getCode());
			result.setDesc(LearnErrorCode.UNKNOWN_ERROR.getMessage());
			if(print){
				logger.info(simpleName + "." + methodName + "-" + "resultJson==>\n{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat));
			}
			return result;
		}
	}
	
}
