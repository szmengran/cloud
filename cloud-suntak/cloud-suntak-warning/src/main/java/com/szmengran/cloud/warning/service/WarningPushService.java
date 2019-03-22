package com.szmengran.cloud.warning.service;

import java.util.List;

import com.szmengran.cloud.warning.entity.ext.T_warning_push_ext;

/** 
 * @Package com.szmengran.cloud.warning.service 
 * @Description: 预警信息服务
 * @date Mar 21, 2019 3:37:09 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface WarningPushService {

    /**
     * 搜索预警推送消息
     * @param validstatus
     * @return
     * @throws Exception      
     * @return: List<T_warning_push_ext>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<T_warning_push_ext> searchWarningPush(String validstatus) throws Exception;
    
    /**
     * 发送异常消息状态会写
     * @param exception
     * @param pushid
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Boolean updateWarningPush(String exception, Long pushid) throws Exception;
    
    /**
     * 发送成功的预警消息将转移到历史表中
     * @param t_warning_push_ext
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Boolean move(T_warning_push_ext t_warning_push_ext) throws Exception;
}
