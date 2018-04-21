package com.szmengran.utils;

import java.io.UnsupportedEncodingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static String secret="TYUIOHJKRTYUIOFS345678RTYFGHJK";
    /**
     * 解析token
     * @param token
     * @return json字符串
     * @throws IllegalArgumentException
     * @throws UnsupportedEncodingException
     */
    public static String parseToken(String token) throws IllegalArgumentException, UnsupportedEncodingException {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();

            return body.getSubject();

        } catch (JwtException | ClassCastException e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成token
     * @param json
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String generateToken(String json) throws UnsupportedEncodingException {
        return Jwts.builder()
        .setSubject(json)
        .signWith(SignatureAlgorithm.HS512, secret.getBytes("UTF-8"))
        .compact();
    }
    
    public static void main(String args[]) throws UnsupportedEncodingException {
    		String json = "{name:limaoyuan}";
    		String token = generateToken(json);
    		System.out.println(token);
    		System.out.println(parseToken(token));
    }
}