package com.suntak.cloud.oa.entity;

/** 
 * @Package com.suntak.cloud.oa.entity 
 * @Description: OA用户信息
 * @date Jul 8, 2019 3:59:31 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Oa_user_info {
    private String id;
    private String name;
    private String code;
    private String login_name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLogin_name() {
        return login_name;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
}
