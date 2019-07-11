package com.suntak.cloud.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Oa_user_info;
import com.suntak.cloud.oa.mapper.OaUserinfoMapper;
import com.suntak.cloud.oa.service.OaUserinfoService;

/** 
 * @Package com.suntak.cloud.oa.service.impl 
 * @Description: TODO
 * @date Jul 8, 2019 4:39:42 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class OaUserinfoServiceImpl implements OaUserinfoService {

    @Autowired
    private OaUserinfoMapper oaUserinfoMapper;
    
    @Override
    public Oa_user_info findByCode(String code) throws Exception {
        List<Oa_user_info> list = oaUserinfoMapper.findByCode(code);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

}
