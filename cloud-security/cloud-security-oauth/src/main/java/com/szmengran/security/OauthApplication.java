package com.szmengran.security;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class OauthApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("/templates/index.html");
//		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
	}
}
