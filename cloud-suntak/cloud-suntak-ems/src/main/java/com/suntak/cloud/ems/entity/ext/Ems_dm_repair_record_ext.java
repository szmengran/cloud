package com.suntak.cloud.ems.entity.ext;

import com.suntak.cloud.ems.entity.Ems_dm_repair_record;

/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: 维修记录扩展信息
 * @date May 17, 2019 9:02:04 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Ems_dm_repair_record_ext extends Ems_dm_repair_record {
    private String equipment_alias;
    private String equipment_no;
    private String use_d;
    private String procedure;
    private String equipment_name;
    public String getEquipment_alias() {
        return equipment_alias;
    }
    public void setEquipment_alias(String equipment_alias) {
        this.equipment_alias = equipment_alias;
    }
    public String getEquipment_no() {
        return equipment_no;
    }
    public void setEquipment_no(String equipment_no) {
        this.equipment_no = equipment_no;
    }
    public String getUse_d() {
        return use_d;
    }
    public void setUse_d(String use_d) {
        this.use_d = use_d;
    }
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public String getEquipment_name() {
        return equipment_name;
    }
    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }
    
}
