package com.suntak.engineering.quote;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
 * @Package com.suntak.engineering.quote 
 * @Description: 工程产品对象
 * @date 2018年1月12日 上午11:24:57 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer product_id;
	private String title;
	private Double lowest_price;
	private Double highest_price;
	private String validstatus;
	private Timestamp createstamp;
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getLowest_price() {
		return lowest_price;
	}
	public void setLowest_price(Double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public Double getHighest_price() {
		return highest_price;
	}
	public void setHighest_price(Double highest_price) {
		this.highest_price = highest_price;
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
