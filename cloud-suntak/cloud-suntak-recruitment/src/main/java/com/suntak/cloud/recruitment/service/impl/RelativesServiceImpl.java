package com.suntak.cloud.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_relatives;
import com.suntak.cloud.recruitment.mapper.RelativesMapper;
import com.suntak.cloud.recruitment.service.RelativesService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 在公司任职的亲属信息
 * @date 2018年7月19日 下午2:34:40
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class RelativesServiceImpl implements RelativesService{

	@Autowired
	private RelativesMapper<T_hr_relatives> relativesMapper;

	@Override
	public void saveOrUpdate(T_hr_relatives t_hr_relatives) throws Exception {
		int num = relativesMapper.update(t_hr_relatives);
		if (num == 0) {
		    relativesMapper.insert(t_hr_relatives);
		}
	}

	@Override
	public T_hr_relatives findByApplicantid(String applicantid) throws Exception {
		List<T_hr_relatives> list = relativesMapper.findByApplicantid(applicantid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
