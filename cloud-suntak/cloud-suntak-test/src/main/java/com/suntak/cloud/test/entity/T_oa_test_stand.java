package com.suntak.cloud.test.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.test
 * @Description: 测试架实体
 * @date 2018年6月11日 下午3:45:36
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_oa_test_stand {
	private String test_stand_code;
	private String warehouse_code;
	private String type;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private String validstatus;
	public String getTest_stand_code() {
		return test_stand_code;
	}
	public void setTest_stand_code(String test_stand_code) {
		this.test_stand_code = test_stand_code;
	}
	public String getWarehouse_code() {
		return warehouse_code;
	}
	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	
}
