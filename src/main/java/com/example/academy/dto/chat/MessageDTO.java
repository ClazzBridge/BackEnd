package com.example.academy.dto.chat;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {
  private String id;
  private String chatRoomId;
  private String senderId;
  private String content;
  private LocalDateTime timestamp;
}
