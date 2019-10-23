package com.suntak.mes.erp.entity;

public class ErpRequest {

	private String primary_item_id;
	private Integer org_id;
	private String wip_entity_name;
	public String getPrimary_item_id() {
		return primary_item_id;
	}
	public void setPrimary_item_id(String primary_item_id) {
		this.primary_item_id = primary_item_id;
	}
	public Integer getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	
}
