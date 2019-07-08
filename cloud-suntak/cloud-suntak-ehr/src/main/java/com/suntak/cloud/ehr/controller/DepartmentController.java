package com.suntak.cloud.ehr.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.client.EnterPriseWechatClient;
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
	private static ExecutorService executor = new ThreadPoolExecutor(20, 2000, 3L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	EnterPriseWechatClient enterPriseWechatClient;
	
	@Autowired
	MicroservicesClient microservicesClient;
	
	@PutMapping("/department")
	public Response updateAll() throws Exception {
	    executor.submit(() -> {
	        try {
                departmentService.deleteAll();
            } catch (Exception e) {
                logger.error("删除企业微信所有部门失败：{}", e);
            }
	    });
		Response response = microservicesClient.getQYToken();
		if (response.getStatus() == 200) {
			String access_token = (String)response.getData();
			DepartmentResponse departmentResponse = enterPriseWechatClient.getDepartment(access_token);
			if (departmentResponse.getErrcode() == 0) {
				T_wechat_department[] departments = departmentResponse.getDepartment();
				for (T_wechat_department department: departments) {
					executor.submit(() -> {
						try {
							departmentService.update(department);
						} catch (Exception e) {
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
