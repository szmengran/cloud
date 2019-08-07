package com.suntak.cloud.ems.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
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
import com.suntak.cloud.ems.client.MicroserviceClient;
import com.suntak.cloud.ems.client.WechatClient;
import com.suntak.cloud.ems.entity.Oz_org_userinfo;
import com.suntak.cloud.ems.entity.ext.Oz_org_userinfo_ext;
import com.suntak.cloud.ems.service.UserinfoService;
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

    private final static ExecutorService executor = new ThreadPoolExecutor(20, 200, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	
    @Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Autowired
	private EhrUserClient ehrUserClient;

    @Autowired
    private MicroserviceClient microserviceClient;
    
    @Autowired
    private UserinfoService userinfoService;
	
	@GetMapping("/getuserinfo/{code}")
	public Response getUserInfo(@PathVariable("code") String code) throws Exception {
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
			Future<Response> furure = executor.submit(() -> {
			    Response resp = ehrUserClient.getUserInfo(empcode);
			    ObjectMapper objectMapper1 = new ObjectMapper();
	            JsonNode jsonNode1 = objectMapper1.readTree(objectMapper1.writeValueAsBytes(resp.getData()));
	            String companycode = jsonNode1.get("companycode").asText();
			    return microserviceClient.getOrgIdByCompanyCode(companycode); 
			});
			Oz_org_userinfo userinfo = userinfoService.findUserByEmployerId(empcode);
			if (userinfo != null) {
			    Oz_org_userinfo_ext oz_org_userinfo_ext = new Oz_org_userinfo_ext();
			    BeanUtils.copyProperties(userinfo, oz_org_userinfo_ext);
			    Response resp = furure.get();
			    oz_org_userinfo_ext.setOrg_id((Integer)resp.getData());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userinfo", oz_org_userinfo_ext);
				map.put("token", JwtUtil.generateToken(new Gson().toJson(oz_org_userinfo_ext)));
				objectMapper = new ObjectMapper();
				response.setData(map);
			} else {
				throw new Exception("登录失败，你的账号还没有在设备系统中绑定！");
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
            Future<Response> furure = executor.submit(() -> {
                Response resp = ehrUserClient.getUserInfo(empcode);
                ObjectMapper objectMapper1 = new ObjectMapper();
                JsonNode jsonNode1 = objectMapper1.readTree(objectMapper1.writeValueAsBytes(resp.getData()));
                String companycode = jsonNode1.get("companycode").asText();
                return microserviceClient.getOrgIdByCompanyCode(companycode); 
            });
            Oz_org_userinfo userinfo = userinfoService.findUserByEmployerId(empcode);
            if (userinfo != null) {
                Oz_org_userinfo_ext oz_org_userinfo_ext = new Oz_org_userinfo_ext();
                BeanUtils.copyProperties(userinfo, oz_org_userinfo_ext);
                Response resp = furure.get();
                oz_org_userinfo_ext.setOrg_id((Integer)resp.getData());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userinfo", oz_org_userinfo_ext);
                map.put("token", JwtUtil.generateToken(new Gson().toJson(oz_org_userinfo_ext)));
                objectMapper = new ObjectMapper();
                response.setData(map);
            } else {
                throw new Exception("登录失败，你的账号还没有在设备系统中绑定！");
            }
		}
		return response;
	}
	
	@GetMapping("/getQYToken")
	public Response getQYToken() throws Exception {
		return wechatClient.getQYToken(secret);
	}
}
