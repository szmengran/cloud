package com.suntak.cloud.haiwd.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.haiwd.mapper.PunchMapper;
import com.suntak.cloud.haiwd.service.PunchService;
import com.suntak.cloud.haiwd.utils.DatabaseContextHolder;
import com.suntak.cloud.haiwd.utils.DatabaseType;
import com.suntak.punch.entity.Punch;

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
	public List<Punch> findPunch(int time, List<Punch> list, DatabaseType databaseType) throws Exception {
		DatabaseContextHolder.setDatabaseType(databaseType);
		StringBuilder strSql = new StringBuilder();
		for (Punch punch: list) {
			strSql.append(",'").append(punch.getRunno()).append("'");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormat = sdf.format(new Date());
		String date = dateFormat.substring(8, 10);
		String yearmonth = dateFormat.substring(0, 7);
		return punchMapper.findWorkPunch(time, date, yearmonth, dateFormat+" 00:00:00", strSql.deleteCharAt(0).toString());
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
