package com.suntak.mes.service;

import java.sql.SQLException;

import com.suntak.mes.entity.TMesCopperWire;

public interface CopperWireService {
	
	/**
	 * 
	 * @description 添加沉铜线记录
	 * @param tMesCopperWire
	 * @throws SQLException
	 * @date Nov 8, 2019 2:44:32 PM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	void insert(TMesCopperWire tMesCopperWire) throws SQLException;
}
