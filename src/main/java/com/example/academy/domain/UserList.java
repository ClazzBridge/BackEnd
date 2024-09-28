package com.example.academy.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "User_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false,  unique = true)
  private String userId;

  @NotNull
  @Column(nullable = false)
  private String password;

  @NotNull
  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String gitUrl;

  @Column(unique = true, length = 15)
  @NotNull
  private String phone;

  @Column(nullable = false)
  @CreationTimestamp
  @NotNull
  @Temporal(TemporalType.DATE)
  private Date registrationDate;

  @Column(nullable = false)
  @NotNull
  @Enumerated(value = EnumType.STRING)
  private UserType userType;

  @NotNull
  @JoinColumn(name = "profile_image_id")
  @ManyToOne
  private ProfileImage profileImage;

  private String experience;

  private String bio;

  public enum UserType {
    ROLE_ADMIN, STUDENT, TEACHER;
  }

}


