package com.suntak.mes.erp;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(name = "cux_prod_report_wip_for_mes")
public class Report {
	private String schedule_ship_date; //交期
	private String inspection_flag; //是否验货标识
	private Integer layer; //层==》芯板数量+2
	private Integer coreboard; //芯板数
	private String jc_pnl_qty; //结存数量
	private String pnl_qty; //本批数量
	private String item_id;
	private String wip_entity_id;
	private String wip_primary_item; //生产型号
	private String wip_entity_name; //工单号
	private String wip_entity_name_par; //父介工单号
	private String operation_description; //结存工段
	private String customercode; //客户编号
	private String board_thickness;
	private String pp_type; //PP类型
	private String pp_vendor; //PP供应商
	private String realvendor; //实际PP供应商
	private String maohe_thickness; //铆合层厚度
	private Integer organization_id; //工厂ID
	private String pnl_width; //宽
	private String pnl_length; //长
	private String pnl_width_b; //B板宽
	private String pnl_length_b; //B板长
	private String process_target_thk_; //压合板厚
	private String note_string_; //流程备注
	private String technice_note_; //工艺指示
	private String maohe_type; //铆合方式
	private String version; //工单版本号
	private Double product_area; //生产面积
	private String tool_num; //菲林编号
	private String lamination_para_; //层压参数
	private Double project; //项目
	private String borderType; //板类型
	private String generic_group_name; //板材
	private Double aspect_ratio_; //厚径比
	private String no_copper_area_inner_; //无铜区面积
	private String rout_side_x_; //锣边宽度
	private String rout_side_y_; //锣边高度
	private String control_plan; //产品品质控制
	private String req_material_type_; //是否指定板材
	private Integer min_tg_req_; //TG值
	private String batch_number;
	private String attribute11; //是否B板
	private Timestamp date_last_moved; //过数时间
	private String text22; //是否重点客户
	public String getSchedule_ship_date() {
		return schedule_ship_date;
	}
	public void setSchedule_ship_date(String schedule_ship_date) {
		this.schedule_ship_date = schedule_ship_date;
	}
	public String getInspection_flag() {
		return inspection_flag;
	}
	public void setInspection_flag(String inspection_flag) {
		this.inspection_flag = inspection_flag;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getCoreboard() {
		return coreboard;
	}
	public void setCoreboard(Integer coreboard) {
		this.coreboard = coreboard;
	}
	public String getJc_pnl_qty() {
		return jc_pnl_qty;
	}
	public void setJc_pnl_qty(String jc_pnl_qty) {
		this.jc_pnl_qty = jc_pnl_qty;
	}
	public String getPnl_qty() {
		return pnl_qty;
	}
	public void setPnl_qty(String pnl_qty) {
		this.pnl_qty = pnl_qty;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getWip_entity_id() {
		return wip_entity_id;
	}
	public void setWip_entity_id(String wip_entity_id) {
		this.wip_entity_id = wip_entity_id;
	}
	public String getWip_primary_item() {
		return wip_primary_item;
	}
	public void setWip_primary_item(String wip_primary_item) {
		this.wip_primary_item = wip_primary_item;
	}
	public Double getProject() {
		return project;
	}
	public void setProject(Double project) {
		this.project = project;
	}
	public String getBorderType() {
		return borderType;
	}
	public void setBorderType(String borderType) {
		this.borderType = borderType;
	}
	public String getGeneric_group_name() {
		return generic_group_name;
	}
	public void setGeneric_group_name(String generic_group_name) {
		this.generic_group_name = generic_group_name;
	}
	public Double getAspect_ratio_() {
		return aspect_ratio_;
	}
	public void setAspect_ratio_(Double aspect_ratio_) {
		this.aspect_ratio_ = aspect_ratio_;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getWip_entity_name_par() {
		return wip_entity_name_par;
	}
	public void setWip_entity_name_par(String wip_entity_name_par) {
		this.wip_entity_name_par = wip_entity_name_par;
	}
	public String getOperation_description() {
		return operation_description;
	}
	public void setOperation_description(String operation_description) {
		this.operation_description = operation_description;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getBoard_thickness() {
		return board_thickness;
	}
	public void setBoard_thickness(String board_thickness) {
		this.board_thickness = board_thickness;
	}
	public String getPp_type() {
		return pp_type;
	}
	public void setPp_type(String pp_type) {
		this.pp_type = pp_type;
	}
	public String getPp_vendor() {
		return pp_vendor;
	}
	public void setPp_vendor(String pp_vendor) {
		this.pp_vendor = pp_vendor;
	}
	public String getRealvendor() {
		return realvendor;
	}
	public void setRealvendor(String realvendor) {
		this.realvendor = realvendor;
	}
	public String getMaohe_thickness() {
		return maohe_thickness;
	}
	public void setMaohe_thickness(String maohe_thickness) {
		this.maohe_thickness = maohe_thickness;
	}
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public String getPnl_width() {
		return pnl_width;
	}
	public void setPnl_width(String pnl_width) {
		this.pnl_width = pnl_width;
	}
	public String getPnl_length() {
		return pnl_length;
	}
	public void setPnl_length(String pnl_length) {
		this.pnl_length = pnl_length;
	}
	public String getPnl_width_b() {
		return pnl_width_b;
	}
	public void setPnl_width_b(String pnl_width_b) {
		this.pnl_width_b = pnl_width_b;
	}
	public String getPnl_length_b() {
		return pnl_length_b;
	}
	public void setPnl_length_b(String pnl_length_b) {
		this.pnl_length_b = pnl_length_b;
	}
	public String getProcess_target_thk_() {
		return process_target_thk_;
	}
	public void setProcess_target_thk_(String process_target_thk_) {
		this.process_target_thk_ = process_target_thk_;
	}
	public String getNote_string_() {
		return note_string_;
	}
	public void setNote_string_(String note_string_) {
		this.note_string_ = note_string_;
	}
	public String getTechnice_note_() {
		return technice_note_;
	}
	public void setTechnice_note_(String technice_note_) {
		this.technice_note_ = technice_note_;
	}
	public String getMaohe_type() {
		return maohe_type;
	}
	public void setMaohe_type(String maohe_type) {
		this.maohe_type = maohe_type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Double getProduct_area() {
		return product_area;
	}
	public void setProduct_area(Double product_area) {
		this.product_area = product_area;
	}
	public String getTool_num() {
		return tool_num;
	}
	public void setTool_num(String tool_num) {
		this.tool_num = tool_num;
	}
	public String getLamination_para_() {
		return lamination_para_;
	}
	public void setLamination_para_(String lamination_para_) {
		this.lamination_para_ = lamination_para_;
	}
	public String getNo_copper_area_inner_() {
		return no_copper_area_inner_;
	}
	public void setNo_copper_area_inner_(String no_copper_area_inner_) {
		this.no_copper_area_inner_ = no_copper_area_inner_;
	}
	public String getRout_side_x_() {
		return rout_side_x_;
	}
	public void setRout_side_x_(String rout_side_x_) {
		this.rout_side_x_ = rout_side_x_;
	}
	public String getRout_side_y_() {
		return rout_side_y_;
	}
	public void setRout_side_y_(String rout_side_y_) {
		this.rout_side_y_ = rout_side_y_;
	}
	public String getControl_plan() {
		return control_plan;
	}
	public void setControl_plan(String control_plan) {
		this.control_plan = control_plan;
	}
	public String getReq_material_type_() {
		return req_material_type_;
	}
	public void setReq_material_type_(String req_material_type_) {
		this.req_material_type_ = req_material_type_;
	}
	public Integer getMin_tg_req_() {
		return min_tg_req_;
	}
	public void setMin_tg_req_(Integer min_tg_req_) {
		this.min_tg_req_ = min_tg_req_;
	}
	public String getBatch_number() {
		return batch_number;
	}
	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}
	public String getAttribute11() {
		return attribute11;
	}
	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}
	public Timestamp getDate_last_moved() {
		return date_last_moved;
	}
	public void setDate_last_moved(Timestamp date_last_moved) {
		this.date_last_moved = date_last_moved;
	}
	public String getText22() {
		return text22;
	}
	public void setText22(String text22) {
		this.text22 = text22;
	}
}
