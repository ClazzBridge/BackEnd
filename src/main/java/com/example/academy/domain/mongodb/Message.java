package com.example.academy.domain.mongodb;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

  @Id
  private String id;

  private String chatRoomId;
  private String senderId;
  private String content;
  private LocalDateTime timestamp;

}
