package com.serius.learn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.serius.learn.api.DemoService;
import com.serius.learn.api.domain.base.Request;
import com.serius.learn.api.domain.base.Result;
import com.serius.learn.api.domain.request.DemoReqDTO;
import com.serius.learn.api.domain.result.DemoResDTO;
import com.serius.learn.biz.DemoBiz;
import com.serius.learn.exception.LearnException;

@Service
public class DemoServiceImpl implements DemoService{
	private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Autowired
	DemoBiz demoBiz;
	
	@Override
	public Result<DemoResDTO> demo(Request<DemoReqDTO> request) {
		//Result<DemoResDTO> result = Result.create();
		Result<DemoResDTO> result = new Result<DemoResDTO>();
		
		try {
			demoBiz.demo();
			DemoResDTO dto = new DemoResDTO();
			result.success(dto);
		} catch (LearnException e) {
			logger.error("接口调用异常：{}", e.getMessage());
			result.fail(e.getCode(), e.getMessage()); 
		}
		
		return result;
	}
}
