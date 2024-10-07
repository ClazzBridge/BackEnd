package com.example.academy.dto;


import javax.servlet.http.Cookie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

  private String accessToken;
  private Cookie refreshTokenCookie;

  public LoginResponseDTO(String accessToken, Cookie refreshTokenCookie) {
    this.accessToken = accessToken;
    this.refreshTokenCookie = refreshTokenCookie;
  }

  // Getter/Setter 생략
}
