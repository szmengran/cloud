package com.szmengran.security;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@RefreshScope
@ComponentScan(basePackages = {"com.suntak.exception.controller", "com.szmengran.security", "com.szmengran.logging.service"})
public class OauthApplication{

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}
}
