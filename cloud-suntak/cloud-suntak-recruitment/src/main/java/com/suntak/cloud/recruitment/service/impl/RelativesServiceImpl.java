package com.suntak.cloud.recruitment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_relatives;
import com.suntak.cloud.recruitment.service.RelativesService;
import com.szmengran.common.orm.dao.AbstractDao;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 在公司任职的亲属信息
 * @date 2018年7月19日 下午2:34:40
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class RelativesServiceImpl implements RelativesService{

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;

	@Override
	public void saveOrUpdate(T_hr_relatives t_hr_relatives) throws Exception {
		int num = abstractDao.update(t_hr_relatives);
		if (num == 0) {
			abstractDao.insert(t_hr_relatives);
		}
	}

	@Override
	public T_hr_relatives findByApplicantid(String applicantid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicantid", applicantid);
		List<T_hr_relatives> list = abstractDao.findByConditions(T_hr_relatives.class, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
