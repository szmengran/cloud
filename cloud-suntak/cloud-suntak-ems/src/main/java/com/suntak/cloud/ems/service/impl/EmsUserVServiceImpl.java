package com.suntak.cloud.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.ems.entity.Ems_user_v;
import com.suntak.cloud.ems.mapper.EmsUserVMapper;
import com.suntak.cloud.ems.service.EmsUserVService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: EBS用户查询服务
 * @date Jul 30, 2019 2:41:25 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsUserVServiceImpl implements EmsUserVService {
    
    @Autowired
    private EmsUserVMapper emsUserVMapper;
    
    @Override
    public Ems_user_v findByUsername(String user_name) throws Exception {
        List<Ems_user_v> list = emsUserVMapper.findByUsername(user_name);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Ems_user_v> findUser(String keyword) throws Exception {
        return emsUserVMapper.findUser(keyword);
    }

}
