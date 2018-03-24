package com.szmengran.engineering.order;

import java.io.Serializable;

/** 
 * @Package com.suntak.engineering.order 
 * @Description: TODO
 * @date 2018年1月22日 下午4:11:47 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_order_file implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String engineering_id;
	private String fileid;
	public String getEngineering_id() {
		return engineering_id;
	}
	public void setEngineering_id(String engineering_id) {
		this.engineering_id = engineering_id;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	
}
