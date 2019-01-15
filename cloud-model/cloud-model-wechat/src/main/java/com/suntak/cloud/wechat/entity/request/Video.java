package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 视频信息
 * @date Jan 8, 2019 4:36:09 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Video {
	
	private String media_id;
	private String title;
	private String description;
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
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
	
}
