package com.sky.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

  /**
   * 生成 JWT
   * @param secretKey jwt 密钥
   * @param ttlMillis 过期时间
   * @param claims 加密信息
   * @return
   */
  public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
    // JWT header
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // JWT 过期时间
    long expMillis = System.currentTimeMillis() + ttlMillis;
    Date exp = new Date(expMillis);

    // JWT body
    JwtBuilder jwtBuilder = Jwts.builder()
            .setClaims(claims)
            .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
            .setExpiration(exp);

    return jwtBuilder.compact();
  }

  /**
   * token 解析
   *
   * @param secretKey jwt 密钥
   * @param token     加密后的token
   * @return
   */
  public static Claims parseJWT(String secretKey, String token) {
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor((secretKey.getBytes(StandardCharsets.UTF_8))))
            .build()
            .parseClaimsJws(token)
            .getBody();

    return claims;
  }
}
