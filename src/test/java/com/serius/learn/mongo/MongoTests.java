package com.serius.learn.mongo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.serius.learn.mongo.entity.DemoEntity;
import com.serius.learn.mongo.repository.DemoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTests {
	@Autowired
	DemoRepository demoRepository;
	
	
	@Test
	public void findTest(){
		DemoEntity entity = new DemoEntity();
		entity.setDemoId("demoId");
		entity.setDemoName("demoName");
		demoRepository.insert(entity);
		
		Criteria criteria = Criteria.where("demoId").is("demoId");
		Query query = new Query(criteria);
		List<DemoEntity> list = demoRepository.queryList(query);
		System.out.println(list.get(0).getDemoName());
	}
}
