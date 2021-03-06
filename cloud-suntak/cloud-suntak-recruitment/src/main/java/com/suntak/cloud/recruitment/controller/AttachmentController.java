package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.service.AttachmentService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写附件信息
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class AttachmentController {
	
	@Autowired
	AttachmentService attachmentService;
	
	@PostMapping(value="/attachment")
	public Response insert(@RequestBody T_hr_attachment t_hr_attachment) throws Exception {
		attachmentService.insert(t_hr_attachment);
		return new Response();
	}
	
}
