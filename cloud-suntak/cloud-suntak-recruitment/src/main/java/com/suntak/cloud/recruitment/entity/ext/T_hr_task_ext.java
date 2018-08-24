package com.suntak.cloud.recruitment.entity.ext;

import com.suntak.cloud.recruitment.entity.T_hr_task;

/**
 * @Package com.suntak.cloud.recruitment.entity.ext
 * @Description: 任务扩展表
 * @date 2018年8月22日 上午11:25:34
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_hr_task_ext extends T_hr_task{
	private String name;
	private String nextrole;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNextrole() {
		return nextrole;
	}
	public void setNextrole(String nextrole) {
		this.nextrole = nextrole;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
