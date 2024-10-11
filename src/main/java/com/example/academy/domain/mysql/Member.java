package com.example.academy.domain.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "avatar_image_id")
  private AvatarImage avatarImage;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_type_id", nullable = false)
  private MemberType memberType;

  @Size(max = 20)
  @NotNull
  @Column(name = "member_id", nullable = false, length = 20)
  private String memberId;

  @Size(max = 100)
  @NotNull
  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @Size(max = 10)
  @NotNull
  @Column(name = "name", nullable = false, length = 10)
  private String name;

  @Size(max = 30)
  @NotNull
  @Column(name = "email", nullable = false, length = 30)
  private String email;

  @Size(max = 20)
  @NotNull
  @Column(name = "phone", nullable = false, length = 20)
  private String phone;

  @Size(max = 100)
  @Column(name = "git_url", length = 100)
  private String gitUrl;

  @Lob
  @Column(name = "bio")
  private String bio;

}