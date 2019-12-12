package com.suntak.cloud.erp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.erp.entity.EbsResponse;
import com.suntak.cloud.erp.mapper.CuxEmsMapper;
import com.suntak.cloud.erp.service.CuxEmsService;

@Service
public class CuxEmsServiceImpl implements CuxEmsService {

	@Autowired
	private CuxEmsMapper cuxEmsMapper;
	
	@Override
	public EbsResponse submitEBS(Integer p_org_id, Long p_Header_Id) {
		Map<String, Object> map = new HashMap<>();
		map.put("retcode", null);
		map.put("errbuf", null);
		map.put("x_ebs_number", null);
		map.put("p_org_id", p_org_id);
		map.put("p_Header_Id", p_Header_Id);
		cuxEmsMapper.submitEBS(map);
		EbsResponse ebsResponse = new EbsResponse();
		ebsResponse.setErrbuf((String)map.get("errbuf"));
		ebsResponse.setRetcode((Integer)map.get("retcode"));
		ebsResponse.setX_ebs_number((String)map.get("x_ebs_number"));
		return ebsResponse;
	}

}
