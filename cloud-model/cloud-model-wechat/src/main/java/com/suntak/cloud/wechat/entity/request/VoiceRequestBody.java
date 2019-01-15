package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 语言消息
 * @date Jan 8, 2019 4:33:52 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class VoiceRequestBody extends AbstractRequestBody {

	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	
}
