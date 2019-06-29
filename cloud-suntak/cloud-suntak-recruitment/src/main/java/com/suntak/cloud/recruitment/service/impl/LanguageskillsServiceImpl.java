package com.suntak.cloud.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_languageskills;
import com.suntak.cloud.recruitment.mapper.LanguageskillsMapper;
import com.suntak.cloud.recruitment.service.LanguageskillsService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:27:43
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class LanguageskillsServiceImpl implements LanguageskillsService{

	@Autowired
	private LanguageskillsMapper<T_hr_languageskills> languageskillsMapper;
	
	@Override
	public void saveOrUpdate(T_hr_languageskills t_hr_languageskills) throws Exception {
		int num = languageskillsMapper.update(t_hr_languageskills);
		if (num == 0) {
		    languageskillsMapper.insert(t_hr_languageskills);
		}
	}

	@Override
	public T_hr_languageskills findByApplicantid(String applicantid) throws Exception {
		List<T_hr_languageskills> list = languageskillsMapper.findByApplicantid(applicantid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
