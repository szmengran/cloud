package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 应聘人员基本信息
 * @date 2018年7月19日 上午10:59:07
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ApplicantServiceImpl implements ApplicantService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void insert(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setCreatestamp(currentTime);
		t_hr_applicant.setUpdatestamp(currentTime);
		t_hr_applicant.setStatus(0);
		abstractDao.insert(t_hr_applicant);
	}

	@Override
	public int update(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setUpdatestamp(currentTime);
		return abstractDao.update(t_hr_applicant);
	}
	
	@Override
	public int updateBaseInfo(T_hr_applicant t_hr_applicant) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		t_hr_applicant.setUpdatestamp(currentTime);
		StringBuilder strSql = new StringBuilder();
		strSql.append("update t_hr_applicant set name=?,sex=?,birthday=?,address=?")
		      .append(",phone=?,email=?")
		      .append(",mandarin=?,english=?,japanese=?,other=?")
		      .append(",relativesname=?,relativesdepartment=?,relativesposition=?,relativesrelationship=?")
		      .append(" where applicantid=?");
		Object[] params = new Object[15];
		params[0] = t_hr_applicant.getName();
		params[1] = t_hr_applicant.getSex();
		params[2] = t_hr_applicant.getBirthday();
		params[3] = t_hr_applicant.getAddress();
		params[4] = t_hr_applicant.getPhone();
		params[5] = t_hr_applicant.getEmail();
		params[6] = t_hr_applicant.getMandarin();
		params[7] = t_hr_applicant.getEnglish();
		params[8] = t_hr_applicant.getJapanese();
		params[9] = t_hr_applicant.getOther();
		params[10] = t_hr_applicant.getRelativesname();
		params[11] = t_hr_applicant.getRelativesdepartment();
		params[12] = t_hr_applicant.getRelativesposition();
		params[13] = t_hr_applicant.getRelativesrelationship();
		params[14] = t_hr_applicant.getApplicantid();
		return abstractDao.executeSql(strSql.toString(), params);
	}

	@Override
	public T_hr_applicant findById(String applicantid) throws Exception {
		T_hr_applicant t_hr_applicant = new T_hr_applicant();
		t_hr_applicant.setApplicantid(applicantid);
		return abstractDao.findByPrimaryKey(t_hr_applicant);
	}

}
