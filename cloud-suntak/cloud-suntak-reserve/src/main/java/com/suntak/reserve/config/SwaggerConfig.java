package com.suntak.reserve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.suntak.reserve.controller";
	private static final String VERSION = "1.0.0";
	
	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				   .title("来访登记API")
				   .version(VERSION)
				   .contact(new Contact("","", "android_li@sina.cn"))
				   .build();
	}
	
	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)
				   .select()
				   .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
				   .build()
	               .apiInfo(apiInfo());
	}
}
