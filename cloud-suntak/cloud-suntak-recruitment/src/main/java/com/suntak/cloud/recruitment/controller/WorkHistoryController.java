package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.WorkHistoryService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写工作经历
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class WorkHistoryController {
	
	@Autowired
	WorkHistoryService workHistoryService;
	
	@PostMapping(value="/workhistory")
	public Response insert(@RequestBody T_hr_workhistory t_hr_workhistory) throws Exception {
		workHistoryService.insert(t_hr_workhistory);
		return new Response();
	}
	
}
