package com.serius.learn.service.dubbo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.serius.learn.api.DemoService;
import com.serius.learn.api.domain.base.Request;
import com.serius.learn.api.domain.base.Result;
import com.serius.learn.api.domain.request.DemoReqDTO;
import com.serius.learn.api.domain.result.DemoResDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath:dubbo-consumer-test.xml"})
public class DemoServiceDubboTests {
	@Autowired
	DemoService demoService;
	
	@Test
	public void test() {
		DemoReqDTO dto = new DemoReqDTO();
		dto.setDemoReq("demo");
		Request<DemoReqDTO> request = new Request<DemoReqDTO>();
		Result<DemoResDTO> result = demoService.demo(request);
		Assert.isTrue(result.isSuccess(), result.getDesc());
	}
}
