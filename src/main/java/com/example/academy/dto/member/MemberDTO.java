package com.example.academy.dto.member;


import com.example.academy.domain.mysql.ProfileImage;
import com.example.academy.type.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

  private String memberId;
  private String name;
  private String email;
  private String phone;
  private MemberType memberType;
  private ProfileImage profileImage;
  private String gitUrl;
  private String bio;

  public MemberDTO(String memberId, String name, String email, String phone, MemberType memberType,
      ProfileImage profileImage, String gitUrl, String bio) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.profileImage = profileImage;
    this.gitUrl = gitUrl;
    this.bio = bio;

  }
}