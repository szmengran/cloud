package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_interview;
import com.suntak.cloud.recruitment.service.InterviewService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写应聘者的面试结果
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class InterviewController {
	
	@Autowired
	InterviewService interviewService;
	
	@PostMapping(value="/interview")
	public Response insert(@RequestBody T_hr_interview t_hr_interview) throws Exception {
		interviewService.insert(t_hr_interview);
		return new Response();
	}
	
}
