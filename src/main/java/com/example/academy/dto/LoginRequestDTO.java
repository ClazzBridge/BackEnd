package com.example.academy.dto;

import com.example.academy.domain.ProfileImage;
import com.example.academy.type.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

  private String memberId;
  private String password;
  private String name;
  private String email;
  private String phone;
  private MemberType memberType;
  private ProfileImage profileImage;

  public LoginRequestDTO() {
  }

  public LoginRequestDTO(String memberId, String password, String name, String email, String phone,
      MemberType memberType, ProfileImage profileImage) {
    this.memberId = memberId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.profileImage = profileImage;
  }
}