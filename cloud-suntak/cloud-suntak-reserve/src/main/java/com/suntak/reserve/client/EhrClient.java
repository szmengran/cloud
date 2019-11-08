package com.suntak.reserve.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

@FeignClient(name = "cloud-suntak-ehr")
public interface EhrClient {

	@GetMapping("/api/v1/ehr/contact/phone/{phone}")
	Response findContactByPhone(@PathVariable("phone") String phone);
}
