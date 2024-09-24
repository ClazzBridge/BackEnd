package com.example.academy.dto;

public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String boardType;
    private String classroomName;

    // 생성자 및 getter, setter

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String content, String authorName, String boardType,
        String classroomName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.boardType = boardType;
        this.classroomName = classroomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
