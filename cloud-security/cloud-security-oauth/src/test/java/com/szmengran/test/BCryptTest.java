//package com.szmengran.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.szmengran.security.OauthApplication;
//
//@RunWith(SpringJUnit4ClassRunner.class)  
//@SpringBootTest(classes = OauthApplication.class)  
//@WebAppConfiguration  
//public class BCryptTest {
//	@Autowired
//    private RedisConnectionFactory connectionFactory;
//    
//    @Bean
//    public RedisTokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }
//
//    private RedisConnection getConnection() {
//		return connectionFactory.getConnection();
//	}
//    
//    @Test
//    public void store() {
//    		RedisConnection conn = getConnection();
//		try {
//			String token = "69966025-c5a9-4d19-9e29-7b8b6983c0ab";
//			byte[] accessKey = token.getBytes();
//			byte[] value = conn.get("auth_to_access:11ab8577ce8a9c4de16f52e8140252c3".getBytes());
//			System.out.println(value);
//			conn.openPipeline();
//			conn.closePipeline();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			conn.close();
//		}
//    }
//	public static void main(String arge[]) {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		Long start = System.currentTimeMillis();
//		String pw = bCryptPasswordEncoder.encode("12345");
//		System.out.println(pw);
//		boolean f = bCryptPasswordEncoder.matches("12345", pw);
//		System.out.println(System.currentTimeMillis());
//        System.out.println(f);
//		System.out.println((System.currentTimeMillis()-start));
////		69966025-c5a9-4d19-9e29-7b8b6983c0ab
//	}
//}
