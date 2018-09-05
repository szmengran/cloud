package com.suntak.cloud.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.suntak.cloud.wechat.client.WechatServiceClient;
import com.suntak.cloud.wechat.service.TokenService;

/**
 * @Package com.szmengran.wechat.token.service.impl
 * @Description: 企业微信
 * @date 2018年9月5日 上午9:38:08
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("qyWechatTokenService")
public class QyWechatTokenServiceImpl implements TokenService{
	
	private static final Logger logger = LoggerFactory.getLogger(QyWechatTokenServiceImpl.class);
	
	@Value("${wechat.corpid}")
    private String appid;
	
	@Autowired
	private WechatServiceClient wechatServiceClient;

	@Cacheable(value = "qywechatToken", key = "#p0")
	@Override
    public Object getToken(String secret) throws Exception {
		return wechatServiceClient.getToken(appid, secret);
    }
	
	@CacheEvict(allEntries = true, value = "qywechatToken")
	@Scheduled(fixedDelay = 90 * 60 * 1000 ,  initialDelay = 500)
	public void qyWechatCacheEvict() {
	      logger.info("remove qywechatToken cache");
	}
}
