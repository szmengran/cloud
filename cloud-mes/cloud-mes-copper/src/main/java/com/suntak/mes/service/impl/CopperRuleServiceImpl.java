package com.suntak.mes.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.suntak.mes.entity.TMesCopperRule;
import com.suntak.mes.mapper.CopperRuleMapper;
import com.suntak.mes.service.CopperRuleService;

public class CopperRuleServiceImpl implements CopperRuleService {

	@Autowired
	private CopperRuleMapper copperRuleMapper;

	@Override
	public Boolean insertAll(List<TMesCopperRule> list, String empCode) throws Exception {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		list.forEach(rule -> {
			rule.setCreatestamp(currentTime);
			rule.setCreate_by(empCode);
			rule.setValidstatus(2);
		});
		return copperRuleMapper.insertBatchForOracle(list) > 0;
	}

	@Override
	public Boolean verifyAll(String empCode) throws Exception {
		copperRuleMapper.disableAllRule(empCode);
		copperRuleMapper.verifyAllRule(empCode);
		return null;
	}

}
