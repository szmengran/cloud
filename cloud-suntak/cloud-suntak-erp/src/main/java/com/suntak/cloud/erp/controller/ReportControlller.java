package com.suntak.cloud.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.erp.entity.ErpRequest;
import com.suntak.cloud.erp.service.ReportService;
import com.suntak.exception.model.Response;
import com.suntak.mes.erp.Report;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "ERP信息查询")
public class ReportControlller {

	@Autowired
	private ReportService reportService;
	
	/**
	 * 
	 * @description 根据生产型号，查询MI参数信息
	 * @param erpRequest
	 * @return
	 * @throws Exception
	 * @date Oct 23, 2019 10:22:01 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/primary")
	@ApiOperation(value = "根据生产型号，查询MI参数信息", response = Response.class)
	public Response findReportByPrimaryItem(@RequestBody ErpRequest erpRequest) throws Exception {
		Report report = reportService.findReportByPrimaryItem(erpRequest.getPrimary_item_id(), erpRequest.getOrg_id());
		Response response = new Response();
		response.setData(report);
		return response;
	}
	
	/**
	 * 
	 * @description 根据工单号查询MI信息
	 * @param erpRequest
	 * @return
	 * @throws Exception
	 * @date Oct 23, 2019 10:24:51 AM
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping("/wip")
	@ApiOperation(value = "根据工单号查询MI信息", response = Response.class)
	public Response findReportByWipEntityName(@RequestBody ErpRequest erpRequest) throws Exception {
		Report report = reportService.findReportByWipEntityName(erpRequest.getWip_entity_name(), erpRequest.getOrg_id());
		Response response = new Response();
		response.setData(report);
		return response;
	}
}
