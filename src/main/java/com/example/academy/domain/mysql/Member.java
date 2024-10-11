package com.example.academy.domain.mysql;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

  @ManyToOne
  @JoinColumn(name = "member_type_id")
  private MemberType memberType;

  @ManyToOne
  @JoinColumn(name = "avatar_image_id", nullable = false)
  private AvatarImage avatarImageId;

  private String gitUrl;
  private String bio;

}