package com.suntak.cloud.wechat.controller;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.wechat.entity.ResponseUserInfo;

/** 
 * @Package com.suntak.cloud.wechat.controller 
 * @Description: 企业微信服务测试
 * @date Mar 18, 2019 11:09:14 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class WechatControllerTest {

//    @Test
//    public void testCheckSendResult() {
//        WechatController wechatController = new WechatController();
//    }
    @Test
    public void testGetUserInfo() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, BusinessException {
        long start = System.currentTimeMillis();
    	ResponseUserInfo object = new ResponseUserInfo();
        object.setErrcode(0);
        object.setErrmsg("ok");
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseUserInfo responseUserInfo = objectMapper.readValue(objectMapper.writeValueAsBytes(object), ResponseUserInfo.class); 
        String errormsg = responseUserInfo.getErrmsg();
        Integer errcode = responseUserInfo.getErrcode();
        if (!"ok".equalsIgnoreCase(errormsg)) {
            throw new BusinessException(errcode, "获取企业微信用户信息失败");
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
