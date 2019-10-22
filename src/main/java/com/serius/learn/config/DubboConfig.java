/**
 * Copyright 2006-2015 handu.com
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serius.learn.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * create by serius on Jun 8, 2017
 */
@Configuration
public class DubboConfig {
	@Value( "${dubbo.registry.address}" )
	private String registryAddress;
	
	@Value( "${dubbo.registry.file}" )
	private String registryFile;

	@Value( "${dubbo.registry.timeout}" )
	private Integer registryTimeOut;

	@Value( "${dubbo.protocol.port}" )
	private Integer protocolPort;
	
	@Value( "${dubbo.http.port}" )
	private String httpPort;
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("base-tool");
		applicationConfig.setOwner("serius_tw");
		applicationConfig.setOrganization("serius_tw");
		applicationConfig.setVersion(httpPort);
		applicationConfig.setRegistry(registryConfig());
		applicationConfig.setMonitor(monitorConfig());
		return applicationConfig;
	}
	
	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setCheck(false);
		return consumerConfig;
	}
	
	@Bean
	public ProviderConfig providerConfig() {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setVersion("serius_tw");
		return providerConfig;
	}
	
	

	/**
	 * dubbo 内部扫描注解 --- 基于注解 驱动dubbo
	 * 
	 * @return BeanFactoryPostProcessor
	 */
	@Bean
	public static AnnotationBean annotationBean() {
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage("com.serius.learn.service");
		return annotationBean;
	}

	/**
	 * 监控中心配置，用于配置连接监控中心相关信息，可选。
	 * 
	 * @return
	 */
	@Bean
	public MonitorConfig monitorConfig() {
		MonitorConfig monitorConfig = new MonitorConfig();
		monitorConfig.setProtocol("registry");
		return monitorConfig;
	}

	/**
	 * 注册中心配置，用于配置连接注册中心相关信息。
	 */
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(registryAddress);
		registryConfig.setFile(registryFile);
		registryConfig.setTimeout(registryTimeOut);
		return registryConfig;
	}
	
	/***
	 * 协议配置，用于配置提供服务的协议信息
	 * 
	 * @return
	 */
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
		protocolConfig.setPort(protocolPort);
		return protocolConfig;
	}

}
