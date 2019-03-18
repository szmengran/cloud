package com.suntak.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.szmengran.mybatis.utils.GeneratedValue;
import com.szmengran.mybatis.utils.GenerationType;
import com.szmengran.mybatis.utils.Table;

/**
 * @Package com.szmengran.common.entity
 * @Description: 短信日志信息
 * @date 2018年4月6日 下午3:01:19
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "id")
public class T_common_sms_log implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String phone;
	private String signname;
	private String templatecode;
	private String templateparam;
	private String outid;
	private String result;
	private Timestamp createstamp;
	private Timestamp updatestamp;
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
	public String getSignname() {
		return signname;
	}
	public void setSignname(String signname) {
		this.signname = signname;
	}
	public String getTemplatecode() {
		return templatecode;
	}
	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Timestamp getCreatestamp() {
		return createstamp;
	}
	public void setCreatestamp(Timestamp createstamp) {
		this.createstamp = createstamp;
	}
	public Timestamp getUpdatestamp() {
		return updatestamp;
	}
	public void setUpdatestamp(Timestamp updatestamp) {
		this.updatestamp = updatestamp;
	}

}
