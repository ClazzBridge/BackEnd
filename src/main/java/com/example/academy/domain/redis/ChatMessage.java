package com.example.academy.domain.redis;

import com.example.academy.common.BaseTimeEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessage extends BaseTimeEntity implements Serializable {

  private String messageId;
  private String roomId;
  private String sender;
  private String content;
  private LocalDateTime timestamp;


}
