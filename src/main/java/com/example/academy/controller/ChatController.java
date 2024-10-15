package com.example.academy.controller;

// @RestController가 아닌 @Controller인 이유
// 1.
// - WebSocket은 HTTP 요청/응답 모델을 따르지 않음
// - WebSocket 통신은 클라이언트가 서버에 연결한 후 양방향으로 실시간 메시지를 주고 받음
// - RestController는 HTTP 기반의 통신 방식에 맞게 설계된 애노테이션이고, WebSocket과는 적합하지 않음
//
// 2.
// - WebSocket에서 메시지 라우팅은 @MessageMapping을 사용해 특정 경로로 들어오는 메시지를 처리
// - 이떄 @Controller가 적합하고, 메시지를 브로드캐스팅하거나 특정 클라이언트에게 전송하는 역할 수행

import com.example.academy.dto.chat.ChatMessageDTO;
import com.example.academy.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

// @SendTo = 1:n으로 메세지를 뿌릴 떄 사용하는 구조, 보통 /topic으로 보냄
// @SentToUser = 1:1로 메세지를 뿌릴 때 사용하는 구조, 보통 /queue로 보냄

@Controller
public class ChatController {

  private final ChatService chatService;
  private final SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
    this.chatService = chatService;
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/public")
  public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessageDTO,
      @DestinationVariable String roomId) {
    chatService.saveMessage(roomId, chatMessageDTO);
    return chatMessageDTO;
  }

  public void broadcastMessage(@Payload ChatMessageDTO chatMessageDTO, String roomId) {
    simpMessagingTemplate.convertAndSend("/topic/public/" + roomId, chatMessageDTO);
  }


}
