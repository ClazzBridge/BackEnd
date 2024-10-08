package com.example.academy.domain.mysql;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Member creator;

    @Column(columnDefinition = "json")
    private String options; // JSON 형식

    @Column(columnDefinition = "json")
    private String responses; // JSON 형식

    // Getters and Setters
}