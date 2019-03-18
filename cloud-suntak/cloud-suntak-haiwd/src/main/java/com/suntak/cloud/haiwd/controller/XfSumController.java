package com.suntak.cloud.haiwd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.haiwd.service.XfSumService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.punch.entity.XfSum;
import com.suntak.utils.JwtUtil;
import com.suntak.admin.user.exception.BusinessException;

/**
 * @Package com.suntak.cloud.microservices.payment.controller
 * @Description: TODO
 * @date Dec 28, 2018 8:31:18 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(path = "/api/v1/haiwd", produces = { "application/json" })
public class XfSumController {
	
	@Autowired
	private XfSumService xfSumService;
	
	@GetMapping("/xfsum/{token}/{month}")
	public Response findXfsumByEmpno(@PathVariable("token") String token, @PathVariable("month") String month) throws Exception {
		String userJson = JwtUtil.parseToken(token);
		if (userJson == null) {
			throw new BusinessException(10007001);
		}
		EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
		String empno = ehrUser.getEmpcode();
		String companycode = ehrUser.getCompanycode();
		XfSum xfSum = xfSumService.findXfsumByEmpno(empno, companycode, month);
		Response response = new Response();
		response.setData(xfSum);
		return response;
	}
}
