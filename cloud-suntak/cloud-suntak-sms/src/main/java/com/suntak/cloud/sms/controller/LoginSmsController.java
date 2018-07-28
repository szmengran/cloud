package com.suntak.cloud.sms.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.sms.client.EhrUserServiceClient;
import com.suntak.cloud.sms.client.SmsCodeServiceClient;
import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.cloud.sms.util.SmsTool;
import com.suntak.exception.model.Response;
import com.szmengran.common.entity.T_common_sms_code;
import com.szmengran.common.entity.T_common_sms_log;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "ehr")
@RestController
@RequestMapping(path = "/api/v1/suntaksms", produces = { "application/json" })
public class LoginSmsController {

	@Autowired
	private EhrUserServiceClient ehrUserServiceClient;
	
	@Autowired
	private SmsServiceClient smsServiceClient;
	
	@Autowired
	private SmsCodeServiceClient smsCodeServiceClient;
	
	@ApiOperation(value = "发送短信登录验证码", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("code/{phone}")
	public Response sendLoginSmsCode(@PathVariable("phone") String phone) throws Exception {
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode("SMS_132391535");
			t_common_sms_log.setSignname("崇达技术");
			t_common_sms_log.setPhone(phone);
			String code = generateCode(4);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			t_common_sms_log.setTemplateparam(SmsTool.transferMapToJson(map));
			smsServiceClient.send(t_common_sms_log);
			
			T_common_sms_code t_common_sms_code = new T_common_sms_code();
			t_common_sms_code.setCode(code);
			t_common_sms_code.setPhone(phone);
			smsCodeServiceClient.saveOrUpdate(t_common_sms_code);
		} catch (Exception e) {
			throw e;
		}
		return new Response();
	}

	@ApiOperation(value = "发送短信登录验证码-内部员工", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@PostMapping("code/{phone}")
	public Response sendVerificationCode(@PathVariable("phone") String phone) throws Exception {
		Response response = null;
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		t_common_sms_log.setTemplatecode("SMS_132391535");
		t_common_sms_log.setSignname("崇达技术");
		t_common_sms_log.setPhone(phone);
		response = ehrUserServiceClient.findEhrUserByPhone(phone);
		String code = generateCode(4);
		if (response.getData() != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			t_common_sms_log.setTemplateparam(SmsTool.transferMapToJson(map));
			smsServiceClient.send(t_common_sms_log);

			T_common_sms_code t_common_sms_code = new T_common_sms_code();
			t_common_sms_code.setCode(code);
			t_common_sms_code.setPhone(phone);
			t_common_sms_code.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
			smsCodeServiceClient.saveOrUpdate(t_common_sms_code);
		}
		return response;
	}
	
	/**
	 * 生成指定位数据的验证码
	 * @param num
	 * @return 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private String generateCode(Integer num) {
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(num);
		for (int i = 0; i < num; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		return sb.toString();
	}
}
