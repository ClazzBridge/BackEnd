package com.example.academy.domain.mongodb;

import java.util.List;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "chat_rooms")
public class ChatRoom {

  @Id
  private String id;

  private String name;
  private List<String> memberIds;

}
