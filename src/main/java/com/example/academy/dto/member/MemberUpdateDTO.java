package com.example.academy.dto.member;

import com.example.academy.domain.mysql.MemberType;
import com.example.academy.domain.mysql.AvatarImage;
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
  private AvatarImage avatarImage;
  private String courseTitle;

  public MemberUpdateDTO() {
  }

  public MemberUpdateDTO(Long id, String memberId, String password, String name, String email,
      String phone, MemberType memberType, AvatarImage avatarImage, String courseTitle) {
    this.id = id;
    this.memberId = memberId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.avatarImage = avatarImage;
    this.courseTitle = courseTitle;
  }
}