package com.example.academy.dto.member;


import com.example.academy.domain.mysql.MemberType;
import com.example.academy.domain.mysql.AvatarImage;
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
  private String avatarImageUrl;
  private String gitUrl;
  private String bio;

  public MemberDTO(String memberId, String name, String email, String phone, MemberType memberType,
      String avatarImageUrl, String gitUrl, String bio) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.avatarImageUrl = avatarImageUrl;
    this.gitUrl = gitUrl;
    this.bio = bio;

  }
}