package com.suntak.cloud.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Package com.suntak.cloud.microservices
 * @Description: 崇达企业微信微服务
 * @date Nov 28, 2018 1:25:32 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@RefreshScope
public class MicroservicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss"); //日期格式处理
        return builder;
    }
}
