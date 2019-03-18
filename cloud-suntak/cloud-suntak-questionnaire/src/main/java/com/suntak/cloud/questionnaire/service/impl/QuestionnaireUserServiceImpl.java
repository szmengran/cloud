package com.suntak.cloud.questionnaire.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_user;
import com.suntak.cloud.questionnaire.service.QuestionnaireUserService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.questionnaire.service.impl
 * @Description: 问卷用户服务
 * @date 2018年4月18日 下午3:43:58
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class QuestionnaireUserServiceImpl implements QuestionnaireUserService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public List<T_questionnaire_user> findAllUsers() throws Exception {
		String strSql = "select a.userid,a.empcode,a.empname from t_questionnaire_user a left join tb_v_rpt_emp_info b on a.empcode=b.empcode and validstatus=1 where b.empstatusname='在职'";
		return abstractDao.findBySql(T_questionnaire_user.class, strSql, null);
	}

	@Override
	public List<T_questionnaire_user> findByConditions(Map<String, Object> params) throws Exception {
		return abstractDao.findByConditions(T_questionnaire_user.class, params);
	}

	@Override
	public void updatePwd(Integer userid, String password, String password3) throws Exception {
		String strSql = "update t_questionnaire_user set password=? where userid=? and password=?";
		Object[] params = new Object[3];
		params[0] = password;
		params[1] = userid;
		params[2] = password3;
		int count = abstractDao.executeSql(strSql, params);
		if (count == 0) {
			throw new BusinessException(5107);
		}
	}

	
}
