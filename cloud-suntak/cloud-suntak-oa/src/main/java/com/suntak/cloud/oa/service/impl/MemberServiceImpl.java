package com.suntak.cloud.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Org_member;
import com.suntak.cloud.oa.mapper.MemberMapper;
import com.suntak.cloud.oa.service.MemberService;

/** 
 * @Package com.suntak.cloud.oa.service.impl 
 * @Description: TODO
 * @date Aug 1, 2019 1:37:31 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    
    @Override
    public Org_member findByCode(String code) throws Exception {
        List<Org_member> members = memberMapper.findByCode(code);
        if (members != null && members.size() > 0) {
            return members.get(0);
        }
        return null;
    }

}
