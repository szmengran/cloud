package com.suntak.cloud.ems.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 配件替换表
 * @date Mar 19, 2019 8:41:04 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class Ems_dm_part_replace {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
    private Long id             ;
    private Long part_id        ;
    private String part_name      ;
    private String part_type      ;
    private Integer qty            ;
    private Float price          ;
    private Float total_price    ;
    private Long repair_id      ;
    private Long equipment_id   ;
    private String part_no        ;
    private Integer organization_id;
    private String line_type_id   ;
    private String section        ;
    private String item_id        ;
    private Integer use_period     ;
    private String uom_code       ;
    private Timestamp replace_date   ;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPart_id() {
        return part_id;
    }
    public void setPart_id(Long part_id) {
        this.part_id = part_id;
    }
    public String getPart_name() {
        return part_name;
    }
    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }
    public String getPart_type() {
        return part_type;
    }
    public void setPart_type(String part_type) {
        this.part_type = part_type;
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
    public Float getTotal_price() {
        return total_price;
    }
    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }
    public Long getRepair_id() {
        return repair_id;
    }
    public void setRepair_id(Long repair_id) {
        this.repair_id = repair_id;
    }
    public Long getEquipment_id() {
        return equipment_id;
    }
    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }
    public String getPart_no() {
        return part_no;
    }
    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }
    public Integer getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }
    public String getLine_type_id() {
        return line_type_id;
    }
    public void setLine_type_id(String line_type_id) {
        this.line_type_id = line_type_id;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getItem_id() {
        return item_id;
    }
    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    public Integer getUse_period() {
        return use_period;
    }
    public void setUse_period(Integer use_period) {
        this.use_period = use_period;
    }
    public String getUom_code() {
        return uom_code;
    }
    public void setUom_code(String uom_code) {
        this.uom_code = uom_code;
    }
    public Timestamp getReplace_date() {
        return replace_date;
    }
    public void setReplace_date(Timestamp replace_date) {
        this.replace_date = replace_date;
    }
}
