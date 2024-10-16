package com.example.academy.dto.member;

import com.example.academy.domain.mysql.AvatarImage;
import com.example.academy.domain.mysql.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberProfileUpdateDTO {
  private Long id;
  private String name;
  private String password;
  private String email;
  private String phone;
  private String gitUrl;
  private String bio;

  public MemberProfileUpdateDTO(Long id, String name, String email, String password, String phone, String gitUrl, String bio) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.gitUrl = gitUrl;
    this.bio = bio;
  }
}
