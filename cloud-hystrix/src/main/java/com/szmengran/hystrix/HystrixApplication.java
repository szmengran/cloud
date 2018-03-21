package com.szmengran.hystrix;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szmengran.hystrix.stream.MockStreamServlet;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
@EnableHystrixDashboard
public class HystrixApplication extends SpringBootServletInitializer {
	
	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HystrixApplication.class).web(WebApplicationType.SERVLET);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public ServletRegistrationBean mockStreamServlet() {
        return new ServletRegistrationBean(new MockStreamServlet(), "/mock.stream");
    }

}
