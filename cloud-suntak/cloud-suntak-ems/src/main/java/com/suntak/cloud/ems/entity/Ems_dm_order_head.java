package com.suntak.cloud.ems.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 备件领用信息
 * @date Jul 23, 2019 8:21:43 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Ems_dm_order_head {
    private Integer id;
    private Integer operation;
    private Timestamp date_time;
    private String order_no;
    private String supplier;
    private String use_d;
    private String equipment_id;
    private String use_p;
    private String operation_type;
    private String remark;
    private Integer organization_id;
    private Integer ebs_status;
    private String repair_id;
    private String maintenance_no;
    private String use_p_id;
    private Integer ebs_state;
    private String ebs_number;
    private String txn_name;
    private String result_str;
    private String exe_time;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOperation() {
        return operation;
    }
    public void setOperation(Integer operation) {
        this.operation = operation;
    }
    public Timestamp getDate_time() {
        return date_time;
    }
    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }
    public String getOrder_no() {
        return order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public String getUse_d() {
        return use_d;
    }
    public void setUse_d(String use_d) {
        this.use_d = use_d;
    }
    public String getEquipment_id() {
        return equipment_id;
    }
    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }
    public String getUse_p() {
        return use_p;
    }
    public void setUse_p(String use_p) {
        this.use_p = use_p;
    }
    public String getOperation_type() {
        return operation_type;
    }
    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
    public Integer getEbs_status() {
        return ebs_status;
    }
    public void setEbs_status(Integer ebs_status) {
        this.ebs_status = ebs_status;
    }
    public String getRepair_id() {
        return repair_id;
    }
    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
    }
    public String getMaintenance_no() {
        return maintenance_no;
    }
    public void setMaintenance_no(String maintenance_no) {
        this.maintenance_no = maintenance_no;
    }
    public String getUse_p_id() {
        return use_p_id;
    }
    public void setUse_p_id(String use_p_id) {
        this.use_p_id = use_p_id;
    }
    public Integer getEbs_state() {
        return ebs_state;
    }
    public void setEbs_state(Integer ebs_state) {
        this.ebs_state = ebs_state;
    }
    public String getEbs_number() {
        return ebs_number;
    }
    public void setEbs_number(String ebs_number) {
        this.ebs_number = ebs_number;
    }
    public String getTxn_name() {
        return txn_name;
    }
    public void setTxn_name(String txn_name) {
        this.txn_name = txn_name;
    }
    public String getResult_str() {
        return result_str;
    }
    public void setResult_str(String result_str) {
        this.result_str = result_str;
    }
    public String getExe_time() {
        return exe_time;
    }
    public void setExe_time(String exe_time) {
        this.exe_time = exe_time;
    }
}
