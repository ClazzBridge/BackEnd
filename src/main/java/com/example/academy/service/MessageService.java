//package com.example.academy.service;
//
//import com.example.academy.domain.mongodb.Message;
//import com.example.academy.dto.chat.MessageReadByChatRoomIdDTO;
//import com.example.academy.dto.chat.MessageReadDTO;
//import com.example.academy.dto.chat.MessageSendDTO;
//import com.example.academy.mapper.chat.MessageMapper;
//import com.example.academy.repository.mongodb.MessageRepository;
//import java.util.ArrayList;
//import java.util.List;
//import org.mapstruct.factory.Mappers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageService {
//
//  private final MessageRepository messageRepository;
//  private final MessageMapper messageMapper = Mappers.getMapper(MessageMapper.class);
//
//  @Autowired
//  public MessageService(MessageRepository messageRepository) {
//    this.messageRepository = messageRepository;
//  }
//
//  public MessageReadDTO sendMessage(MessageSendDTO messageSendDTO) {
//    Message newMessage = messageMapper.messageSendToMessage(messageSendDTO);
//    Message savedMessage = messageRepository.save(newMessage);
//
//    return messageMapper.messageToMessageReadDTO(savedMessage);
//  }
//
//  public List<MessageReadDTO> getAllMessagesByChatRoomId(
//      MessageReadByChatRoomIdDTO messageReadByChatRoomIdDTO) {
//    List<Message> messages = messageRepository.findByChatRoomId(
//        messageReadByChatRoomIdDTO.getChatRoomId());
//    List<MessageReadDTO> messageReadDTOs = new ArrayList<>();
//    for (Message message : messages) {
//      messageReadDTOs.add(messageMapper.messageToMessageReadDTO(message));
//    }
//    return messageReadDTOs;
//  }
//
//}
