package com.example.academy.enums;

public enum BoardTypes {
    NOTICES("공지사항"),
    FREE("일반");

    private final String description;

    BoardTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
