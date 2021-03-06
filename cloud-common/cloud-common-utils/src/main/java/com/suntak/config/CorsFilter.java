package com.suntak.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @Describe 
 * @CopyRight Lee
 * @author <a href="mailto:android_li@sina.cn">LiMaoYuan</a>
 * @creaetTime 2015-3-9 下午10:50:54
 */
public class CorsFilter extends OncePerRequestFilter{
//	private final List<String> allowedOrigins = Arrays.asList("http://localhost:8000","http://localhost:8001","http://192.168.0.116:6001","http://192.168.0.117:6001","http://localhost:8081"); 

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		//跨域请求处理
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//		String origin = httpServletRequest.getHeader("Origin");
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, PATCH, HEAD, POST, PUT, DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		httpServletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
