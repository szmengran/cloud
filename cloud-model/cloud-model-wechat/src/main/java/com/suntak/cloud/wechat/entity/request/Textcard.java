package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 卡片消息
 * @date 2018年8月31日 下午3:42:42
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Textcard {
	private String title;
	private String description;
	private String url;
	private String btntxt;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBtntxt() {
		return btntxt;
	}
	public void setBtntxt(String btntxt) {
		this.btntxt = btntxt;
	}
	
}
