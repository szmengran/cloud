package com.szmengran.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Package com.szmengran.common.entity
 * @Description: 短信验证码表
 * @date 2018年4月19日 下午4:25:05
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_common_sms_code implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4243245678923421L;
	private String phone;
	private String code;
	private Timestamp updatestamp;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
	
}
