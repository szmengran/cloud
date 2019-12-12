package com.suntak.cloud.ems.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 保养记录
 * @date Aug 23, 2019 1:20:00 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Ems_dm_maintain {
    private Long id;
    private Integer organization_id;
    private Long equipment_id;
    private String equipment_alias;
    private String equipment_name;
    private String equipment_no;
    private String procedure;
    private String maintain_level;
    private Timestamp execute_time;
    private String status;
    private String status_name;
    private String typename;
    private String type;
    private Timestamp plan_date;
    private String before_img;
    private String after_img;
    private String area_person;
    private String maintain_person;
    private Integer solo_person_id;
    private String solo_person_name;
    private String maintain_result;
    private String mlt_use_state;
    private Long process_id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
    public Long getEquipment_id() {
        return equipment_id;
    }
    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }
    public String getEquipment_alias() {
        return equipment_alias;
    }
    public void setEquipment_alias(String equipment_alias) {
        this.equipment_alias = equipment_alias;
    }
    public String getEquipment_name() {
        return equipment_name;
    }
    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }
    public String getEquipment_no() {
        return equipment_no;
    }
    public void setEquipment_no(String equipment_no) {
        this.equipment_no = equipment_no;
    }
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public String getMaintain_level() {
        return maintain_level;
    }
    public void setMaintain_level(String maintain_level) {
        this.maintain_level = maintain_level;
    }
    public Timestamp getExecute_time() {
        return execute_time;
    }
    public void setExecute_time(Timestamp execute_time) {
        this.execute_time = execute_time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus_name() {
        return status_name;
    }
    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    public String getTypename() {
        return typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getPlan_date() {
        return plan_date;
    }
    public void setPlan_date(Timestamp plan_date) {
        this.plan_date = plan_date;
    }
    public String getBefore_img() {
        return before_img;
    }
    public void setBefore_img(String before_img) {
        this.before_img = before_img;
    }
    public String getAfter_img() {
        return after_img;
    }
    public void setAfter_img(String after_img) {
        this.after_img = after_img;
    }
    public String getArea_person() {
        return area_person;
    }
    public void setArea_person(String area_person) {
        this.area_person = area_person;
    }
    public String getMaintain_person() {
        return maintain_person;
    }
    public void setMaintain_person(String maintain_person) {
        this.maintain_person = maintain_person;
    }
    public Integer getSolo_person_id() {
		return solo_person_id;
	}
	public void setSolo_person_id(Integer solo_person_id) {
		this.solo_person_id = solo_person_id;
	}
	public String getSolo_person_name() {
        return solo_person_name;
    }
    public void setSolo_person_name(String solo_person_name) {
        this.solo_person_name = solo_person_name;
    }
    public String getMaintain_result() {
        return maintain_result;
    }
    public void setMaintain_result(String maintain_result) {
        this.maintain_result = maintain_result;
    }
    public String getMlt_use_state() {
        return mlt_use_state;
    }
    public void setMlt_use_state(String mlt_use_state) {
        this.mlt_use_state = mlt_use_state;
    }
	public Long getProcess_id() {
		return process_id;
	}
	public void setProcess_id(Long process_id) {
		this.process_id = process_id;
	}
    
}
