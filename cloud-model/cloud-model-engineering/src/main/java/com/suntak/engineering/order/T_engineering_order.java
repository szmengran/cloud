package com.suntak.engineering.order;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
 * @Package com.suntak.engineering.order 
 * @Description: 工程订单明细信息
 * @date 2018年1月22日 下午3:23:11 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String engineering_id;
	private String userid;
	private Integer area;
	private Integer pcsarea;
	private Integer layer;
	private Integer impedance;
	private Integer puzzle;
	private Integer resistance;
	private Integer pcbchar;
	private Integer copper;
	private Integer standard;
	private Integer mark;
	private Integer delivery;
	private Double price;
	private Double discount;
	private Double postage;
	private Integer quantity;
	private String validstatus;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	public String getEngineering_id() {
		return engineering_id;
	}
	public void setEngineering_id(String engineering_id) {
		this.engineering_id = engineering_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getPcsarea() {
		return pcsarea;
	}
	public void setPcsarea(Integer pcsarea) {
		this.pcsarea = pcsarea;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getImpedance() {
		return impedance;
	}
	public void setImpedance(Integer impedance) {
		this.impedance = impedance;
	}
	public Integer getPuzzle() {
		return puzzle;
	}
	public void setPuzzle(Integer puzzle) {
		this.puzzle = puzzle;
	}
	public Integer getResistance() {
		return resistance;
	}
	public void setResistance(Integer resistance) {
		this.resistance = resistance;
	}
	public Integer getPcbchar() {
		return pcbchar;
	}
	public void setPcbchar(Integer pcbchar) {
		this.pcbchar = pcbchar;
	}
	public Integer getCopper() {
		return copper;
	}
	public void setCopper(Integer copper) {
		this.copper = copper;
	}
	public Integer getStandard() {
		return standard;
	}
	public void setStandard(Integer standard) {
		this.standard = standard;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public Integer getDelivery() {
		return delivery;
	}
	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getPostage() {
		return postage;
	}
	public void setPostage(Double postage) {
		this.postage = postage;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
	
}
