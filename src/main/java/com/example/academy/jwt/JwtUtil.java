package com.example.academy.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

  private final SecretKey secretKey;

  public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public Long getUserId(String token) {
    Claims claims = parseClaims(token);
    return claims.get("id", Long.class);
  }

  public String getRole(String token) {
    Claims claims = parseClaims(token);
    return claims.get("role", String.class);
  }

  public boolean isExpired(String token) {
    try {
      Claims claims = Jwts.parser()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
      Date expiration = claims.getExpiration();
      return expiration.before(new Date());
    } catch (Exception e) {
      return true; // 토큰 파싱 실패 시 만료로 간주
    }
  }


  public Boolean validateToken(String token) {
    try {
      parseClaims(token);
      return true; // 유효한 토큰
    } catch (Exception e) {
      return false; // 유효하지 않은 토큰
    }
  }

  public String createJWT(Long id, String role, Long expiredMs) {
    return Jwts.builder()
        .claim("id", id)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  public String createRefreshJWT(Long id, String role, Long expiredMs) {
    return Jwts.builder()
        .claim("id", id)
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  public String refreshAccessToken(String refreshToken) {
    if (!validateToken(refreshToken) || isExpired(refreshToken)) {
      throw new RuntimeException("Refresh token is invalid or expired");
    }

    Long userId = getUserId(refreshToken);
    String role = getRole(refreshToken);
    return createJWT(userId, role, 60 * 60 * 20L); // 7200 => 7.2초
  }

  private Claims parseClaims(String token) {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
