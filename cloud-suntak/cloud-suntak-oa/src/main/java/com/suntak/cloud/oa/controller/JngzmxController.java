package com.suntak.cloud.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.oa.service.OaService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;
import com.szmengran.utils.JwtUtil;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.oa.controller
 * @Description: 技能工资明细
 * @date Jan 28, 2019 11:41:19 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "account")
@RestController
@RequestMapping(path="/api/v1/oa", produces = { "application/json" })
public class JngzmxController {
	
	@Autowired
	@Qualifier("jngzmxService")
	private OaService oaService;
	
	@PatchMapping("sign/jngzmx/{id}/{token}")
	public Response signById(@PathVariable("id") String id, @PathVariable("token") String token) throws Exception {
		String userJson = JwtUtil.parseToken(token);
		if (userJson == null) {
			throw new BusinessException(10007001);
		}
		EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
		oaService.signById(id, ehrUser.getEmpcode(), ehrUser.getEmpname());
		return new Response();
	}
	
	@GetMapping("jngzmx/{id}")
	public Response findById(@PathVariable("id") String id) throws Exception {
		Response response = new Response();
		response.setData(oaService.findById(id));
		return response;
	}
	
}
