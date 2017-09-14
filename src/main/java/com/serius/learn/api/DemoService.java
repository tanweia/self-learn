package com.serius.learn.api;

import com.serius.learn.api.domain.base.Request;
import com.serius.learn.api.domain.base.Result;
import com.serius.learn.api.domain.request.DemoReqDTO;
import com.serius.learn.api.domain.result.DemoResDTO;

public interface DemoService {
	
	Result<DemoResDTO> demo(Request<DemoReqDTO> request);
}
