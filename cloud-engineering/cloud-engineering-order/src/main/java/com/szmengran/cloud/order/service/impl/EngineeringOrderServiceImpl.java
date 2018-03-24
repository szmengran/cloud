package com.szmengran.cloud.order.service.impl;

import org.springframework.stereotype.Service;

import com.szmengran.cloud.order.service.EngineeringOrderService;
import com.szmengran.common.service.AbstractService;
import com.szmengran.utils.OrderStatus;

@Service
public class EngineeringOrderServiceImpl extends AbstractService implements EngineeringOrderService{

	@Override
	public void receive(String engineering_id, String designuserid) throws Exception {
		String strSql = "update t_engineering_order set designuserid=?,validstatus=? where engineering_id=? and validstatus=?";
		Object params[] = new Object[4];
		params[0] = designuserid;
		params[1] = OrderStatus.DESIGN_PROCESSING;
		params[2] = engineering_id;
		params[3] = OrderStatus.WAIT_FOR_RECEIVE;
		super.executeSql(strSql, params);
	}

}
