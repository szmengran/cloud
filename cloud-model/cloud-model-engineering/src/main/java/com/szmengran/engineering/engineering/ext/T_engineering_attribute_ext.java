package com.szmengran.engineering.engineering.ext;

import java.util.List;

import com.szmengran.engineering.quote.T_engineering_attribute;
import com.szmengran.engineering.quote.T_engineering_value;

/** 
 * @Package com.suntak.quote.ext 
 * @Description: 工程报价属性扩展类
 * @date 2018年1月11日 下午4:52:33 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class T_engineering_attribute_ext extends T_engineering_attribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T_engineering_value> values;
	public List<T_engineering_value> getValues() {
		return values;
	}
	public void setValues(List<T_engineering_value> values) {
		this.values = values;
	}
	
}
