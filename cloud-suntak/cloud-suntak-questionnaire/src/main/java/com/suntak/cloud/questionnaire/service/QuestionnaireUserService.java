package com.suntak.cloud.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.suntak.cloud.questionnaire.entity.T_questionnaire_user;

/**
 * @Package com.suntak.cloud.questionnaire.service
 * @Description: 问卷调查用户服务
 * @date 2018年4月18日 下午3:41:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface QuestionnaireUserService {

	/**
	 * 查询所有的用户信息
	 * @return
	 * @throws Exception      
	 * @return: List<T_questionnaire_user>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_questionnaire_user> findAllUsers() throws Exception;
	
	/**
	 * 查找用户信息
	 * @param params
	 * @return
	 * @throws Exception      
	 * @return: List<T_questionnaire_user>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_questionnaire_user> findByConditions(Map<String, Object> params) throws Exception;
	
	/**
	 * 修改密码
	 * @param userid
	 * @param password
	 * @param password3
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void updatePwd(Integer userid, String password, String password3) throws Exception;
}
