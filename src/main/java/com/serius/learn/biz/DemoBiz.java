package com.serius.learn.biz;

import org.springframework.stereotype.Service;

import com.serius.learn.enums.LearnErrorCode;
import com.serius.learn.exception.LearnException;

@Service
public class DemoBiz {
	
	public String demo() {
		throw new LearnException(LearnErrorCode.SYSTEM_ERROR);
	}
	
}
