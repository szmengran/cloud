package com.suntak.cloud.ehr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ehr.entity.CodeResource;
import com.suntak.cloud.ehr.mapper.CodeResourceMapper;
import com.suntak.cloud.ehr.service.CodeResourceService;

@Service
public class CodeResourceServiceImpl implements CodeResourceService {

	@Autowired
	private CodeResourceMapper codeResourceMapper;
	
	@Override
	public List<CodeResource> findResource(String c_type_code) {
		return codeResourceMapper.findResource(c_type_code);
	}

}
