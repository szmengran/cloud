package com.suntak.cloud.activity.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class T_activity_service implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9824326432798749L;
	private Integer service_id;
	private String avatar;
	private String title;
	private String content;
	private String url;
	private Integer month;
	private Integer day;
	private String roomid;
	private Integer type;
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp createstamp;
	private Timestamp updatestamp;
	private String vaildstatus;
	public Integer getService_id() {
		return service_id;
	}
	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
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
	public String getVaildstatus() {
		return vaildstatus;
	}
	public void setVaildstatus(String vaildstatus) {
		this.vaildstatus = vaildstatus;
	}
	
}
