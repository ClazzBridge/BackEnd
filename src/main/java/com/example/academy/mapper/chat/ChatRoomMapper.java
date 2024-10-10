package com.example.academy.mapper.chat;

import com.example.academy.domain.mongodb.ChatRoom;
import com.example.academy.dto.chat.ChatRoomCreateDTO;
import com.example.academy.dto.chat.ChatRoomReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatRoomMapper {

  ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

  ChatRoomReadDTO chatRoomToChatRoomReadDTO(ChatRoom chatRoom);

  ChatRoomCreateDTO chatRoomToChatRoomCreateDTO(ChatRoom chatRoom);

  @Mapping(target = "id", ignore = true)
  ChatRoom chatRoomCreateDTOToChatRoom(ChatRoomCreateDTO chatRoomCreateDTO);
}
