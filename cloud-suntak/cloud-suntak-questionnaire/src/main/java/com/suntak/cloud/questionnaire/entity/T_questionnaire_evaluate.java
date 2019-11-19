package com.suntak.cloud.questionnaire.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Package com.suntak.cloud.questionnaire.entity
 * @Description: 评价表
 * @date 2018年4月18日 下午3:27:49
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_questionnaire_evaluate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer evaluateid;
	private Integer userid;
	private Integer customerid;
	private String yearmonth;
	private Double attribute_1;
	private Double attribute_2;
	private Double attribute_3;
	private Double attribute_4;
	private Double attribute_5;
	private Double total;
	private Integer status;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private String question;
	private String remark;
	
	public Integer getEvaluateid() {
		return evaluateid;
	}
	public void setEvaluateid(Integer evaluateid) {
		this.evaluateid = evaluateid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public Double getAttribute_1() {
		return attribute_1;
	}
	public void setAttribute_1(Double attribute_1) {
		this.attribute_1 = attribute_1;
	}
	public Double getAttribute_2() {
		return attribute_2;
	}
	public void setAttribute_2(Double attribute_2) {
		this.attribute_2 = attribute_2;
	}
	public Double getAttribute_3() {
		return attribute_3;
	}
	public void setAttribute_3(Double attribute_3) {
		this.attribute_3 = attribute_3;
	}
	public Double getAttribute_4() {
		return attribute_4;
	}
	public void setAttribute_4(Double attribute_4) {
		this.attribute_4 = attribute_4;
	}
	public Double getAttribute_5() {
		return attribute_5;
	}
	public void setAttribute_5(Double attribute_5) {
		this.attribute_5 = attribute_5;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
