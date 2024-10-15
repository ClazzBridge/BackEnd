//package com.example.academy.service;
//
//import com.example.academy.dto.chat.ChatRoomCreateDTO;
//import com.example.academy.dto.chat.ChatRoomReadByMemberIdDTO;
//import com.example.academy.dto.chat.ChatRoomReadDTO;
//import com.example.academy.mapper.chat.ChatRoomMapper;
//import com.example.academy.repository.mongodb.ChatRoomRepository;
//import java.util.ArrayList;
//import java.util.List;
//import org.mapstruct.factory.Mappers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ChatRoomService {
//
//  private final ChatRoomRepository chatRoomRepository;
//  private final ChatRoomMapper chatRoomMapper = Mappers.getMapper(ChatRoomMapper.class);
//
//  @Autowired
//  public ChatRoomService(ChatRoomRepository chatRoomRepository) {
//    this.chatRoomRepository = chatRoomRepository;
//  }
//
//  public ChatRoomReadDTO createChatRoom(ChatRoomCreateDTO chatRoomCreateDTO) {
//    ChatRoom chatRoom = chatRoomMapper.chatRoomCreateDTOToChatRoom(chatRoomCreateDTO);
//    ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
//
//    return chatRoomMapper.chatRoomToChatRoomReadDTO(savedChatRoom);
//  }
//
//  public List<ChatRoomReadDTO> getAllChatRooms() {
//    List<ChatRoom> chatRooms = chatRoomRepository.findAll();
//    List<ChatRoomReadDTO> chatRoomReadDTOs = new ArrayList<>();
//
//    for (ChatRoom chatRoom : chatRooms) {
//      chatRoomReadDTOs.add(chatRoomMapper.chatRoomToChatRoomReadDTO(chatRoom));
//    }
//    return chatRoomReadDTOs;
//  }
//
//  public List<ChatRoomReadDTO> getChatRoomsByMemberId(
//      ChatRoomReadByMemberIdDTO chatRoomReadByMemberIdDTO) {
//    List<ChatRoom> chatRooms = chatRoomRepository.findAll();
//    List<ChatRoomReadDTO> chatRoomReadDTOs = new ArrayList<>();
//
//    for (ChatRoom chatRoom : chatRooms) {
//      List<String> memberIds = chatRoom.getMemberIds();
//      if (memberIds.contains(chatRoomReadByMemberIdDTO.getMemberId())) {
//        chatRoomReadDTOs.add(chatRoomMapper.chatRoomToChatRoomReadDTO(chatRoom));
//      }
//    }
//
//    return chatRoomReadDTOs;
//  }
//
//}
