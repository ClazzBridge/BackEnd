package com.example.academy.dto.member;

import com.example.academy.domain.mysql.AvatarImage;
import com.example.academy.type.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMemberDTO {

  private Long id;
  private String memberId;
  private String password;
  private String name;
  private String email;
  private String phone;
  private MemberType memberType;
  private String avatarImage;
  private String courseTitle;

  public GetMemberDTO() {
  }

  public GetMemberDTO(Long id, String memberId, String password, String name, String email,
      String phone, MemberType memberType, String avatarImage, String courseTitle) {
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