package com.suntak.cloud.ehr.entity;

import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: 崇达小助手设置
 * @date Jan 24, 2019 11:25:54 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "empno,type")
public class T_microservices_setting {
	private String empno;
	private String type;
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
