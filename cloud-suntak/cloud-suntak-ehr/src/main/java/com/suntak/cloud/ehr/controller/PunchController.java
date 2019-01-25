package com.suntak.cloud.ehr.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.ehr.client.PunchClient;
import com.suntak.cloud.ehr.client.WechatClient;
import com.suntak.cloud.ehr.service.MicroservicesSettingService;
import com.suntak.cloud.ehr.utils.Constants;
import com.suntak.cloud.wechat.entity.request.Textcard;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.ehr.controller
 * @Description: 考勤打卡提醒
 * @date Jan 15, 2019 3:50:01 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "ehr")
@RestController
@RequestMapping(path = "/api/v1/ehr", produces = { "application/json" })
public class PunchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PunchController.class);
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(100);
	public static final String ASSISTANT_NAME = "【崇达小助手】";
	
	@Value("${wechat.qy.AgentId}")
	private String agentId;
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Value("${wechat.punchSetting.url}")
	private String url;
	
	@Autowired
	private PunchClient punchClient;
	
	@Autowired
	private WechatClient wechatClient;

	@Autowired
	private MicroservicesSettingService microservicesSettingService;
	
	@GetMapping("/punch/{time}/{minute}/{scanSecond}")
	public Response findWorkPunch(@PathVariable("time") int time, @PathVariable("minute") int minute, @PathVariable("scanSecond") int scanSecond) throws Exception {
		Future<List<String>> futureList = EXECUTOR.submit(() -> {
			return microservicesSettingService.findSettingByType(Constants.SETTING_TYPE_PUNCH);
		});
		Response response = punchClient.findPunch(time, minute, scanSecond);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) response.getData();
		List<String> settingList = futureList.get();
		list.removeAll(settingList);
		EXECUTOR.submit(() -> {
			if (list != null && list.size() > 0) {
				try {
					if (list.size() <= 500) {
						StringBuilder users = new StringBuilder();
						for (String empno: list) {
							users.append("|").append(empno);
						}
						users = users.deleteCharAt(0);
						sendTextcard(users.toString(), time);
					} else {
						int part = list.size() / 500;//分批数
						for (int i = 0; i < part; i++) {
		                    List<String> listPage = list.subList(0, 500);
		                    StringBuilder users = new StringBuilder();
							for (String empno: listPage) {
								users.append("|").append(empno);
							}
							users = users.deleteCharAt(0);
							sendTextcard(users.toString(), time);
							list.subList(0, 500).clear();
		                }
					}
					
				} catch (Exception e) {
					LOG.error(e.getMessage());
				}
			}
		});
		return new Response();
	}
	
	public static void main(String args[]) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add("index"+i);
		}
		int part = list.size() / 2;//分批数
		for (int i = 0; i < part; i++) {
            List<String> listPage = list.subList(0, 2);
            StringBuilder users = new StringBuilder();
			for (String empno: listPage) {
				users.append("|").append(empno);
			}
			users = users.deleteCharAt(0);
			System.out.println(users.toString());
			list.subList(0, 2).clear();
        }
	}
	
	private void sendTextcard(String users, int time) {
		String title = "快上班啦，你似乎还没有打卡啊！";
		if (time % 2 == 0) {
			title = "下班啦，别忘了打卡啊！";
		}
		TextcardRequestBody textcardRequestBody = new TextcardRequestBody();
		textcardRequestBody.setAgentid(agentId);
		textcardRequestBody.setTouser(users);
		Textcard textcard = new Textcard();
		textcard.setTitle(title);
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"gray\">")
		  .append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()))
		  .append("</div>")
		  .append("<div class=\"normal\">")
		  .append("如需取消打卡提醒，请点击设置按钮！")
		  .append(ASSISTANT_NAME)
		  .append("很高兴为你服务！")
		  .append("</div>");
		textcard.setDescription(sb.toString());
		textcard.setUrl(url);
		textcard.setBtntxt("设置");
		textcardRequestBody.setTextcard(textcard);
		wechatClient.sendTextcard(secret, textcardRequestBody);
	}
}
