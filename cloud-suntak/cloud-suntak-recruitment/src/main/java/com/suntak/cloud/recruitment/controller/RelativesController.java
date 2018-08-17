package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_relatives;
import com.suntak.cloud.recruitment.service.RelativesService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写在公司上班的亲属信息
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class RelativesController {
	
	@Autowired
	RelativesService relativesService;
	
	@PostMapping(value="/relatives/{applicantid}")
	public Response insert(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_relatives t_hr_relatives) throws Exception {
		t_hr_relatives.setApplicantid(applicantid);
		relativesService.saveOrUpdate(t_hr_relatives);
		return new Response();
	}

	@GetMapping(value="/relatives/{applicantid}")
	public Response find(@PathVariable("applicantid") String applicantid) throws Exception {
		T_hr_relatives t_hr_relatives = relativesService.findByApplicantid(applicantid);
		Response response = new Response();
		response.setData(t_hr_relatives);
		return response;
	}
}
