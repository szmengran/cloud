package com.szmengran.engineering.engineering.ext;

import com.szmengran.engineering.order.T_engineering_order;

/** 
 * @Package com.suntak.quote.engineering.ext 
 * @Description: 工程订单明细扩展表
 * @date 2018年1月22日 下午3:59:36 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_order_ext extends T_engineering_order{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] fileids;
	public String[] getFileids() {
		return fileids;
	}
	public void setFileids(String[] fileids) {
		this.fileids = fileids;
	}
}
