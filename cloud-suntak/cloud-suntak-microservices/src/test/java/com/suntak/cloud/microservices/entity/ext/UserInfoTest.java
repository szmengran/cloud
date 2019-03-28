package com.suntak.cloud.microservices.entity.ext;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

/** 
 * @Package com.suntak.cloud.microservices.entity.ext 
 * @Description: TODO
 * @date Mar 20, 2019 2:51:41 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class UserInfoTest {

    @Test
    public void testJson() throws IOException {
        
        long start1 = System.currentTimeMillis();
        UserInfo value1 = new UserInfo();
        value1.setUserId("2222");
        value1.setAvatar("xxxxx");
        JSONObject jsonObject = JSONObject.fromObject(value1);
        System.out.println(jsonObject.get("UserId"));
        System.out.println((System.currentTimeMillis()-start1));
        

        long start = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo value = new UserInfo();
        value.setUserId("2222");
        value.setAvatar("xxxxx");
        JsonNode node = objectMapper.readTree(objectMapper.writeValueAsString(value));
        System.out.println((System.currentTimeMillis()-start));
        System.out.println(node.get("UserId").asText());
        System.out.println((System.currentTimeMillis()-start));
        assertEquals("2222", node.get("UserId").asText());
    }
}
