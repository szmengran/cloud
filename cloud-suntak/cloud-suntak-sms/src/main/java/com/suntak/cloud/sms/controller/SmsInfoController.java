package com.suntak.cloud.sms.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.cloud.sms.entity.T_sms_info;
import com.suntak.cloud.sms.service.SmsInfoService;
import com.suntak.common.entity.T_common_sms_log;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/** 
 * @Package com.suntak.cloud.sms.controller 
 * @Description: 公共短信API
 * @date Mar 5, 2019 3:59:20 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "公共节日祝福API")
@RestController
@RequestMapping(path = "/api/v1/suntaksms", produces = { "application/json" })
public class SmsInfoController {
	
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(100);
	private static final Logger LOG = LoggerFactory.getLogger(SmsInfoController.class);
	
	@Autowired
	private SmsInfoService smsInfoService;
	
	@Autowired
	private SmsServiceClient smsServiceClient;
	
	@ApiOperation(value = "定时发送信息", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("smsInfo")
	public Response smsInfo() throws Exception {
		List<T_sms_info> list = smsInfoService.findAutoSendSms();
		for(T_sms_info t_sms_info: list) {
			EXECUTOR.submit(() -> {
				T_common_sms_log t_common_sms_log = new T_common_sms_log();
				try {
					Integer id = t_sms_info.getId();
					t_common_sms_log.setTemplatecode(t_sms_info.getTemplatecode());
					t_common_sms_log.setSignname(t_sms_info.getSignname());
					t_common_sms_log.setPhone(t_sms_info.getPhone());
					t_common_sms_log.setId(id);
					t_common_sms_log.setOutid(t_sms_info.getOutid());
					t_common_sms_log.setTemplateparam(t_sms_info.getTemplateparam());
					Response resp = smsServiceClient.send(t_common_sms_log);
					if (200 == resp.getStatus()) {
					    try {
					        smsInfoService.move(t_sms_info);
					    } catch (Exception e) {
					        smsInfoService.updateException(id, "发送成功，信息转移失败："+e.getMessage());
					    }
					} else {
					    smsInfoService.updateException(id, new Gson().toJson(resp.getData()));
					}
				} catch (Exception e) {
					LOG.error(e.getMessage());
					e.printStackTrace();
				}
			});
		}
		return new Response();
	}
}
