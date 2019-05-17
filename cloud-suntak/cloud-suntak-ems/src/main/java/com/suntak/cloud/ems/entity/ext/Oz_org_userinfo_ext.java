package com.suntak.cloud.ems.entity.ext;

import com.suntak.cloud.ems.entity.Oz_org_userinfo;

/** 
 * @Package com.suntak.cloud.ems.entity.ext 
 * @Description: TODO
 * @date May 15, 2019 11:47:10 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Oz_org_userinfo_ext extends Oz_org_userinfo{

    /**
     * 
     */
    private static final long serialVersionUID = -5291913271549123949L;
    private Integer org_id;

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }
    
}
