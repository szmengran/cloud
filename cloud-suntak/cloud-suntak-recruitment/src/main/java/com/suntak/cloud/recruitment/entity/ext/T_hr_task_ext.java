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
	private String subflowname;
	private String nextflowid;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubflowname() {
		return subflowname;
	}
	public void setSubflowname(String subflowname) {
		this.subflowname = subflowname;
	}
	public String getNextflowid() {
		return nextflowid;
	}
	public void setNextflowid(String nextflowid) {
		this.nextflowid = nextflowid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
