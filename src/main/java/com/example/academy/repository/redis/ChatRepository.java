package com.example.academy.repository.redis;

import com.example.academy.domain.redis.ChatMessage;
import com.example.academy.domain.redis.ChatRoom;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRepository {

  private final RedisTemplate<String, ChatMessage> redisTemplate;
  private final ListOperations<String, ChatMessage> listOperations;

  @Autowired
  public ChatRepository(RedisTemplate<String, ChatMessage> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.listOperations = redisTemplate.opsForList();
  }

  public void saveMessage(String roomId, ChatMessage message) {
    listOperations.rightPush(roomId, message);
  }

  public List<ChatMessage> getMessages(String roomId) {
    return listOperations.range(roomId, 0, -1);
  }

  public List<ChatRoom> findAllRooms() {
    List<Object> rooms = redisTemplate.opsForHash().values("CHAT_ROOM");

    // Object를 ChatRoom 타입으로 캐스팅
    return rooms.stream()
        .map(room -> (ChatRoom) room)
        .collect(Collectors.toList());
  }

  public void saveChatRoom(ChatRoom chatRoom) {
    redisTemplate.opsForHash().put("CHAT_ROOM", chatRoom.getRoomId(), chatRoom);
  }

  public ChatRoom findChatRoomsByMemberId(String roomId) {
    return (ChatRoom) redisTemplate.opsForHash().get("CHAT_ROOM", roomId);
  }

}
