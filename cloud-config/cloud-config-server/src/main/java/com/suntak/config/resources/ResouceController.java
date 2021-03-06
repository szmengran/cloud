package com.suntak.config.resources;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.szmengran.config.resources
 * @Description: TODO
 * @date 2018年9月4日 上午11:12:45
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@RestController
public class ResouceController {

	@GetMapping("/resources/{fileName}")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws Exception {
	    if (fileName != null) {
	    	response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            InputStream in = null; 
            BufferedInputStream bis = null;
            try {
            	in = getClass().getResourceAsStream("/"+fileName); 
                bis = new BufferedInputStream(in);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
	    }
	    return null;
	}
}
