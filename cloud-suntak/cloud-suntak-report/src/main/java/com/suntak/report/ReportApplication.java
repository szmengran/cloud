package com.suntak.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@RefreshScope
public class ReportApplication {

	public static void main(String args[]) {
		SpringApplication.run(ReportApplication.class, args);
	}
}
