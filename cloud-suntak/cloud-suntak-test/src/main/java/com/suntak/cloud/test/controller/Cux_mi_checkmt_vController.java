package com.suntak.cloud.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.Cux_mi_checkmt_v;
import com.suntak.cloud.test.service.Cux_mi_checkmt_vService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.test.controller
 * @Description: 冶具信息查找服务
 * @date 2018年10月16日 下午12:57:23
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "cux_mi_checkmt_v")
@RestController
@RequestMapping(value="/api/v1/test", produces = { "application/json" })
public class Cux_mi_checkmt_vController {

	@Autowired
	Cux_mi_checkmt_vService cux_mi_checkmt_vService;
	@GetMapping(value="/cux_mi_checkmt_v/{compancode}/{item}")
	
	public Response findByConditions(@PathVariable("compancode") String compancode, @PathVariable("item") String item) throws Exception {
		Cux_mi_checkmt_v cux_mi_checkmt_v = cux_mi_checkmt_vService.findByConditions(compancode, item);
		Response response = new Response();
		response.setData(cux_mi_checkmt_v);
		return response;
	}
}
