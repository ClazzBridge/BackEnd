package com.example.academy.type;

public enum MemberType {

  ROLE_STUDENT("수강생"),
  ROLE_TEACHER("강사"),
  ROLE_ADMIN("관리자");

  private final String description;

  MemberType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
