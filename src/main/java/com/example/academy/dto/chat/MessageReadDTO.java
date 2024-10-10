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
public class MessageReadDTO {

  private String senderId;
  private String content;
  private LocalDateTime timestamp;
}
