package com.suntak.cloud.wechat.entity;
/** 
 * @Package com.suntak.cloud.wechat.entity 
 * @Description: 用户授权信息
 * @date Mar 18, 2019 3:23:03 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ResponseUserInfo {
    private Integer errcode;
    private String errmsg;
    public Integer getErrcode() {
        return errcode;
    }
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
