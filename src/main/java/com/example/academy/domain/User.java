package com.example.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

enum UserType {
  STUDENT,
  TEACHER,
  MANAGER;
}

@Entity // JPA에서 엔티티 클래스임을 나타냄. DB 테이블과 매핑되는 객체임을 의미.
@Table // 이 엔티티가 데이터베이스의 테이블과 매핑됨을 나타냄. 테이블의 이름 등을 설정할 수 있음.
@Getter // Lombok 라이브러리로, 모든 필드에 대한 getter 메서드를 자동 생성.
@Setter // Lombok 라이브러리로, 모든 필드에 대한 Setter 메서드를 자동 생성.
@NoArgsConstructor // Lombok 라이브러리로, 인자가 없는 기본 생성자를 자동으로 생성.
@AllArgsConstructor // Lombok 라이브러리로, 모든 필드를 인자로 받는 생성자를 자동으로 생성.
public class User {

  @Id // 해당 필드가 테이블의 기본 키임을 나타냄.
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // 기본 키 값을 자동 생성하는 전략을 설정. IDENTITY 전략은 DB에서 자동으로 키 값을 생성.
  @Column(nullable = false) // 이 필드가 DB에서 null 값을 가질 수 없음을 명시.
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String name;

  @NotNull
  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserType userType;
}