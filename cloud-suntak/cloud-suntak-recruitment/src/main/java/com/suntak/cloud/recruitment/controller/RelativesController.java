package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping(value="/relatives")
	public Response insert(@RequestBody T_hr_relatives t_hr_relatives) throws Exception {
		relativesService.insert(t_hr_relatives);
		return new Response();
	}
	
}
