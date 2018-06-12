package com.suntak.cloud.test.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.test.entity
 * @Description: 库位实体
 * @date 2018年6月12日 上午10:40:46
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_oa_test_warehouse {
	private String warehouse_code;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private String validstatus;
	public String getWarehouse_code() {
		return warehouse_code;
	}
	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
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
