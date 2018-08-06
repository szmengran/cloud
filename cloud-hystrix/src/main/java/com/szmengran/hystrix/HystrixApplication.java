package com.szmengran.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
@RefreshScope
@EnableHystrixDashboard
public class HystrixApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(HystrixApplication.class, args);
	}

}
