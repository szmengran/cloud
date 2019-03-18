package com.suntak.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.fasterxml.jackson.annotation.JsonInclude;

@EnableResourceServer
@SpringBootApplication
@ComponentScan(basePackages = {"com.suntak.exception.controller", "com.szmengran.cloud"})
public class EngineeringOrderApplication {
	public static void main(String[] args) {
		SpringApplication.run(EngineeringOrderApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
