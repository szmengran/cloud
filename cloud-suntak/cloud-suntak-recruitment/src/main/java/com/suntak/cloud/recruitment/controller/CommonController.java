package com.suntak.cloud.recruitment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.entity.T_hr_resume;
import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.AttachmentService;
import com.suntak.cloud.recruitment.service.EducationHistoryService;
import com.suntak.cloud.recruitment.service.FamilyMemberService;
import com.suntak.cloud.recruitment.service.ResumeService;
import com.suntak.cloud.recruitment.service.WorkHistoryService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: TODO
 * @date 2018年8月23日 上午9:37:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class CommonController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private EducationHistoryService educationHistoryService;
	
	@Autowired
	private WorkHistoryService workHistoryService;
	
	@Autowired
	private FamilyMemberService familyMemberService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping(value="/common/{applicantid}")
	public Response findByApplicantid(@PathVariable("applicantid") final String applicantid) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<T_hr_applicant> applicantFuture = executor.submit(() -> { //Lambda 是一个 callable， 提交后便立即执行，这里返回的是 FutureTask 实例
            return applicantService.findById(applicantid);
        });
		Future<T_hr_educationhistory> educationhistoryFuture = executor.submit(() -> {
			return educationHistoryService.findByApplicantid(applicantid);
		});
		Future<List<T_hr_workhistory>> worksFuture = executor.submit(() -> {
			return workHistoryService.findByApplicantid(applicantid);
		});
		Future<List<T_hr_familymember>> familymemebersFuture = executor.submit(() -> {
			return familyMemberService.findByApplicantid(applicantid);
		});
		Future<List<T_hr_resume>> resumeFuture = executor.submit(() -> {
            return resumeService.findByApplicantid(applicantid);
        });
        Future<T_hr_attachment> attachmentFuture = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
		T_hr_applicant t_hr_applicant = applicantFuture.get();
		T_hr_educationhistory educationhistory =  educationhistoryFuture.get();
		List<T_hr_workhistory> works =  worksFuture.get();
		List<T_hr_familymember> familymemebers =  familymemebersFuture.get();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicant", t_hr_applicant);
		map.put("educationhistory", educationhistory);
		map.put("works", works);
		map.put("familymemebers", familymemebers);
		map.put("resume", resumeFuture.get());
		map.put("attachment", attachmentFuture.get());
		Response response = new Response();
		response.setData(map);
		return response;
	}
}
