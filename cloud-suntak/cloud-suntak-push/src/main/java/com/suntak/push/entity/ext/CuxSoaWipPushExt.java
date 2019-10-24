package com.suntak.push.entity.ext;

import com.suntak.push.entity.CuxSoaWipPush;

public class CuxSoaWipPushExt extends CuxSoaWipPush {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627294867638544641L;
	private Integer range_start;
	private Integer range_end;
	public Integer getRange_start() {
		return range_start;
	}
	public void setRange_start(Integer range_start) {
		this.range_start = range_start;
	}
	public Integer getRange_end() {
		return range_end;
	}
	public void setRange_end(Integer range_end) {
		this.range_end = range_end;
	}
	
}
