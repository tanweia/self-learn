package com.serius.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.serius.learn.mongo.repository.impl.BaseRepositoryImpl;

@SpringBootApplication
@EnableMongoRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = "com.serius.learn.mongo")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}