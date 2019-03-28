package com.suntak.cloud.sms.util;

import java.util.Map;

import com.google.gson.Gson;

/**
 * @Package com.suntak.cloud.sms.util
 * @Description: 短信处理工具
 * @date 2018年7月28日 上午9:56:14
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class SmsTool {
	
	/**
	 * 将Map对象转换为Json字符串
	 * @param map
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public static String transferMapToJson(Map<String, Object> map) throws Exception {
		return new Gson().toJson(map);
	}
}
