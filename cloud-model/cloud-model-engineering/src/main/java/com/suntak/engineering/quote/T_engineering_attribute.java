package com.suntak.engineering.quote;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
 * @Package com.suntak.quote 
 * @Description: 工程报价属性
 * @date 2018年1月11日 下午4:47:06 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer attribute_id;
	private Integer product_id;
	private String name;
	private String code;
	private String type;
	private String defaultvalue;
	private Integer displayno;
	private String validstatus;
	private Timestamp createstamp;
	public Integer getAttribute_id() {
		return attribute_id;
	}
	public void setAttribute_id(Integer attribute_id) {
		this.attribute_id = attribute_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public Integer getDisplayno() {
		return displayno;
	}
	public void setDisplayno(Integer displayno) {
		this.displayno = displayno;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	
}
