package com.suntak.cloud.oa.service;
/** 
 * @Package com.suntak.cloud.oa.service 
 * @Description: TODO
 * @date Jun 13, 2019 2:22:26 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public interface OaRecruitmentService {

    public Boolean launchFormCollaboration(String submitMemberLoginName,String formData,String formNo,String attachments,long[] attachmentIds) throws Exception;
}
