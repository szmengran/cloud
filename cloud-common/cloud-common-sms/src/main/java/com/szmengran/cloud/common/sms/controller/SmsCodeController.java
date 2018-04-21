package com.szmengran.cloud.common.sms.controller;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;
import com.szmengran.cloud.common.sms.service.SmsCodeService;
import com.szmengran.common.entity.T_common_sms_code;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("验证码")
@RestController
@RequestMapping(path = "/api/v1/smses", produces = { "application/json" })
public class SmsCodeController {
	
	private final static Logger logger = LoggerFactory.getLogger(SmsCodeController.class);
	
	@Autowired
	private SmsCodeService smsCodeService;
	
	@ApiOperation(value = "验证码保存或更新", response = Response.class)
	@PostMapping("/code")
	public Response save(@RequestBody T_common_sms_code t_common_sms_code) throws Exception {
		logger.info("save sms code request:{}", new Gson().toJson(t_common_sms_code));
		if (StringUtils.isBlank(t_common_sms_code.getPhone())) {
			throw new BusinessException(5001);
		}
		T_common_sms_code t_common_sms_code_ = smsCodeService.findByPrimaryKey(t_common_sms_code);
		Response response = new Response();
		if (checkTime(t_common_sms_code.getUpdatestamp(), 1*60)){
			throw new BusinessException(5103);
		}
		if (t_common_sms_code_ != null) {
			smsCodeService.update(t_common_sms_code);
		} else {
			smsCodeService.save(t_common_sms_code);
		}
		return response;
	}
	
	@ApiOperation(value = "验证码检查是否正确", response = Response.class)
	@GetMapping("/{code}/{phone}")
	public Response check(@PathVariable("code") String code, @PathVariable("phone") String phone) throws Exception {
		if (StringUtils.isBlank(phone)) {
			throw new BusinessException(5001);
		}
		T_common_sms_code t_common_sms_code = new T_common_sms_code();
		t_common_sms_code.setPhone(phone);
		t_common_sms_code = smsCodeService.findByPrimaryKey(t_common_sms_code);
		if (t_common_sms_code == null || !code.equals(t_common_sms_code.getCode())) {
			throw new BusinessException(5101);
		} else if (!checkTime(t_common_sms_code.getUpdatestamp(), 15*60)){
			throw new BusinessException(5102);
		}
		
		Response response = new Response();
		return response;
	}
	
	/**
	 * 根据时间检查验证码是否过期
	 * @param timestamp
	 * @param seconds
	 * @return      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private Boolean checkTime(Timestamp timestamp, Integer seconds) {
		return (System.currentTimeMillis() - timestamp.getTime())/1000 <= seconds;
	}
}
