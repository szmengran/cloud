package com.szmengran.security;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OauthApplication{

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}
}
