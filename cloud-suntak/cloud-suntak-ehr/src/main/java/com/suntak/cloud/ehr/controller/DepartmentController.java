package com.suntak.cloud.ehr.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.client.DepartmentClient;
import com.suntak.cloud.ehr.client.MicroservicesClient;
import com.suntak.cloud.ehr.entity.DepartmentResponse;
import com.suntak.cloud.ehr.entity.T_wechat_department;
import com.suntak.cloud.ehr.service.DepartmentService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.ehr.controller
 * @Description: TODO
 * @date Dec 17, 2018 4:10:45 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/ehr")
public class DepartmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	DepartmentClient departmentClient;
	
	@Autowired
	MicroservicesClient microservicesClient;
	
	@PutMapping("/department")
	public Response updateAll() throws Exception {
		Response response = microservicesClient.getQYToken();
		if (response.getStatus() == 200) {
			String access_token = (String)response.getData();
			DepartmentResponse departmentResponse = departmentClient.getDepartment(access_token);
			if (departmentResponse.getErrcode() == 0) {
				T_wechat_department[] departments = departmentResponse.getDepartment();
				ExecutorService executor = Executors.newCachedThreadPool();
				for (T_wechat_department department: departments) {
					executor.submit(() -> {
						try {
							departmentService.update(department);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error(e.getMessage());
						}
					});
				}
			}
			return new Response();
		} else {
			throw new Exception("获取企业微信token失败！");
		}
	}
}
