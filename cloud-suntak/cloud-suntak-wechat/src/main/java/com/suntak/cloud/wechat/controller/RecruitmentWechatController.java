package com.suntak.cloud.wechat.controller;

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

import com.suntak.cloud.wechat.client.WechatServiceClient;
import com.suntak.cloud.wechat.entity.MsgRequestBody;
import com.suntak.cloud.wechat.service.WechatService;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

import net.sf.json.JSONObject;

/**
 * @Package com.suntak.cloud.wechat.controller
 * @Description: TODO
 * @date 2018年8月31日 下午2:47:42
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/wechat")
public class RecruitmentWechatController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecruitmentWechatController.class);
	
	@Value("${wechat.recruitment.Secret}")
	private String secret;
	
	@Autowired
	private WechatServiceClient wechatServiceClient;
	
	@Autowired
	private WechatService wechatService;
	
	@PostMapping("/textcard")
	public Response sendTextcard(@RequestBody MsgRequestBody msgRequestBody) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(wechatService.getToken(secret));
		String access_token = jsonObject.getString("access_token");
		String errormsg = jsonObject.getString("errmsg");
		if ("ok".equalsIgnoreCase(errormsg)) {
			JSONObject responseMsg = JSONObject.fromObject(wechatServiceClient.send(msgRequestBody, access_token));
			errormsg = responseMsg.getString("errmsg");
			if ("ok".equalsIgnoreCase(errormsg)) {
				return new Response();
			} else {
				logger.error(errormsg);
				throw new BusinessException(10014001, "发送企业微信消息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(10014001, "获取企业微信TOKEN失败");
		}
	}
	
	@GetMapping("/getuserinfo/{code}")
	public Response getUserInfo(@PathVariable("code") String code) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(wechatService.getToken(secret));
		String access_token = jsonObject.getString("access_token");
		String errormsg = jsonObject.getString("errmsg");
		Integer errcode = jsonObject.getInt("errcode");
		if ("ok".equalsIgnoreCase(errormsg)) {
			Object object = wechatService.getUserInfo(access_token, code);
			JSONObject responseUserInfo = JSONObject.fromObject(object);
			errormsg = responseUserInfo.getString("errmsg");
			errcode = jsonObject.getInt("errcode");
			if ("ok".equalsIgnoreCase(errormsg)) {
				Response response = new Response();
				response.setData(object);
				return response;
			} else {
				logger.error(errormsg);
				throw new BusinessException(errcode, "获取企业微信用户信息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(errcode, "获取企业微信TOKEN失败");
		}
		
	}
}
