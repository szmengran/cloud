package com.suntak.mes.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.mes.entity.TMesCopperWire;
import com.suntak.mes.mapper.CopperWireMapper;
import com.suntak.mes.service.CopperWireService;

@Service
public class CopperWireServiceImpl implements CopperWireService {

	@Autowired
	private CopperWireMapper copperWireMapper;
	
	@Override
	public void insert(TMesCopperWire tMesCopperWire) throws SQLException {
		tMesCopperWire.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		tMesCopperWire.setValidstatus(1);
		copperWireMapper.insert(tMesCopperWire);
	}

}
