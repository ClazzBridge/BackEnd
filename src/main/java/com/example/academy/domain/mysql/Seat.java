package com.example.academy.domain.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

  @NotNull
  @Column(nullable = false)
  private String seatNumber; // 좌석 번호

  @NotNull
  @Column(nullable = false)
  private Boolean isExist = true; // 자리 공석 여부

  @NotNull
  @Column(nullable = false)
  private Boolean isOnline = false; //온/오프

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;
}