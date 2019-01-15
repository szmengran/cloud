package com.suntak.cloud.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.wechat.client.WechatClient;
import com.suntak.cloud.wechat.entity.request.FileRequestBody;
import com.suntak.cloud.wechat.entity.request.ImgRequestBody;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.cloud.wechat.entity.request.VideoRequestBody;
import com.suntak.cloud.wechat.entity.request.VoiceRequestBody;
import com.suntak.cloud.wechat.entity.response.MsgResponseBody;
import com.suntak.cloud.wechat.entity.response.TokenResponseBody;
import com.suntak.cloud.wechat.service.WechatService;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

import net.sf.json.JSONObject;

/**
 * @Package com.suntak.cloud.wechat.controller
 * @Description: 微信服务
 * @date 2018年8月31日 下午2:47:42
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/wechat")
public class WechatController {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Autowired
	private WechatClient wechatClient;
	
	@Autowired
	private WechatService wechatService;
	
	/**
	 * 发送企业微信文本消息
	 * @param textRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/text/{secret}")
	public Response sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody) throws Exception {
		textRequestBody.setMsgtype("text");
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		if ("ok".equalsIgnoreCase(errormsg)) {
			MsgResponseBody msgResponseBody = wechatClient.send(textRequestBody, access_token);
			errormsg = msgResponseBody.getErrmsg();
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
	
	/**
	 * 发送企业微信图片消息
	 * @param secret
	 * @param imgRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/image/{secret}")
	public Response sendImage(@PathVariable("secret") String secret, @RequestBody ImgRequestBody imgRequestBody) throws Exception {
		imgRequestBody.setMsgtype("image");
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		if ("ok".equalsIgnoreCase(errormsg)) {
			MsgResponseBody msgResponseBody = wechatClient.send(imgRequestBody, access_token);
			errormsg = msgResponseBody.getErrmsg();
			if ("ok".equalsIgnoreCase(errormsg)) {
				return new Response();
			} else {
				logger.error(errormsg);
				throw new BusinessException(10014001, "发送企业微信图片消息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(10014001, "获取企业微信TOKEN失败");
		}
	}
	
	/**
	 * 发送语言消息
	 * @param secret
	 * @param voiceRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/voice/{secret}")
	public Response sendVoice(@PathVariable("secret") String secret, @RequestBody VoiceRequestBody voiceRequestBody) throws Exception {
		voiceRequestBody.setMsgtype("voice");
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		if ("ok".equalsIgnoreCase(errormsg)) {
			MsgResponseBody msgResponseBody = wechatClient.send(voiceRequestBody, access_token);
			errormsg = msgResponseBody.getErrmsg();
			if ("ok".equalsIgnoreCase(errormsg)) {
				return new Response();
			} else {
				logger.error(errormsg);
				throw new BusinessException(10014001, "发送企业微信语言消息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(10014001, "获取企业微信TOKEN失败");
		}
	}
	
	/**
	 * 发送视频消息
	 * @param secret
	 * @param videoRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/video/{secret}")
	public Response sendFile(@PathVariable("secret") String secret, @RequestBody VideoRequestBody videoRequestBody) throws Exception {
		videoRequestBody.setMsgtype("video");
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		if ("ok".equalsIgnoreCase(errormsg)) {
			MsgResponseBody msgResponseBody = wechatClient.send(videoRequestBody, access_token);
			errormsg = msgResponseBody.getErrmsg();
			if ("ok".equalsIgnoreCase(errormsg)) {
				return new Response();
			} else {
				logger.error(errormsg);
				throw new BusinessException(10014001, "发送企业微信文件消息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(10014001, "获取企业微信TOKEN失败");
		}
	}
	
	/**
	 * 发送微信文件消息
	 * @param secret
	 * @param fileRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/file/{secret}")
	public Response sendFile(@PathVariable("secret") String secret, @RequestBody FileRequestBody fileRequestBody) throws Exception {
		fileRequestBody.setMsgtype("file");
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		if ("ok".equalsIgnoreCase(errormsg)) {
			MsgResponseBody msgResponseBody = wechatClient.send(fileRequestBody, access_token);
			errormsg = msgResponseBody.getErrmsg();
			if ("ok".equalsIgnoreCase(errormsg)) {
				return new Response();
			} else {
				logger.error(errormsg);
				throw new BusinessException(10014001, "发送企业微信文件消息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(10014001, "获取企业微信TOKEN失败");
		}
	}
	
	/**
	 * 发送微信文本卡片消息
	 * @param secret
	 * @param textcardRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/textcard/{secret}")
	public Response sendTextcard(@PathVariable("secret") String secret, @RequestBody TextcardRequestBody textcardRequestBody) throws Exception {
		textcardRequestBody.setMsgtype("textcard");
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		if ("ok".equalsIgnoreCase(errormsg)) {
			MsgResponseBody msgResponseBody = wechatClient.send(textcardRequestBody, access_token);
			errormsg = msgResponseBody.getErrmsg();
			if ("ok".equalsIgnoreCase(errormsg)) {
				return new Response();
			} else {
				logger.error(errormsg);
				throw new BusinessException(10014001, "发送企业微信文本卡片消息失败");
			}
		} else {
			logger.error(errormsg);
			throw new BusinessException(10014001, "获取企业微信TOKEN失败");
		}
	}
	
	@GetMapping("/getuserinfo/{code}/{secret}")
	public Response getUserInfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception {
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		Integer errcode = tokenResponseBody.getErrcode();
		if ("ok".equalsIgnoreCase(errormsg)) {
			Object object = wechatService.getUserInfo(access_token, code);
			JSONObject responseUserInfo = JSONObject.fromObject(object);
			errormsg = responseUserInfo.getString("errmsg");
			errcode = responseUserInfo.getInt("errcode");
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
	
	@GetMapping("/getQYToken/{secret}")
	public Response getQYToken(@PathVariable("secret") String secret) throws Exception {
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		Integer errcode = tokenResponseBody.getErrcode();
		Response response = new Response();
		if ("ok".equalsIgnoreCase(errormsg)) {
			response.setData(access_token);
			return response;
		} else {
			logger.error(errormsg);
			throw new BusinessException(errcode, "获取企业微信TOKEN失败");
		}
	}
}
