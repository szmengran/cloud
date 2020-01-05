package com.suntak.push.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.suntak.cloud.wechat.entity.request.Articles;
import com.suntak.cloud.wechat.entity.request.News;
import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.push.client.WechatClient;
import com.suntak.push.entity.CuxSoaWipPush;
import com.suntak.push.entity.RobotResponse;
import com.suntak.push.entity.TPushRobot;
import com.suntak.push.entity.ext.CuxSoaWipPushExt;
import com.suntak.push.mapper.CuxSoaWipPushMapper;
import com.suntak.push.service.WipPushService;

@Service
public class WipPushServiceImpl implements WipPushService {

	private static final Logger logger = LoggerFactory.getLogger(MiPushServiceImpl.class);
	
	@Autowired
	private CuxSoaWipPushMapper CuxSoaWipPushMapper;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Override
	public void send() {
		List<TPushRobot> robots = CuxSoaWipPushMapper.findRobot();
		for (TPushRobot robot: robots) {
			CuxSoaWipPushExt cuxSoaWipPushExt = new CuxSoaWipPushExt();
			cuxSoaWipPushExt.setDepartment_name(robot.getName());
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			cuxSoaWipPushExt.setPush_date(currentTime);
			String attribute30 = "wip_"+System.currentTimeMillis();
			cuxSoaWipPushExt.setAttribute30(attribute30);
			cuxSoaWipPushExt.setLast_update_date(currentTime);
			cuxSoaWipPushExt.setOrganization_id(robot.getOrg_id());
			cuxSoaWipPushExt.setData_type(robot.getStatus());
			cuxSoaWipPushExt.setRange_start(robot.getRange_start());
			cuxSoaWipPushExt.setRange_end(robot.getRange_end());
			Boolean flag = CuxSoaWipPushMapper.updatePush(cuxSoaWipPushExt) > 0;
			if (flag) {
				NewsRequestBody newsRequestBody = generateRequestBody(robot, attribute30);
				logger.info("机器人请求：{},{}", robot.getRobotid(), new Gson().toJson(newsRequestBody));
				RobotResponse robotResponse = wechatClient.sendNews(robot.getRobotid(), newsRequestBody);
				logger.info("机器人响应：{}", new Gson().toJson(robotResponse));
			}
		}
		
	}

	private NewsRequestBody generateRequestBody(TPushRobot robot, String attribute30) {
		NewsRequestBody newsRequestBody = new NewsRequestBody();
		newsRequestBody.setMsgtype("news");
		News news = new News();
		Articles[] articles = new Articles[1];
		Articles article = new Articles();
		article.setTitle("【"+robot.getName()+"-"+robot.getStatus()+"】生产型号停留异常通知");
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		article.setDescription(date + "【"+robot.getName()+"-"+robot.getStatus()+"】生产型号停留异常通知");
		article.setUrl(robot.getUrl()+"?attribute30="+attribute30);
		article.setPicurl(robot.getPicurl());
		articles[0] = article;
		news.setArticles(articles);
		newsRequestBody.setNews(news);
		return newsRequestBody;
	}

	@Override
	public List<CuxSoaWipPush> findBySeq(String attribute30) {
		return CuxSoaWipPushMapper.findBySeq(attribute30);
	}
}
