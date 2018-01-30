package com.target.client.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkeletonFeignConfiguration {

	private static final int THREE_SECONDS = 3000;

	@Bean
	public Logger.Level feignLogger() {
		return Logger.Level.FULL;
	}

	@Bean
	public Request.Options options() {
		return new Request.Options(THREE_SECONDS, THREE_SECONDS);
	}

	@Bean
	public SkeletonErrorDecoder myErrorDecoder() {
		return new SkeletonErrorDecoder();
	}

}
