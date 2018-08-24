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
import com.suntak.cloud.recruitment.entity.T_hr_contact;
import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.entity.T_hr_languageskills;
import com.suntak.cloud.recruitment.entity.T_hr_relatives;
import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.ContactService;
import com.suntak.cloud.recruitment.service.EducationHistoryService;
import com.suntak.cloud.recruitment.service.FamilyMemberService;
import com.suntak.cloud.recruitment.service.LanguageskillsService;
import com.suntak.cloud.recruitment.service.RelativesService;
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
	ApplicantService applicantService;
	
	@Autowired
	EducationHistoryService educationHistoryService;
	
	@Autowired
	WorkHistoryService workHistoryService;
	
	@Autowired
	RelativesService relativesService;
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	FamilyMemberService familyMemberService;
	
	@Autowired
	LanguageskillsService languageskillsService;
	
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
		Future<T_hr_relatives> relativesFuture = executor.submit(() -> {
			return relativesService.findByApplicantid(applicantid);
		});
		Future<T_hr_contact> contactFuture = executor.submit(() -> {
			return contactService.findByApplicantid(applicantid);
		});
		Future<List<T_hr_familymember>> familymemebersFuture = executor.submit(() -> {
			return familyMemberService.findByApplicantid(applicantid);
		});
		Future<T_hr_languageskills> languageskillsFuture = executor.submit(() -> {
			return languageskillsService.findByApplicantid(applicantid);
		});
		T_hr_applicant t_hr_applicant = applicantFuture.get();
		T_hr_educationhistory educationhistory =  educationhistoryFuture.get();
		List<T_hr_workhistory> works =  worksFuture.get();
		T_hr_relatives relatives =  relativesFuture.get();
		T_hr_contact contact =  contactFuture.get();
		List<T_hr_familymember> familymemebers =  familymemebersFuture.get();
		T_hr_languageskills languageskills =  languageskillsFuture.get();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicant", t_hr_applicant);
		map.put("educationhistory", educationhistory);
		map.put("works", works);
		map.put("relatives", relatives);
		map.put("contact", contact);
		map.put("familymemebers", familymemebers);
		map.put("languageskills", languageskills);
		Response response = new Response();
		response.setData(map);
		return response;
	}
}
