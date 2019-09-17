package com.suntak.cloud.haiwd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.suntak.cloud.haiwd.entity.CheckindataRequest;
import com.suntak.cloud.haiwd.entity.CheckindataResponse;

@FeignClient(name = "qyapi", url = "https://qyapi.weixin.qq.com")
public interface QyapiClient {

	@PostMapping(value = "/cgi-bin/checkin/getcheckindata?access_token={access_token}")
	CheckindataResponse query(@RequestBody CheckindataRequest checkindataRequest, @PathVariable("access_token") String access_token) throws Exception;
}
