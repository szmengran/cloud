package com.suntak.report.efficiency.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.suntak.api.wechat.WechatRobotClient;
import com.suntak.cloud.wechat.entity.request.Articles;
import com.suntak.cloud.wechat.entity.request.News;
import com.suntak.cloud.wechat.entity.request.NewsRequestBody;
import com.suntak.exception.model.Response;
import com.suntak.report.utils.Constants;

/**   
 * 通过linux的crontab job进行调用
 * curl --request GET 'http://www.gzdemi.com/zdzy/v1/autoTaskServices/redPacketRollback.do'
 * Author： <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
 * DateTime： 2016年12月22日 下午1:17:05 
 */
@RestController
public class MonitorAutoTaskController {
	
	private Logger logger = LoggerFactory.getLogger(MonitorAutoTaskController.class);

	@Value("${report.efficiency.url}")
	private String url;
	
	@Value("${report.efficiency.robotids}")
	private String strRobotids;
	
	@Value("${report.efficiency.picUrl}")
	private String picUrl;
	
	@Autowired
	private WechatRobotClient wechatClient;
	
	/**
	 * 提取并发送产能监控数据
	 * @param username
	 * @param password
	 * @param groupid
	 */
	@GetMapping(value = "/efficiency/monitor/hour/{count}/{dateTime}")
	public Response sendMonitorReport( @PathVariable("count") Integer count, @PathVariable("dateTime") String dateTime){
		try {
			logger.info("------------------------------每日【产能监控报表】通知任务启动------------------------");
			Calendar calendar = Calendar.getInstance();
			String title = "人均效率监控表";
			if(StringUtils.isBlank(dateTime.trim().replace("*", ""))){
				dateTime = new SimpleDateFormat("yyyy-MM-dd HH").format(calendar.getTime());
			}
			if(count == 12){ //12个小时的数据，也就是一个班次的数据
				if(Integer.parseInt(dateTime.split(" ")[1]) < 20 && Integer.parseInt(dateTime.split(" ")[1]) >= 8){ //当提取时间在08点到20点之间时，提取的是昨天20点到今天08点的数据
					dateTime = dateTime.split(" ")[0]+" 08";
					calendar.add(Calendar.DATE, -1);
					Date resultDate = calendar.getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");  
					title = title+"["+sdf.format(resultDate)+"夜班]";
				}else{
					dateTime = dateTime.split(" ")[0]+" 20"; //提取的是今天08点到今天20点的数据
					SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");  
					title = title+"["+sdf.format(calendar.getTime())+"白班]";
				}
			}
			String date = dateTime.split(" ")[0];
			String time = dateTime.split(" ")[1]+":00:00";
			String[] robotids = strRobotids.split(",");
			String stamp = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
			for (int i=0; i<robotids.length; i++) {
				NewsRequestBody requestBody = new NewsRequestBody();
				requestBody.setMsgtype("news");
				News news = new News();
				Articles[] articles = new Articles[1];
				Articles article = new Articles();
				article.setTitle(title);
				article.setDescription(Constants.ORG_NAMES[i]+"\n"+stamp);
				article.setUrl(url+"/report/monitor/t_report_monitor.html?date="+date+"&time="+time+"&count="+count+"&org_id="+Constants.ORG_IDS[i]+"&t="+System.currentTimeMillis());
				article.setPicurl(picUrl);
				articles[0] = article;
				news.setArticles(articles);
				requestBody.setNews(news);
				wechatClient.sendRobotNews(robotids[i], requestBody);
				logger.info("请求数据：{}", new Gson().toJson(requestBody));
			}
			logger.info("------------------------------每日【产能监控报表】通知任务结束------------------------");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return new Response();
	}
	
	@RequestMapping(value = "/efficiency/monitor/hour/{date}", method = RequestMethod.GET)
	public Response sendYearmonthMonitorReport(@PathVariable("date") String date){
		try {
			logger.info("------------------------------每日【产能监控报表】通知任务启动------------------------");
			Calendar calendar = Calendar.getInstance();
			String title = new SimpleDateFormat("yyyy年MM月").format(new Date())+"人均效率"+"[月度汇总]";
			if(StringUtils.isBlank(date.trim().replace("*", ""))){
				date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			}
			
			String[] robotids = strRobotids.split(",");
			String stamp = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
			for (int i=0; i<robotids.length; i++) {
				NewsRequestBody requestBody = new NewsRequestBody();
				requestBody.setMsgtype("news");
				News news = new News();
				Articles[] articles = new Articles[1];
				Articles article = new Articles();
				article.setTitle(title);
				article.setDescription(Constants.ORG_NAMES[i]+"\n"+stamp);
				article.setUrl(url+"/report/monitor/t_report_monitor_yearmonth.html?date="+date+"&org_id="+Constants.ORG_IDS[i]+"&t="+System.currentTimeMillis());
				article.setPicurl(picUrl);
				articles[0] = article;
				news.setArticles(articles);
				requestBody.setNews(news);
				wechatClient.sendRobotNews(robotids[i], requestBody);
				logger.info("请求数据：{}", new Gson().toJson(requestBody));
			}
			logger.info("------------------------------每日【产能监控报表】通知任务结束------------------------");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return new Response();
	}
	
	@RequestMapping(value = "/efficiency/daymonitor/hour/{date}", method = RequestMethod.GET)
	public Response sendDayMonitorReport(@PathVariable("date") String date){
		try {
			logger.info("------------------------------每日【产能监控报表】通知任务启动------------------------");
			Calendar calendar = Calendar.getInstance();
			String title = "人均效率监控表";
			if(StringUtils.isBlank(date.trim().replace("*", ""))){
				date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			}
			String d[] = date.split("-");
			calendar.set(Integer.parseInt(d[0]), Integer.parseInt(d[1])-1, Integer.parseInt(d[2])-1);
			Date resultDate = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");  
			title = title+"["+sdf.format(resultDate)+"]";
			
			String[] robotids = strRobotids.split(",");
			String stamp = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
			for (int i=0; i<robotids.length; i++) {
				NewsRequestBody requestBody = new NewsRequestBody();
				requestBody.setMsgtype("news");
				News news = new News();
				Articles[] articles = new Articles[1];
				Articles article = new Articles();
				article.setTitle(title);
				article.setDescription(Constants.ORG_NAMES[i]+"\n"+stamp);
				article.setUrl(url+"/report/monitor/t_report_monitor_day.html?date="+date+"&org_id="+Constants.ORG_IDS[i]+"&t="+System.currentTimeMillis());
				article.setPicurl(picUrl);
				articles[0] =  article;
				news.setArticles(articles);
				requestBody.setNews(news);
				wechatClient.sendRobotNews(robotids[i], requestBody);
				logger.info("请求数据：{}", new Gson().toJson(requestBody));
			}
			logger.info("------------------------------每日【产能监控报表】通知任务结束------------------------");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return new Response();
	}

}
