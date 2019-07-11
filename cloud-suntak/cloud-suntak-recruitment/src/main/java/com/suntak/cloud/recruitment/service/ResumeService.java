package com.suntak.cloud.recruitment.service;

import java.util.List;

import com.suntak.cloud.recruitment.entity.T_hr_resume;

/** 
 * @Package com.suntak.cloud.recruitment.service 
 * @Description: 简历服务
 * @date Jul 10, 2019 9:08:15 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface ResumeService {

    /**
     * 删除简历
     * @param applicantid
     * @param resume
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Boolean delete(String applicantid, String resume) throws Exception;
    
    /**
     * 新增简历
     * @param t_hr_resume
     * @return
     * @throws Exception      
     * @return: Boolean      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    Boolean insert(T_hr_resume t_hr_resume) throws Exception;
    
    /**
     * 根据applicantid查找简历
     * @param applicantid
     * @return
     * @throws Exception      
     * @return: List<T_hr_resume>      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    List<T_hr_resume> findByApplicantid(String applicantid) throws Exception;
}
