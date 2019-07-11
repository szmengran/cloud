package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_resume;
import com.suntak.cloud.recruitment.mapper.ResumeMapper;
import com.suntak.cloud.recruitment.service.ResumeService;

/** 
 * @Package com.suntak.cloud.recruitment.service.impl 
 * @Description: 简历管理
 * @date Jul 10, 2019 9:16:10 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;
    
    @Override
    public Boolean delete(String applicantid, String resume) throws Exception {
        T_hr_resume t_hr_resume = new T_hr_resume();
        t_hr_resume.setApplicantid(applicantid);
        t_hr_resume.setResume(resume);
        return resumeMapper.delete(t_hr_resume) > 0;
    }

    @Override
    public Boolean insert(T_hr_resume t_hr_resume) throws Exception {
        t_hr_resume.setCreatestamp(new Timestamp(System.currentTimeMillis()));
        return resumeMapper.insert(t_hr_resume) > 0;
    }

    @Override
    public List<T_hr_resume> findByApplicantid(String applicantid) throws Exception {
        return resumeMapper.findByApplicantid(applicantid);
    }

}
