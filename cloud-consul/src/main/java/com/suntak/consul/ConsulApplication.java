package com.suntak.consul;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAutoConfiguration
@RestController
@EnableConfigurationProperties
@EnableFeignClients
@Slf4j
public class ConsulApplication {

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Environment env;

	@Autowired
	private SampleClient sampleClient;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Registration registration;

	@Value("${spring.application.name:cloud-consul}")
	private String appName;

	@RequestMapping("/me")
	public ServiceInstance me() {
		return this.registration;
	}

	@RequestMapping("/")
	public ServiceInstance lb() {
		return loadBalancer.choose(appName);
	}

	@RequestMapping("/rest")
	public String rest() {
		return this.restTemplate.getForObject("http://"+appName+"/me", String.class);
	}

	@RequestMapping("/choose")
	public String choose() {
		return loadBalancer.choose(appName).getUri().toString();
	}

	@RequestMapping("/myenv")
	public String env(@RequestParam("prop") String prop) {
		return env.getProperty(prop, "Not Found");
	}

	@RequestMapping("/prop")
	public String prop() {
		return properties().getProp();
	}

	@RequestMapping("/instances")
	public List<ServiceInstance> instances() {
		return discoveryClient.getInstances(appName);
	}

	@RequestMapping("/feign")
	public String feign() {
		return sampleClient.choose();
	}

	/*@Bean
	public SubtypeModule sampleSubtypeModule() {
		return new SubtypeModule(SimpleRemoteEvent.class);
	}*/

	@Bean
	public Properties properties() {
		return new Properties();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(ConsulApplication.class, args);
	}

	/*@Override
	public void onApplicationEvent(SimpleRemoteEvent event) {
		log.info("Received event: {}", event);
	}*/

	@FeignClient("cloud-consul")
	public interface SampleClient {

		@RequestMapping(value = "/choose", method = RequestMethod.GET)
		String choose();
	}
}
