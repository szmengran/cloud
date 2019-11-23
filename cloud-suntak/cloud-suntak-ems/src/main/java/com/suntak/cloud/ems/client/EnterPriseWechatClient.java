package com.suntak.cloud.ems.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suntak.cloud.ems.entity.Contact;

/**
 * @Package com.suntak.cloud.ehr.client
 * @Description: TODO
 * @date Dec 17, 2018 10:30:51 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "qyapi", url = "https://qyapi.weixin.qq.com")
public interface EnterPriseWechatClient {

	@GetMapping("/cgi-bin/user/get?access_token={access_token}&userid={userid}")
	Contact getContact(@PathVariable("access_token") String access_token, @PathVariable("userid") String userid) throws Exception;
}
