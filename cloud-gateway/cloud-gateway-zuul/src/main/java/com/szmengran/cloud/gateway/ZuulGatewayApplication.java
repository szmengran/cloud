package com.szmengran.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.szmengran.cloud.gateway
 * @Description: ZUUL api gateway
 * @date 2018年3月27日 下午3:37:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableZuulProxy
@RestController
public class ZuulGatewayApplication {
	public static void main(String args[]) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello world!";
	}
}
