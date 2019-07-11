package com.suntak.cloud.recruitment.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.mapper.AttachmentMapper;
import com.suntak.cloud.recruitment.service.AttachmentService;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: TODO
 * @date 2018年7月19日 下午2:22:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Override
	public Boolean insert(T_hr_attachment t_hr_attachment) throws Exception {
	    Timestamp current = new Timestamp(System.currentTimeMillis());
	    t_hr_attachment.setCreatestamp(current);
	    t_hr_attachment.setUpdatestamp(current);
	    return attachmentMapper.insert(t_hr_attachment) > 0;
	}

    @Override
    public Boolean update(T_hr_attachment t_hr_attachment) throws Exception {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        t_hr_attachment.setUpdatestamp(current);
        return attachmentMapper.update(t_hr_attachment) > 0;
    }

    @Override
    public T_hr_attachment findById(String applicantid) throws Exception {
        T_hr_attachment t_hr_attachment = new T_hr_attachment();
        t_hr_attachment.setApplicantid(applicantid);
        return attachmentMapper.findById(t_hr_attachment);
    }

}
