package com.serius.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    
	@Override
    public void addInterceptors(InterceptorRegistry registry) {

		/**
		 * 多个拦截器组成一个拦截器链
		 * addPathPatterns 用于添加拦截规则
		 * excludePathPatterns 用户排除拦截
		 */
		/*registry.addInterceptor(null).addPathPatterns("/**")
      	.excludePathPatterns("/check");*/
        super.addInterceptors(registry);
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
}
