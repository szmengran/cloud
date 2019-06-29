package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.mapper.TaskMapper;
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
	private TaskMapper<T_hr_attachment> taskMapper;
	
	@Override
	public void insert(T_hr_attachment t_hr_attachment) throws Exception {
	    taskMapper.insert(t_hr_attachment);
	}

}
