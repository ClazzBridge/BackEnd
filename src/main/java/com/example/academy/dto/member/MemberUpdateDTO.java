package com.example.academy.dto.member;

import com.example.academy.domain.mysql.ProfileImage;
import com.example.academy.type.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDTO {

  private Long id;
  private String memberId;
  private String password;
  private String name;
  private String email;
  private String phone;
  private MemberType memberType;
  private ProfileImage profileImage;
  private String title;

  public MemberUpdateDTO() {
  }

  public MemberUpdateDTO(Long id, String memberId, String password, String name, String email,
      String phone, MemberType memberType, ProfileImage profileImage, String title) {
    this.id = id;
    this.memberId = memberId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.profileImage = profileImage;
    this.title = title;
  }
}