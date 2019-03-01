package com.szmengran.admin.user.exception;

import com.suntak.exception.AbstractException;

/** 
 * @Package com.suntak.engineering.exception 
 * @Description: 自定义异常
 * @date 2018年2月20日 上午11:17:02 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class BusinessException extends AbstractException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BusinessException(Integer status) {
		super(CustomerExceptionMessage.getMessage(status));
		super.setStatus(status);
	}
	
	public BusinessException(Integer status, String errormsg) {
		super(errormsg);
		super.setStatus(status);
	}
}
