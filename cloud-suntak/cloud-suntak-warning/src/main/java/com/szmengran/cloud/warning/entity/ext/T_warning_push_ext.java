package com.szmengran.cloud.warning.entity.ext;

import com.szmengran.cloud.warning.entity.T_warning_push;

/** 
 * @Package com.szmengran.cloud.warning.entity.ext 
 * @Description: 预警消息扩展实体
 * @date Mar 21, 2019 4:27:47 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_warning_push_ext extends T_warning_push {
    private String touser2;
    private String topart;
    private String totag;
    public String getTouser2() {
        return touser2;
    }
    public void setTouser2(String touser2) {
        this.touser2 = touser2;
    }
    public String getTopart() {
        return topart;
    }
    public void setTopart(String topart) {
        this.topart = topart;
    }
    public String getTotag() {
        return totag;
    }
    public void setTotag(String totag) {
        this.totag = totag;
    }
}
