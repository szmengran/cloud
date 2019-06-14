package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.client.WechatClient;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/** 
 * @Package com.suntak.cloud.recruitment.controller 
 * @Description: 微信API的服务
 * @date May 22, 2019 1:51:26 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "微信API的服务")
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class WechatController {

    @Autowired
    private WechatClient wechatClient;

    @Value("${wechat.recruitment.Secret}")
    private String secret;
    
    @GetMapping("/getuserinfo/{code}")
    public Response getUserinfo(@PathVariable("code") String code) throws Exception {
        return wechatClient.getUserinfo(code, secret);
    }
}
