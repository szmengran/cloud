package com.suntak.cloud.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_languageskills;
import com.suntak.cloud.recruitment.service.LanguageskillsService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:27:43
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class LanguageskillsServiceImpl implements LanguageskillsService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void saveOrUpdate(T_hr_languageskills t_hr_languageskills) throws Exception {
		int num = abstractDao.update(t_hr_languageskills);
		if (num == 0) {
			abstractDao.insert(t_hr_languageskills);
		}
	}

	@Override
	public T_hr_languageskills findByApplicantid(String applicantid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicantid", applicantid);
		List<T_hr_languageskills> list = abstractDao.findByConditions(T_hr_languageskills.class, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
