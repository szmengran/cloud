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
import com.suntak.push.entity.CuxSoaMiPush;
import com.suntak.push.entity.RobotResponse;
import com.suntak.push.entity.TPushRobot;
import com.suntak.push.entity.ext.CuxSoaMiPushExt;
import com.suntak.push.mapper.CuxSoaMiPushMapper;
import com.suntak.push.service.MiPushService;

@Service
public class MiPushServiceImpl implements MiPushService {

	private static final Logger logger = LoggerFactory.getLogger(MiPushServiceImpl.class);
	
	@Autowired
	private CuxSoaMiPushMapper cuxSoaMiPushMapper;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Override
	public void send() {
		List<TPushRobot> robots = cuxSoaMiPushMapper.findCommonRobot();
		for (TPushRobot robot: robots) {
			CuxSoaMiPushExt cuxSoaMiPushExt = new CuxSoaMiPushExt();
			cuxSoaMiPushExt.setMi_status(robot.getName());
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			cuxSoaMiPushExt.setPush_date(currentTime);
			String attribute30 = "mi_"+System.currentTimeMillis();
			cuxSoaMiPushExt.setAttribute30(attribute30);
			cuxSoaMiPushExt.setLast_update_date(currentTime);
			cuxSoaMiPushExt.setOrganization_id(robot.getOrg_id());
			cuxSoaMiPushExt.setRange_start(robot.getRange_start());
			cuxSoaMiPushExt.setRange_end(robot.getRange_end());
			Boolean flag = cuxSoaMiPushMapper.updateCommonPush(cuxSoaMiPushExt) > 0;
			if (flag) {
				NewsRequestBody newsRequestBody = generateRequestBody(robot, attribute30);
				logger.info("机器人请求：{},{}", robot.getRobotid(), new Gson().toJson(newsRequestBody));
				RobotResponse robotResponse = wechatClient.sendNews(robot.getRobotid(), newsRequestBody);
				logger.info("机器人响应：{}", new Gson().toJson(robotResponse));
			}
		}
		robots = cuxSoaMiPushMapper.findSpecialRobot();
		for (TPushRobot robot: robots) {
			CuxSoaMiPushExt cuxSoaMiPushExt = new CuxSoaMiPushExt();
			cuxSoaMiPushExt.setMi_status(robot.getName());
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			cuxSoaMiPushExt.setPush_date(currentTime);
			String attribute30 = "mi_"+System.currentTimeMillis();
			cuxSoaMiPushExt.setAttribute30(attribute30);
			cuxSoaMiPushExt.setLast_update_date(currentTime);
			cuxSoaMiPushExt.setOrganization_id(robot.getOrg_id());
			cuxSoaMiPushExt.setRange_start(robot.getRange_start());
			cuxSoaMiPushExt.setRange_end(robot.getRange_end());
			Boolean flag = cuxSoaMiPushMapper.updateSpecialPush(cuxSoaMiPushExt) > 0;
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
		article.setTitle("【"+robot.getName()+"】生产型号停留异常通知");
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		article.setDescription(date + "【"+robot.getName()+"】生产型号停留异常通知");
		article.setUrl(robot.getUrl()+"?attribute30="+attribute30);
		article.setPicurl(robot.getPicurl());
		articles[0] = article;
		news.setArticles(articles);
		newsRequestBody.setNews(news);
		return newsRequestBody;
	}

	@Override
	public List<CuxSoaMiPush> findBySeq(String attribute30) {
		return cuxSoaMiPushMapper.findBySeq(attribute30);
	}
}
