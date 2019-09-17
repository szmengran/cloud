package com.suntak.cloud.haiwd.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.suntak.cloud.haiwd.entity.Kq_kqdata;
import com.suntak.cloud.haiwd.mapper.dl.DLKqdataMapper;
import com.suntak.cloud.haiwd.mapper.sz.KqdataMapper;
import com.suntak.cloud.haiwd.service.KqdataService;

public class KqdataServiceImpl implements KqdataService {

	@Autowired
	private KqdataMapper kqdataMapper;
	
	@Autowired
	private DLKqdataMapper dlkqdataMapper;
	
	@Override
	public Boolean insert(Kq_kqdata kq_kqdata, String companycode) throws Exception {
		kq_kqdata.setGuid(UUID.randomUUID().toString());
		if ("0071".equals(companycode)) {
			return dlkqdataMapper.insert(kq_kqdata) > 0;
		} else {
		    return kqdataMapper.insert(kq_kqdata) > 0;
		}
	}

}
