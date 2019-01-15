package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: markdown消息
 * @date Jan 8, 2019 4:44:23 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class MarkdownRequestBody extends AbstractRequestBody {
	
	private Markdown markdown;

	public Markdown getMarkdown() {
		return markdown;
	}

	public void setMarkdown(Markdown markdown) {
		this.markdown = markdown;
	}
	
}
