package com.suntak.cloud.microservices.entity.ext;

import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * @Package com.suntak.cloud.microservices.entity.ext 
 * @Description: 用户信息
 * @date Mar 20, 2019 2:48:49 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class UserInfo {

    @JsonProperty("UserId")
    private String userId;
    private String avatar;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
