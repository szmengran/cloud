package com.suntak.cloud.sms.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.suntak.cloud.sms.entity.T_sms_template;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.sms.mapper 
 * @Description: 短信模版数据库操作
 * @date May 18, 2019 11:42:55 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface TemplateMapper extends IMapper<T_sms_template>{

}
