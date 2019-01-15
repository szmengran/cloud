package com.suntak.cloud.wechat.entity.request;
/**
 * @Package com.suntak.cloud.wechat.entity.request
 * @Description: 文件消息
 * @date Jan 9, 2019 10:02:33 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class FileRequestBody extends AbstractRequestBody {
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
}
