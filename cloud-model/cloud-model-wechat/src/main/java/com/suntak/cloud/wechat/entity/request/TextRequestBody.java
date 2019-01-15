package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 文本消息
 * @date Jan 8, 2019 4:28:47 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class TextRequestBody extends AbstractRequestBody {
	private Integer safe;
	private Text text;
	public Integer getSafe() {
		return safe;
	}
	public void setSafe(Integer safe) {
		this.safe = safe;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	
}
