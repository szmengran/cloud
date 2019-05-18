package com.suntak.cloud.sms.service;

import java.util.List;

import com.suntak.cloud.sms.entity.T_sms_template;

/** 
 * @Package com.suntak.cloud.sms.service 
 * @Description: TODO
 * @date May 18, 2019 11:43:41 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface TemplateService {

    /**
     * 查找模版信息
     * @return
     * @throws Exception      
     * @return: List<T_sms_template>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<T_sms_template> findTemplates() throws Exception;
}
