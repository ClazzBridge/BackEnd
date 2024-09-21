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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.usertype.UserType;


@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(unique = true)
  private String email;

  @NotNull
  @Column(unique = true)
  private String github;

  @Column(unique = true)
  @NotNull
  private String phone;

  @Column(nullable = false)
  @CreationTimestamp
  @NotNull
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

  public enum UserType {
    ADMIN, STUDENT, TEACHER;
  }

}


