package com.example.academy.dto.member;


import com.example.academy.domain.mysql.AvatarImage;
import com.example.academy.domain.mysql.MemberType;
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
  private AvatarImage avatarImage;
  private String courseTitle;

  public MemberSignUpDTO() {
  }

  public MemberSignUpDTO(String memberId, String password, String name, String email, String phone,
      MemberType memberType, AvatarImage avatarImage, String courseTitle) {
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