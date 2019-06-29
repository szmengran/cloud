package com.suntak.cloud.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Package com.suntak.cloud.interview
 * @Description: 面试流程
 * @date 2018年7月19日 上午9:56:22
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@EnableFeignClients
@SpringBootApplication
@RefreshScope
public class SuntakRecruitmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuntakRecruitmentApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
