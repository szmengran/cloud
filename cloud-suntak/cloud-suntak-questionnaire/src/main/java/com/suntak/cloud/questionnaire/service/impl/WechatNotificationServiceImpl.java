package com.suntak.cloud.questionnaire.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.suntak.cloud.questionnaire.client.WechatClient;
import com.suntak.cloud.questionnaire.controller.QuestionnaireController;
import com.suntak.cloud.questionnaire.entity.T_questionnaire_evaluate;
import com.suntak.cloud.questionnaire.entity.other.Questionnaire;
import com.suntak.cloud.questionnaire.service.NotificationService;
import com.suntak.cloud.questionnaire.service.QuestionnaireService;
import com.suntak.cloud.wechat.entity.request.Text;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.szmengran.common.orm.dao.AbstractDao;

@Service
public class WechatNotificationServiceImpl implements NotificationService {
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);
	private final static ExecutorService executor = new ThreadPoolExecutor(50, 1000, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	@Autowired
	@Qualifier("oracleDao")
	AbstractDao abstractDao;
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Override
	public void send(String yearmonth) throws Exception {
		Boolean flag = questionnaireService.check(yearmonth);
		if (flag) {
			List<Questionnaire> questionnaireList = questionnaireService.findResult(yearmonth);
			int size = questionnaireList.size();
			for (int i=0; i<size; i++) {
				final int no = i;
				executor.submit(() -> {
					Questionnaire questionnaire = questionnaireList.get(no);
					try {
						String year = yearmonth.substring(0,4);
						String month = yearmonth.substring(4,6);
						StringBuilder msg = new StringBuilder();
						msg.append(questionnaire.getEmpname())
						   .append("：您好，您")
						   .append(year).append("年").append(month).append("月内部客户服务评价结果为")
						   .append(questionnaire.getAvgscore()).append("分，本次参与评价的人员共").append(size).append("人，您的得分排名为")
						   .append(no+1).append("名，您收到的评价反馈如下。评价结果将直接用于")
						   .append(year).append("年").append(month).append("月的绩效考评中，感谢您对集团绩效组的工作支持。\n");
						Object[] params = new Object[2];
						params[0] = yearmonth;
						params[1] = questionnaire.getUserid();
						List<T_questionnaire_evaluate> list = questionnaireService.findQuestion(params);
						if (list != null) {
							int index = 1;
							StringBuilder remarkSb = new StringBuilder();
							for (T_questionnaire_evaluate evaluate: list) {
								if (StringUtils.isNotBlank(evaluate.getRemark())) {
									remarkSb.append(index++).append(".").append(evaluate.getRemark()).append("\n");
								}
							}
							msg.append("一、1-5项评分反馈\n");
							if (remarkSb.length() > 0) {
								msg.append(remarkSb);
							} else {
								msg.append("无\n");
							}
							
							index = 1;
							StringBuilder questionSb = new StringBuilder();
							for (T_questionnaire_evaluate evaluate: list) {
								if (StringUtils.isNotBlank(evaluate.getQuestion())) {
									questionSb.append(index++).append(".").append(evaluate.getQuestion()).append("\n");
								}
							}
							msg.append("二、开放式问题反馈（问题：您认为被评价人本月工作中是否有需要调整或改进的部分？）\n");
							if (questionSb.length() > 0) {
								msg.append(questionSb);
							} else {
								msg.append("无\n");
							}
						} else {
							msg.append("一、1-5项评分反馈\n");
							msg.append("无\n");
							msg.append("二、开放式问题反馈（问题：您认为被评价人本月工作中是否有需要调整或改进的部分？）\n");
							msg.append("无\n");
						}
						TextRequestBody textRequestBody = new TextRequestBody();
						textRequestBody.setMsgtype("text");
						textRequestBody.setSafe(0);
						textRequestBody.setAgentid("1000028");
						Text text = new Text();
						text.setContent(msg.toString());
						textRequestBody.setText(text);
						textRequestBody.setTouser(questionnaire.getEmpcode());
						wechatClient.sendText("T0bECCL7__nAwvVUsnc60ogcOy5ZjSGv7oR2IFag8Ag", textRequestBody);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				});
			}
		}
	}

}
