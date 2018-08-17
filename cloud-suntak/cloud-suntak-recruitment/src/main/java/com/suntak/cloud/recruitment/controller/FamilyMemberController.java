package com.suntak.cloud.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.service.FamilyMemberService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写家庭成员信息
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class FamilyMemberController {
	
	@Autowired
	FamilyMemberService familyMemberService;

	@PostMapping(value="/familyMember/{applicantid}")
	public Response saveOrUpdate(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_familymember t_hr_familymember) throws Exception {
		t_hr_familymember.setApplicantid(applicantid);
		familyMemberService.saveOrUpdate(t_hr_familymember);
		return new Response();
	}

	@GetMapping(value="/familyMember/{applicantid}")
	public Response find(@PathVariable("applicantid") String applicantid) throws Exception {
		List<T_hr_familymember> t_hr_familymember = familyMemberService.findByApplicantid(applicantid);
		Response response = new Response();
		response.setData(t_hr_familymember);
		return response;
	}

	@DeleteMapping(value="/familyMember/{familymemberid}")
	public Response delete(@PathVariable("familymemberid") Integer familymemberid) throws Exception {
		familyMemberService.delete(familymemberid);
		return new Response();
	}
}
