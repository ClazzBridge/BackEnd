package com.example.academy.dto.auth;

import com.example.academy.domain.mysql.ProfileImage;
import com.example.academy.type.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

  private String memberId;
  private String password;


  public LoginRequestDTO() {
  }

  public LoginRequestDTO(String memberId, String password) {
    this.memberId = memberId;
    this.password = password;
  }
}