package com.example.academy.dto;

import com.example.academy.domain.StudentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentStatusDTO {

  private Long id;
  private Boolean isUnderstanding;
  private Boolean isHandRaised;


  public StudentStatusDTO(StudentStatus studentStatus) {
    this.id = studentStatus.getId();
    this.isUnderstanding = studentStatus.getIsUnderstanding();
    this.isHandRaised = studentStatus.getIsHandRaised();
  }

  // 새로운 생성자: 개별 필드를 받는 생성자 추가
  public StudentStatusDTO(Long id, Boolean isUnderStanding, Boolean isHandRaised) {
    this.id = id;
    this.isUnderstanding = isUnderStanding;
    this.isHandRaised = isHandRaised;
  }

}
