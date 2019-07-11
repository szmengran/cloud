package com.suntak.cloud.recruitment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.cloud.recruitment.client.OaClient;
import com.suntak.cloud.recruitment.service.OaService;

/** 
 * @Package com.suntak.cloud.recruitment.service.impl 
 * @Description: TODO
 * @date Jul 8, 2019 4:55:47 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class OaServiceImpl implements OaService {

    @Autowired
    private OaClient oaClient;
    
    @Cacheable(value = "com.suntak.cloud.recruitment.service.impl.OaServiceImpl", key = "#p0")
    @Override
    public String findLoginNameByCode(String code) throws Exception {
        Object obj = oaClient.findByCode(code).getData();
        if (obj == null) {
            throw new Exception("该工号【"+code+"】在OA中找不到对应的登录名！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(obj));
        return jsonNode.get("login_name").asText();
    }
    
    @CacheEvict(allEntries = true, value = "com.suntak.cloud.recruitment.service.impl.OaServiceImpl")
    @Scheduled(fixedDelay = 90 * 60 * 1000 ,  initialDelay = 500)
    public void qyWechatCacheEvict() {
    }
}
