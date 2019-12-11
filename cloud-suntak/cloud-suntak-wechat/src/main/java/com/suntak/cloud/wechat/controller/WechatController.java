package com.suntak.cloud.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.wechat.client.WechatClient;
import com.suntak.cloud.wechat.entity.request.AbstractRequestBody;
import com.suntak.cloud.wechat.entity.request.FileRequestBody;
import com.suntak.cloud.wechat.entity.request.ImgRequestBody;
import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.cloud.wechat.entity.request.VideoRequestBody;
import com.suntak.cloud.wechat.entity.request.VoiceRequestBody;
import com.suntak.cloud.wechat.entity.response.MsgResponseBody;
import com.suntak.cloud.wechat.entity.response.TokenResponseBody;
import com.suntak.cloud.wechat.service.WechatService;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.wechat.controller
 * @Description: 微信服务
 * @date 2018年8月31日 下午2:47:42
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("崇达企业微信操作API")
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
	@ApiOperation("发送企业微信文本消息")
	@PostMapping("/text/{secret}")
	public Response sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody) throws Exception {
		textRequestBody.setMsgtype("text");
		return send(secret, textRequestBody);
	}
	
	/**
	 * 设备系统通过企业微信接口发送信息
	 * @param username
	 * @param password
	 * @param textRequestBody
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信文本消息")
	@PostMapping("/text/{username}/{password}")
	public Response sendText(@PathVariable("username") String username, @PathVariable("password") String password, @RequestBody TextRequestBody textRequestBody) throws Exception {
	    logger.info("发送企业微信文本消息:{},{},{}", username, password, new Gson().toJson(textRequestBody));
	    textRequestBody.setAgentid("1000030");
	    if (!username.equals("shebei") || !password.equals("SFJ13SKFJIWEFSF28922SF77S")) {
	        throw new Exception("用户名或密码不正确，不能调用企业微信消息接口！");
	    }
	    textRequestBody.setMsgtype("text");
	    return send("oPsgPVWXn-h5S45GTdIvxitp7eJ-MGvt1P_SjGaVdeA", textRequestBody);
	}
	
	/**
	 * 发送企业微信图片消息
	 * @param secret
	 * @param imgRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信图片消息")
	@PostMapping("/image/{secret}")
	public Response sendImage(@PathVariable("secret") String secret, @RequestBody ImgRequestBody imgRequestBody) throws Exception {
		imgRequestBody.setMsgtype("image");
        return send(secret, imgRequestBody);
	}
	
	/**
	 * 发送语音消息
	 * @param secret
	 * @param voiceRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信语音消息")
	@PostMapping("/voice/{secret}")
	public Response sendVoice(@PathVariable("secret") String secret, @RequestBody VoiceRequestBody voiceRequestBody) throws Exception {
		voiceRequestBody.setMsgtype("voice");
        return send(secret, voiceRequestBody);
	}
	
	/**
	 * 发送视频消息
	 * @param secret
	 * @param videoRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信视频消息")
	@PostMapping("/video/{secret}")
	public Response sendVideo(@PathVariable("secret") String secret, @RequestBody VideoRequestBody videoRequestBody) throws Exception {
		videoRequestBody.setMsgtype("video");
        return send(secret, videoRequestBody);
	}
	
	/**
	 * 发送微信文件消息
	 * @param secret
	 * @param fileRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信文件消息")
	@PostMapping("/file/{secret}")
	public Response sendFile(@PathVariable("secret") String secret, @RequestBody FileRequestBody fileRequestBody) throws Exception {
		fileRequestBody.setMsgtype("file");
        return send(secret, fileRequestBody);
	}
	
	/**
	 * 发送微信文本卡片消息
	 * @param secret
	 * @param textcardRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信卡片消息")
	@PostMapping("/textcard/{secret}")
	public Response sendTextcard(@PathVariable("secret") String secret, @RequestBody TextcardRequestBody textcardRequestBody) throws Exception {
		textcardRequestBody.setMsgtype("textcard");
        return send(secret, textcardRequestBody);
	}
	
	@ApiOperation("发送企业微信图文消息")
	@PostMapping("/news/{secret}")
	public Response sendNews(@PathVariable("secret") String secret, @RequestBody NewsRequestBody newsRequestBody) throws Exception {
		newsRequestBody.setMsgtype("news");
		return send(secret, newsRequestBody);
	}

	/**
	 * 获取用户授权信息
	 * @param code
	 * @param secret
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/getuserinfo/{code}/{secret}")
	public Response getUserInfo(@PathVariable("code") String code, @PathVariable("secret") String secret) throws Exception {
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		Integer errcode = tokenResponseBody.getErrcode();
		if (!"ok".equalsIgnoreCase(errormsg)) {
			logger.error(errormsg);
			throw new BusinessException(errcode, "获取企业微信TOKEN失败");
		}
        Object object = wechatService.getUserInfo(access_token, code);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(object));
        errormsg = jsonNode.get("errmsg").asText();
        errcode = jsonNode.get("errcode").asInt();
        if (!"ok".equalsIgnoreCase(errormsg)) {
            logger.error(errormsg);
            throw new BusinessException(errcode, "获取企业微信用户信息失败");
        }
        Response response = new Response();
        response.setData(object);
        return response;
	}
	
	/**
	 * 获取Token
	 * @param secret
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/getQYToken/{secret}")
	public Response getQYToken(@PathVariable("secret") String secret) throws Exception {
		TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
		String access_token = tokenResponseBody.getAccess_token();
		String errormsg = tokenResponseBody.getErrmsg();
		Integer errcode = tokenResponseBody.getErrcode();
		Response response = new Response();
		if (!"ok".equalsIgnoreCase(errormsg)) {
			logger.error(errormsg);
			throw new BusinessException(errcode, "获取企业微信TOKEN失败");
		} 
		response.setData(access_token);
		return response;
	}

    /**
     * 检查发送结果
     * @param msgResponseBody
     * @param abstractRequestBody
     * @return      
     * @return: boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    private boolean checkSendResult(MsgResponseBody msgResponseBody, AbstractRequestBody abstractRequestBody) {
        if (!"ok".equalsIgnoreCase(msgResponseBody.getErrmsg())) {
            return false;
        }
        if (!StringUtils.isEmpty(abstractRequestBody.getTouser())) {
            if (StringUtils.isEmpty(msgResponseBody.getInvaliduser()) || abstractRequestBody.getTouser().length() > msgResponseBody.getInvaliduser().length()) {
                return true;
            }
        }
        if (!StringUtils.isEmpty(abstractRequestBody.getToparty())) {
            if (StringUtils.isEmpty(msgResponseBody.getInvalidparty()) || abstractRequestBody.getToparty().length() > msgResponseBody.getInvalidparty().length()) {
                return true;
            }
        }
        if (!StringUtils.isEmpty(abstractRequestBody.getTotag())) {
            if (StringUtils.isEmpty(msgResponseBody.getInvalidtag()) || abstractRequestBody.getTotag().length() > msgResponseBody.getInvalidtag().length()) {
                return true;
            }
        }
        return false;
    }
    
	/**
	 * 公共的消息发送接口
	 * @param secret
	 * @param abstractRequestBody
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private Response send(String secret, AbstractRequestBody abstractRequestBody) throws Exception {
	    TokenResponseBody tokenResponseBody = wechatService.getToken(secret);
        String access_token = tokenResponseBody.getAccess_token();
        String errormsg = tokenResponseBody.getErrmsg();
        if (!"ok".equalsIgnoreCase(errormsg)) {
            logger.error(errormsg);
            throw new BusinessException(10014001, "获取企业微信TOKEN失败");
        } 

        MsgResponseBody msgResponseBody = wechatClient.send(abstractRequestBody, access_token);
        errormsg = msgResponseBody.getErrmsg();
        if (!checkSendResult(msgResponseBody, abstractRequestBody)) {
            logger.error("发送企业微信消息失败：{}, {}", errormsg, new Gson().toJson(abstractRequestBody));
            throw new BusinessException(10014001, "发送企业微信消息失败");
        }
        return new Response();
        
	}
}
