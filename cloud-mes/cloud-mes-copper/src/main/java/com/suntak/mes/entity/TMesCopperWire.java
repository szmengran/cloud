package com.suntak.mes.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(name = "t_mes_copper_wire", id = "id")
public class TMesCopperWire implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1525753310059210375L;
	private String id;
	private String wip_entity_name;
	private Integer lineid;
	private Integer times;
	private String product_type;
	private String sequence;
	private String project;
	private String board_type;
	private String empcode;
	private String board_material;
	private String tg;
	private Float board_thickness;
	private String scale;
	private String min_aperture;
	private Integer number;
	private Float front_speed;
	private Float grinding_speed;
	private Float clear_speed;
	private Float speed;
	private Float copper_speed;
	private String size;
	private Float area;
	private String type;
	private String remark;
	private String controller;
	private Timestamp product_time;
	private Integer validstatus;
	private Timestamp createstamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public Integer getLineid() {
		return lineid;
	}
	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getBoard_material() {
		return board_material;
	}
	public void setBoard_material(String board_material) {
		this.board_material = board_material;
	}
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	public Float getBoard_thickness() {
		return board_thickness;
	}
	public void setBoard_thickness(Float board_thickness) {
		this.board_thickness = board_thickness;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getMin_aperture() {
		return min_aperture;
	}
	public void setMin_aperture(String min_aperture) {
		this.min_aperture = min_aperture;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Float getFront_speed() {
		return front_speed;
	}
	public void setFront_speed(Float front_speed) {
		this.front_speed = front_speed;
	}
	public Float getGrinding_speed() {
		return grinding_speed;
	}
	public void setGrinding_speed(Float grinding_speed) {
		this.grinding_speed = grinding_speed;
	}
	public Float getClear_speed() {
		return clear_speed;
	}
	public void setClear_speed(Float clear_speed) {
		this.clear_speed = clear_speed;
	}
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public Float getCopper_speed() {
		return copper_speed;
	}
	public void setCopper_speed(Float copper_speed) {
		this.copper_speed = copper_speed;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Float getArea() {
		return area;
	}
	public void setArea(Float area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public Timestamp getProduct_time() {
		return product_time;
	}
	public void setProduct_time(Timestamp product_time) {
		this.product_time = product_time;
	}
	public Integer getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(Integer validstatus) {
		this.validstatus = validstatus;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	
}
