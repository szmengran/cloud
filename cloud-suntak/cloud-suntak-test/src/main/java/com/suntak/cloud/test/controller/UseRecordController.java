package com.suntak.cloud.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.test.entity.T_oa_test_use_record;
import com.suntak.cloud.test.service.UseRecordService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.test.controller
 * @Description: 使用记录
 * @date 2018年10月8日 下午2:06:39
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "t_oa_test_stand")
@RestController
@RequestMapping(path = "/api/test", produces = { "application/json" })
public class UseRecordController {
	
	@Autowired
	UseRecordService useRecordService;
	
	@GetMapping("/v1/useRecord/{companycode}/{test_stand_code}")
	public Response findLastRecordByTestStandCode(@PathVariable("companycode") String companycode, @PathVariable("test_stand_code") String test_stand_code) throws Exception{
		T_oa_test_use_record userRecord = useRecordService.findLastRecordByTestStandCode(companycode, test_stand_code);
		Response response = new Response();
		response.setData(userRecord);
		return response;
	}
}
