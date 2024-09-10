package com.example.academy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 자동 증가 ID

    @Column(length = 100, unique = true)
    private String userName;  // 사용자 계정 ID

    @Column(length = 100)
    private String password;  // 사용자 계정 비밀번호

    @Column( length = 100)
    private String name;  // 사용자 이름

    @Column(unique = true)
    private String email;  // 이메일 (고유)

    @Column(unique = true)
    private String phone;  // 전화번호 (고유)

    @Column(unique = true)
    private String gitUrl;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "registration_date")
    private LocalDate registrationDate;  // 가입 날짜

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private UserType role;  // 사용자 유형 (ENUM)


    @Column(name = "profile_image_id")
    private int profileImageId;  // 프로필 사진 ID

    @Column(columnDefinition = "TEXT")
    private String experience;  // 강사 경력 정보 (optional)

    public enum UserType {
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_STUDENT // 정의가 되어 있어야 합니다.
    }

    public UserList() {
    }


}
