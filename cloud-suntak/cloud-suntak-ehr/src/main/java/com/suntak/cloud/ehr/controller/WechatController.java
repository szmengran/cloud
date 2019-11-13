package com.suntak.cloud.ehr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.ehr.client.WechatClient;
import com.suntak.cloud.ehr.entity.Contact;
import com.suntak.cloud.ehr.service.ContactService;
import com.suntak.cloud.ehr.service.EhrUserService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.microservices.controller
 * @Description: 企业微信服务
 * @date Nov 29, 2018 8:40:33 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "企业微信用户服务")
@RestController
public class WechatController {

	private static final ExecutorService executor = new ThreadPoolExecutor(20, 500, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	private WechatClient wechatClient;

	@Autowired
	ContactService contactService;
	
	@Autowired
	private EhrUserService ehrUserService;
	
	/**
	 * 
	 * @description 获取企业微信上的用户信息
	 * @param code
	 * @param secret
	 * @return
	 * @throws Exception
	 * @date Nov 12, 2019 1:19:19 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/getuserinfo/{code}/{secret}")
	public Response getUserInfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception {
		Response response = wechatClient.getUserInfo(code, secret);
///
//		response = new Response();
//		Map<String, String> amap = new HashMap<String, String>();
//		amap.put("UserId", "000742");
//		response.setData(amap);
///
		if (response.getStatus() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(response.getData()));
            String empcode = jsonNode.get("UserId").asText();
			Future<Contact> contactFuture = executor.submit(() -> {
				return contactService.getContact(empcode);
			});
			StringBuffer conditions = new StringBuffer();
			conditions.append("empstatusname='在职' and empcode=?");
			Object[] params = new Object[1];
			params[0] = empcode;
			List<EhrUser> list = ehrUserService.findByCondition(conditions.toString(), params);
			
			if (list != null && list.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				EhrUser ehrUser = list.get(0);
				map.put("userinfo", ehrUser);
				map.put("token", JwtUtil.generateToken(new Gson().toJson(ehrUser)));
				map.put("avatar", contactFuture.get().getAvatar());
				response.setData(map);
			} else {
				throw new BusinessException(4000);
			}
		}
		return response;
	}
	
	@GetMapping("/getQYToken")
	public Response getQYToken() throws Exception {
		return wechatClient.getQYToken(secret);
	}
}
