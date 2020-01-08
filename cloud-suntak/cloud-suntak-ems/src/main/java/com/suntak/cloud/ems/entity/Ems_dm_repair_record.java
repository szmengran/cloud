package com.suntak.cloud.ems.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 维修记录表
 * @date Mar 14, 2019 2:19:46 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id="id")
public class Ems_dm_repair_record {
    
    private Long id                      ;
    private Long equipment_id            ;
    private String e_degree                ;
    private String maintenance_no          ;
    private String maintenance_apllicant   ;
    private Timestamp maintenance_time        ;
    private String fault_description       ;
    private String maintenance_unit        ;
    private String maintenance_person      ;
    private Integer maintenance_state       ;
    private String maintenance_leve        ;
    private String fault_class             ;
    private Timestamp start_time              ;
    private Timestamp end_time                ;
    private Double maintenance_cost        ;
    private String down_time               ;
    private String time_cost               ;
    private String fault_solve             ;
    private String user_score              ;
    private String user_opinion            ;
    private Timestamp created_date            ;
    private String created_by              ;
    private Timestamp updated_date            ;
    private String updated_by              ;
    private Integer organization_id         ;
    private String type                    ;
    private Integer maintenance_apllicant_id;
    private Integer maintenance_person_id   ;
    private Integer distribution_id         ;
    private Integer status                  ;
    private Timestamp distribution_date       ;
    private String distribution_by         ;
    private Integer repai_mode              ;
    private Integer bill_mode               ;
    private Integer time_occupy             ;
    private Integer repair_time             ;
    private String apply_phone             ;
    private String ssistperson_ids         ;
    private String ssistperson_names       ;
    private Timestamp last_modified_date      ;
    private Integer last_modifier_id        ;
    private String last_modifier_name      ;
    private String service_pass            ;
    private String failure_cause           ;
    private String maintenance_plan        ;
    private Integer source;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEquipment_id() {
        return equipment_id;
    }
    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }
    public String getE_degree() {
        return e_degree;
    }
    public void setE_degree(String e_degree) {
        this.e_degree = e_degree;
    }
    public String getMaintenance_no() {
        return maintenance_no;
    }
    public void setMaintenance_no(String maintenance_no) {
        this.maintenance_no = maintenance_no;
    }
    public String getMaintenance_apllicant() {
        return maintenance_apllicant;
    }
    public void setMaintenance_apllicant(String maintenance_apllicant) {
        this.maintenance_apllicant = maintenance_apllicant;
    }
    public Timestamp getMaintenance_time() {
        return maintenance_time;
    }
    public void setMaintenance_time(Timestamp maintenance_time) {
        this.maintenance_time = maintenance_time;
    }
    public String getFault_description() {
        return fault_description;
    }
    public void setFault_description(String fault_description) {
        this.fault_description = fault_description;
    }
    public String getMaintenance_unit() {
        return maintenance_unit;
    }
    public void setMaintenance_unit(String maintenance_unit) {
        this.maintenance_unit = maintenance_unit;
    }
    public String getMaintenance_person() {
        return maintenance_person;
    }
    public void setMaintenance_person(String maintenance_person) {
        this.maintenance_person = maintenance_person;
    }
    public Integer getMaintenance_state() {
        return maintenance_state;
    }
    public void setMaintenance_state(Integer maintenance_state) {
        this.maintenance_state = maintenance_state;
    }
    public String getMaintenance_leve() {
        return maintenance_leve;
    }
    public void setMaintenance_leve(String maintenance_leve) {
        this.maintenance_leve = maintenance_leve;
    }
    public String getFault_class() {
        return fault_class;
    }
    public void setFault_class(String fault_class) {
        this.fault_class = fault_class;
    }
    public Timestamp getStart_time() {
        return start_time;
    }
    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }
    public Timestamp getEnd_time() {
        return end_time;
    }
    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }
    public Double getMaintenance_cost() {
        return maintenance_cost;
    }
    public void setMaintenance_cost(Double maintenance_cost) {
        this.maintenance_cost = maintenance_cost;
    }
    public String getDown_time() {
        return down_time;
    }
    public void setDown_time(String down_time) {
        this.down_time = down_time;
    }
    public String getTime_cost() {
        return time_cost;
    }
    public void setTime_cost(String time_cost) {
        this.time_cost = time_cost;
    }
    public String getFault_solve() {
        return fault_solve;
    }
    public void setFault_solve(String fault_solve) {
        this.fault_solve = fault_solve;
    }
    public String getUser_score() {
        return user_score;
    }
    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }
    public String getUser_opinion() {
        return user_opinion;
    }
    public void setUser_opinion(String user_opinion) {
        this.user_opinion = user_opinion;
    }
    public Timestamp getCreated_date() {
        return created_date;
    }
    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }
    public String getCreated_by() {
        return created_by;
    }
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    public Timestamp getUpdated_date() {
        return updated_date;
    }
    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }
    public String getUpdated_by() {
        return updated_by;
    }
    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
    public Integer getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getMaintenance_apllicant_id() {
        return maintenance_apllicant_id;
    }
    public void setMaintenance_apllicant_id(Integer maintenance_apllicant_id) {
        this.maintenance_apllicant_id = maintenance_apllicant_id;
    }
    public Integer getMaintenance_person_id() {
        return maintenance_person_id;
    }
    public void setMaintenance_person_id(Integer maintenance_person_id) {
        this.maintenance_person_id = maintenance_person_id;
    }
    public Integer getDistribution_id() {
        return distribution_id;
    }
    public void setDistribution_id(Integer distribution_id) {
        this.distribution_id = distribution_id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Timestamp getDistribution_date() {
        return distribution_date;
    }
    public void setDistribution_date(Timestamp distribution_date) {
        this.distribution_date = distribution_date;
    }
    public String getDistribution_by() {
        return distribution_by;
    }
    public void setDistribution_by(String distribution_by) {
        this.distribution_by = distribution_by;
    }
    public Integer getRepai_mode() {
        return repai_mode;
    }
    public void setRepai_mode(Integer repai_mode) {
        this.repai_mode = repai_mode;
    }
    public Integer getBill_mode() {
        return bill_mode;
    }
    public void setBill_mode(Integer bill_mode) {
        this.bill_mode = bill_mode;
    }
    public Integer getTime_occupy() {
        return time_occupy;
    }
    public void setTime_occupy(Integer time_occupy) {
        this.time_occupy = time_occupy;
    }
    public Integer getRepair_time() {
        return repair_time;
    }
    public void setRepair_time(Integer repair_time) {
        this.repair_time = repair_time;
    }
    public String getApply_phone() {
        return apply_phone;
    }
    public void setApply_phone(String apply_phone) {
        this.apply_phone = apply_phone;
    }
    public String getSsistperson_ids() {
        return ssistperson_ids;
    }
    public void setSsistperson_ids(String ssistperson_ids) {
        this.ssistperson_ids = ssistperson_ids;
    }
    public String getSsistperson_names() {
        return ssistperson_names;
    }
    public void setSsistperson_names(String ssistperson_names) {
        this.ssistperson_names = ssistperson_names;
    }
    public Timestamp getLast_modified_date() {
        return last_modified_date;
    }
    public void setLast_modified_date(Timestamp last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
    public Integer getLast_modifier_id() {
        return last_modifier_id;
    }
    public void setLast_modifier_id(Integer last_modifier_id) {
        this.last_modifier_id = last_modifier_id;
    }
    public String getLast_modifier_name() {
        return last_modifier_name;
    }
    public void setLast_modifier_name(String last_modifier_name) {
        this.last_modifier_name = last_modifier_name;
    }
    public String getService_pass() {
        return service_pass;
    }
    public void setService_pass(String service_pass) {
        this.service_pass = service_pass;
    }
    public String getFailure_cause() {
        return failure_cause;
    }
    public void setFailure_cause(String failure_cause) {
        this.failure_cause = failure_cause;
    }
    public String getMaintenance_plan() {
        return maintenance_plan;
    }
    public void setMaintenance_plan(String maintenance_plan) {
        this.maintenance_plan = maintenance_plan;
    }
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
}
