package com.suntak.cloud.recruitment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.EducationHistoryService;
import com.suntak.cloud.recruitment.service.WorkHistoryService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: TODO
 * @date 2018年8月13日 上午8:41:29
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class RecruitmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecruitmentController.class);
	
	@Autowired
	ApplicantService applicantService;
	
	@Autowired
	EducationHistoryService educationHistoryService;
	
	@Autowired
	WorkHistoryService workHistoryService;
	
	@GetMapping(value="/{applicantid}")
	public Response findByApplicantid(@PathVariable("applicantid") String applicantid) throws Exception {
		CompletableFuture<T_hr_applicant> future = CompletableFuture.supplyAsync(() -> {
			try {
				return applicantService.findById(applicantid);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            logger.error("findByApplicantid", e);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error("findByApplicantid", e);
	        }
			return null;
		});
		
		CompletableFuture<List<T_hr_educationhistory>> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				return educationHistoryService.findByApplicantid(applicantid);
			} catch (InterruptedException e) {
				e.printStackTrace();
	            logger.error("findByApplicantid", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("findByApplicantid", e);
			}
			return null;
		});
		
		CompletableFuture<List<T_hr_workhistory>> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				return workHistoryService.findByApplicantid(applicantid);
			} catch (InterruptedException e) {
				e.printStackTrace();
	            logger.error("findByApplicantid", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("findByApplicantid", e);
			}
			return null;
		});
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicant", future.get());
		map.put("educationhistory", future1.get());
		map.put("workhistory", future2.get());
		Response response = new Response();
		response.setData(map);
		return response;
	}
}
