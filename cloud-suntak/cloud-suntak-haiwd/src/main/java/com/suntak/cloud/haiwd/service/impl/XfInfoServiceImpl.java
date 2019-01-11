package com.suntak.cloud.haiwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.haiwd.entity.Cux_xf_info;
import com.suntak.cloud.haiwd.mapper.XfInfoMapper;
import com.suntak.cloud.haiwd.service.XfInfoService;
import com.suntak.cloud.haiwd.utils.DatabaseContextHolder;
import com.suntak.cloud.haiwd.utils.DatabaseType;

/**
 * @Package com.suntak.cloud.microservices.payment.service.impl
 * @Description: 消费查询服务
 * @date Dec 25, 2018 9:02:57 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class XfInfoServiceImpl implements XfInfoService{

	@Autowired
	private XfInfoMapper xfInfoMapper;
	
	@Override
	public List<Cux_xf_info> findSTXFByConditions(String empno, String companycode) throws Exception {
		if ("0071".equals(companycode)) {
			DatabaseContextHolder.setDatabaseType(DatabaseType.dldb);
		} else {
			DatabaseContextHolder.setDatabaseType(DatabaseType.szdb);
		}
		return xfInfoMapper.findSTXFByConditions(empno, companycode);
	}

	@Override
	public List<Cux_xf_info> findCSXFByConditions(String empno, String companycode) throws Exception {
		if ("0071".equals(companycode)) {
			DatabaseContextHolder.setDatabaseType(DatabaseType.dldb);
		} else {
			DatabaseContextHolder.setDatabaseType(DatabaseType.szdb);
		}
		return xfInfoMapper.findCSXFByConditions(empno, companycode);
	}

}
