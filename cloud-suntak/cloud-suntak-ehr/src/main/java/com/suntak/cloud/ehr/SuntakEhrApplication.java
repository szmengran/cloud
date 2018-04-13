package com.suntak.cloud.ehr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.suntak.exception.controller", "com.suntak.cloud", "com.szmengran.logging.service"})
public class SuntakEhrApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuntakEhrApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
	
}
