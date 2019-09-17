package com.suntak.cloud.haiwd.service;

import com.suntak.cloud.haiwd.entity.Kq_kqdata;

public interface KqdataService {

	/**
	 * 考勤数据保存
	 * @param kq_kqdata
	 * @param databaseType
	 * @return
	 * @throws Exception
	 */
	Boolean insert(Kq_kqdata kq_kqdata, String companycode) throws Exception;
}
