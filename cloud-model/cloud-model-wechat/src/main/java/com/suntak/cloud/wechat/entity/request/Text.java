package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 文本消息
 * @date Dec 22, 2018 1:13:52 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Text {
	private String content;
	
	public Text() {
	}
	
	public Text(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
