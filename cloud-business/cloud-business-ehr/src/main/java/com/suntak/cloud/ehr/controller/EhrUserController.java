package com.suntak.cloud.ehr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.service.EhrUserService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.ehr.controller
 * @Description: 人事系统用户信息
 * @date 2018年4月11日 下午2:43:35
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(path = "/api/v1", produces = { "application/json" })
public class EhrUserController {
	
	@Autowired
	EhrUserService ehrUserService;
	
	@GetMapping("birthdayusers/{monthdate}")
	public Response findBirthdayFromEhrUser(@PathVariable String monthdate) throws Exception {
		Response response = new Response();
		if(StringUtils.isBlank(monthdate.replace("*", ""))) {
			monthdate = new SimpleDateFormat("MM-dd").format(new Date());
		}
		StringBuffer conditions = new StringBuffer();
		conditions.append("empstatusname='在职' and to_char(c_birth_date, 'mm-dd')='")
		.append(monthdate).append("'");
		List<EhrUser> list = ehrUserService.findByCondition(conditions.toString());
		response.setData(list);
		return response;
	}
}
