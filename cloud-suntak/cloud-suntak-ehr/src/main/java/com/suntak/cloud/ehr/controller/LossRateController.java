package com.suntak.cloud.ehr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.entity.LossRate;
import com.suntak.cloud.ehr.service.LossRateService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.ehr.controller
 * @Description: 员工流失率查询
 * @date 2018年7月16日 上午11:00:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "ehr")
@RestController
public class LossRateController {

	@Autowired
	@Qualifier("lossRateService")
	LossRateService lossRateService;
	
	@ApiOperation(value = "获取生日的员工信息", response = Response.class)
	@GetMapping("lossrate/{querydate}/{companycode}")
	public Response findBirthdayFromEhrUser(@PathVariable("querydate") String querydate, @PathVariable("companycode") String companycode) throws Exception {
		List<LossRate> list = lossRateService.findByConditions(querydate, companycode);
		Response response = new Response();
		response.setData(list);
		return response;
	}
}
