package com.suntak.cloud.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.suntak.cloud.questionnaire.entity.T_questionnaire_evaluate;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_user;
import com.suntak.cloud.questionnaire.entity.ext.T_questionnaire_evaluate_ext;
import com.suntak.cloud.questionnaire.entity.other.Questionnaire;

/**
 * @Package com.suntak.cloud.questionnaire.service
 * @Description: 问卷调查用户服务
 * @date 2018年4月18日 下午3:41:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface QuestionnaireService {
	
	/**
	 * 根据用户查询该用户需要评价的人员信息
	 * @param userid
	 * @return
	 * @throws Exception      
	 * @return: List<T_questionnaire_user>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_questionnaire_user> findByUserid(Integer userid) throws Exception;
	/**
	 * 根据用户id查询调查问卷
	 * @param userid
	 * @param yearmonth
	 * @return
	 * @throws Exception      
	 * @return: List<T_questionnaire_evaluate>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_questionnaire_evaluate_ext> findQuestionnaireByConditions(Integer userid, String yearmonth) throws Exception;
	/**
	 * 根据用户id生成对应的调查问卷
	 * @param userid
	 * @param yearmonth
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void generateQuestionnaireByConditions(Integer userid, String yearmonth) throws Exception;
	/**
	 * 保存一条问卷调查记录
	 * @param t_questionnaire_evaluate
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void save(T_questionnaire_evaluate t_questionnaire_evaluate) throws Exception;
	
	/**
	 * 更新一条问卷调查记录
	 * @param t_questionnaire_evaluate
	 * @throws Exception      
	 * @return: void      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void update(T_questionnaire_evaluate t_questionnaire_evaluate) throws Exception;
	
	/**
	 * 更新一个用户的问卷信息
	 * @param userid
	 * @param yearmonth
	 * @param t_questionnaire_evaluates
	 * @return
	 * @throws Exception      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean updateAll(Integer userid, String yearmonth, T_questionnaire_evaluate[] t_questionnaire_evaluates) throws Exception;
	
	/**
	 * 根据条件查询问卷信息
	 * @param params
	 * @return
	 * @throws Exception      
	 * @return: List<T_questionnaire_evaluate>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<T_questionnaire_evaluate> findByConditions(Map<String, Object> params) throws Exception;
	
	/**
	 * 根据月度查询问卷调查结果
	 * @param yearmonth
	 * @return
	 * @throws Exception      
	 * @return: List<Questionnaire>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<Questionnaire> findResult(String yearmonth) throws Exception;
	
	/**
	 * 查找还没有评估的用户
	 * @param yearmonth
	 * @return
	 * @throws Exception      
	 * @return: List<Questionnaire>      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	List<Questionnaire> findNotEvaluateUser(String yearmonth) throws Exception;
	
	
	/**
	 * 检查问卷调查是否完成
	 * @param yearmonth
	 * @return
	 * @throws Exception      
	 * @return: Boolean      
	 * @throws   
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	Boolean check(String yearmonth) throws Exception;
}
