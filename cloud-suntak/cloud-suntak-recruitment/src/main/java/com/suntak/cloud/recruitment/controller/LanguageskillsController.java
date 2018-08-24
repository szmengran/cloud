package com.suntak.cloud.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_languageskills;
import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.LanguageskillsService;
import com.suntak.cloud.recruitment.service.TaskService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写语言能力
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class LanguageskillsController {
	
	@Value("${task.interview.startrole}")
	private String startrole;
	@Value("${task.interview.workflowid}")
	private Integer workflowid;
	
	@Autowired
	LanguageskillsService languageskillsService;
	
	@Autowired
	ApplicantService applicantService;
	
	@Autowired
	TaskService taskService;
	
	@PostMapping(value="/languageskills/{applicantid}")
	public Response saveOrUpdate(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_languageskills t_hr_languageskills) throws Exception {
		t_hr_languageskills.setApplicantid(applicantid);
		languageskillsService.saveOrUpdate(t_hr_languageskills);
		new Thread(new Runnable() {
			@Override
			public void run() {
				T_hr_applicant t_hr_applicant;
				try {
					t_hr_applicant = applicantService.findById(applicantid);
					T_hr_task t_hr_task = new T_hr_task();
					t_hr_task.setAssignrole(startrole);
					t_hr_task.setWorkflowid(workflowid);
					t_hr_task.setApplicantid(applicantid);
					t_hr_task.setTitle(new StringBuilder(t_hr_applicant.getName()).append("-").append(t_hr_applicant.getPhone()).toString());
					taskService.insert(t_hr_task);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		return new Response();
	}

	@GetMapping(value="/languageskills/{applicantid}")
	public Response find(@PathVariable("applicantid") String applicantid) throws Exception {
		T_hr_languageskills t_hr_languageskills = languageskillsService.findByApplicantid(applicantid);
		Response response = new Response();
		response.setData(t_hr_languageskills);
		return response;
	}
	
}
