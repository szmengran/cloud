package com.szmengran.cloud.order.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.exception.model.Response;
import com.szmengran.cloud.order.service.EngineeringOrderService;

@RestController
@RequestMapping(path = "/cloud/api/v1", produces={"application/json"})
@ComponentScan(basePackages={"com.suntak.exception.controller"})
public class EngineeringOrderController {
	
	@Autowired
	EngineeringOrderService engineeringOrderService;
	
	@PutMapping(value="/engineeringorder/{engineering_id}")
	public Response receiveOrder(Principal principal, @PathVariable String engineering_id) throws Exception{
		Response response = new Response();
		String designuserid = principal.getName();
		engineeringOrderService.receive(engineering_id, designuserid);
		return response;
	}
}
