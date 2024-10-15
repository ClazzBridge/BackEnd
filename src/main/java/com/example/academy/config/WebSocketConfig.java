package com.example.academy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

// @EnableWebSocketMessageBroker
// STOMP 프로토콜을 사용하면, 프레임워크가 자동으로 메시지 라우팅 및 브로커를 처리해줌
// 더 낮은 수준의 WebSocket API를 사용할 수 있지만, STOMP를 사용하면 메시지의 목적지와 구독 관리가 간편해짐
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements
    WebSocketMessageBrokerConfigurer { // WebSocket 설정을 위한 구현 클래스

  // SockJS
  // 브라우저가 WebSocket을 지원하지 않는 경우를 대비, 기본적으로는 WebSocket 사용함
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // WebSocket 엔드포인트 설정, 클라이언트가 이 URL로 소켓 연결을 시도함
    // SockJS를 사용해 브라우저 호환성을 높임 (WebSocket을 지원하지 않는 브라우저도 연결이 가능해짐)
    registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    registry.addEndpoint("/clazzbridge").addInterceptors(new HttpSessionHandshakeInterceptor())
        .withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 메시지 브로커 구성 : "topic" 경로에 메시지를 브로드캐스팅하는 간단한 브로커 사용
    registry.enableSimpleBroker("/topic", "/queue");
    // 클라이언트가 "/app" 경로로 메시지를 보낼 수 있게 함
    registry.setApplicationDestinationPrefixes("/app");
  }
}