package com.example.academy.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

  private String userName;
  private String password;

  public LoginRequestDTO() {
  }

  public LoginRequestDTO(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }
}
