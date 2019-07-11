package com.suntak.cloud.recruitment.service;
/** 
 * @Package com.suntak.cloud.recruitment.service 
 * @Description: TODO
 * @date Jul 8, 2019 4:54:24 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface OaService {

    /**
     * 根据工号查找登录名
     * @param code
     * @return
     * @throws Exception      
     * @return: String      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    String findLoginNameByCode(String code) throws Exception;
}
