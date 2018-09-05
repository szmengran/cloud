package com.suntak.cloud.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.WorkHistoryService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: 填写工作经历
 * @date 2018年7月19日 下午1:06:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class WorkHistoryController {
	
	@Autowired
	WorkHistoryService workHistoryService;
	
	@PostMapping(value="/workHistory/{applicantid}")
	public Response fillInWorkHistory(@PathVariable("applicantid") String applicantid, @RequestBody T_hr_workhistory t_hr_workhistory) throws Exception {
		t_hr_workhistory.setApplicantid(applicantid);
		workHistoryService.saveOrUpdate(t_hr_workhistory);
		return new Response();
	}

	@GetMapping(value="/workHistory/{applicantid}")
	public Response findWorkByApplicantId(@PathVariable("applicantid") String applicantid) throws Exception {
		List<T_hr_workhistory> t_hr_workhistorys = workHistoryService.findByApplicantid(applicantid);
		Response response = new Response();
		response.setData(t_hr_workhistorys);
		return response;
	}
	
	@DeleteMapping(value="/workHistory/{workhistoryid}")
	public Response delete(@PathVariable("workhistoryid") Integer workhistoryid) throws Exception {
		workHistoryService.delete(workhistoryid);
		return new Response();
	}
}
