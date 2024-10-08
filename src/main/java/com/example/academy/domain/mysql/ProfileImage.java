package com.example.academy.domain.mysql;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profile_image")
public class ProfileImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String pictureUrl;

  // Getters and Setters

  public String getPictureUrl() {
    return pictureUrl;
  }
}