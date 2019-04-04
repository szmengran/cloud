package com.suntak.cloud.sms.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.suntak.cloud.sms.entity 
 * @Description: 公共短信发送接口表
 * @date Mar 5, 2019 4:47:48 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class T_sms_info {
	private Integer id;
	private String phone;
	private String templatecode;
	private String signname;
	private String templateparam;
	private String outid;
	private Timestamp autosendtime;
	private Timestamp createstamp;
	private String validstatus;
	private String exception;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTemplatecode() {
		return templatecode;
	}
	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}
	public String getSignname() {
		return signname;
	}
	public void setSignname(String signname) {
		this.signname = signname;
	}
	public String getTemplateparam() {
		return templateparam;
	}
	public void setTemplateparam(String templateparam) {
		this.templateparam = templateparam;
	}
	public String getOutid() {
		return outid;
	}
	public void setOutid(String outid) {
		this.outid = outid;
	}
	public Timestamp getAutosendtime() {
        return autosendtime;
    }
    public void setAutosendtime(Timestamp autosendtime) {
        this.autosendtime = autosendtime;
    }
    public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
    public String getException() {
        return exception;
    }
    public void setException(String exception) {
        this.exception = exception;
    }
}
