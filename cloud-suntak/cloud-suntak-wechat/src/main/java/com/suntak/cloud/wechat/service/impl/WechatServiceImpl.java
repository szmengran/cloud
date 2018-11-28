package com.suntak.cloud.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.suntak.cloud.wechat.client.WechatClient;
import com.suntak.cloud.wechat.service.WechatService;

/**
 * @Package com.suntak.cloud.wechat.service.impl
 * @Description: 微信服务
 * @date 2018年9月6日 下午2:14:29
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class WechatServiceImpl implements WechatService{
	
	private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);
	
	@Value("${wechat.corpid}")
    private String appid;
	
	@Autowired
	private WechatClient wechatClient;

	@Cacheable(value = "qywechatToken", key = "#p0")
	@Override
    public Object getToken(String secret) throws Exception {
		return wechatClient.getToken(appid, secret);
    }
	
	@CacheEvict(allEntries = true, value = "qywechatToken")
	@Scheduled(fixedDelay = 90 * 60 * 1000 ,  initialDelay = 500)
	public void qyWechatCacheEvict() {
	      logger.info("remove qywechatToken cache");
	}
	
	@Override
	public Object getUserInfo(String access_token, String code) throws Exception {
		return wechatClient.getUserInfo(access_token, code);
	}

}
