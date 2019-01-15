package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 文本卡片消息
 * @date Jan 8, 2019 4:38:07 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class TextcardRequestBody extends AbstractRequestBody {

	private Textcard textcard;

	public Textcard getTextcard() {
		return textcard;
	}

	public void setTextcard(Textcard textcard) {
		this.textcard = textcard;
	}
	
}
