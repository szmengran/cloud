package com.szmengran.cloud.order.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.szmengran.cloud.order.service.EngineeringOrderService;

@RestController
@RequestMapping(path = "/cloud/api/v1", produces={"application/json"})
public class EngineeringOrderController {
	
	@Autowired
	EngineeringOrderService engineeringOrderService;
	
	@PatchMapping("/engineeringorder/{engineering_id}")
	public Response receiveOrder(Principal principal, @PathVariable String engineering_id) throws Exception{
		Response response = new Response();
		String designuserid = principal.getName();
		engineeringOrderService.receive(engineering_id, designuserid);
		response.setData("接单成功");
		return response;
	}
	
	@GetMapping("/abc")
	public Response test() throws Exception {
		Response response = new Response();
		return response;
	}
}
