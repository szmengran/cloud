package com.suntak.cloud.ems.entity;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 订单行
 * @date Jul 26, 2019 1:23:08 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Ems_dm_order_line {
    
    @GeneratedValue(generator="HIBERNATE_SEQUENCE", strategy=GenerationType.SEQUENCE)
    private Integer id;
    private Integer part_no;
    private String part_name;
    private String type;
    private Integer qty;
    private Float price;
    private Integer head_id;
    private Integer part_id;
    private Integer organization_id;
    private String uom_code;
    private Float amount;
    private Integer item_id;
    private Integer line_type_id;
    private Integer distribution_account;
    private String item_type;
    private Integer ebs_user_id;
    private String ebs_user_name;
    private String subinventory_code;
    private String subinventory_name;
    private String item_refer;
    private Integer equipment_id;
    private String equipment_name;
    private String equipment_no;
    private Integer use_d;
    private String use_dname;
    private String cost_type;
    private Integer line_id;
    private Integer actual_qty;
    private Integer onhand_qty;
public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public Integer getPart_no() {
    return part_no;
}
public void setPart_no(Integer part_no) {
    this.part_no = part_no;
}
public String getPart_name() {
    return part_name;
}
public void setPart_name(String part_name) {
    this.part_name = part_name;
}
public String getType() {
    return type;
}
public void setType(String type) {
    this.type = type;
}
public Integer getQty() {
    return qty;
}
public void setQty(Integer qty) {
    this.qty = qty;
}
public Float getPrice() {
    return price;
}
public void setPrice(Float price) {
    this.price = price;
}
public Integer getHead_id() {
    return head_id;
}
public void setHead_id(Integer head_id) {
    this.head_id = head_id;
}
public Integer getPart_id() {
    return part_id;
}
public void setPart_id(Integer part_id) {
    this.part_id = part_id;
}
public Integer getOrganization_id() {
    return organization_id;
}
public void setOrganization_id(Integer organization_id) {
    this.organization_id = organization_id;
}
public String getUom_code() {
    return uom_code;
}
public void setUom_code(String uom_code) {
    this.uom_code = uom_code;
}
public Float getAmount() {
    return amount;
}
public void setAmount(Float amount) {
    this.amount = amount;
}
public Integer getItem_id() {
    return item_id;
}
public void setItem_id(Integer item_id) {
    this.item_id = item_id;
}
public Integer getLine_type_id() {
    return line_type_id;
}
public void setLine_type_id(Integer line_type_id) {
    this.line_type_id = line_type_id;
}
public Integer getDistribution_account() {
    return distribution_account;
}
public void setDistribution_account(Integer distribution_account) {
    this.distribution_account = distribution_account;
}
public String getItem_type() {
    return item_type;
}
public void setItem_type(String item_type) {
    this.item_type = item_type;
}
public Integer getEbs_user_id() {
    return ebs_user_id;
}
public void setEbs_user_id(Integer ebs_user_id) {
    this.ebs_user_id = ebs_user_id;
}
public String getEbs_user_name() {
    return ebs_user_name;
}
public void setEbs_user_name(String ebs_user_name) {
    this.ebs_user_name = ebs_user_name;
}
public String getSubinventory_code() {
    return subinventory_code;
}
public void setSubinventory_code(String subinventory_code) {
    this.subinventory_code = subinventory_code;
}
public String getSubinventory_name() {
    return subinventory_name;
}
public void setSubinventory_name(String subinventory_name) {
    this.subinventory_name = subinventory_name;
}
public String getItem_refer() {
    return item_refer;
}
public void setItem_refer(String item_refer) {
    this.item_refer = item_refer;
}
public Integer getEquipment_id() {
    return equipment_id;
}
public void setEquipment_id(Integer equipment_id) {
    this.equipment_id = equipment_id;
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
public Integer getUse_d() {
    return use_d;
}
public void setUse_d(Integer use_d) {
    this.use_d = use_d;
}
public String getUse_dname() {
    return use_dname;
}
public void setUse_dname(String use_dname) {
    this.use_dname = use_dname;
}
public String getCost_type() {
    return cost_type;
}
public void setCost_type(String cost_type) {
    this.cost_type = cost_type;
}
public Integer getLine_id() {
    return line_id;
}
public void setLine_id(Integer line_id) {
    this.line_id = line_id;
}
public Integer getActual_qty() {
    return actual_qty;
}
public void setActual_qty(Integer actual_qty) {
    this.actual_qty = actual_qty;
}
public Integer getOnhand_qty() {
    return onhand_qty;
}
public void setOnhand_qty(Integer onhand_qty) {
    this.onhand_qty = onhand_qty;
}

}
