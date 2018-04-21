package com.suntak.ehr.entity;
/**
 * @Package com.suntak.ehr.entity
 * @Description: 问卷调查短信模型
 * @date 2018年4月21日 下午1:57:01
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Questionnaire_sms {
	private String name;
	private String year;
	private String month;
	private Double score;
	private Integer num;
	private Integer rank;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
