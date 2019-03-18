package com.suntak.cloud.common.sms.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.common.sms.service.SmsCaptchaService;
import com.suntak.cloud.common.sms.service.SmsService;
import com.suntak.common.entity.T_common_sms_captcha;
import com.suntak.common.entity.T_common_sms_log;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.json.JSONObject;

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("验证码")
@RestController
@RequestMapping(path = "/api/v1/smses", produces = { "application/json" })
public class SmsCaptchaController {
	
	private final static Logger logger = LoggerFactory.getLogger(SmsCaptchaController.class);
	
	@Value("${aliyun.sms.templatecode}")
	private String TEMPLATECODE;
	
	@Value("${aliyun.sms.signname}")
	private String SIGNNAME;

	@Autowired
	private SmsCaptchaService smsCodeService;
	
	@Autowired
	private SmsService smsService;
	
	@ApiOperation(value = "发送短信登录验证码", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("captcha/{phone}")
	public Response sendLoginSmsCode(@PathVariable("phone") String phone) throws Exception {
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode(TEMPLATECODE);
			t_common_sms_log.setSignname(SIGNNAME);
			t_common_sms_log.setPhone(phone);
			String captcha = generateCode(4);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("captcha", captcha);
			JSONObject jsonObject = JSONObject.fromObject(map);
			t_common_sms_log.setTemplateparam(jsonObject.toString());
			smsService.send(t_common_sms_log);
			T_common_sms_captcha t_common_sms_code = new T_common_sms_captcha();
			t_common_sms_code.setCaptcha(captcha);
			t_common_sms_code.setPhone(phone);
			saveOrUpdate(t_common_sms_code);
		} catch (Exception e) {
			throw e;
		}
		return new Response();
	}
	
	private String generateCode(Integer num) {
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(num);
		for (int i = 0; i < num; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb.toString();
	}
	
	@ApiOperation(value = "验证码保存或更新", response = Response.class)
	@PostMapping("/captcha")
	public Response saveOrUpdate(@RequestBody T_common_sms_captcha t_common_sms_code) throws Exception {
		logger.info("save sms code request:{}", new Gson().toJson(t_common_sms_code));
		if (StringUtils.isBlank(t_common_sms_code.getPhone())) {
			throw new BusinessException(5001);
		}
		int num = smsCodeService.update(t_common_sms_code);
		if (num == 0) {
			smsCodeService.save(t_common_sms_code);
		}
		return new Response();
	}
	
	@ApiOperation(value = "验证码检查是否正确", response = Response.class)
	@GetMapping("/{captcha}/{phone}")
	public Response check(@PathVariable("captcha") String captcha, @PathVariable("phone") String phone) throws Exception {
		if (StringUtils.isBlank(phone)) {
			throw new BusinessException(5001);
		}
		T_common_sms_captcha t_common_sms_code = new T_common_sms_captcha();
		t_common_sms_code.setPhone(phone);
		t_common_sms_code = smsCodeService.findByPrimaryKey(t_common_sms_code);
		if (t_common_sms_code == null || !captcha.equals(t_common_sms_code.getCaptcha())) {
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
