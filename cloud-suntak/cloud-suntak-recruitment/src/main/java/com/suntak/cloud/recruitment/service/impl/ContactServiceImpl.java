package com.suntak.cloud.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.suntak.cloud.recruitment.mapper.ContactMapper;
import com.suntak.cloud.recruitment.service.ContactService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:24:20
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactMapper<T_hr_contact> contactMapper;
	
	@Override
	public void saveOrUpdate(T_hr_contact t_hr_contact) throws Exception {
		int num = contactMapper.update(t_hr_contact);
		if (num == 0) {
		    contactMapper.insert(t_hr_contact);
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
		List<T_hr_contact> list = contactMapper.findByApplicantid(applicantid);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
