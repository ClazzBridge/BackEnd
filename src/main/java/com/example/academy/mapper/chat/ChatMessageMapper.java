package com.example.academy.mapper.chat;

import com.example.academy.domain.redis.ChatMessage;
import com.example.academy.dto.chat.ChatMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMessageMapper {

  ChatMessageMapper INSTANCE = Mappers.getMapper(ChatMessageMapper.class);

  ChatMessageDTO chatMessageToChatMessageDTO(ChatMessage chatMessage);

}
