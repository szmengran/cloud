package com.suntak.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.suntak.utils.JwtUtil;

public class JwtUtilTest {

	@Test
	public void testGenTokenProformance() throws UnsupportedEncodingException {
		long start = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<>();
		map.put("key", "value");
		String token = JwtUtil.generateToken(new Gson().toJson(map));
		token = JwtUtil.generateToken(new Gson().toJson(map));
		token = JwtUtil.generateToken(new Gson().toJson(map));
		System.out.println(token);
		System.out.println(System.currentTimeMillis() - start);
	}
}
