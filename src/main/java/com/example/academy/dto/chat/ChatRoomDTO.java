package com.example.academy.dto.chat;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatRoomDTO {
  private String id;
  private String name;
  private List<String> userIds;
}
