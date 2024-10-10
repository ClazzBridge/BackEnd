package com.example.academy.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageSendDTO {

  private String chatRoomId;
  private String senderId;
  private String content;
}
