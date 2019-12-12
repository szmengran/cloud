package com.suntak.cloud.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.erp.mapper.ReportMapper;
import com.suntak.cloud.erp.service.ReportService;
import com.suntak.mes.erp.Report;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;
	
	@Override
	public Report findReportByWipEntityName(String wipEntityName, Integer orgId) throws Exception {
		List<Report> list = reportMapper.findReportByWipEntityName(wipEntityName, orgId);
		if (list == null || list.size() == 0) {
			throw new Exception("找不到工单信息");
		} else if (list.size() > 1) {
			throw new Exception("视图查询错误，存在多条工单信息");
		}
		return list.get(0);
	}

	@Override
	public Report findReportByPrimaryItem(String primaryItem, Integer orgId) throws Exception {
		List<Report> list = reportMapper.findReportByPrimaryItem(primaryItem, orgId);
		if (list == null || list.size() == 0) {
			throw new Exception("找不到型号信息");
		} else if (list.size() > 1) {
			throw new Exception("查询错误，该型号存在多条参数信息");
		}
		return list.get(0);
	}

}
