package com.suntak.cloud.microservices.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.suntak.cloud.microservices.client.WechatClient;
import com.suntak.cloud.microservices.entity.ErrorNotification;
import com.suntak.cloud.wechat.entity.request.Text;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.Textcard;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.microservices.utils
 * @Description: 企业微信错误通知
 * @date Nov 28, 2018 2:46:50 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class WechatErrorNotification {

	/**
	 * 通知用户请求失败
	 * @param wechatClient
	 * @param errorNotication
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public static Response sendError(WechatClient wechatClient, ErrorNotification errorNotification) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> {
			return notificationPrincipal(wechatClient, errorNotification);
		});
		
		TextRequestBody textRequestBody = new TextRequestBody();
		textRequestBody.setTouser(errorNotification.getToUser());
		textRequestBody.setMsgtype("text");
		Text text = new Text(errorNotification.getContent());
		textRequestBody.setText(text);
		textRequestBody.setAgentid(errorNotification.getAgentId());
		return wechatClient.sendText(errorNotification.getSecret(), textRequestBody);
	}
	
	/**
	 * 发送错误通知给服务负责人
	 * @param wechatClient
	 * @param errorNotication
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	public static Response notificationPrincipal(WechatClient wechatClient, ErrorNotification errorNotification) throws Exception {
		TextcardRequestBody msgRequestBody = new TextcardRequestBody();
		msgRequestBody.setTouser(errorNotification.getPrincipal());
		msgRequestBody.setMsgtype("textcard");
		Textcard textcard = new Textcard();
		textcard.setTitle(errorNotification.getTitle());
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"gray\">")
		  .append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()))
		  .append("</div>")
		  .append("<div class=\"normal\">")
		  .append(errorNotification.getError())
		  .append("</div>");
		textcard.setDescription(sb.toString());
		textcard.setUrl(errorNotification.getUrl());
		msgRequestBody.setTextcard(textcard);
		msgRequestBody.setAgentid(errorNotification.getAgentId());
		return wechatClient.sendTextcard(errorNotification.getSecret(), msgRequestBody);
	}
}
