package com.suntak.cloud.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.entity.T_microservices_setting;
import com.suntak.cloud.ehr.service.MicroservicesSettingService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.ehr.controller
 * @Description: TODO
 * @date Jan 24, 2019 11:43:35 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(path = "/api/v1/ehr", produces = { "application/json" })
public class MicroservicesSettingController {
	
	@Autowired
	private MicroservicesSettingService MicroservicesSettingService;
	
	@ApiOperation(value = "获取崇达小助手设置信息", response = Response.class)
	@GetMapping("setting/{empno}")
	public Response findSettingByEmpno(@PathVariable("empno") String empno) throws Exception {
		List<T_microservices_setting> list = MicroservicesSettingService.findSettingByEmpno(empno);
		Response response = new Response();
		response.setData(list);
		return response;
	}
	
	@ApiOperation(value = "获取崇达小助手新增设置信息", response = Response.class)
	@PostMapping("setting")
	public Response insert(@RequestBody T_microservices_setting t_microservices_setting) throws Exception {
		Boolean flag = MicroservicesSettingService.insert(t_microservices_setting);
		Response response = new Response();
		response.setData(flag);
		return response;
	}
	
	@ApiOperation(value = "获取崇达小助手删除设置信息", response = Response.class)
	@DeleteMapping("setting/{empno}/{type}")
	public Response findSettingByEmpno(@PathVariable("empno") String empno, @PathVariable("type") String type) throws Exception {
		T_microservices_setting t_microservices_setting = new T_microservices_setting();
		t_microservices_setting.setEmpno(empno);
		t_microservices_setting.setType(type);
		Boolean flag = MicroservicesSettingService.delete(t_microservices_setting);
		Response response = new Response();
		response.setData(flag);
		return response;
	}
}
