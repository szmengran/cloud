package com.suntak.cloud.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Package com.suntak.cloud.activity
 * @Description: 活动管理
 * @date 2018年5月23日 上午9:10:35
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@EnableFeignClients
@SpringBootApplication
@RefreshScope
@ComponentScan(basePackages= {"com.suntak.exception.controller", "com.suntak.cloud", "com.szmengran.logging.service", "com.szmengran.common.orm"})
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
