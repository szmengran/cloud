package com.suntak.cloud.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.order.service.EngineeringOrderService;
import com.suntak.cloud.order.utils.OrderStatus;
import com.szmengran.common.orm.dao.AbstractDao;

@Service
public class EngineeringOrderServiceImpl implements EngineeringOrderService{

	@Autowired
	@Qualifier("mySqlDao")
	AbstractDao abstractDao;
	
	@Override
	public void receive(String engineering_id, String designuserid) throws Exception {
		String strSql = "update t_engineering_order set designuserid=?,validstatus=? where engineering_id=? and validstatus=?";
		Object params[] = new Object[4];
		params[0] = designuserid;
		params[1] = OrderStatus.DESIGN_PROCESSING;
		params[2] = engineering_id;
		params[3] = OrderStatus.WAIT_FOR_RECEIVE;
		abstractDao.executeSql(strSql, params);
	}

}
