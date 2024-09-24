package com.example.academy.domain;

import javax.persistence.*;
import lombok.Getter;

@Getter

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    private boolean isOccupied;

    // Getters and Setters
}