package com.suntak.push.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.wechat.entity.request.Articles;
import com.suntak.cloud.wechat.entity.request.News;
import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.push.client.WechatClient;
import com.suntak.push.entity.CuxSoaWipPush;
import com.suntak.push.entity.TPushRobot;
import com.suntak.push.mapper.CuxSoaWipPushMapper;
import com.suntak.push.service.PushService;

@Service("wipPushService")
public class WipPushServiceImpl implements PushService {

	@Autowired
	private CuxSoaWipPushMapper CuxSoaWipPushMapper;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Override
	public void send() {
		List<TPushRobot> robots = CuxSoaWipPushMapper.findRobot();
		for (TPushRobot robot: robots) {
			CuxSoaWipPush cuxSoaWipPush = new CuxSoaWipPush();
			cuxSoaWipPush.setDepartment_name(robot.getName());
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			cuxSoaWipPush.setPush_date(currentTime);
			String attribute30 = "wip_"+System.currentTimeMillis();
			cuxSoaWipPush.setAttribute30(attribute30);
			cuxSoaWipPush.setLast_update_date(currentTime);
			Boolean flag = CuxSoaWipPushMapper.updatePush(cuxSoaWipPush) > 0;
			if (flag) {
				NewsRequestBody newsRequestBody = generateRequestBody(robot, attribute30);
				wechatClient.sendNews(robot.getRobotid(), newsRequestBody);
			}
		}
		
	}

	private NewsRequestBody generateRequestBody(TPushRobot robot, String attribute30) {
		NewsRequestBody newsRequestBody = new NewsRequestBody();
		newsRequestBody.setMsgtype("news");
		News news = new News();
		Articles[] articles = new Articles[1];
		Articles article = new Articles();
		article.setTitle("【"+robot.getName()+"】工单停留通知");
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		article.setDescription(date + "【"+robot.getName()+"】工单停留通知");
		article.setUrl(robot.getUrl()+"?attribute30="+attribute30);
		article.setPicurl(robot.getPicurl());
		articles[0] = article;
		news.setArticles(articles);
		newsRequestBody.setNews(news);
		return newsRequestBody;
	}
}
