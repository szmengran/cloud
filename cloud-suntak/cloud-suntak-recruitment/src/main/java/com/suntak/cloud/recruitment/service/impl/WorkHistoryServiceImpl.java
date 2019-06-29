package com.suntak.cloud.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.mapper.WorkHistoryMapper;
import com.suntak.cloud.recruitment.service.WorkHistoryService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 工作经历服务
 * @date 2018年7月19日 下午2:36:41
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class WorkHistoryServiceImpl implements WorkHistoryService{

	@Autowired
	private WorkHistoryMapper<T_hr_workhistory> workHistoryMapper;
	
	@Override
	public void saveOrUpdate(T_hr_workhistory t_hr_workhistory) throws Exception {
		if (t_hr_workhistory.getWorkhistoryid() == null) {
		    workHistoryMapper.insert(t_hr_workhistory);
		} else {
		    workHistoryMapper.update(t_hr_workhistory);
		}
	}

	@Override
	public List<T_hr_workhistory> findByApplicantid(String applicantid) throws Exception {
		return workHistoryMapper.findByApplicantid(applicantid);
	}
	

	@Override
	public void delete(Integer workhistoryid) throws Exception {
		T_hr_workhistory t_hr_workhistory = new T_hr_workhistory();
		t_hr_workhistory.setWorkhistoryid(workhistoryid);
		workHistoryMapper.delete(t_hr_workhistory);
	}
}
