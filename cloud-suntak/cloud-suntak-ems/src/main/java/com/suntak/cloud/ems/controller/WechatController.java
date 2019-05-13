package com.suntak.cloud.ems.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.suntak.cloud.ems.client.EhrUserClient;
import com.suntak.cloud.ems.client.WechatClient;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.microservices.controller
 * @Description: 企业微信服务
 * @date Nov 29, 2018 8:40:33 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "microservices")
@RestController
@RequestMapping(path = "/api/v1/ems", produces = { "application/json" })
public class WechatController {

	private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(2, 50, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Autowired
	private EhrUserClient ehrUserClient;
	
	@GetMapping("/getuserinfo/{code}")
	public Response getUserInfo(@PathVariable("code") String code) throws Exception {
		Response response = wechatClient.getUserInfo(code, secret);
		if (response.getStatus() == 200) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(response.getData()));
			String empcode = jsonNode.get("UserId").asText();
			Future<Response> contactResponse = EXECUTOR.submit(() -> {
				return ehrUserClient.getContact(empcode);
			});
			Response ehrUserResponse = ehrUserClient.getUserInfo(empcode);
			if (ehrUserResponse.getStatus() == 200) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userinfo", ehrUserResponse.getData());
				map.put("token", JwtUtil.generateToken(new Gson().toJson(ehrUserResponse.getData())));
				Response resp = contactResponse.get();
				objectMapper = new ObjectMapper();
	            jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(resp.getData()));
				String avatar = jsonNode.get("avatar").asText();
				map.put("avatar", avatar);
				response.setData(map);
			} else {
				return ehrUserResponse;
			}
			
		}
		return response;
	}
	
	@GetMapping("/getuserinfo/{code}/{secret}")
	public Response getUserInfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception {
		Response response = wechatClient.getUserInfo(code, secret);
		if (response.getStatus() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(response.getData()));
            String empcode = jsonNode.get("UserId").asText();
			Future<Response> contactResponse = EXECUTOR.submit(() -> {
				return ehrUserClient.getContact(empcode);
			});
			Response ehrUserResponse = ehrUserClient.getUserInfo(empcode);
			if (ehrUserResponse.getStatus() == 200) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userinfo", ehrUserResponse.getData());
				map.put("token", JwtUtil.generateToken(new Gson().toJson(ehrUserResponse.getData())));
				Response resp = contactResponse.get();
				objectMapper = new ObjectMapper();
                jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(resp.getData()));
                String avatar = jsonNode.get("avatar").asText();
				map.put("avatar", avatar);
				response.setData(map);
			} else {
				return ehrUserResponse;
			}
			
		}
		return response;
	}
	
	@GetMapping("/getQYToken")
	public Response getQYToken() throws Exception {
		return wechatClient.getQYToken(secret);
	}
}
