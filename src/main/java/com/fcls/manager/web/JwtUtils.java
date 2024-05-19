package com.fcls.manager.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    private String secret = "qwertyuioplkjnbvfdcxsaz";
    /**
     * 生成token
     */
    public String createToken(Map<String,Object> map) {
        String token = Jwts.builder()
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }

    /**
     * 根据token解析出用户信息
     */
    public Claims parseToken(String token) {
        // 解析token，需要使用和创建token时相同的秘钥
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
