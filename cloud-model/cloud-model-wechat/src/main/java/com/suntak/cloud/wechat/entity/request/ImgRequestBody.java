package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 图片消息
 * @date Jan 8, 2019 4:30:51 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ImgRequestBody extends AbstractRequestBody {
	
	private Image image;
	private Integer safe;
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Integer getSafe() {
		return safe;
	}
	public void setSafe(Integer safe) {
		this.safe = safe;
	}
}
