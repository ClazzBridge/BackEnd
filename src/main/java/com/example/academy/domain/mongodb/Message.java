package com.example.academy.domain.mongodb;

import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "messages")
public class Message {

  @Id
  private String id;

  private String chatRoomId;
  private String senderId;
  private String content;

  @CreationTimestamp
  private LocalDateTime timestamp;

}
