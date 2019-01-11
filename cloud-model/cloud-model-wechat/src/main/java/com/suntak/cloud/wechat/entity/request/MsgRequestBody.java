package com.suntak.cloud.wechat.entity;
/**
 * @Package com.suntak.cloud.wechat.entity
 * @Description: 企业微信消息发送主体
 * @date 2018年8月31日 下午3:45:26
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class MsgRequestBody {
	private String touser;
	private String toparty;
	private String totag;
	private String msgtype;
	private String agentid;
	private Textcard textcard;
	private Text text;
	private String secret;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public Textcard getTextcard() {
		return textcard;
	}
	public void setTextcard(Textcard textcard) {
		this.textcard = textcard;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
}
