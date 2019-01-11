package com.suntak.cloud.haiwd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.haiwd.entity.Punch;
import com.suntak.cloud.haiwd.mapper.PunchMapper;
import com.suntak.cloud.haiwd.service.PunchService;
import com.suntak.cloud.haiwd.utils.DatabaseContextHolder;
import com.suntak.cloud.haiwd.utils.DatabaseType;

/**
 * @Package com.suntak.cloud.haiwd.service.impl
 * @Description: TODO
 * @date Jan 11, 2019 11:30:58 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class PunchServiceImpl implements PunchService {

	@Autowired
	private PunchMapper punchMapper;
	
	@Override
	public List<Punch> findWorkPunch(String date, String yearmonth, String resultdate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Punch> findRunno(int time, int minute, int scanSecond, DatabaseType databaseType) throws Exception {
		DatabaseContextHolder.setDatabaseType(databaseType);
		switch (time) {
			case 1:
				return punchMapper.findWorkRunno1(minute, scanSecond);
			case 3:
				return punchMapper.findWorkRunno3(minute, scanSecond);
			case 2:
				return punchMapper.findOffRunno2(minute, scanSecond);
			case 4:
				return punchMapper.findOffRunno4(minute, scanSecond);
			default:
				return null;
		}
	}

}
