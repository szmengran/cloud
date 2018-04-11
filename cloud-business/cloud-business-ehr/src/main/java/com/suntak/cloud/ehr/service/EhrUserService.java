package com.suntak.cloud.ehr.service;

import java.util.List;

import com.suntak.ehr.entity.EhrUser;

/**
 * @Package com.suntak.cloud.ehr.service
 * @Description: 人事信息
 * @date 2018年4月11日 下午2:20:33
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface EhrUserService {
	/**
	 * 查找用户
	 * @return
	 * @throws Exception      
	 * @return: List<EhrUser>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<EhrUser> findByCondition(String conditions) throws Exception;
}
