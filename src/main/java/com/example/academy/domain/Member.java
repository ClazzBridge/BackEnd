package com.example.academy.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import com.example.academy.type.MemberType;
import javax.persistence.TemporalType;
import lombok.Data;

  @Data
  @Entity
  @Table(name = "member")
  public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String memberId; //로그인용 아이디

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType memberType;

    @ManyToOne
    @JoinColumn(name = "profile_image_id", nullable = false)
    private ProfileImage profileImage;

    private String experience;
    private String gitUrl;
    private String bio;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private StudentStatus studentStatus;

  }

