package com.suntak.cloud.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Package com.suntak.cloud.oa
 * @Description: TODO
 * @date Jan 25, 2019 2:47:39 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@EnableFeignClients
@SpringBootApplication
@RefreshScope
@MapperScan("com.suntak.cloud.oa.mapper")
public class OaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OaApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
