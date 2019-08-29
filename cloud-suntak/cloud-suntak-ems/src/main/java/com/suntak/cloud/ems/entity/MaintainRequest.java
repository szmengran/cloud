package com.suntak.cloud.ems.entity;
/** 
 * @Package com.suntak.cloud.ems.entity 
 * @Description: TODO
 * @date Aug 28, 2019 10:00:04 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class MaintainRequest {
    private Ems_dm_maintain ems_dm_maintain;
    private Ems_dm_maintain_content[] ems_dm_maintain_content;

    public Ems_dm_maintain getEms_dm_maintain() {
        return ems_dm_maintain;
    }

    public void setEms_dm_maintain(Ems_dm_maintain ems_dm_maintain) {
        this.ems_dm_maintain = ems_dm_maintain;
    }

    public Ems_dm_maintain_content[] getEms_dm_maintain_content() {
        return ems_dm_maintain_content;
    }

    public void setEms_dm_maintain_content(Ems_dm_maintain_content[] ems_dm_maintain_content) {
        this.ems_dm_maintain_content = ems_dm_maintain_content;
    }
    
}
