package com.suntak.punch.entity;

import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.microservices.payment.entity
 * @Description: 消费记录表
 * @date Dec 24, 2018 1:55:19 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Cux_xf_info {
	private Timestamp xfposday; //消费时间
	private Float xfposmoney; //消费金额
	private String empno; //工号
	private String devid; //设备ID
	private Integer mealtypeid; //餐类型
	private Float xfcardmoney; //卡余额
	public Timestamp getXfposday() {
		return xfposday;
	}
	public void setXfposday(Timestamp xfposday) {
		this.xfposday = xfposday;
	}
	public Float getXfposmoney() {
		return xfposmoney;
	}
	public void setXfposmoney(Float xfposmoney) {
		this.xfposmoney = xfposmoney;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public Integer getMealtypeid() {
		return mealtypeid;
	}
	public void setMealtypeid(Integer mealtypeid) {
		this.mealtypeid = mealtypeid;
	}
	public Float getXfcardmoney() {
		return xfcardmoney;
	}
	public void setXfcardmoney(Float xfcardmoney) {
		this.xfcardmoney = xfcardmoney;
	}
}
