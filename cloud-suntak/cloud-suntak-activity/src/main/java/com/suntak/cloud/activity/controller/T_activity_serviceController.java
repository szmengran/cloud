package com.suntak.cloud.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.activity.entity.T_activity_service;
import com.suntak.cloud.activity.service.T_activity_serviceService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Package com.suntak.cloud.activity.controller
 * @Description: 文体活动信息
 * @date 2018年5月23日 上午9:11:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "activity")
@RestController
@RequestMapping(path = "/api/v1/activity", produces = { "application/json" })
public class T_activity_serviceController {
	
	@Autowired
	T_activity_serviceService t_activity_serviceService;
	
	@ApiOperation(value = "根据主键获取活动信息", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping(value = "/services/{service_id}")
	public Response getActivity(@PathVariable("service_id") Integer service_id) throws Exception{
		T_activity_service t_activity_service = t_activity_serviceService.findById(service_id);
		Response response = new Response();
		response.setData(t_activity_service);
		return response;
	}
}
