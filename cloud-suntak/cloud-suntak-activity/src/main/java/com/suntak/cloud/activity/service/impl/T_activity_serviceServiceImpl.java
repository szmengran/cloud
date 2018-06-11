package com.suntak.cloud.activity.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.activity.entity.T_activity_service;
import com.suntak.cloud.activity.service.T_activity_serviceService;
import com.szmengran.common.service.BaseService;

/**
 * @Package com.suntak.cloud.activity.service.impl
 * @Description: 活动服务号
 * @date 2018年5月23日 上午9:17:11
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class T_activity_serviceServiceImpl implements T_activity_serviceService{

	@Autowired
	@Qualifier("oracleService")
	BaseService abstractService;
	
	@Override
	public List<T_activity_service> findByConditions(StringBuffer conditions, Object[] params) throws Exception {
		return abstractService.findByConditions(new T_activity_service(), conditions, params);
	}
	
	public T_activity_service findById(Integer service_id) throws Exception {
		T_activity_service t_activity_service = new T_activity_service();
		t_activity_service.setService_id(service_id);
		return abstractService.findByPrimaryKey(t_activity_service);
	}
	
	@Override
	public List<T_activity_service> findAutoServiceItem(String date) throws Exception {
		StringBuffer conditions = new StringBuffer();
		conditions.append(" and validstatus='1' and starttime <= ? and (endtime >= ? or endtime is null) and (month is null or month=?) and (day is null or day=?)");
		Date datetime = null;
		if ("*".equals(date)) {
			datetime = new Date();
		} else {
			datetime = new SimpleDateFormat("yyyy-MM-dd HH").parse(date);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		Object params[] = new Object[4];
		params[0] = datetime;
		params[1] = datetime;
		params[2] = month;
		params[3] = day;
		return abstractService.findByConditions(new T_activity_service(), conditions, params);
	}
}
