package com.suntak.cloud.sms.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @Package com.suntak.cloud.sms.util 
 * @Description: TODO
 * @date Mar 18, 2019 3:58:42 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class SmsToolTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SmsToolTest.class);
    
    @Test
    public void testTransferMapToJson() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("abc", "111");
        logger.info(SmsTool.transferMapToJson(map));
    }
}
