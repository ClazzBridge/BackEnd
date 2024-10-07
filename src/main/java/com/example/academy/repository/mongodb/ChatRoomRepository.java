package com.example.academy.repository.mongodb;

import com.example.academy.domain.mongodb.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

}
