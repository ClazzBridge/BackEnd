package com.example.academy.dto.member;


import com.example.academy.domain.mysql.MemberType;
import com.example.academy.domain.mysql.AvatarImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

  private Long id;
  private String name;
  private String email;
  private String phone;
  private String memberType;
  private String avatarImageUrl;
  private String gitUrl;
  private String bio;

  public MemberDTO(Long id, String name, String email, String phone, MemberType memberType,
      String avatarImageUrl, String gitUrl, String bio) {

    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType.getType();
    this.avatarImageUrl = avatarImageUrl;
    this.gitUrl = gitUrl;
    this.bio = bio;

  }
}