package com.suntak.cloud.ems.entity;

import java.sql.Timestamp;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 设备信息
 * @date Mar 18, 2019 4:34:47 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Ems_dm_equipment_details {
    private Long id                         ;
    private String equipment_name             ; //设备名称
    private String equipment_no               ;
    private String specification_model        ;
    private String equipment_type             ;
    private String manufacturer               ;
    private String suppliers                  ;
    private Timestamp start_time                 ;
    private String acquisition_mode           ;
    private String finance_d_m_h              ;
    private String deprectation_method        ;
    private Float original_value             ;
    private Float residual_rate              ;
    private Float fixed_asset_life           ;
    private Float inspection_cycle           ;
    private Timestamp last_inspection            ;
    private Timestamp next_inspection            ;
    private Timestamp last_repair                ;
    private Integer is_use                     ;
    private String use_d                      ;
    private String operator                   ;
    private String installation_location      ;
    private String remark                     ;
    private String type                       ;
    private Timestamp created_date               ;
    private String created_by                 ;
    private Timestamp updated_date               ;
    private String updated_by                 ;
    private Integer organization_id            ;
    private String contract_no                ;
    private String manager_d                  ;
    private Integer scrap_state                ;
    private Long parent_id                  ;
    private String asset_id                   ;
    private String asset_name                 ; //固定资产名称
    private String suppliers_id               ;
    private String equipment_alias            ;
    private String currency                   ;
    private Float tax                        ;
    private String procedure                  ;
    private String serial_no                  ;
    private String brand                      ;
    private String power                      ;
    private String size_e                     ;
    private String weight                     ;
    private String asset_class                ;
    private String asset_type                 ;
    private Integer isimport_equipment         ;
    private Timestamp delivery                   ;
    private Integer is_erp_import              ;
    private Float cost                       ;
    private Integer is_used_equal              ;
    private Float sell_rever                 ;
    private Float bf_cost                    ;
    private Float is_show                    ;
    private Integer have_instruction           ;
    private Integer no_index                   ;
    private String manager_code               ;
    private String demand_no                  ;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getSpecification_model() {
        return specification_model;
    }
    public void setSpecification_model(String specification_model) {
        this.specification_model = specification_model;
    }
    public String getEquipment_type() {
        return equipment_type;
    }
    public void setEquipment_type(String equipment_type) {
        this.equipment_type = equipment_type;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(String suppliers) {
        this.suppliers = suppliers;
    }
    public Timestamp getStart_time() {
        return start_time;
    }
    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }
    public String getAcquisition_mode() {
        return acquisition_mode;
    }
    public void setAcquisition_mode(String acquisition_mode) {
        this.acquisition_mode = acquisition_mode;
    }
    public String getFinance_d_m_h() {
        return finance_d_m_h;
    }
    public void setFinance_d_m_h(String finance_d_m_h) {
        this.finance_d_m_h = finance_d_m_h;
    }
    public String getDeprectation_method() {
        return deprectation_method;
    }
    public void setDeprectation_method(String deprectation_method) {
        this.deprectation_method = deprectation_method;
    }
    public Float getOriginal_value() {
        return original_value;
    }
    public void setOriginal_value(Float original_value) {
        this.original_value = original_value;
    }
    public Float getResidual_rate() {
        return residual_rate;
    }
    public void setResidual_rate(Float residual_rate) {
        this.residual_rate = residual_rate;
    }
    public Float getFixed_asset_life() {
        return fixed_asset_life;
    }
    public void setFixed_asset_life(Float fixed_asset_life) {
        this.fixed_asset_life = fixed_asset_life;
    }
    public Float getInspection_cycle() {
        return inspection_cycle;
    }
    public void setInspection_cycle(Float inspection_cycle) {
        this.inspection_cycle = inspection_cycle;
    }
    public Timestamp getLast_inspection() {
        return last_inspection;
    }
    public void setLast_inspection(Timestamp last_inspection) {
        this.last_inspection = last_inspection;
    }
    public Timestamp getNext_inspection() {
        return next_inspection;
    }
    public void setNext_inspection(Timestamp next_inspection) {
        this.next_inspection = next_inspection;
    }
    public Timestamp getLast_repair() {
        return last_repair;
    }
    public void setLast_repair(Timestamp last_repair) {
        this.last_repair = last_repair;
    }
    public Integer getIs_use() {
        return is_use;
    }
    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }
    public String getUse_d() {
        return use_d;
    }
    public void setUse_d(String use_d) {
        this.use_d = use_d;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getInstallation_location() {
        return installation_location;
    }
    public void setInstallation_location(String installation_location) {
        this.installation_location = installation_location;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public String getContract_no() {
        return contract_no;
    }
    public void setContract_no(String contract_no) {
        this.contract_no = contract_no;
    }
    public String getManager_d() {
        return manager_d;
    }
    public void setManager_d(String manager_d) {
        this.manager_d = manager_d;
    }
    public Integer getScrap_state() {
        return scrap_state;
    }
    public void setScrap_state(Integer scrap_state) {
        this.scrap_state = scrap_state;
    }
    public Long getParent_id() {
        return parent_id;
    }
    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
    public String getAsset_id() {
        return asset_id;
    }
    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }
    public String getAsset_name() {
        return asset_name;
    }
    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }
    public String getSuppliers_id() {
        return suppliers_id;
    }
    public void setSuppliers_id(String suppliers_id) {
        this.suppliers_id = suppliers_id;
    }
    public String getEquipment_alias() {
        return equipment_alias;
    }
    public void setEquipment_alias(String equipment_alias) {
        this.equipment_alias = equipment_alias;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public Float getTax() {
        return tax;
    }
    public void setTax(Float tax) {
        this.tax = tax;
    }
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public String getSerial_no() {
        return serial_no;
    }
    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getPower() {
        return power;
    }
    public void setPower(String power) {
        this.power = power;
    }
    public String getSize_e() {
        return size_e;
    }
    public void setSize_e(String size_e) {
        this.size_e = size_e;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getAsset_class() {
        return asset_class;
    }
    public void setAsset_class(String asset_class) {
        this.asset_class = asset_class;
    }
    public String getAsset_type() {
        return asset_type;
    }
    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }
    public Integer getIsimport_equipment() {
        return isimport_equipment;
    }
    public void setIsimport_equipment(Integer isimport_equipment) {
        this.isimport_equipment = isimport_equipment;
    }
    public Timestamp getDelivery() {
        return delivery;
    }
    public void setDelivery(Timestamp delivery) {
        this.delivery = delivery;
    }
    public Integer getIs_erp_import() {
        return is_erp_import;
    }
    public void setIs_erp_import(Integer is_erp_import) {
        this.is_erp_import = is_erp_import;
    }
    public Float getCost() {
        return cost;
    }
    public void setCost(Float cost) {
        this.cost = cost;
    }
    public Integer getIs_used_equal() {
        return is_used_equal;
    }
    public void setIs_used_equal(Integer is_used_equal) {
        this.is_used_equal = is_used_equal;
    }
    public Float getSell_rever() {
        return sell_rever;
    }
    public void setSell_rever(Float sell_rever) {
        this.sell_rever = sell_rever;
    }
    public Float getBf_cost() {
        return bf_cost;
    }
    public void setBf_cost(Float bf_cost) {
        this.bf_cost = bf_cost;
    }
    public Float getIs_show() {
        return is_show;
    }
    public void setIs_show(Float is_show) {
        this.is_show = is_show;
    }
    public Integer getHave_instruction() {
        return have_instruction;
    }
    public void setHave_instruction(Integer have_instruction) {
        this.have_instruction = have_instruction;
    }
    public Integer getNo_index() {
        return no_index;
    }
    public void setNo_index(Integer no_index) {
        this.no_index = no_index;
    }
    public String getManager_code() {
        return manager_code;
    }
    public void setManager_code(String manager_code) {
        this.manager_code = manager_code;
    }
    public String getDemand_no() {
        return demand_no;
    }
    public void setDemand_no(String demand_no) {
        this.demand_no = demand_no;
    }
}
