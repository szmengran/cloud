package com.suntak.cloud.microservices.entity;
/**
 * @Package com.suntak.cloud.microservices.entity
 * @Description: TODO
 * @date Nov 28, 2018 1:57:02 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ProofOfIncomeResponse {
	private String msg;
	private String url;
	private String status;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
