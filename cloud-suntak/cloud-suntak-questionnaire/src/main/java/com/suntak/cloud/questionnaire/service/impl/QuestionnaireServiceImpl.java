package com.suntak.cloud.questionnaire.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.suntak.cloud.questionnaire.entity.T_questionnaire_evaluate;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_user;
import com.suntak.cloud.questionnaire.entity.ext.T_questionnaire_evaluate_ext;
import com.suntak.cloud.questionnaire.entity.other.Questionnaire;
import com.suntak.cloud.questionnaire.service.QuestionnaireService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.service.AbstractService;

/**
 * @Package com.suntak.cloud.questionnaire.service.impl
 * @Description: 问卷用户服务
 * @date 2018年4月18日 下午3:43:58
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class QuestionnaireServiceImpl extends AbstractService implements QuestionnaireService{
	
	private final static String SEQ_QUESTIONNAIRE_EVALUATE = "seq_t_questionnaire_evaluate";
	
	@Override
	public List<T_questionnaire_user> findByUserid(Integer userid) throws Exception {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select * from t_questionnaire_user where userid in (")
			  .append(" select userid")
			  .append(" from T_questionnaire_user_role_r")
			  .append(" where roleid in (select toroleid")
			  .append(" from T_questionnaire_role_evaluate")
			  .append(" where roleid in (select roleid")
			  .append(" from T_questionnaire_user_role_r")
			  .append(" where userid = ?))")
			  .append(" )");
		Object params[] = new Object[1];
		params[0] = userid;
		return super.findBySql(new T_questionnaire_user(), strSql.toString(), params);
	}

	@Override
	public List<T_questionnaire_evaluate_ext> findQuestionnaireByConditions(Integer userid, String yearmonth) throws Exception {
		Object params[] = new Object[2];
		params[0] = userid;
		params[1] = yearmonth;
		StringBuffer strSql = new StringBuffer("select a.*,b.empname from t_questionnaire_evaluate a, t_questionnaire_user b ");
		strSql.append(" where a.customerid = b.userid and a.userid=? and yearmonth=? order by b.displayno,b.empcode");
		List<T_questionnaire_evaluate_ext> list = super.findBySql(new T_questionnaire_evaluate_ext(), strSql.toString(), params);
		if (list == null || list.size() == 0) {
			generateQuestionnaireByConditions(userid, yearmonth);
			list = super.findBySql(new T_questionnaire_evaluate_ext(), strSql.toString(), params);
		}
		return list;
	}

	
	@Override
	public void generateQuestionnaireByConditions(Integer userid, String yearmonth) throws Exception {
		List<T_questionnaire_user> list = findByUserid(userid);
		if (list == null || list.size() == 0) {
			return;
		}
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		List<T_questionnaire_evaluate> evaluateList = new ArrayList<T_questionnaire_evaluate>();
		for (int i=0; i<list.size(); i++) {
			T_questionnaire_user t_questionnaire_user = list.get(i);
			T_questionnaire_evaluate t_questionnaire_evaluate = new T_questionnaire_evaluate();
			t_questionnaire_evaluate.setUserid(userid);
			t_questionnaire_evaluate.setCustomerid(t_questionnaire_user.getUserid());
			t_questionnaire_evaluate.setCreatestamp(currentTime);
			t_questionnaire_evaluate.setUpdatestamp(currentTime);
			t_questionnaire_evaluate.setYearmonth(yearmonth);
			t_questionnaire_evaluate.setStatus(0);
			evaluateList.add(t_questionnaire_evaluate);
		}
		super.addBatch(evaluateList, DbPrimaryKeyType.SEQ, SEQ_QUESTIONNAIRE_EVALUATE);
	}

	@Override
	public void save(T_questionnaire_evaluate t_questionnaire_evaluate) throws Exception {
		Timestamp createstamp = new Timestamp(System.currentTimeMillis());
		t_questionnaire_evaluate.setCreatestamp(createstamp);
		t_questionnaire_evaluate.setUpdatestamp(createstamp);
		t_questionnaire_evaluate.setStatus(0);
		super.save(t_questionnaire_evaluate, DbPrimaryKeyType.SEQ, SEQ_QUESTIONNAIRE_EVALUATE);
	}

	@Override
	public void update(T_questionnaire_evaluate t_questionnaire_evaluate) throws Exception {
		String strSql = "update t_questionnaire_evaluate set attribute_1=?,attribute_2=?,attribute_3=?,attribute_4=?,attribute_5=?,status=1,updatestamp=? where evaluateid=? and userid=?";
		Object params[] = new Object[8];
		params[0] = t_questionnaire_evaluate.getAttribute_1();
		params[1] = t_questionnaire_evaluate.getAttribute_2();
		params[2] = t_questionnaire_evaluate.getAttribute_3();
		params[3] = t_questionnaire_evaluate.getAttribute_4();
		params[4] = t_questionnaire_evaluate.getAttribute_5();
		params[5] = t_questionnaire_evaluate.getAttribute_1()+t_questionnaire_evaluate.getAttribute_2()+t_questionnaire_evaluate.getAttribute_3()+t_questionnaire_evaluate.getAttribute_4()+t_questionnaire_evaluate.getAttribute_5();
		params[6] = new Timestamp(System.currentTimeMillis());
		params[7] = t_questionnaire_evaluate.getEvaluateid();
		params[8] = t_questionnaire_evaluate.getUserid();
		super.executeSql(strSql, params);
	}
	
	@Override
	public Boolean updateAll(Integer userid, String yearmonth, T_questionnaire_evaluate[] t_questionnaire_evaluates) throws Exception {
		DBManager dbManager = new DBManager(super.getWriteDataSource());
		Boolean flag = true;
		try {
			dbManager.openConnection();
			dbManager.beginTransaction();
			String strSql = "update t_questionnaire_evaluate set attribute_1=?,attribute_2=?,attribute_3=?,attribute_4=?,attribute_5=?,total=?,status=1,updatestamp=? where evaluateid=? and userid=? and yearmonth=?";
			Object params[] = new Object[10];
			for (int i=0; i<t_questionnaire_evaluates.length; i++) {
				T_questionnaire_evaluate t_questionnaire_evaluate = t_questionnaire_evaluates[i];
				if (t_questionnaire_evaluate.getAttribute_1() == null || t_questionnaire_evaluate.getAttribute_2() == null || t_questionnaire_evaluate.getAttribute_3() == null || t_questionnaire_evaluate.getAttribute_4() == null || t_questionnaire_evaluate.getAttribute_5() == null) {
					continue;
				}
				params[0] = t_questionnaire_evaluate.getAttribute_1();
				params[1] = t_questionnaire_evaluate.getAttribute_2();
				params[2] = t_questionnaire_evaluate.getAttribute_3();
				params[3] = t_questionnaire_evaluate.getAttribute_4();
				params[4] = t_questionnaire_evaluate.getAttribute_5();
				params[5] = t_questionnaire_evaluate.getAttribute_1()+t_questionnaire_evaluate.getAttribute_2()+t_questionnaire_evaluate.getAttribute_3()+t_questionnaire_evaluate.getAttribute_4()+t_questionnaire_evaluate.getAttribute_5();
				params[6] = new Timestamp(System.currentTimeMillis());
				params[7] = t_questionnaire_evaluate.getEvaluateid();
				params[8] = userid;
				params[9] = yearmonth;
				int count = super.executeSql(dbManager, strSql, params);
				if (count == 0) {
					flag = false;
				}
			}
			dbManager.commitTransaction();
		} catch (Exception e) {
			dbManager.rollbackTransaction();
			throw e;
		} finally {
			dbManager.close();
		}
		return flag;
	}

	@Override
	public List<T_questionnaire_evaluate> findByConditions(Map<String, Object> params) throws Exception {
		return super.findByConditions(new T_questionnaire_evaluate(), params);
	}

	@Override
	public List<Questionnaire> findResult(String yearmonth) throws Exception {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select a.userid,a.empcode,b.EMPNAME,b.DEPTNAME,b.JOB_LEVEL,b.C_MOBILE_TEL phone,t.yearmonth,t.totalcount,t.alreadycount,t.avgscore")
		      .append(" from T_QUESTIONNAIRE_USER a left join tb_v_rpt_emp_info b on a.empcode=b.EMPCODE,(")
		      .append(" SELECT customerid,yearmonth,count(*) totalcount,sum(decode(status,1,1,0)) alreadycount,avg(total) avgscore FROM T_QUESTIONNAIRE_EVALUATE")
		      .append(" where yearmonth = ? group by customerid,yearmonth) t")
		      .append(" where a.userid = t.customerid order by t.avgscore desc");
		Object params[] = new Object[1];
		params[0] = yearmonth;
		return super.findBySql(new Questionnaire(), strSql.toString(), params);
	}

	@Override
	public List<Questionnaire> findNotEvaluateUser(String yearmonth) throws Exception {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select a.userid,a.empcode,b.EMPNAME,b.DEPTNAME,b.JOB_LEVEL,b.C_MOBILE_TEL phone")
		      .append(" from T_QUESTIONNAIRE_USER a left join tb_v_rpt_emp_info b on a.empcode=b.EMPCODE where exists (")
		      .append(" SELECT 1 FROM T_QUESTIONNAIRE_EVALUATE c")
		      .append(" where c.status=0 and a.userid=c.userid and yearmonth=?)");
		Object params[] = new Object[1];
		params[0] = yearmonth;
		return super.findBySql(new Questionnaire(), strSql.toString(), params);
	}
	
	@Override
	public Boolean check(String yearmonth) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("yearmonth", yearmonth);
		params.put("status", 0);
		List<T_questionnaire_evaluate> list = super.findByConditions(new T_questionnaire_evaluate(), params);
		if (list == null || list.size() ==0) {
			return true;
		}
		return false;
	}
	
	
}
