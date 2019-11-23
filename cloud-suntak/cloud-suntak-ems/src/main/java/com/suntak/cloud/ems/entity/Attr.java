package com.suntak.cloud.ems.entity;
/**
 * @Package com.suntak.cloud.ehr.entity
 * @Description: 外部字段
 * @date Dec 22, 2018 3:13:32 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Attr {
	private String name;
	private String value;
	private Integer type;
	private Text text;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
}
