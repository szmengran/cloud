package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.suntak.cloud.recruitment.service.ContactService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写紧急联系人信息
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@PostMapping(value="/contact/{applicantid}")
	public Response saveOrUpdate(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_contact t_hr_contact) throws Exception {
		t_hr_contact.setApplicantid(applicantid);
		contactService.saveOrUpdate(t_hr_contact);
		return new Response();
	}

	@GetMapping(value="/contact/{applicantid}")
	public Response find(@PathVariable("applicantid") String applicantid) throws Exception {
		T_hr_contact t_hr_contact = contactService.findByApplicantid(applicantid);
		Response response = new Response();
		response.setData(t_hr_contact);
		return response;
	}
}
