package com.suntak.cloud.wechat.service;

import com.suntak.cloud.wechat.entity.response.TokenResponseBody;

/**
 * @Package com.suntak.cloud.wechat.service
 * @Description: 微信授权
 * @date 2018年9月6日 下午2:13:25
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface WechatService {
	
	/**
	 * 获取token
	 * @param secret
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public TokenResponseBody getToken(String secret) throws Exception;
	
	/**
	 * 根据授权码获取用户的信息
	 * @param access_token
	 * @param code
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Object getUserInfo(String access_token, String code) throws Exception;
}
