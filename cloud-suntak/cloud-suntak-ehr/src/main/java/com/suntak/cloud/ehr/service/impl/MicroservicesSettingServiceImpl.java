package com.suntak.cloud.ehr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.entity.T_microservices_setting;
import com.suntak.cloud.ehr.mapper.MicroservicesSettingMapper;
import com.suntak.cloud.ehr.service.MicroservicesSettingService;

/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: TODO
 * @date Jan 24, 2019 11:33:21 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class MicroservicesSettingServiceImpl implements MicroservicesSettingService {
	
	@Autowired
	private MicroservicesSettingMapper microservicesSettingMapper;
	
	@Override
	public List<T_microservices_setting> findSettingByEmpno (String empno) throws Exception {
		return microservicesSettingMapper.findSettingByEmpno(empno);
	}
	
	@Override
	public List<String> findSettingByType(String type) throws Exception {
		List<T_microservices_setting> list =  microservicesSettingMapper.findSettingByType(type);
		List<String> settingList = new ArrayList<String>();
		for (T_microservices_setting t_microservices_setting: list) {
			settingList.add(t_microservices_setting.getEmpno());
		}
		return settingList;
	}
	
	@Override
	public Boolean insert(T_microservices_setting t_microservices_setting) throws Exception {
		return microservicesSettingMapper.insert(t_microservices_setting) > 0;
	}

	@Override
	public Boolean delete(T_microservices_setting t_microservices_setting) throws Exception {
		return microservicesSettingMapper.delete(t_microservices_setting) > 0;
	}
}
