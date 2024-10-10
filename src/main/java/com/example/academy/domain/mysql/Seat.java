package com.example.academy.domain.mysql;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private String seatNumber;

    private boolean seatPresence;

    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // Getters and Setters
}