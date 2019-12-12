package com.suntak.cloud.erp.service;

import com.suntak.cloud.erp.entity.EbsResponse;

public interface CuxEmsService {

	EbsResponse submitEBS(Integer p_org_id, Long p_Header_Id);
}
