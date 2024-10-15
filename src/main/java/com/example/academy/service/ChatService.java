package com.example.academy.service;

import com.example.academy.domain.mysql.Member;
import com.example.academy.domain.redis.ChatMessage;
import com.example.academy.domain.redis.ChatRoom;
import com.example.academy.dto.chat.ChatMessageDTO;
import com.example.academy.dto.chat.ChatRoomCreateDTO;
import com.example.academy.dto.chat.ChatRoomReadDTO;
import com.example.academy.mapper.chat.ChatMessageMapper;
import com.example.academy.mapper.chat.ChatRoomMapper;
import com.example.academy.repository.mysql.MemberRepository;
import com.example.academy.repository.redis.ChatRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

  private final ChatRepository chatRepository;
  private final ChatMessageMapper chatMessageMapper = Mappers.getMapper(ChatMessageMapper.class);
  private final ChatRoomMapper chatRoomMapper = Mappers.getMapper(ChatRoomMapper.class);
  private final MemberRepository memberRepository;

  @Autowired
  public ChatService(ChatRepository chatRepository, MemberRepository memberRepository) {
    this.chatRepository = chatRepository;
    this.memberRepository = memberRepository;
  }

  public void saveMessage(String roomId, ChatMessageDTO chatMessageDTO) {
    ChatMessage chatMessage = new ChatMessage(
        String.valueOf(System.currentTimeMillis()),
        roomId,
        chatMessageDTO.getSender(),
        chatMessageDTO.getContent(),
        LocalDateTime.now()
    );

    chatRepository.saveMessage(roomId, chatMessage);
  }

  public List<ChatMessageDTO> getMessages(String roomId) {
    List<ChatMessage> chatMessages = chatRepository.getMessages(roomId);
    List<ChatMessageDTO> chatMessageDTOS = new ArrayList<>();
    for (ChatMessage chatMessage : chatMessages) {
      chatMessageDTOS.add(chatMessageMapper.chatMessageToChatMessageDTO(chatMessage));
    }

    return chatMessageDTOS;
  }

  public ChatRoomReadDTO createChatRoom(ChatRoomCreateDTO chatRoomCreateDTO) {
    Set<Member> members = new HashSet<>();
    List<String> memberIds = chatRoomCreateDTO.getMemberIds();
    for (String memberId : memberIds) {
      memberRepository.findByMemberId(memberId).ifPresent(members::add);
    }
    ChatRoom chatRoom = new ChatRoom(
        String.valueOf(System.currentTimeMillis()),
        chatRoomCreateDTO.getRoomName(),
        chatRoomCreateDTO.isGroup(),
        members);

    chatRepository.saveChatRoom(chatRoom);

    return chatRoomMapper.chatRoomToChatRoomReadDTO(chatRoom);
  }

  public List<ChatRoomReadDTO> getChatRoomsByMemberId(String memberId) {
    List<ChatRoom> chatRooms = chatRepository.findAllRooms();
    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new RuntimeException("Member not found"));
    List<ChatRoomReadDTO> chatRoomReadDTOS = new ArrayList<>();
    for (ChatRoom chatRoom : chatRooms) {
      if (chatRoom.getParticipants().contains(member)) {
        chatRoomReadDTOS.add(chatRoomMapper.chatRoomToChatRoomReadDTO(chatRoom));
      }
    }

    return chatRoomReadDTOS;
  }

}
