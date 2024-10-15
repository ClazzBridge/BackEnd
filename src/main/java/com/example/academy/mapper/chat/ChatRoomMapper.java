package com.example.academy.mapper.chat;

import com.example.academy.domain.redis.ChatRoom;
import com.example.academy.dto.chat.ChatRoomReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatRoomMapper {

  ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

  ChatRoomReadDTO chatRoomToChatRoomReadDTO(ChatRoom chatRoom);

}
