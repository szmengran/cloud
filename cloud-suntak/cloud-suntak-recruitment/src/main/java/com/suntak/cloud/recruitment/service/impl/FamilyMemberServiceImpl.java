package com.suntak.cloud.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.service.FamilyMemberService;
import com.szmengran.common.orm.DbPrimaryKeyType;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 家庭成员信息
 * @date 2018年7月19日 下午2:36:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{
	
	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;

	@Override
	public void saveOrUpdate(T_hr_familymember t_hr_familymember) throws Exception {
		if (t_hr_familymember.getFamilymemberid() == null) {
			abstractDao.insert(t_hr_familymember, DbPrimaryKeyType.SEQ, "seq_t_hr_familymember");
		} else {
			abstractDao.update(t_hr_familymember);
		}
	}

	@Override
	public List<T_hr_familymember> findByApplicantid(String applicantid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicantid", applicantid);
		return abstractDao.findByConditions(T_hr_familymember.class, params);
	}

	@Override
	public void delete(Integer familymemberid) throws Exception {
		T_hr_familymember t_hr_familymember = new T_hr_familymember();
		t_hr_familymember.setFamilymemberid(familymemberid);
		abstractDao.delete(t_hr_familymember);
	}
	

}
