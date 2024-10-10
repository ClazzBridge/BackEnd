package com.example.academy.controller;

import com.example.academy.dto.chat.ChatRoomCreateDTO;
import com.example.academy.dto.chat.ChatRoomReadByMemberIdDTO;
import com.example.academy.dto.chat.ChatRoomReadDTO;
import com.example.academy.service.ChatRoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat-rooms")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @Autowired
  public ChatRoomController(ChatRoomService chatRoomService) {
    this.chatRoomService = chatRoomService;
  }

  @PostMapping
  public ChatRoomReadDTO createChatRoom(@RequestBody ChatRoomCreateDTO chatRoomCreateDTO) {
    return chatRoomService.createChatRoom(chatRoomCreateDTO);
  }

  @GetMapping
  public List<ChatRoomReadDTO> getAllChatRoomsByMemberId(
      @RequestBody ChatRoomReadByMemberIdDTO chatRoomReadByMemberIdDTO) {
    return chatRoomService.getChatRoomsByMemberId(chatRoomReadByMemberIdDTO);
  }
}
