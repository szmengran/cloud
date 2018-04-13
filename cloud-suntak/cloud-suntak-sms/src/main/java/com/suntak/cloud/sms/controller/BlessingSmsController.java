package com.suntak.cloud.sms.controller;

import java.util.List;
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

/**
 * @Package com.szmengran.cloud.common.sms.controller
 * @Description: 短信服务
 * @date 2018年4月6日 下午3:10:00
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(path = "/api/v1", produces = { "application/json" })
public class BlessingSmsController {
	
	private final static Logger logger = LoggerFactory.getLogger(BlessingSmsController.class);
	
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
	@GetMapping("birthdayblessing/{monthdate}")
	public Response autoSendBirthdayBlessing(@PathVariable("monthdate") String monthdate) throws Exception {
		Response response = null;
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode("SMS_130912375");
			t_common_sms_log.setSignname("崇达技术");
			response = ehrUserServiceClient.getBirthdayEhrUser(monthdate);
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
					executor.submit(new Runnable() {
						@Override
		                public void run() {
							t_common_sms_log.setPhone(ehrUser.getPhone());
							t_common_sms_log.setTemplateparam(new StringBuffer().append("{\"name\":\"")
									.append(ehrUser.getEmpname()).append("\"}").toString());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
