package com.szmengran.admin.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.suntak.exception.model.CommonExceptionMessage;

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
        messageMap.put("2000", "工艺信息填写不完整，请填写再继续");
        messageMap.put("2001", "PCB文件没有上传，请上传PCB文件再继续！");
        messageMap.put("3002", "输入的旧密码错误");
        
        //短信部分异常代码
        messageMap.put("5001", "由于没有手机号码，阿里云短信发送不成功");
        messageMap.put("5002", "阿里云短信发送不成功");
        
        //suntak
        messageMap.put("5100", "问卷调查还没有配置用户");
        messageMap.put("5101", "验证码不正确，请重新输入");
        messageMap.put("5102", "验证码已过期，请重新发送");
        messageMap.put("5103", "验证码请求过于频繁，请1分钟后再试");
        messageMap.put("5104", "用户名或密码错误");
        messageMap.put("5105", "token校验异常，请重新登录");
        messageMap.put("5106", "token已失效，请重新登录");
        messageMap.put("5107", "原密码不正确");
        messageMap.put("5108", "该用户已被停用，如有疑问，请联系管理员");
    }
    
    public static String getMessage(Integer status) {
    		String msg = messageMap.get(String.valueOf(status));
    		if (StringUtils.isEmpty(msg)) {
    			return CommonExceptionMessage.getMessage(status);
    		}
    		return msg;
    }
}
