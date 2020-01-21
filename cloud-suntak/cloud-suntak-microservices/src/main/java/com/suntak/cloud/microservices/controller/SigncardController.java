package com.suntak.cloud.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.microservices.entity.Signcard;
import com.suntak.cloud.microservices.service.SigncardService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

@RestController
public class SigncardController {

	@Autowired
	private SigncardService signcardService;
	
	@PostMapping("/signcard/{token}")
	public Response insert(@PathVariable("token") String token, @RequestBody Signcard signcard) throws Exception {
		String userJson = JwtUtil.parseToken(token);
		if (userJson == null) {
			throw new BusinessException(10007001);
		}
		EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
		signcardService.insert(signcard);
		signcard.setEmpcode(ehrUser.getEmpcode());
		return new Response();
	}
	
}
