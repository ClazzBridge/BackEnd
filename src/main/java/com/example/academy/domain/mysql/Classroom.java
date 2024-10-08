package com.example.academy.domain.mysql;

import javax.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "classroom")
public class Classroom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name; //강의실 명

  @Column(nullable = false)
  private int capacity; // 수용 인원

  private boolean isOccupied; //점유 여부

  // Getters and Setters
}