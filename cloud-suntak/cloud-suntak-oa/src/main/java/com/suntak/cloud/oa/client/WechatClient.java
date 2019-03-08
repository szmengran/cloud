package com.suntak.cloud.oa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.FileRequestBody;
import com.suntak.cloud.wechat.entity.request.ImgRequestBody;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.cloud.wechat.entity.request.VideoRequestBody;
import com.suntak.cloud.wechat.entity.request.VoiceRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.microservices.client
 * @Description: TODO
 * @date 2018年9月5日 下午1:35:12
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "cloud-suntak-wechat")
public interface WechatClient {

	/**
	 * 获取企业微信token
	 * @param secret
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping("/api/v1/wechat/getQYToken/{secret}")
	public Response getQYToken(@PathVariable("secret") String secret) throws Exception;
	
	/**
	 * 发送文本消息
	 * @param secret
	 * @param textRequestBody
	 * @return      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping(value = "/api/v1/wechat/text/{secret}")
	public Response sendText(@PathVariable("secret") String secret, @RequestBody TextRequestBody textRequestBody);
	
	/**
	 * 发送图片消息
	 * @param secret
	 * @param imgRequestBody
	 * @return
	 * @throws Exception      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/api/v1/wechat/image/{secret}")
	public Response sendImage(@PathVariable("secret") String secret, @RequestBody ImgRequestBody imgRequestBody) throws Exception;
	
	/**
	 * 发送语音消息
	 * @param secret
	 * @param voiceRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/api/v1/wechat/voice/{secret}")
	public Response sendVoice(@PathVariable("secret") String secret, @RequestBody VoiceRequestBody voiceRequestBody) throws Exception;
	
	/**
	 * 发送视频消息
	 * @param secret
	 * @param videoRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/api/v1/wechat/video/{secret}")
	public Response sendVideo(@PathVariable("secret") String secret, @RequestBody VideoRequestBody videoRequestBody) throws Exception;
	
	/**
	 * 发送微信文件消息
	 * @param secret
	 * @param fileRequestBody
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/api/v1/wechat/file/{secret}")
	public Response sendFile(@PathVariable("secret") String secret, @RequestBody FileRequestBody fileRequestBody) throws Exception;
	
	/**
	 * 发送卡片消息
	 * @param secret
	 * @param textcardRequestBody
	 * @return      
	 * @return: Response      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping(value = "/api/v1/wechat/textcard/{secret}")
	public Response sendTextcard(@PathVariable("secret") String secret, @RequestBody TextcardRequestBody textcardRequestBody);
}
