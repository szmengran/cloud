package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: TODO
 * @date Jan 8, 2019 4:35:43 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class VideoRequestBody extends AbstractRequestBody {

	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
}
