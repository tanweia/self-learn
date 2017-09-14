package com.serius.learn.service.mock;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import com.serius.learn.api.domain.base.Request;
import com.serius.learn.api.domain.base.Result;
import com.serius.learn.api.domain.request.DemoReqDTO;
import com.serius.learn.api.domain.result.DemoResDTO;
import com.serius.learn.biz.DemoBiz;
import com.serius.learn.service.DemoServiceImpl;

@RunWith(JMockit.class)
public class DemoServiceMockTests {
	@Tested
	DemoServiceImpl demoServiceImpl;
	
	@Injectable
	DemoBiz demoBiz;
	
	@Test
	public void test() {
		new Expectations() {
			{
				demoBiz.demo();
				result = "";
			}
		};
		DemoReqDTO dto = new DemoReqDTO();
		dto.setDemoReq("demo");
		Request<DemoReqDTO> request = new Request<DemoReqDTO>();
		Result<DemoResDTO> result = demoServiceImpl.demo(request);
		Assert.isTrue(result.isSuccess(), result.getDesc());
	}
}
