package com.suntak.cloud.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.activity.entity.T_activity_apply;
import com.suntak.cloud.activity.service.T_activity_applyService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Package com.suntak.cloud.activity.controller
 * @Description: 活动报名
 * @date 2018年5月23日 上午11:28:55
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "activity")
@RestController
@RequestMapping(path="/api/v1/activity", produces= {"application/json"})
public class T_activity_applyController {
	
	@Autowired
	T_activity_applyService t_activity_applyService;
	
	@ApiOperation(value = "活动报名申请", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PostMapping(value = "/apply/{service_id}")
	public Response apply(@PathVariable("service_id") Integer service_id, @RequestBody List<T_activity_apply> t_activity_applys) throws Exception {
		t_activity_applyService.apply(service_id, t_activity_applys);
		Response response = new Response();
		return response;
	}
}
