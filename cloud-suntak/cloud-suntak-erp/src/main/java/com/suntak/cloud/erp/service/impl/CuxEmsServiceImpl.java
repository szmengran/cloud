package com.suntak.cloud.erp.service.impl;

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
		Integer retcode = null;
		String errbuf = null;
		String x_ebs_number = null;
		cuxEmsMapper.submitEBS(retcode, errbuf, p_org_id, p_Header_Id, x_ebs_number);
		EbsResponse ebsResponse = new EbsResponse();
		ebsResponse.setErrbuf(errbuf);
		ebsResponse.setRetcode(retcode);
		ebsResponse.setX_ebs_number(x_ebs_number);
		return ebsResponse;
	}

}
