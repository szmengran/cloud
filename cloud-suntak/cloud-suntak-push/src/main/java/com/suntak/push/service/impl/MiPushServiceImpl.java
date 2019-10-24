package com.suntak.push.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
		List<TPushRobot> robots = cuxSoaMiPushMapper.findRobot();
		for (TPushRobot robot: robots) {
			CuxSoaMiPush cuxSoaMiPush = new CuxSoaMiPush();
			cuxSoaMiPush.setMi_status(robot.getName());
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			cuxSoaMiPush.setPush_date(currentTime);
			String attribute30 = "mi_"+System.currentTimeMillis();
			cuxSoaMiPush.setAttribute30(attribute30);
			cuxSoaMiPush.setLast_update_date(currentTime);
			CuxSoaMiPushExt cuxSoaMiPushExt = new CuxSoaMiPushExt();
			BeanUtils.copyProperties(cuxSoaMiPush, cuxSoaMiPushExt);
			cuxSoaMiPushExt.setRange_start(robot.getRange_start());
			cuxSoaMiPushExt.setRange_end(robot.getRange_end());
			Boolean flag = cuxSoaMiPushMapper.updatePush(cuxSoaMiPushExt) > 0;
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
		article.setTitle("【"+robot.getName()+"】工单停留异常通知");
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		article.setDescription(date + "【"+robot.getName()+"】工单停留异常通知");
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
