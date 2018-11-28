package com.suntak.cloud.microservices.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.microservices.utils.Constants;
import com.google.gson.Gson;
import com.suntak.cloud.microservices.client.ProofOfIncomeClient;
import com.suntak.cloud.microservices.client.WechatClient;
import com.suntak.cloud.microservices.entity.ErrorNotification;
import com.suntak.cloud.microservices.entity.ProofOfIncome;
import com.suntak.cloud.microservices.entity.ProofOfIncomeResponse;
import com.suntak.cloud.microservices.utils.WechatErrorNotification;
import com.suntak.cloud.wechat.entity.MsgRequestBody;
import com.suntak.cloud.wechat.entity.Textcard;
import com.suntak.exception.model.Response;
import com.szmengran.utils.MD5Util;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.microservices.controller
 * @Description: 收入证明服务
 * @date Nov 28, 2018 1:35:42 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "microservices")
@RestController
@RequestMapping(path = "/api/v1/microservices", produces = { "application/json" })
public class ProofController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProofController.class);
	
	@Value("${wechat.qy.AgentId}")
	private String agentId;
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Value("${wechat.notification.toUser}")
	private String principal;
	
	@Autowired
	private ProofOfIncomeClient proofOfIncomeClient;
	
	@Autowired
	private WechatClient wechatClient;
	
	@PostMapping("/proofOfIncome")
	public Response applyProofOfIncome(@RequestBody ProofOfIncome proofOfIncome) throws Exception {
		logger.info(new Gson().toJson(proofOfIncome));
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> {
			try{
				String code = "EMP_INCOME";
				String sert = MD5Util.MD5Encode(code+"."+proofOfIncome.getEmp_code(), "utf-8");
				String json = proofOfIncomeClient.apply(code, proofOfIncome.getEmp_code(), sert, proofOfIncome.getReason(), proofOfIncome.getTax_flag());
				ProofOfIncomeResponse proofOfIncomeResponse = new Gson().fromJson(json, ProofOfIncomeResponse.class);
				String url = proofOfIncomeResponse.getUrl();
				qywechatNotification(proofOfIncome.getEmp_code(), url);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				ErrorNotification errorNotification = new ErrorNotification();
				errorNotification.setAgentId(agentId);
				errorNotification.setContent(Constants.ASSISTANT_NAME+"很遗憾通知你，你申请的收入证明文件没有通过，请重新申请！");
				errorNotification.setTitle("收入证明");
				errorNotification.setToUser(proofOfIncome.getEmp_code());
				errorNotification.setPrincipal(principal);
				errorNotification.setError(e.getMessage());
				try {
					WechatErrorNotification.sendError(wechatClient, errorNotification);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error(e1.getMessage());
				}
			}
		});
		return new Response();
	}
	
	/**
	 * 收入证明企业微信通知
	 * @param toUser
	 * @param url
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private Response qywechatNotification(String toUser, String url) throws Exception {
		MsgRequestBody msgRequestBody = new MsgRequestBody();
		msgRequestBody.setTouser(toUser);
		msgRequestBody.setMsgtype("textcard");
		Textcard textcard = new Textcard();
		textcard.setTitle("收入证明");
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"gray\">")
		  .append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()))
		  .append("</div>")
		  .append("<div class=\"normal\">")
		  .append("你好，你申请的收入证明文件已经生成，请下载！")
		  .append(Constants.ASSISTANT_NAME)
		  .append("很高兴为你服务！")
		  .append("</div>");
		textcard.setDescription(sb.toString());
		textcard.setUrl(url);
		textcard.setBtntxt("下载");
		msgRequestBody.setTextcard(textcard);
		msgRequestBody.setAgentid(agentId);
		msgRequestBody.setSecret(secret);
		return wechatClient.sendTextcard(msgRequestBody);
	}
}
