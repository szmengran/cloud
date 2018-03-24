package com.szmengran.engineering.quote;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
 * @Package com.suntak.quote 
 * @Description: 工程报价属性值
 * @date 2018年1月11日 下午4:49:46 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_value implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value_id;
	private Integer attribute_id;
	private Integer product_id;
	private String value;
	private Double increase;
	private Integer displayno;
	private String validstatus;
	private Timestamp createstamp;
	public String getValue_id() {
		return value_id;
	}
	public void setValue_id(String value_id) {
		this.value_id = value_id;
	}
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Double getIncrease() {
		return increase;
	}
	public void setIncrease(Double increase) {
		this.increase = increase;
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
