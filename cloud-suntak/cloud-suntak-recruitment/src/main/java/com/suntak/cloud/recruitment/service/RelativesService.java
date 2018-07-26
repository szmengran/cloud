package com.suntak.cloud.recruitment.service;

import com.suntak.cloud.recruitment.entity.T_hr_relatives;

/**
 * @Package com.suntak.cloud.interview.service
 * @Description: 填写是否有亲属在公司上班
 * @date 2018年7月19日 上午10:02:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface RelativesService {
	
	/**
	 * 填写是否有亲属在公司上班
	 * @param t_hr_relatives
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public void insert(T_hr_relatives t_hr_relatives) throws Exception;
	
}