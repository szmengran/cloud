package com.suntak.cloud.microservices.entity;
/**
 * @Package com.suntak.cloud.microservices.entity
 * @Description: 收入证明
 * @date Nov 28, 2018 1:48:58 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ProofOfIncome {
	private String code;
	private String sert;
	private String reason;
	private String tax_flag;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSert() {
		return sert;
	}
	public void setSert(String sert) {
		this.sert = sert;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTax_flag() {
		return tax_flag;
	}
	public void setTax_flag(String tax_flag) {
		this.tax_flag = tax_flag;
	}
}
