package com.suntak.cloud.microservices.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.suntak.cloud.microservices.payment.entity.Cux_xf_info;
import com.suntak.cloud.microservices.payment.service.XfInfoService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;
import com.szmengran.utils.JwtUtil;

/**
 * @Package com.suntak.cloud.microservices.payment.controller
 * @Description: 消费查询服务
 * @date Dec 25, 2018 9:48:21 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(path = "/api/v1/microservices", produces = { "application/json" })
public class XfInfoController {
	
	@Autowired
	private XfInfoService xfInfoService;
	
	@GetMapping("/stxf/{token}/{pageNum}/{pageSize}")
	public Response findSTXFByConditions(@PathVariable("token") String token, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) throws Exception {
		String userJson = JwtUtil.parseToken(token);
		if (userJson == null) {
			throw new BusinessException(10007001);
		}
		EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
		String empno = ehrUser.getEmpcode();
		String companycode = ehrUser.getCompanycode();
		if (pageNum == null) {
			pageNum = 1;
			pageSize = 10;
		} 
		PageHelper.startPage(pageNum, pageSize);
		List<Cux_xf_info> list = xfInfoService.findSTXFByConditions(empno, companycode);
		Response response = new Response();
		response.setData(list);
		return response;
	}
	
	@GetMapping("/csxf/{token}/{pageNum}/{pageSize}")
	public Response findCSXFByConditions(@PathVariable("token") String token, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) throws Exception {
		String userJson = JwtUtil.parseToken(token);
		if (userJson == null) {
			throw new BusinessException(10007001);
		}
		EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
		String empno = ehrUser.getEmpcode();
		String companycode = ehrUser.getCompanycode();
		if (pageNum == null) {
			pageNum = 1;
			pageSize = 10;
		} 
		PageHelper.startPage(pageNum, pageSize);
		List<Cux_xf_info> list = xfInfoService.findCSXFByConditions(empno, companycode);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
