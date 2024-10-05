package com.example.academy.domain;

import com.example.academy.type.MemberType;
import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.Getter;

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

}