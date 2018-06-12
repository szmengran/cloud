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
	private Integer warehouse_code;
	private String wip_primary_item;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private String validstatus;
	public String getTest_stand_code() {
		return test_stand_code;
	}
	public void setTest_stand_code(String test_stand_code) {
		this.test_stand_code = test_stand_code;
	}
	public Integer getWarehouse_code() {
		return warehouse_code;
	}
	public void setWarehouse_code(Integer warehouse_code) {
		this.warehouse_code = warehouse_code;
	}
	public String getWip_primary_item() {
		return wip_primary_item;
	}
	public void setWip_primary_item(String wip_primary_item) {
		this.wip_primary_item = wip_primary_item;
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
