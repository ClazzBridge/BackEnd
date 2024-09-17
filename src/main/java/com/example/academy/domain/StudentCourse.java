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
@Table(name = "Seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {


  @NotNull
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "classroom_id")
  private ClassRoom classRoom;

  @NotNull
  @Column(nullable = false)
  private String seatNumber;

  @NotNull
  @Column(nullable = false)
  private boolean seatPresence = true;

  @NotNull
  @Column(nullable = false)
  private boolean isOccupied = false;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
