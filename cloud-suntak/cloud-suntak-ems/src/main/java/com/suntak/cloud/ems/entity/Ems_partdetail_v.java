package com.suntak.cloud.ems.entity;
/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: 配件明细信息
 * @date Mar 19, 2019 11:26:47 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Ems_partdetail_v {
    private Long id               ;
    private Integer organization_id  ;
    private String organization_code;
    private String organization_name;
    private String category1        ;
    private String category2        ;
    private String item_id          ;
    private String primary_uom_code ;
    private String part_no          ;
    private String part_name        ;
    private String uom_code         ;
    private String part_type        ;
    private Float price            ;
    private Integer onhand_qty       ;
    private Integer onroad_qty       ;
    private Integer total_onhand_qty ;
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
    public String getOrganization_code() {
        return organization_code;
    }
    public void setOrganization_code(String organization_code) {
        this.organization_code = organization_code;
    }
    public String getOrganization_name() {
        return organization_name;
    }
    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }
    public String getCategory1() {
        return category1;
    }
    public void setCategory1(String category1) {
        this.category1 = category1;
    }
    public String getCategory2() {
        return category2;
    }
    public void setCategory2(String category2) {
        this.category2 = category2;
    }
    public String getItem_id() {
        return item_id;
    }
    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    public String getPrimary_uom_code() {
        return primary_uom_code;
    }
    public void setPrimary_uom_code(String primary_uom_code) {
        this.primary_uom_code = primary_uom_code;
    }
    public String getPart_no() {
        return part_no;
    }
    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }
    public String getPart_name() {
        return part_name;
    }
    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }
    public String getUom_code() {
        return uom_code;
    }
    public void setUom_code(String uom_code) {
        this.uom_code = uom_code;
    }
    public String getPart_type() {
        return part_type;
    }
    public void setPart_type(String part_type) {
        this.part_type = part_type;
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Integer getOnhand_qty() {
        return onhand_qty;
    }
    public void setOnhand_qty(Integer onhand_qty) {
        this.onhand_qty = onhand_qty;
    }
    public Integer getOnroad_qty() {
        return onroad_qty;
    }
    public void setOnroad_qty(Integer onroad_qty) {
        this.onroad_qty = onroad_qty;
    }
    public Integer getTotal_onhand_qty() {
        return total_onhand_qty;
    }
    public void setTotal_onhand_qty(Integer total_onhand_qty) {
        this.total_onhand_qty = total_onhand_qty;
    }

}
