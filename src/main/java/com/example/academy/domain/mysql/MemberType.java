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
@Table(name = "member_type")
public class MemberType {

  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Size(max = 100)
  @NotNull
  @Column(name = "type", nullable = false, length = 100)
  private String type;

}