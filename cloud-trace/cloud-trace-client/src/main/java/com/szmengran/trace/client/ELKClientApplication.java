package com.szmengran.trace.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.szmengran.trace.zipkin
 * @Description: TODO
 * @date 2018年7月30日 下午2:10:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@RestController
public class ELKClientApplication {

	private static Logger log = LoggerFactory.getLogger(ELKClientApplication.class);
	  
	@RequestMapping("/xxx")
	public String home() {
		log.info("Handling home");
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(ELKClientApplication.class, args);
	}
}
