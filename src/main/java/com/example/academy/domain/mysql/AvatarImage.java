package com.example.academy.domain.mysql;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "avatar_image")
public class AvatarImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "avatar_image_url" ,nullable = false)
  private String avatarImageUrl;


}