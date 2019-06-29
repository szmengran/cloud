package com.suntak.cloud.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.mapper.FamilyMemberMapper;
import com.suntak.cloud.recruitment.service.FamilyMemberService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 家庭成员信息
 * @date 2018年7月19日 下午2:36:17
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{
	
	@Autowired
	private FamilyMemberMapper<T_hr_familymember> familyMemberMapper;

	@Override
	public void saveOrUpdate(T_hr_familymember t_hr_familymember) throws Exception {
		if (t_hr_familymember.getFamilymemberid() == null) {
		    familyMemberMapper.insert(t_hr_familymember);
		} else {
		    familyMemberMapper.update(t_hr_familymember);
		}
	}

	@Override
	public List<T_hr_familymember> findByApplicantid(String applicantid) throws Exception {
		return familyMemberMapper.findByApplicantid(applicantid);
	}

	@Override
	public void delete(Integer familymemberid) throws Exception {
		T_hr_familymember t_hr_familymember = new T_hr_familymember();
		t_hr_familymember.setFamilymemberid(familymemberid);
		familyMemberMapper.delete(t_hr_familymember);
	}
	

}
