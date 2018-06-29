package com.suntak.cloud.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.ext.Cux_soa_ft_data_v_ext;
import com.suntak.cloud.test.service.CuxSoaFtDataVService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.test.controller
 * @Description: 资料列表查询
 * @date 2018年6月25日 下午3:02:27
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("cux_soa_ft_data_v")
@RestController
@RequestMapping(value="/api/v1/test")
public class CuxSoaFtDataVController {

	@Autowired
	CuxSoaFtDataVService cuxSoaFtDataVService;
	
	@GetMapping(value="/cux_soa_ft_data_vs/{empcode}/{status}")
	public Response findByConditions(@PathVariable("empcode") String empcode, @PathVariable("status") String status) throws Exception {
		List<Cux_soa_ft_data_v_ext> cux_soa_ft_data_v_exts = cuxSoaFtDataVService.findByConditions(empcode, status);
		Response response = new Response();
		response.setData(cux_soa_ft_data_v_exts);
		return response;
	}
}
