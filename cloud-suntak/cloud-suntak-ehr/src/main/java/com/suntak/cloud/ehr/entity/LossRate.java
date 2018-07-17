package com.suntak.cloud.ehr.entity;
/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: 流失率
 * @date 2018年7月16日 上午10:27:06
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class LossRate {
	private String deptname;
	private String kename;
	private Integer startmonth;
	private Integer afterdaymonth;
	private Integer betweendaymonth; //在这期间离职的员工数量
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getKename() {
		return kename;
	}
	public void setKename(String kename) {
		this.kename = kename;
	}
	public Integer getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(Integer startmonth) {
		this.startmonth = startmonth;
	}
	public Integer getAfterdaymonth() {
		return afterdaymonth;
	}
	public void setAfterdaymonth(Integer afterdaymonth) {
		this.afterdaymonth = afterdaymonth;
	}
	public Integer getBetweendaymonth() {
		return betweendaymonth;
	}
	public void setBetweendaymonth(Integer betweendaymonth) {
		this.betweendaymonth = betweendaymonth;
	}
}
