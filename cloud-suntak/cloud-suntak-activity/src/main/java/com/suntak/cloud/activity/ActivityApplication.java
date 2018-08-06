package com.suntak.cloud.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class ActivityApplication {
	public static void main(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
	}

	@RequestMapping("/xxxx")
	public String home() {
		return "Hello World";
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
