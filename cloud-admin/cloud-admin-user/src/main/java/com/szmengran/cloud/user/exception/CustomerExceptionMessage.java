package com.szmengran.cloud.user.exception;

import java.util.HashMap;
import java.util.Map;

/** 
 * @Package com.suntak.engineering.exception 
 * @Description: 自定义异常
 * @date 2018年2月20日 上午11:01:26 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class CustomerExceptionMessage {
	private static Map<String,String> messageMap = new HashMap<String,String>();
    //初始化状态码与文字说明
    static {
        messageMap.put("2000", "工艺信息填写不完整，请填写再继续！");
        messageMap.put("2001", "PCB文件没有上传，请上传PCB文件再继续！");
        messageMap.put("2001", "PCB文件没有上传，请上传PCB文件再继续！");
        messageMap.put("3000", "用户名不存在");
        messageMap.put("3001", "用户已被停用，如有疑问请联系管理员");
        messageMap.put("3002", "输入的密码不正确");
    }
    
    public static String getMessage(Integer status) {
    		return messageMap.get(String.valueOf(status));
    }
}
