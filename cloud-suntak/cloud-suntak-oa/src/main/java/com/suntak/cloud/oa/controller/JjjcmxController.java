package com.suntak.cloud.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_jjjcmx_v;
import com.suntak.cloud.oa.service.JjjcmxService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;
import com.suntak.admin.user.exception.BusinessException;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.oa.controller
 * @Description: TODO
 * @date Jan 28, 2019 11:41:19 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "oa")
@RestController
@RequestMapping(path="/api/v1/oa", produces = { "application/json" })
public class JjjcmxController {
	
	@Autowired
	private JjjcmxService jjjcmxService;
	
	@PatchMapping("sign/jjjcmx/{id}/{token}")
	public Response signById(@PathVariable("id") String id, @PathVariable("token") String token) throws Exception {
		String userJson = JwtUtil.parseToken(token);
		if (userJson == null) {
			throw new BusinessException(10007001);
		}
		EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
		jjjcmxService.signById(id, ehrUser.getEmpcode(), ehrUser.getEmpname());
		return new Response();
	}
	
	@GetMapping("jjjcmx/{id}")
	public Response findById(@PathVariable("id") String id) throws Exception {
		Cux_oa_qywx_jjjcmx_v cux_oa_qywx_jjjcmx_v = jjjcmxService.findById(id);
		Response response = new Response();
		response.setData(cux_oa_qywx_jjjcmx_v);
		return response;
	}
	
}
