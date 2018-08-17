package com.suntak.cloud.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.suntak.cloud.recruitment.service.ContactService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:24:20
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Override
	public void saveOrUpdate(T_hr_contact t_hr_contact) throws Exception {
		int num = abstractDao.update(t_hr_contact);
		if (num == 0) {
			abstractDao.insert(t_hr_contact);
		}
	}
	
	/**
	 * 根据应聘者id查找紧急联系人
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@Override
	public T_hr_contact findByApplicantid(String applicantid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicantid", applicantid);
		List<T_hr_contact> list = abstractDao.findByConditions(T_hr_contact.class, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
