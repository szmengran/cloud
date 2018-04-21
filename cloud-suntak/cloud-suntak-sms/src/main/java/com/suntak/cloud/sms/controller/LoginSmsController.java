package com.suntak.cloud.sms.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.cloud.sms.client.EhrUserServiceClient;
import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
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
	
	private final static Logger logger = LoggerFactory.getLogger(LoginSmsController.class);
	private final static Integer MSG_TYPE_CODE=1; //短信验证码

	@Autowired
	private EhrUserServiceClient ehrUserServiceClient;
	
	@Autowired
	private SmsServiceClient smsServiceClient;
	
	/**
	 * 定时发送生日祝福信息
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation(value = "发送短信登录验证码", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("code/{phone}")
	public Response sendLoginSmsCode(@PathVariable("phone") String phone) throws Exception {
		Response response = null;
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode("SMS_132391535");
			t_common_sms_log.setSignname("崇达技术");
			response = ehrUserServiceClient.findEhrUserByPhone(phone);
			this.send(response, t_common_sms_log, MSG_TYPE_CODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private void send(Response response, T_common_sms_log t_common_sms_log, Integer type) {
		if (response.getData() != null) {
			MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
			List<EhrUser> list = objectMapper.convertValue(response.getData(), new TypeReference<List<EhrUser>>() { });
			ExecutorService executor = Executors.newFixedThreadPool(20);
			for (int i=0; i<list.size(); i++) {
				final EhrUser ehrUser = list.get(i);
				//没有手机号或手机号不是11位则跳过
				if(StringUtils.isBlank(ehrUser.getPhone()) || ehrUser.getPhone().length() != 11) {
					continue;
				}
				final String code = generateCode(4);
				executor.submit(new Runnable() {
					@Override
	                public void run() {
						t_common_sms_log.setPhone(ehrUser.getPhone());
						if (MSG_TYPE_CODE == type) {
							t_common_sms_log.setTemplateparam(
									new StringBuffer()
									.append("{")
									.append("\"code\":\"").append(code).append("\"")
									.append("}")
									.toString()
									);
						}
						try {
							smsServiceClient.send(t_common_sms_log);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error(e.getMessage());
						}
	                }
				});
			}
			executor.shutdown();
		}
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
}
