package com.suntak.cloud.ehr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.entity.T_wechat_department;
import com.suntak.cloud.ehr.mapper.DepartmentMapper;
import com.suntak.cloud.ehr.service.DepartmentService;

/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: TODO
 * @date Dec 17, 2018 3:30:56 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public void insert(T_wechat_department t_wechat_department) throws Exception {
		departmentMapper.insert(t_wechat_department);
	}

	@Override
	public void update(T_wechat_department t_wechat_department) throws Exception {
		int count = departmentMapper.update(t_wechat_department);
		if (count == 0) {
			insert(t_wechat_department);
		}
	}

	@Override
	public Boolean deleteAll() throws Exception {
	    return departmentMapper.deleteAll() > 0;
	}
}
