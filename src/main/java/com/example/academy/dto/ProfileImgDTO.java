package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileImgDTO {

  private String pictureUrl;
  private Long userId;

  public ProfileImgDTO(String pictureUrl, Long userId) {
    this.pictureUrl = pictureUrl;
    this.userId = userId;
  }
}


