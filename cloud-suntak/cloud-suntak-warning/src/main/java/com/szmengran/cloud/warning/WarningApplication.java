package com.szmengran.cloud.warning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/** 
 * @Package com.szmengran.cloud.warning 
 * @Description: 程序运行入口
 * @date Mar 22, 2019 2:00:27 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@EnableFeignClients
@RefreshScope
@SpringBootApplication
public class WarningApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarningApplication.class, args);
    }
    
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
