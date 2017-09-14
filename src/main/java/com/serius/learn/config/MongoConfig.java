package com.serius.learn.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;


/**
 * create by tanweia on Jun 8, 2017
 * 主要负责性能调优的一些配置
 */
@Configuration
public class MongoConfig {
	
	@Bean
	public MongoClientOptions mongoClientOptions() {
		MongoClientOptions options = new MongoClientOptions.Builder()
				.writeConcern(WriteConcern.W1/*.withJournal(true)*/) 
				.minConnectionsPerHost(10)
				.connectionsPerHost(100) // 每个主机最大请求数
				.threadsAllowedToBlockForConnectionMultiplier(10) // 一个socket最大的等待请求数
				.connectTimeout(10000) // 链接超时时间
				.maxWaitTime(1000 * 60 * 2) // 长链接的最大等待时间
				.socketKeepAlive(false) // 是否保持长链接
				.socketTimeout(5000) // 读写数据超时时间
				.readPreference(ReadPreference.primaryPreferred()) // 最近优先策略
				.build();
		return options;
	}
	
}
