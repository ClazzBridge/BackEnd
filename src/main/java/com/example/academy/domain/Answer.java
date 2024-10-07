package com.example.academy.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity // JPA에서 엔티티 클래스임을 나타냄. DB 테이블과 매핑되는 객체임을 의미.
@Table // 이 엔티티가 데이터베이스의 테이블과 매핑됨을 나타냄. 테이블의 이름 등을 설정할 수 있음.
@Getter // Lombok 라이브러리로, 모든 필드에 대한 getter 메서드를 자동 생성.
@Setter
public class Answer {

  @Id // 해당 필드가 테이블의 기본 키임을 나타냄.
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // 기본 키 값을 자동 생성하는 전략을 설정. IDENTITY 전략은 DB에서 자동으로 키 값을 생성.
  @Column(nullable = false) // 이 필드가 DB에서 null 값을 가질 수 없음을 명시.
  private Long id;

  @ManyToOne
  @JoinColumn(name = "question_id", nullable = false)
  private Question question;

  @OneToOne
  @JoinColumn(name = "teacher_id", nullable = false)
  private Member member;

  @NotNull  // null을 허용하지 않음
  @Size(min = 1)  // 최소 길이가 1 이상이어야 함
  @Column(nullable = false)  // 이 필드가 DB에서 null 값을 가질 수 없음을 명시.
  private String content;

  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;
}
