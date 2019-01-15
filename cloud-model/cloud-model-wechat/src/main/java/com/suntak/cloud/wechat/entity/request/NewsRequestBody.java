package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 图文消息
 * @date Jan 8, 2019 4:39:17 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class NewsRequestBody extends AbstractRequestBody {

	private News news;

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
}
