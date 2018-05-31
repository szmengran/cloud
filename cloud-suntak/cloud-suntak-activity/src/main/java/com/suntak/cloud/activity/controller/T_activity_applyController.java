package com.suntak.cloud.activity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.suntak.cloud.activity.entity.T_activity_apply;
import com.suntak.cloud.activity.entity.T_activity_service;
import com.suntak.cloud.activity.entity.ext.T_activity_apply_ext;
import com.suntak.cloud.activity.service.T_activity_applyService;
import com.suntak.cloud.activity.service.T_activity_serviceService;
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
	
	@Autowired
	T_activity_serviceService t_activity_serviceService;
	
	@ApiOperation(value = "活动报名申请", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PostMapping(value = "/apply/{username}/{service_id}")
	public Response apply(@PathVariable("username") String username, @PathVariable("service_id") Integer service_id, @RequestBody List<T_activity_apply> t_activity_applys) throws Exception {
		Response response = new Response();
		try {
			t_activity_applyService.apply(username, service_id, t_activity_applys);
		} catch (Exception e) {
			if (e.getMessage().contains("UK_T_ACTIVITY_APPLY")) {
				response.setData("存在已报名的员工，请勿重复报名！");
				response.setStatus(204);
			} else {
				throw e;
			}
		}
		return response;
	}
	
	@ApiOperation(value = "活动签到", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PatchMapping("/apply/{username}/{apply_id}/{service_id}/{empcode}")
	public Response signin(@PathVariable("username") String username, @PathVariable("apply_id") Integer apply_id, @PathVariable("service_id") Integer service_id, @PathVariable("empcode") String empcode) throws Exception {
		t_activity_applyService.signin(username, apply_id);
		new Thread(new Runnable() {
			@Override
			public void run() {
				T_activity_service t_activity_service;
				try {
					t_activity_service = t_activity_serviceService.findById(service_id);
					RestTemplate restT = new RestTemplate();
					restT.postForEntity("http://openfire.suntakpcb.com:6001/suntak-consumer-web/api/v1/serviceitem/notification/notification/Li542184118123/"+empcode, t_activity_service, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		Response response = new Response();
		return response;
	}
	
	@ApiOperation(value = "查找报名的员工信息", response = Response.class)
	@GetMapping("/apply/{service_id}/{companycode}/{empcode}")
	public Response findByConditions(@PathVariable("companycode") String companycode, @PathVariable("service_id") String service_id, @PathVariable("empcode") String empcode) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("a.service_id", service_id);
		if (companycode !=null && !"*".equals(companycode)) {
			params.put("b.companycode", companycode);
		}
		if (empcode !=null && !"*".equals(empcode)) {
			params.put("a.empcode", empcode);
		}
		
		List<T_activity_apply_ext> list = t_activity_applyService.findByConditions(params);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
