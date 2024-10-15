//package com.example.academy.mapper.chat;
//
//import com.example.academy.domain.mongodb.Message;
//import com.example.academy.dto.chat.MessageReadDTO;
//import com.example.academy.dto.chat.MessageSendDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface MessageMapper {
//
//  MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);
//
//  @Mapping(target = "send_at", ignore = true)
//  @Mapping(target = "id", ignore = true)
//  Message messageSendToMessage(MessageSendDTO messageSendDTO);
//
//  MessageReadDTO messageToMessageReadDTO(Message message);
//}
