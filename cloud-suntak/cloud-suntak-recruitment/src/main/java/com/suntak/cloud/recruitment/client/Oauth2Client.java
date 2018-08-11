package com.suntak.cloud.recruitment.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Package com.suntak.cloud.sms.client
 * @Description: TODO
 * @date 2018年8月8日 上午10:17:50
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class Oauth2Client {
	
//	@PostMapping("/oauth/token")
//	Response token(@PathVariable("code") String code, @PathVariable("phone") String phone) throws Exception;
	
	public static void main(String[] args) {  
        String []cmds = {"curl", "-i", "-w", "状态%{http_code}；DNS时间%{time_namelookup}；"  
                + "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"  
                + "总时间%{time_total}","http://localhost:10000/oauth/token"};  
//        String[] cmds = {"curl", "-XPOST", "-u recruitment:Recruitment123", " -d grant_type=client_credentials ", "状态%{http_code}；DNS时间%{time_namelookup}；"  
//        		+ "等待时间%{time_pretransfer}TCP 连接%{time_connect}；发出请求%{time_starttransfer}；"  
//        		+ "总时间%{time_total}","http://localhost:10000/oauth/token"};  
        ProcessBuilder pb=new ProcessBuilder(cmds);  
        pb.redirectErrorStream(true);  
        Process p;  
        try {  
            p = pb.start();  
            BufferedReader br=null;  
            String line=null;  
              
            br=new BufferedReader(new InputStreamReader(p.getInputStream()));  
            while((line=br.readLine())!=null){  
                    System.out.println("\t"+line);  
            }  
            br.close();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    } 
}
