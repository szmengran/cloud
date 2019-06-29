package com.suntak.cloud.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.mapper.EducationHistoryMapper;
import com.suntak.cloud.recruitment.service.EducationHistoryService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 学习经历
 * @date 2018年7月19日 下午2:24:51
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EducationHistoryServiceImpl implements EducationHistoryService{
	
	@Autowired
	private EducationHistoryMapper<T_hr_educationhistory> educationHistoryMapper;
	
	@Override
	public void saveOrUpdate(T_hr_educationhistory t_hr_educationhistory) throws Exception {
		int num = educationHistoryMapper.update(t_hr_educationhistory);
		if (num == 0) {
		    educationHistoryMapper.insert(t_hr_educationhistory);
		}
	}

	@Override
	public T_hr_educationhistory findByApplicantid(String applicantid) throws Exception {
		List<T_hr_educationhistory> list = educationHistoryMapper.findByApplicantid(applicantid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
