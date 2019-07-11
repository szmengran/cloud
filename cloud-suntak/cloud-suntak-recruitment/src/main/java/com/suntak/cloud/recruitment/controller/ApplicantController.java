package com.suntak.cloud.recruitment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.entity.T_hr_resume;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.AttachmentService;
import com.suntak.cloud.recruitment.service.ResumeService;
import com.suntak.exception.model.Response;


/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写员工基本信息
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class ApplicantController {
	
    private static final ExecutorService executor = new ThreadPoolExecutor(20, 200, 3L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private ResumeService resumeService;
    
    @Autowired
    private AttachmentService attachmentService;
	
	@PostMapping(value="/applicant/{applicantid}")
	public Response fillInBaseInfo(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_applicant t_hr_applicant) throws Exception {
		t_hr_applicant.setApplicantid(applicantid);
		int num = applicantService.updateBaseInfo(t_hr_applicant);
		if(num == 0) {
			applicantService.insert(t_hr_applicant);
		}
		return new Response();
	}
	
	@GetMapping(value="/applicant/{applicantid}")
	public Response findById(@PathVariable("applicantid") String applicantid) throws Exception {
	    Future<T_hr_attachment> attachment = executor.submit(() -> {
	        return attachmentService.findById(applicantid);
	    });
	    Future<List<T_hr_resume>> resumes = executor.submit(() -> {
	        return resumeService.findByApplicantid(applicantid);
	    });
		T_hr_applicant t_hr_applicant = applicantService.findById(applicantid);
		Map<String, Object> map = new HashMap<>();
		map.put("attachment", attachment.get());
		map.put("resume", resumes.get());
		map.put("applicant", t_hr_applicant);
		Response response = new Response();
		response.setData(map);
		return response;
	}
	
}
