package com.szmengran.cloud.warning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szmengran.cloud.warning.entity.T_warning_push;
import com.szmengran.cloud.warning.entity.ext.T_warning_push_ext;
import com.szmengran.cloud.warning.mapper.WarningPushExtMapper;
import com.szmengran.cloud.warning.mapper.WarningPushHistoryMapper;
import com.szmengran.cloud.warning.mapper.WarningPushMapper;
import com.szmengran.cloud.warning.service.WarningPushService;

/** 
 * @Package com.szmengran.cloud.warning.service.impl 
 * @Description: TODO
 * @date Mar 21, 2019 3:56:51 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class WarningPushServiceImpl implements WarningPushService {

    @Autowired
    private WarningPushMapper warningPushMapper;
    
    @Autowired
    private WarningPushExtMapper warningPushExtMapper;
    
    @Autowired
    private WarningPushHistoryMapper warningPushHistoryMapper;
    
    @Override
    public List<T_warning_push_ext> searchWarningPush(String validstatus) throws Exception {
        return warningPushExtMapper.searchWarningPush(validstatus);
    }

    @Override
    public Boolean updateWarningPush(String exception, Long pushid) throws Exception {
        return warningPushMapper.updateWarningPush(exception, pushid) > 0;
    }

    @Transactional
    @Override
    public Boolean move(T_warning_push_ext t_warning_push_ext) throws Exception {
        Long pushid = t_warning_push_ext.getPushid();
        warningPushHistoryMapper.insertInto(pushid);
        T_warning_push t_warning_push = new T_warning_push();
        t_warning_push.setPushid(pushid);
        warningPushMapper.delete(t_warning_push);
        return true;
    }

}
