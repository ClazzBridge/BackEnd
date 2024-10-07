package com.example.academy.dto;

import com.example.academy.domain.Member;
import com.example.academy.domain.ProfileImage;
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
  private StudentStatusDTO studentStatusDTO;

  public MemberDTO(String memberId, String name, String email, String phone, MemberType memberType,
      ProfileImage profileImage, String gitUrl, String bio, StudentStatusDTO studentStatusDTO) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.memberType = memberType;
    this.profileImage = profileImage;
    this.gitUrl = gitUrl;
    this.bio = bio;

    if (studentStatusDTO != null) {
      this.studentStatusDTO = new StudentStatusDTO(
          studentStatusDTO.getId(),
          studentStatusDTO.getIsUnderStanding(),
          studentStatusDTO.getIsHandRaised()
      );
    }
  }
}