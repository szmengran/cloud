package com.suntak.cloud.wechat.entity.response;
/**
 * @Package com.suntak.cloud.wechat.entity.response
 * @Description: 消息发送返回信息
 * @date Jan 9, 2019 9:55:52 AM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class MsgResponseBody {
	
	private Integer errcode;
	private String errmsg;
	private String invaliduser;
	private String invalidparty;
	private String invalidtag;
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getInvaliduser() {
		return invaliduser;
	}
	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}
	public String getInvalidparty() {
		return invalidparty;
	}
	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}
	public String getInvalidtag() {
		return invalidtag;
	}
	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}
	
}
