package com.suntak.cloud.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Package com.suntak.cloud.wechat
 * @Description: 企业微信交互表
 * @date 2018年8月31日 下午2:33:47
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@RefreshScope
@EnableFeignClients
public class WechatApplicantion {
	public static void main(String[] args) {
		SpringApplication.run(WechatApplicantion.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
