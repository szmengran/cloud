package com.szmengran.cloud.business.sms.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.szmengran.cloud.business.sms.client.EhrUserServiceClient;
import com.szmengran.cloud.business.sms.client.SmsServiceClient;
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
				@SuppressWarnings("unchecked")
				List<Map<String, String>> list = (List<Map<String, String>>)response.getData();
				for (int i=0; i<list.size(); i++) {
					Map<String, String> map = list.get(i);
					final EhrUser ehrUser = new EhrUser(map.get("empcode"), map.get("empname"), map.get("phone"));
					//没有手机号或手机号不是11位则跳过
					if(StringUtils.isBlank(ehrUser.getPhone()) || ehrUser.getPhone().length() != 11) {
						continue;
					}
					new Thread(new Runnable() {
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
					}).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
