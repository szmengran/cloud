package com.suntak.cloud.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.suntak.cloud.sms.client.EhrUserServiceClient;
import com.suntak.cloud.sms.client.SmsServiceClient;
import com.suntak.cloud.sms.entity.T_sms_template;
import com.suntak.cloud.sms.service.TemplateService;
import com.suntak.cloud.sms.util.SmsTool;
import com.suntak.common.entity.T_common_sms_log;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;

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
public class BlessingSmsController {
	
    private final static ExecutorService executor = new ThreadPoolExecutor(20, 200, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	private final static Logger logger = LoggerFactory.getLogger(BlessingSmsController.class);
	private final static Integer MSG_TYPE_ONBOARD=1;
	private final static Integer MSG_TYPE_BIRTHDAY=2;

	@Autowired
	private EhrUserServiceClient ehrUserServiceClient;
	
	@Autowired
	private SmsServiceClient smsServiceClient;
	
	@Autowired
	private TemplateService templateService;
	
	/**
	 * 定时发送生日祝福信息
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation(value = "定时发送生日祝福信息", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("birthdayblessing/{monthdate}")
	public Response autoSendBirthdayBlessing(@PathVariable("monthdate") String monthdate) throws Exception {
		Response response = null;
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode("SMS_130912375");
			t_common_sms_log.setSignname("崇达技术");
			response = ehrUserServiceClient.getBirthdayEhrUser(monthdate);
			this.send(response, t_common_sms_log, MSG_TYPE_BIRTHDAY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Deprecated
	@ApiOperation(value = "定时发送入职满整年通知信息", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("onboardblessing/{monthdate}")
	public Response autoSendOnboardBlessing(@PathVariable("monthdate") String monthdate) throws Exception {
		Response response = null;
		T_common_sms_log t_common_sms_log = new T_common_sms_log();
		try {
			t_common_sms_log.setTemplatecode("SMS_130929238");
			t_common_sms_log.setSignname("崇达技术");
			response = ehrUserServiceClient.getOnboardEhrUser(monthdate);
			this.send(response, t_common_sms_log, MSG_TYPE_ONBOARD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	@ApiOperation(value = "定时发送入职满整年通知信息", response = Response.class)
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input", response = Response.class) })
	@GetMapping("autoSendOnboard/{monthdate}")
	public Response autoSendOnboard(@PathVariable("monthdate") String monthdate) throws Exception {
	    Response response = null;
	    try {
	        Future<List<T_sms_template>> future = executor.submit(() -> {
	           return templateService.findTemplates(); 
	        });
	        response = ehrUserServiceClient.getOnboardEhrUser(monthdate);
	        List<T_sms_template> list = future.get();
	        if (response.getData() != null) {
	            List<EhrUser> ehrUsers = transferResponseToList(response);
	            for (EhrUser user: ehrUsers) {
	                T_common_sms_log t_common_sms_log = new T_common_sms_log();
	                String templateCode = getCode(user.getYear() >= 20 ? "20" : user.getYear()+"", list);
	                t_common_sms_log.setTemplatecode(templateCode);
	                t_common_sms_log.setPhone(user.getPhone());
	                t_common_sms_log.setSignname("崇达技术");
	                executor.submit(() -> {
	                  try {
	                      smsServiceClient.send(t_common_sms_log);
	                  } catch (Exception e) {
	                      logger.error("发送短信失败：", new Gson().toJson(t_common_sms_log), e);
	                  }
	                });
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return response;
	}
	
	private String getCode(String year, List<T_sms_template> list) throws Exception {
	    for (T_sms_template template: list) {
	        if (year.equals(template.getId())) {
	            return template.getCode();
	        }
	    }
	    throw new IllegalArgumentException("找不到对应的短信模版");
	}
	
	/**
	 * 将返回的数据转换为EhrUser列表
	 * @param response
	 * @return 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private List<EhrUser> transferResponseToList(Response response) {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
		return objectMapper.convertValue(response.getData(), new TypeReference<List<EhrUser>>() { });
	}
	
	private void send(Response response, final T_common_sms_log t_common_sms_log_tmp, Integer type) {
		if (response.getData() != null) {
			List<EhrUser> list = transferResponseToList(response);
			for (int i=0; i<list.size(); i++) {
				final EhrUser ehrUser = list.get(i);
				//没有手机号或手机号不是11位则跳过
				if(StringUtils.isBlank(ehrUser.getPhone()) || ehrUser.getPhone().length() != 11) {
					continue;
				}
				executor.submit(new Runnable() {
					@Override
	                public void run() {
						T_common_sms_log t_common_sms_log = new T_common_sms_log();
						BeanUtils.copyProperties(t_common_sms_log_tmp, t_common_sms_log);
						t_common_sms_log.setPhone(ehrUser.getPhone());
						try {
							if (MSG_TYPE_ONBOARD == type) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("name", ehrUser.getEmpname());
								map.put("year", ehrUser.getYear());
								map.put("year1", ehrUser.getYear());
								map.put("year2", ehrUser.getYear());
								t_common_sms_log.setTemplateparam(SmsTool.transferMapToJson(map));
							} else if (MSG_TYPE_BIRTHDAY == type) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("name", ehrUser.getEmpname());
								t_common_sms_log.setTemplateparam(SmsTool.transferMapToJson(map));
							}
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
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "李茂源");
		map.put("year", "2019");
		String json = SmsTool.transferMapToJson(map);
		System.out.println(json);
	}
}
