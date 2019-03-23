package com.szmengran.cloud.warning.entity;

import java.sql.Timestamp;

import com.szmengran.mybatis.utils.Table;

/** 
 * @Package com.szmengran.cloud.warning.entity 
 * @Description: 预警消息实体
 * @date Mar 21, 2019 3:13:01 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Table(id = "pushid")
public class T_warning_push_history {
    private Long pushid;
    private String typeid;
    private String touser;
    private String msgtype;
    private Long orgid;
    private String wipid;
    private String message;
    private String Attribute1;
    private String Attribute2;
    private String Attribute3;
    private Timestamp createstamp;
    private Timestamp updatestamp;
    private String validstatus;
    public Long getPushid() {
        return pushid;
    }
    public void setPushid(Long pushid) {
        this.pushid = pushid;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public Long getOrgid() {
        return orgid;
    }
    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }
    public String getWipid() {
        return wipid;
    }
    public void setWipid(String wipid) {
        this.wipid = wipid;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getAttribute1() {
        return Attribute1;
    }
    public void setAttribute1(String attribute1) {
        Attribute1 = attribute1;
    }
    public String getAttribute2() {
        return Attribute2;
    }
    public void setAttribute2(String attribute2) {
        Attribute2 = attribute2;
    }
    public String getAttribute3() {
        return Attribute3;
    }
    public void setAttribute3(String attribute3) {
        Attribute3 = attribute3;
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
    public String getValidstatus() {
        return validstatus;
    }
    public void setValidstatus(String validstatus) {
        this.validstatus = validstatus;
    }
}
