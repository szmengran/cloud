package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Oz_org_userinfo;
import com.suntak.cloud.ems.mapper.UserinfoMapper;
import com.suntak.cloud.ems.service.UserinfoService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 用户操作服务
 * @date Mar 26, 2019 3:25:08 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;
    
    @Override
    public Oz_org_userinfo findUserByEmployerId(String employerId) throws Exception {
        List<Oz_org_userinfo> list = userinfoMapper.findUserinfo(employerId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
