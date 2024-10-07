package com.example.academy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDTO {

  String value;

  public RefreshTokenDTO() {
  }

  public RefreshTokenDTO(String value) {
    this.value = value;
  }
}
