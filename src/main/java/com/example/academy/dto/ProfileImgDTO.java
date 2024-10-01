package com.example.academy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileImgDTO {

  private String pictureUrl;
  private Long memberId;

  public ProfileImgDTO(String pictureUrl, Long memberId) {
    this.pictureUrl = pictureUrl;
    this.memberId = memberId;
  }
}


