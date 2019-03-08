package com.suntak.cloud.oa.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.oa.client.WechatClient;
import com.suntak.cloud.wechat.entity.request.FileRequestBody;
import com.suntak.cloud.wechat.entity.request.ImgRequestBody;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.cloud.wechat.entity.request.VideoRequestBody;
import com.suntak.cloud.wechat.entity.request.VoiceRequestBody;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Package com.suntak.cloud.wechat.controller
 * @Description: 微信服务
 * @date 2018年8月31日 下午2:47:42
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "崇达企业微信操作API")
@RestController
@RequestMapping("/api/v1/oa")
public class WechatController {
	
	private static final Logger LOG = LoggerFactory.getLogger(WechatController.class);
	
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Value("${wechat.qy.AgentId}")
	private String agentId;
	
	@Value("${wechat.power.businessnos}")
	private String strBusinessNos;
	
	@Autowired
	private WechatClient wechatClient;
	
	/**
	 * 校验业务号是否合法
	 * @param businessNo
	 * @return      
	 * @return: boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private boolean checkBusinessNo(String businessNo) throws BusinessException{
		String[] businessNos = strBusinessNos.split(",");
		for (String no: businessNos) {
			if (no.equalsIgnoreCase(businessNo)) {
				return true;
			}
		}
		throw new BusinessException(403, "该业务号【"+businessNo+"】没有权限发送消息");
	}
	
	/**
	 * 发送企业微信文本消息
	 * @param textRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@ApiOperation("发送企业微信文本消息")
	@PostMapping("/text/{businessNo}")
	public Response sendText(@PathVariable("businessNo") String businessNo, @RequestBody TextRequestBody textRequestBody, HttpServletResponse httpServletResponse) throws Exception {
		textRequestBody.setAgentid(agentId);
		LOG.info("发送企业微信文本消息:{},{}", businessNo, textRequestBody);
		checkBusinessNo(businessNo);
		return wechatClient.sendText(secret, textRequestBody);
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
	@PostMapping("/image/{businessNo}")
	public Response sendImage(@PathVariable("businessNo") String businessNo, @RequestBody ImgRequestBody imgRequestBody) throws Exception {
		imgRequestBody.setAgentid(agentId);
		LOG.info("发送企业微信图片消息:{},{}", businessNo, imgRequestBody);
		checkBusinessNo(businessNo);
		return wechatClient.sendImage(secret, imgRequestBody);
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
	@PostMapping("/voice/{businessNo}")
	public Response sendVoice(@PathVariable("businessNo") String businessNo, @RequestBody VoiceRequestBody voiceRequestBody) throws Exception {
		voiceRequestBody.setAgentid(agentId);
		LOG.info("发送企业微信语音消息:{},{}", businessNo, voiceRequestBody);
		checkBusinessNo(businessNo);
		return wechatClient.sendVoice(secret, voiceRequestBody);
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
	@PostMapping("/video/{businessNo}")
	public Response sendFile(@PathVariable("businessNo") String businessNo, @RequestBody VideoRequestBody videoRequestBody) throws Exception {
		videoRequestBody.setAgentid(agentId);
		LOG.info("发送企业微信视频消息:{},{}", businessNo, videoRequestBody);
		checkBusinessNo(businessNo);
		return wechatClient.sendVideo(secret, videoRequestBody);
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
	@PostMapping("/file/{businessNo}")
	public Response sendFile(@PathVariable("businessNo") String businessNo, @RequestBody FileRequestBody fileRequestBody) throws Exception {
		fileRequestBody.setAgentid(agentId);
		LOG.info("发送企业微信文件消息:{},{}", businessNo, fileRequestBody);
		checkBusinessNo(businessNo);
		return wechatClient.sendFile(secret, fileRequestBody);
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
	@PostMapping("/textcard/{businessNo}")
	public Response sendTextcard(@PathVariable("businessNo") String businessNo, @RequestBody TextcardRequestBody textcardRequestBody) throws Exception {
		textcardRequestBody.setAgentid(agentId);
		LOG.info("发送企业微信卡片消息:{},{}", businessNo, textcardRequestBody);
		checkBusinessNo(businessNo);
		return wechatClient.sendTextcard(secret, textcardRequestBody);
	}
}
