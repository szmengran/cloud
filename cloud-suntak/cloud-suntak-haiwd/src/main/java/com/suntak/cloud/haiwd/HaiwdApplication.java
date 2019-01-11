package com.suntak.cloud.haiwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Package com.suntak.cloud.haiwd
 * @Description: 海威达服务
 * @date Jan 11, 2019 1:48:12 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
public class HaiwdApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HaiwdApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss"); //日期格式处理
        return builder;
    }
}
