package com.example.academy.service;

import com.example.academy.domain.mongodb.ChatRoom;
import com.example.academy.dto.mongodb.ChatRoomDTO;
import com.example.academy.mapper.ChatRoomMapper;
import com.example.academy.repository.mongodb.ChatRoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatRoomService {

  private ChatRoomRepository chatRoomRepository;
  private ChatRoomMapper chatRoomMapper = ChatRoomMapper.INSTANCE;

  @Autowired
  public ChatRoomService(ChatRoomRepository chatRoomRepository) {
    this.chatRoomRepository = chatRoomRepository;
  }

  public ChatRoomDTO createChatRoom(String name, List<String> userIds) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.setName(name);
    chatRoom.setUserIds(userIds);
    ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

    return chatRoomMapper.chatRoomToChatRoomDTO(savedChatRoom);
  }

  public List<ChatRoom> getAllChatRooms() {
    return chatRoomRepository.findAll();
  }

}
