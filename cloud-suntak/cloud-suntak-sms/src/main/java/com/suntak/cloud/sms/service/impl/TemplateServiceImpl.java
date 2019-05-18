package com.suntak.cloud.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.sms.entity.T_sms_template;
import com.suntak.cloud.sms.mapper.TemplateMapper;
import com.suntak.cloud.sms.service.TemplateService;

/** 
 * @Package com.suntak.cloud.sms.service.impl 
 * @Description: TODO
 * @date May 18, 2019 11:44:49 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;
    
    public List<T_sms_template> findTemplates() throws Exception {
        return templateMapper.findByConditions(T_sms_template.class, null, null);
    }
}
