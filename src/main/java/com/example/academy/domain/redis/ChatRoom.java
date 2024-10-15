package com.example.academy.domain.redis;

import com.example.academy.domain.mysql.Member;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoom implements Serializable {

  private String roomId;
  private String roomName;
  private boolean isGroup;
  private Set<Member> participants = new HashSet<>();

  public void addParticipant(Member participant) {
    participants.add(participant);
  }
}
