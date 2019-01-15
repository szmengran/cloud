package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 图文消息
 * @date Jan 8, 2019 4:39:39 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class News {
	
	private Articles[] articles;

	public Articles[] getArticles() {
		return articles;
	}

	public void setArticles(Articles[] articles) {
		this.articles = articles;
	}
	
}
