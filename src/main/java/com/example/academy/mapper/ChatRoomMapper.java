package com.example.academy.mapper;

import com.example.academy.domain.mongodb.ChatRoom;
import com.example.academy.dto.mongodb.ChatRoomDTO;
import org.mapstruct.factory.Mappers;

public interface ChatRoomMapper {
  ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

  ChatRoomDTO chatRoomToChatRoomDTO(ChatRoom chatRoom);
}
