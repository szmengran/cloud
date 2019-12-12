package com.suntak.cloud.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.erp.entity.EbsResponse;
import com.suntak.cloud.erp.service.CuxEmsService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

@RestController
@Api("EBS信息对接")
public class CuxEmsController {

	@Autowired
	private CuxEmsService cuxEmsService;
	
	@GetMapping("/ebs/{org_id}/{header_id}")
	public Response submitEbs(@PathVariable("org_id") Integer org_id, @PathVariable("header_id") Long header_id) {
		EbsResponse ebsResponse = cuxEmsService.submitEBS(org_id, header_id);
		Response response = new Response();
		response.setData(ebsResponse);
		return response;
	}
}
