package com.example.academy.domain.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "avatar_image")
public class AvatarImage {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Size(max = 255)
  @NotNull
  @Column(name = "avatar_image_url", nullable = false)
  private String avatarImageUrl;

}