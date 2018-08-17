package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写员工基本信息
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class ApplicantController {
	
	@Autowired
	ApplicantService applicantService;
	
	@PostMapping(value="/applicant/{applicantid}")
	public Response fillInBaseInfo(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_applicant t_hr_applicant) throws Exception {
		t_hr_applicant.setApplicantid(applicantid);
		int num = applicantService.update(t_hr_applicant);
		if(num == 0) {
			applicantService.insert(t_hr_applicant);
		}
		return new Response();
	}
	
	@GetMapping(value="/applicant/{applicantid}")
	public Response findById(@PathVariable("applicantid") String applicantid) throws Exception {
		T_hr_applicant t_hr_applicant = applicantService.findById(applicantid);
		Response response = new Response();
		response.setData(t_hr_applicant);
		return response;
	}
	
}
