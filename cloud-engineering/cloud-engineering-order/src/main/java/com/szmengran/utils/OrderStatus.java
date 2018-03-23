package com.szmengran.utils;

public class OrderStatus {
	public final static String CANCEL="0"; //取消
	public final static String WAIT_FOR_RECEIVE="1"; //待接单
	public final static String DESIGN_PROCESSING="2"; //设计中
	public final static String UPDATING="3"; //修改中
	public final static String STOP="4"; //暂挂中
	public final static String RETURN_UPDATING="5"; //返修中
	public final static String RETURN_BACK="6"; //退货中
	public final static String WAIT_FOR_PAY="7"; //待付款
	public final static String WAIT_FOR_RECEIPT="8"; //贷收货
	public final static String WAIT_FOR_EVALUATE="9"; //待评价
	public final static String FINISH="10"; //已完成
}
