package com.example.academy.interceptor;

import com.example.academy.jwt.JwtUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthenticatorInterceptor implements ChannelInterceptor {

  private final JwtUtil jwtUtil;

  public WebSocketAuthenticatorInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

    // WebSocket 연결 핸드셰이크 시 Authorization 헤더에서 JWT 토큰 처리
    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
      String token = accessor.getFirstNativeHeader("Authorization");
      System.out.println("hihi");

      if (token != null && token.startsWith("Bearer ")) {
        token = token.substring(7);

        // JWT 토큰 검증 및 사용자 정보 설정
        if (jwtUtil.validateToken(token) && !jwtUtil.isExpired(token)) {
          Long userId = jwtUtil.getUserId(token);
          String role = jwtUtil.getRole(token);

          UserDetails userDetails = User.withUsername(String.valueOf(userId))
              .password("")
              .authorities(role)
              .build();

          UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(userDetails, null,
                  userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);

          accessor.setUser(authenticationToken);  // 인증된 사용자 설정
        }
      }
    }

    return message;
  }
}