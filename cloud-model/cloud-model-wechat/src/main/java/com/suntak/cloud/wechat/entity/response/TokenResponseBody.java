package com.suntak.cloud.wechat.entity.response;
/**
 * @Package com.suntak.cloud.wechat.entity.response
 * @Description: token 响应信息
 * @date Jan 8, 2019 4:57:05 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class TokenResponseBody {

	private Integer errcode;
	private String errmsg;
	private String access_token;
	private Integer expires_in;
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
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
}
