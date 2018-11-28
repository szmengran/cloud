package com.suntak.cloud.microservices.entity;
/**
 * @Package com.suntak.cloud.microservices.entity
 * @Description: TODO
 * @date Nov 28, 2018 2:59:28 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class ErrorNotification {
	private String agentId;
	private String toUser; //请求服务人
	private String url;
	private String principal; //服务负责人
	private String error;
	private String title;
	private String content;
	private String secret;
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
}
