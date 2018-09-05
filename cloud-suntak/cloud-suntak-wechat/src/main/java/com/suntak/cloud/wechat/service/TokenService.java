package com.suntak.cloud.wechat.service;

/**
 * @Package com.szmengran.wechat.token.service
 * @Description: 获取Token服务
 * @date 2018年9月4日 下午4:36:39
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface TokenService {

	/**
	 * 获取token
	 * @param secret
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Object getToken(String secret) throws Exception;
}
