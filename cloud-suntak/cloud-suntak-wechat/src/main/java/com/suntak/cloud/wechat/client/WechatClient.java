package com.suntak.cloud.wechat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.wechat.entity.request.AbstractRequestBody;
import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.cloud.wechat.entity.response.MsgResponseBody;
import com.suntak.cloud.wechat.entity.response.RobotResponse;
import com.suntak.cloud.wechat.entity.response.TokenResponseBody;

/**
 * @Package com.suntak.cloud.wechat.client
 * @Description: 企业微信接口操作
 * @date 2018年8月31日 下午2:36:03
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "wechat",url = "https://qyapi.weixin.qq.com")
public interface WechatClient {
	@GetMapping(value = "/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}")
	TokenResponseBody getToken(@PathVariable("corpid") String corpid, @PathVariable("corpsecret") String corpsecret);

	/**
	 * 
	 * @description 机器人发送企业微信图文消息
	 * @param robotid
	 * @param newsRequestBody
	 * @return
	 * @throws Exception
	 * @date Sep 27, 2019 2:47:34 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/cgi-bin/webhook/send?key={robotid}")
	RobotResponse sendNews(@PathVariable("robotid") String robotid, @RequestBody NewsRequestBody newsRequestBody);

	/**
	 * 发送企业微信消息
	 * @param abstractRequestBody
	 * @param access_token
	 * @return 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping(value = "/cgi-bin/message/send?access_token={access_token}")
	MsgResponseBody send(@RequestBody AbstractRequestBody abstractRequestBody, @PathVariable("access_token") String access_token);
	
	/**
	 * 企业微信通过授权码获取用户信息
	 * @param access_token
	 * @param code
	 * @return 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping(value="/cgi-bin/user/getuserinfo?access_token={access_token}&code={code}")
	Object getUserInfo(@PathVariable("access_token") String access_token, @PathVariable("code") String code);
}
