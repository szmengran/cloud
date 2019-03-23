package com.szmengran.cloud.warning.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.suntak.cloud.wechat.entity.request.AbstractRequestBody;
import com.suntak.cloud.wechat.entity.request.Text;
import com.suntak.cloud.wechat.entity.request.TextRequestBody;
import com.suntak.cloud.wechat.entity.request.Textcard;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;
import com.szmengran.cloud.warning.client.WechatClient;
import com.szmengran.cloud.warning.entity.ext.T_warning_push_ext;
import com.szmengran.cloud.warning.service.WarningPushService;
import com.szmengran.cloud.warning.utils.MsgTypeConstants;

/** 
 * @Package com.szmengran.cloud.warning.controller 
 * @Description: 预警消息服务
 * @date Mar 21, 2019 4:04:45 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
@RequestMapping("/api/v1/warning")
public class WarningPushController {

    private static final Logger logger = LoggerFactory.getLogger(WarningPushController.class);
    
    private static final ExecutorService executor = new ThreadPoolExecutor(2, 200, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    
    @Value("${wechat.qy.AgentId}")
    private String agentId;
    
    @Value("${wechat.qy.Secret}")
    private String secret;
    
    @Autowired
    private WechatClient wechatClient;
    
    @Autowired
    private WarningPushService warningPushService;
    
    @GetMapping("/push/{validstatus}")
    public Response sendWarningPush(@PathVariable("validstatus") String validstatus) throws Exception {
        PageHelper.startPage(1, 100, "a.createstamp");
        List<T_warning_push_ext> list = warningPushService.searchWarningPush(validstatus);
        for (T_warning_push_ext t_warning_push_ext: list) {
            executor.submit(() -> {
                try {
                    Response response = send(t_warning_push_ext);
                    if (200 != response.getStatus()) {
                        throw new Exception(response.getMessage());
                    }
                    warningPushService.move(t_warning_push_ext);
                } catch (Exception e) {
                    logger.error("{}", e);
                    try {
                        warningPushService.updateWarningPush(e.getMessage(), t_warning_push_ext.getPushid());
                    } catch (Exception e1) {
                        logger.error("{}", e1);
                    }
                }
            });
        }
        return new Response();
    }
    
    /**
     * 发送消息
     * @param t_warning_push_ext
     * @throws Exception      
     * @return: void      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    private Response send(T_warning_push_ext t_warning_push_ext) throws Exception {
        switch (t_warning_push_ext.getMsgtype()) {
            case MsgTypeConstants.TEXT:
                return sendTextBody(t_warning_push_ext);
            case MsgTypeConstants.TEXTCARD:
                return sendTextcardBody(t_warning_push_ext);
        }
        return null;
    }
    
    /**
     * 设置公共信息
     * @param abstractRequestBody
     * @param t_warning_push_ext
     * @throws Exception      
     * @return: void      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    private void setCommonMsg(AbstractRequestBody abstractRequestBody, T_warning_push_ext t_warning_push_ext) throws Exception {
        String touser = t_warning_push_ext.getTouser();
        if (StringUtils.isBlank(touser))  {
            abstractRequestBody.setTouser(t_warning_push_ext.getTouser2());
            abstractRequestBody.setToparty(t_warning_push_ext.getTopart());
            abstractRequestBody.setTotag(t_warning_push_ext.getTotag());
        } else {
            abstractRequestBody.setTouser(touser);
        }
        abstractRequestBody.setAgentid(agentId);
    }
    
    /**
     * 发送文本消息
     * @param t_warning_push_ext
     * @return
     * @throws Exception      
     * @return: Response      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    private Response sendTextBody(T_warning_push_ext t_warning_push_ext) throws Exception {
        TextRequestBody textRequestBody = new TextRequestBody();
        textRequestBody.setMsgtype("text");
        Text text = new Text();
        text.setContent(t_warning_push_ext.getMessage());
        textRequestBody.setText(text);
        setCommonMsg(textRequestBody, t_warning_push_ext);
        return wechatClient.sendText(secret, textRequestBody);
    }
    
    /**
     * 发送卡片消息
     * @param t_warning_push_ext
     * @throws Exception      
     * @return: Response      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    private Response sendTextcardBody(T_warning_push_ext t_warning_push_ext) throws Exception {
        TextcardRequestBody textcardRequestBody = new TextcardRequestBody();
        textcardRequestBody.setMsgtype("textcard");
        System.out.println(t_warning_push_ext.getMessage());
        Textcard textcard = new Gson().fromJson(t_warning_push_ext.getMessage(), Textcard.class);
        textcardRequestBody.setTextcard(textcard);
        setCommonMsg(textcardRequestBody, t_warning_push_ext);
        return wechatClient.sendTextcard(secret, textcardRequestBody);
    }
    
}
