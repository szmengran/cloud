package com.suntak.cloud.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 设备系统对接API
 * @author limaoyuan
 *
 */
@EnableFeignClients
@SpringBootApplication
@RefreshScope
public class EmsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        return builder;
    }
}
