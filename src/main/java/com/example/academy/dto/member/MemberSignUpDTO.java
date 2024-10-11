package com.example.academy.dto.member;

import com.example.academy.type.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpDTO {

  private String memberId;
  private String password;
  private String name;
  private String email;
  private String phone;
  private MemberType memberType;
  private ProfileImage profileImage;
  private String courseTitle;

  public MemberSignUpDTO() {
  }

  public MemberSignUpDTO(String memberId, String password, String name, String email, String phone,
      MemberType memberType, ProfileImage profileImage, String courseTitle) {
    this.memberId = memberId;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.profileImage = profileImage;
    this.courseTitle = courseTitle;
  }
}