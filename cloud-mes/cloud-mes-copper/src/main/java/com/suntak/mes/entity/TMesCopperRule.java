package com.suntak.mes.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(name = "t_mes_copper_rule", id = "id")
public class TMesCopperRule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4177047063913349266L;
	private String id;
	private String project;
	private String plate_type;
	private Integer times;
	private Integer min_tg_value;
	private Integer max_tg_value;
	private Float min_thickness;
	private Float max_thickness;
	private Float min_aspect_ratio_;
	private Float max_aspect_ratio_;
	private Float standard;
	private Float standard_down;
	private Float standard_up;
	private Float down_limit;
	private Float up_limit;
	private Integer lineid;
	private String linename;
	private Integer org_id;
	private Float front_speed;
	private Float grinding_speed;
	private Float clear_speed;
	private Float speed;
	private Float copper_speed;
	private String create_by;
	private Timestamp createstamp;
	private String update_by;
	private Timestamp updatestamp;
	private String verify_by;
	private Timestamp verifystamp;
	private Integer validstatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getPlate_type() {
		return plate_type;
	}
	public void setPlate_type(String plate_type) {
		this.plate_type = plate_type;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Integer getMin_tg_value() {
		return min_tg_value;
	}
	public void setMin_tg_value(Integer min_tg_value) {
		this.min_tg_value = min_tg_value;
	}
	public Integer getMax_tg_value() {
		return max_tg_value;
	}
	public void setMax_tg_value(Integer max_tg_value) {
		this.max_tg_value = max_tg_value;
	}
	public Float getMin_thickness() {
		return min_thickness;
	}
	public void setMin_thickness(Float min_thickness) {
		this.min_thickness = min_thickness;
	}
	public Float getMax_thickness() {
		return max_thickness;
	}
	public void setMax_thickness(Float max_thickness) {
		this.max_thickness = max_thickness;
	}
	public Float getMin_aspect_ratio_() {
		return min_aspect_ratio_;
	}
	public void setMin_aspect_ratio_(Float min_aspect_ratio_) {
		this.min_aspect_ratio_ = min_aspect_ratio_;
	}
	public Float getMax_aspect_ratio_() {
		return max_aspect_ratio_;
	}
	public void setMax_aspect_ratio_(Float max_aspect_ratio_) {
		this.max_aspect_ratio_ = max_aspect_ratio_;
	}
	public Float getStandard() {
		return standard;
	}
	public void setStandard(Float standard) {
		this.standard = standard;
	}
	public Float getStandard_down() {
		return standard_down;
	}
	public void setStandard_down(Float standard_down) {
		this.standard_down = standard_down;
	}
	public Float getStandard_up() {
		return standard_up;
	}
	public void setStandard_up(Float standard_up) {
		this.standard_up = standard_up;
	}
	public Float getDown_limit() {
		return down_limit;
	}
	public void setDown_limit(Float down_limit) {
		this.down_limit = down_limit;
	}
	public Float getUp_limit() {
		return up_limit;
	}
	public void setUp_limit(Float up_limit) {
		this.up_limit = up_limit;
	}
	public Integer getLineid() {
		return lineid;
	}
	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public Integer getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
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
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}
	public String getVerify_by() {
		return verify_by;
	}
	public void setVerify_by(String verify_by) {
		this.verify_by = verify_by;
	}
	public Timestamp getVerifystamp() {
		return verifystamp;
	}
	public void setVerifystamp(Timestamp verifystamp) {
		this.verifystamp = verifystamp;
	}
	public Integer getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(Integer validstatus) {
		this.validstatus = validstatus;
	}
}
