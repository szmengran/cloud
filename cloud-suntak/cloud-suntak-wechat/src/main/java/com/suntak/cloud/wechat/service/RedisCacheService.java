package com.suntak.cloud.wechat.service;
/**
 * @Package com.suntak.cloud.wechat.service
 * @Description: Redis缓存服务
 * @date 2018年9月3日 下午5:14:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface RedisCacheService {
	/**
	 * 
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public Object getCacheToken() throws Exception;
}
