package com.szmengran.cloud.common.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
@ComponentScan(basePackages = {"com.suntak.exception.controller", "com.szmengran.cloud", "com.szmengran.logging.service"})
public class SmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
	
}
