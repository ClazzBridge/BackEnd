package com.example.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentStatus {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private Boolean isUnderstanding = false; //이해도 측정

  @NotNull
  @Column(nullable = false)
  private Boolean isHandRaised = false; // 손들기

  @OneToOne
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;


}
