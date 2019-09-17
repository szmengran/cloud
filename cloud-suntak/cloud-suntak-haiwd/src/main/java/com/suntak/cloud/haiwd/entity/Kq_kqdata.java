package com.suntak.cloud.haiwd.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

@Table(id="guid")
public class Kq_kqdata {
	private String guid;
	private String empsysid;
	private String kqdate;
	private Integer kqtime;
	private String iskeyin;
	private String iskoukuan;
	private String remark;
	private String resultmark;
	private String ismodified;
	private Integer devid;
	private String keyinoprtno;
	private Timestamp keyinday;
	private String lotnoguid;
	private Integer cardid;
	private Integer readerid;
	private String isinvalid;
	private Integer synchflagid;
	private Integer runnoflagdays;
	private String isnorange;
	private Timestamp synchflagday;
	private Timestamp kqdatetime;
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getEmpsysid() {
		return empsysid;
	}
	public void setEmpsysid(String empsysid) {
		this.empsysid = empsysid;
	}
	public String getKqdate() {
		return kqdate;
	}
	public void setKqdate(String kqdate) {
		this.kqdate = kqdate;
	}
	public Integer getKqtime() {
		return kqtime;
	}
	public void setKqtime(Integer kqtime) {
		this.kqtime = kqtime;
	}
	public String getIskeyin() {
		return iskeyin;
	}
	public void setIskeyin(String iskeyin) {
		this.iskeyin = iskeyin;
	}
	public String getIskoukuan() {
		return iskoukuan;
	}
	public void setIskoukuan(String iskoukuan) {
		this.iskoukuan = iskoukuan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResultmark() {
		return resultmark;
	}
	public void setResultmark(String resultmark) {
		this.resultmark = resultmark;
	}
	public String getIsmodified() {
		return ismodified;
	}
	public void setIsmodified(String ismodified) {
		this.ismodified = ismodified;
	}
	public Integer getDevid() {
		return devid;
	}
	public void setDevid(Integer devid) {
		this.devid = devid;
	}
	public String getKeyinoprtno() {
		return keyinoprtno;
	}
	public void setKeyinoprtno(String keyinoprtno) {
		this.keyinoprtno = keyinoprtno;
	}
	public Timestamp getKeyinday() {
		return keyinday;
	}
	public void setKeyinday(Timestamp keyinday) {
		this.keyinday = keyinday;
	}
	public String getLotnoguid() {
		return lotnoguid;
	}
	public void setLotnoguid(String lotnoguid) {
		this.lotnoguid = lotnoguid;
	}
	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}
	public Integer getReaderid() {
		return readerid;
	}
	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}
	public String getIsinvalid() {
		return isinvalid;
	}
	public void setIsinvalid(String isinvalid) {
		this.isinvalid = isinvalid;
	}
	public Integer getSynchflagid() {
		return synchflagid;
	}
	public void setSynchflagid(Integer synchflagid) {
		this.synchflagid = synchflagid;
	}
	public Integer getRunnoflagdays() {
		return runnoflagdays;
	}
	public void setRunnoflagdays(Integer runnoflagdays) {
		this.runnoflagdays = runnoflagdays;
	}
	public String getIsnorange() {
		return isnorange;
	}
	public void setIsnorange(String isnorange) {
		this.isnorange = isnorange;
	}
	public Timestamp getSynchflagday() {
		return synchflagday;
	}
	public void setSynchflagday(Timestamp synchflagday) {
		this.synchflagday = synchflagday;
	}
	public Timestamp getKqdatetime() {
		return kqdatetime;
	}
	public void setKqdatetime(Timestamp kqdatetime) {
		this.kqdatetime = kqdatetime;
	}
	
}
