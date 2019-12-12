package com.suntak.cloud.ems.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.exception.model.Response;

@FeignClient("cloud-suntak-erp")
public interface ErpClient {

	@GetMapping("/api/v1/erp/ebs/{org_id}/{header_id}")
	Response submitEbs(@PathVariable("org_id") Integer org_id, @PathVariable("header_id") Long header_id) throws Exception;
}
