package com.suntak.cloud.haiwd.service;

import com.suntak.cloud.haiwd.entity.CheckindataRequest;

public interface CheckindataService {

	/**
	 * 加载企业微信考勤数据
	 * @param checkindataRequest
	 * @throws Exception
	 */
	void load(CheckindataRequest checkindataRequest) throws Exception;
}
