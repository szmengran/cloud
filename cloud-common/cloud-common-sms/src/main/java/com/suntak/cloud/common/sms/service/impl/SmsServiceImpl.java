package com.suntak.cloud.common.sms.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.common.sms.mapper.SmsLogMapper;
import com.suntak.cloud.common.sms.service.SmsService;
import com.suntak.common.entity.T_common_sms_log;

/**
 * @Package com.szmengran.cloud.common.sms.service.impl
 * @Description: 短信发送服务
 * @date 2018年4月6日 下午2:17:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class SmsServiceImpl implements SmsService{

	@Autowired
	private SmsLogMapper smsLogMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Value("${aliyun.sms.AccessKeyID}")
    private String ACCESSKEYID;
	
	@Value("${aliyun.sms.AccessKeySecret}")
	private String ACCESSKEYSECRET;
	
    @Override
	public void send(T_common_sms_log t_common_sms_log) throws Exception {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", t_common_sms_log.getPhone());
        request.putQueryParameter("SignName", t_common_sms_log.getSignname());
        request.putQueryParameter("TemplateCode", t_common_sms_log.getTemplatecode());
        request.putQueryParameter("TemplateParam", t_common_sms_log.getTemplateparam());
        request.putQueryParameter("OutId", t_common_sms_log.getOutid());
        
        Timestamp createstamp = new Timestamp(System.currentTimeMillis());
        t_common_sms_log.setCreatestamp(createstamp);
        t_common_sms_log.setUpdatestamp(createstamp);
        
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info("aliyun sms send result:{}", new Gson().toJson(response));
            t_common_sms_log.setResult("1");
            smsLogMapper.insert(t_common_sms_log);
        } catch (Exception e) {
            e.printStackTrace();
            t_common_sms_log.setResult("0");
            smsLogMapper.insert(t_common_sms_log);
            throw new BusinessException(5002);
        }
	}

    //	@SuppressWarnings("deprecation")
//	@Override
//	public void send(T_common_sms_log t_common_sms_log) throws Exception {
//	    // 设置超时时间-可自行调整
//	    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//	    System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//	    // 初始化ascClient需要的几个参数
//	    final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
//	    final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
//	    // 替换成你的AK
//	    final String accessKeyId = ACCESSKEYID;// 你的accessKeyId,参考本文档步骤2
//	    final String accessKeySecret = ACCESSKEYSECRET;// 你的accessKeySecret，参考本文档步骤2
//	    // 初始化ascClient,暂时不支持多region（请勿修改）
//	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//	    IAcsClient acsClient = new DefaultAcsClient(profile);
//	    // 组装请求对象
//	    SendSmsRequest request = new SendSmsRequest();
//	    // 使用post提交
//	    request.setMethod(MethodType.POST);
//	    // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
//	    request.setPhoneNumbers(t_common_sms_log.getPhone());
//	    // 必填:短信签名-可在短信控制台中找到
//	    request.setSignName(t_common_sms_log.getSignname());
//	    // 必填:短信模板-可在短信控制台中找到
//	    request.setTemplateCode(t_common_sms_log.getTemplatecode());
//	    // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//	    // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//	    request.setTemplateParam(t_common_sms_log.getTemplateparam());
//	    // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
//	    // request.setSmsUpExtendCode("90997");
//	    // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//	    request.setOutId(t_common_sms_log.getOutid());
//	    // 请求失败这里会抛ClientException异常
//	    SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//	    logger.info("aliyun sms send result:{}", new Gson().toJson(sendSmsResponse));
//	    Timestamp createstamp = new Timestamp(System.currentTimeMillis());
//	    t_common_sms_log.setCreatestamp(createstamp);
//	    t_common_sms_log.setUpdatestamp(createstamp);
//	    if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//	        t_common_sms_log.setResult("1");
//	        smsLogMapper.insert(t_common_sms_log);
//	    } else {
//	        t_common_sms_log.setResult("0");
//	        smsLogMapper.insert(t_common_sms_log);
//	        throw new BusinessException(5002);
//	    }
//	}
}
