package com.suntak.report.monitor.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class T_report_monitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long organization_id;
	private String process;
	private String calculation_date;
	private String calculation_time;
	private Integer count_hour;
	private String companyname;
	private Double outpnl_area;
	private Double outcp_area;
	private Double worktime;
	private Double spendtime;
	private Double standard;
	private Integer peoples;
	private Timestamp createstamp;
	public Long getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Long organization_id) {
		this.organization_id = organization_id;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getCalculation_date() {
		return calculation_date;
	}
	public void setCalculation_date(String calculation_date) {
		this.calculation_date = calculation_date;
	}
	public String getCalculation_time() {
		return calculation_time;
	}
	public void setCalculation_time(String calculation_time) {
		this.calculation_time = calculation_time;
	}
	public Integer getCount_hour() {
		return count_hour;
	}
	public void setCount_hour(Integer count_hour) {
		this.count_hour = count_hour;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Double getOutpnl_area() {
		return outpnl_area;
	}
	public void setOutpnl_area(Double outpnl_area) {
		this.outpnl_area = outpnl_area;
	}
	public Double getOutcp_area() {
		return outcp_area;
	}
	public void setOutcp_area(Double outcp_area) {
		this.outcp_area = outcp_area;
	}
	public Double getWorktime() {
		return worktime;
	}
	public void setWorktime(Double worktime) {
		this.worktime = worktime;
	}
	public Double getSpendtime() {
		return spendtime;
	}
	public void setSpendtime(Double spendtime) {
		this.spendtime = spendtime;
	}
	public Double getStandard() {
		return standard;
	}
	public void setStandard(Double standard) {
		this.standard = standard;
	}
	public Integer getPeoples() {
		return peoples;
	}
	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	
}
