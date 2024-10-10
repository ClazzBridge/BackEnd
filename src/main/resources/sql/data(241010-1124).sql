-- member 테이블의 외래 키 삭제
ALTER TABLE member
    DROP FOREIGN KEY fk_profile_image;

-- course 테이블의 외래 키 삭제
ALTER TABLE course
    DROP FOREIGN KEY fk_instructor_course;
ALTER TABLE course
    DROP FOREIGN KEY fk_classroom_course;


-- seat 테이블의 외래 키 삭제
ALTER TABLE seat
    DROP FOREIGN KEY fk_course_seat;
ALTER TABLE seat
    DROP FOREIGN KEY fk_member_seat;

-- student_course 테이블의 외래 키 삭제
ALTER TABLE student_course
    DROP FOREIGN KEY fk_student;
ALTER TABLE student_course
    DROP FOREIGN KEY fk_course;

-- assignment 테이블의 외래 키 삭제
ALTER TABLE assignment
    DROP FOREIGN KEY fk_course_assignment;

-- submission 테이블의 외래 키 삭제
ALTER TABLE submission
    DROP FOREIGN KEY fk_assignment_submission;
ALTER TABLE submission
    DROP FOREIGN KEY fk_student_submission;

-- board 테이블의 외래 키 삭제
ALTER TABLE board
    DROP FOREIGN KEY fk_board_type;

-- post 테이블의 외래 키 삭제
ALTER TABLE post
    DROP FOREIGN KEY fk_board_post;
ALTER TABLE post
    DROP FOREIGN KEY fk_course_post;
ALTER TABLE post
    DROP FOREIGN KEY fk_author_post;

-- comment 테이블의 외래 키 삭제
ALTER TABLE comment
    DROP FOREIGN KEY fk_post_comment;
ALTER TABLE comment
    DROP FOREIGN KEY fk_author_comment;

-- schedule 테이블의 외래 키 삭제
ALTER TABLE schedule
    DROP FOREIGN KEY fk_course_schedule;

-- vote 테이블의 외래 키 삭제
ALTER TABLE vote
    DROP FOREIGN KEY fk_creator_vote;
ALTER TABLE vote
    DROP FOREIGN KEY fk_course_vote;

-- answer 테이블의 외래 키 삭제
ALTER TABLE answer
    DROP FOREIGN KEY fk_question_Answer;
ALTER TABLE answer
    DROP FOREIGN KEY fk_teacher_Answer;

-- question 테이블의 외래 키 삭제
ALTER TABLE question
    DROP FOREIGN KEY fk_member_question;

-- student_status 테이블의 외래 키 삭제
ALTER TABLE student_status
    DROP FOREIGN KEY fk_member_student_status;

drop table if exists member restrict;
drop table if exists profile_image restrict;
drop table if exists course restrict;
drop table if exists classroom restrict;
drop table if exists seat restrict;
drop table if exists student_course restrict;
drop table if exists assignment restrict;
drop table if exists submission restrict;
drop table if exists board restrict;
drop table if exists post restrict;
drop table if exists comment restrict;
drop table if exists schedule restrict;
drop table if exists vote restrict;
drop table if exists question restrict;
drop table if exists answer restrict;
drop table if exists student_status restrict;
drop table if exists board_type restrict;



CREATE TABLE member
(
    id               INT AUTO_INCREMENT PRIMARY KEY,                               -- 사용자 고유 식별자 (Primary Key)
    member_id        VARCHAR(20) UNIQUE                                  NOT NULL, -- 사용자 계정
    password         VARCHAR(100)                                        NOT NULL, -- 사용자 비밀번호
    name             VARCHAR(10)                                         NOT NULL, -- 사용자 이름
    email            VARCHAR(30) UNIQUE                                  NOT NULL, -- 이메일 주소 (고유)
    phone            VARCHAR(20)                                         NOT NULL, -- 전화번호 (고유)
    member_type      ENUM ('ROLE_STUDENT', 'ROLE_TEACHER', 'ROLE_ADMIN') NOT NULL, -- 사용자 유형 (수강생, 강사, 관리자)
    profile_image_id INT,                                                          -- 프로필 사진 ID
    git_url          VARCHAR(100),                                                 -- git 주소
    bio              TEXT                                                          -- 수강생 자기소개 정보
);

-- profile_image (프로필 사진) 테이블 생성
CREATE TABLE profile_image
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 프로필 사진 고유 식별자 (Primary Key)
    picture_url VARCHAR(255)                    -- 프로필 사진 URL
);

-- course (강의) 테이블 생성
CREATE TABLE course
(
    id            INT AUTO_INCREMENT PRIMARY KEY, -- 강의 고유 식별자 (Primary Key)
    title         VARCHAR(100) NOT NULL UNIQUE,   -- 강의 제목
    description   TEXT         NOT NULL,          -- 강의 설명
    start_date    DATE         NOT NULL,          -- 강의 시작 날짜
    end_date      DATE         NOT NULL,          -- 강의 종료 날짜
    instructor_id INT,                            -- 강사 ID
    classroom_id  INT                             -- 강의실 ID
);

-- Classroom (강의실) 테이블 생성
CREATE TABLE classroom
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 강의실 고유 식별자 (Primary Key)
    name        VARCHAR(100) NOT NULL UNIQUE,   -- 강의실 이름
    capacity    INT          NOT NULL,          -- 강의실 수용 인원
    is_occupied BOOLEAN DEFAULT FALSE           -- 강의실 점유 여부 (기본값: 비어 있음)
);

-- seat (좌석) 테이블 생성
CREATE TABLE seat
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 좌석 고유 식별자 (Primary Key)
    course_id   INT,                            -- 강의 ID
    seat_number VARCHAR(10) NOT NULL,           -- 좌석 번호
    is_exist    BOOLEAN DEFAULT TRUE,           -- 좌석 존재 여부(기본값: 있음)
    is_online   BOOLEAN DEFAULT FALSE,          -- 출석 여부 (기본값: 오프라인)
    member_id   INT                             -- 좌석에 배정된 사용자 ID
);

-- student_course (수강생-강의) 테이블 생성
CREATE TABLE student_course
(
    id         INT AUTO_INCREMENT PRIMARY KEY, -- 수강생-강의 고유 식별자 (Primary Key)
    student_id INT,                            -- 수강생 ID
    course_id  INT                             -- 강의 ID
);

-- assignment (과제) 테이블 생성
CREATE TABLE assignment
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 과제 고유 식별자 (Primary Key)
    course_id   INT,                            -- 강의 ID
    title       VARCHAR(100) NOT NULL,          -- 과제 제목
    description TEXT,                           -- 과제 설명
    due_date    DATE                            -- 과제 마감 날짜
);

-- submission (과제 제출) 테이블 생성
CREATE TABLE submission
(
    id              INT AUTO_INCREMENT PRIMARY KEY, -- 과제 제출 고유 식별자 (Primary Key)
    assignment_id   INT,                            -- 과제 ID
    student_id      INT,                            -- 수강생 ID
    title           VARCHAR(100) NOT NULL,          -- 제출 제목
    content         TEXT,                           -- 제출 내용
    submission_url  VARCHAR(255),                   -- 제출 파일 경로
    submission_date DATE                            -- 제출 날짜
);

-- board (게시판) 테이블 생성
CREATE TABLE board
(
    id            INT AUTO_INCREMENT PRIMARY KEY, -- 게시판 고유 식별자 (Primary Key)
    board_type_id INT                             -- 게시판 유형 ID
);

-- board_type (게시판 유형) 테이블 생성
CREATE TABLE board_type
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 게시판 유형 고유 식별자 (PK)
    description VARCHAR(100) NOT NULL UNIQUE    -- 게시판 카테고리
);

-- post (게시물) 테이블 생성
CREATE TABLE post
(
    id         INT AUTO_INCREMENT PRIMARY KEY,                                 -- 게시물 고유 식별자 (Primary Key)
    board_id   INT,                                                            -- 게시판 ID
    course_id  INT,                                                            -- 강의 ID
    author_id  INT,                                                            -- 작성자 ID
    title      VARCHAR(100) NOT NULL,                                          -- 게시물 제목
    content    TEXT,                                                           -- 게시물 내용
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                            -- 게시물 작성 시각
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 게시물 수정 시각
);

-- comment (댓글) 테이블 생성
CREATE TABLE comment
(
    id         INT AUTO_INCREMENT PRIMARY KEY,                                 -- 댓글 고유 식별자 (Primary Key)
    post_id    INT,                                                            -- 게시물 ID
    author_id  INT,                                                            -- 작성자 ID
    content    TEXT,                                                           -- 댓글 내용
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                            -- 댓글 작성 시각
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 댓글 수정 시각
);

-- schedule (일정) 테이블 생성
CREATE TABLE schedule
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 강의실 일정 고유 식별자 (Primary Key)
    course_id   INT,                            -- 강의 ID
    event_title VARCHAR(100) NOT NULL,          -- 일정 제목
    start_date  TIMESTAMP,                      -- 일정 시작 날짜
    end_date    TIMESTAMP,                      -- 일정 종료 날짜
    description TEXT                            -- 일정 설명
);

-- vote (투표) 테이블 생성
CREATE TABLE vote
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 투표 고유 식별자 (Primary Key)
    course_id   INT,                            -- 강의 ID
    title       VARCHAR(100) NOT NULL,          -- 투표 제목
    description TEXT,                           -- 투표 설명
    start_date  TIMESTAMP,                      -- 투표 시작 날짜
    end_date    TIMESTAMP,                      -- 투표 종료 날짜
    is_expired  BOOLEAN DEFAULT FALSE,          -- 투표 종료 여부
    creator_id  INT,                            -- 투표 생성자 ID
    options     JSON,                           -- 투표 항목 (JSON 형식)
    responses   JSON                            -- 투표 결과 (JSON 형식)
);

-- question (질문) 테이블 생성
CREATE TABLE question
(
    id             int AUTO_INCREMENT PRIMARY KEY,                                 -- 질문 고유 식별자 (Primary Key)
    member_id      INT,                                                            -- 작성자 ID
    content        TEXT NOT NULL,                                                  -- 질문 제목
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,                             -- 질문 기재 시간
    updated_at     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 질문 수정 시간
    is_solved      BOOLEAN  DEFAULT FALSE,                                         -- 답변 완료 여부
    is_recommended BOOLEAN  DEFAULT FALSE                                          -- 강사님의 질문 추천 여부
);

-- answer (답변) 테이블 생성
CREATE TABLE answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,                                -- 답변 고유 식별자 (Primary Key)
    question_id INT,                                                           -- 질문 ID
    teacher_id  INT,                                                           -- 작성자 ID
    content     TEXT NOT NULL,                                                 -- 답변 내용
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,                            -- 답변 작성 시각
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 답변 수정 시각
);

-- student_status (학생 상태) 테이블 생성
CREATE TABLE student_status
(
    id               INT AUTO_INCREMENT PRIMARY KEY, -- 학생 상태 고유 식별자 (Primary Key)
    is_understanding BOOLEAN DEFAULT FALSE,          -- 이해 여부
    is_hand_raised   BOOLEAN DEFAULT FALSE,          -- 손 들기 여부
    member_id        INT                             -- 멤버 ID
);


-- member 테이블에 profile_image_id에 대한 외래 키 추가
ALTER TABLE member
    ADD CONSTRAINT fk_profile_image
        FOREIGN KEY (profile_image_id) REFERENCES profile_image (id) ON DELETE SET NULL;

-- Course 테이블에 instructor_id와 classroom_id에 대한 외래 키 추가
ALTER TABLE course
    ADD CONSTRAINT fk_instructor_course
        FOREIGN KEY (instructor_id) REFERENCES member (id) ON DELETE SET NULL,
    ADD CONSTRAINT fk_classroom_course
        FOREIGN KEY (classroom_id) REFERENCES classroom (id);

-- Seat 테이블에 classroom_id와 user_id에 대한 외래 키 추가
ALTER TABLE seat
    ADD CONSTRAINT fk_course_seat
        FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_member_seat
        FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE SET NULL;

-- Student_Course 테이블에 student_id와 course_id에 대한 외래 키 추가
ALTER TABLE student_course
    ADD CONSTRAINT fk_student
        FOREIGN KEY (student_id) REFERENCES member (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_course
        FOREIGN KEY (course_id) REFERENCES course (id);

-- assignment 테이블에 course_id에 대한 외래 키 추가
ALTER TABLE assignment
    ADD CONSTRAINT fk_course_assignment
        FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE;

-- Submission 테이블에 assignment_id와 student_id에 대한 외래 키 추가
ALTER TABLE submission
    ADD CONSTRAINT fk_assignment_submission
        FOREIGN KEY (assignment_id) REFERENCES assignment (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_student_submission
        FOREIGN KEY (student_id) REFERENCES member (id) ON DELETE CASCADE;

-- 게시판 테이블에 외래 키 추가
ALTER TABLE board
    ADD CONSTRAINT fk_board_type
        FOREIGN KEY (board_type_id) REFERENCES board_type (id) ON DELETE CASCADE;

-- Post 테이블에 board_id, classroom_id, author_id에 대한 외래 키 추가
ALTER TABLE post
    ADD CONSTRAINT fk_board_post
        FOREIGN KEY (board_id) REFERENCES board (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_course_post
        FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_author_post
        FOREIGN KEY (author_id) REFERENCES member (id) ON DELETE CASCADE;

-- Comment 테이블에 post_id와 author_id에 대한 외래 키 추가
ALTER TABLE comment
    ADD CONSTRAINT fk_post_comment
        FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_author_comment
        FOREIGN KEY (author_id) REFERENCES member (id) ON DELETE CASCADE;

-- schedule 테이블에 Classroom 대한 외래 키 추가
ALTER TABLE schedule
    ADD CONSTRAINT fk_course_schedule
        FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE;

-- Vote 테이블에 creator_id에 대한 외래 키 추가
ALTER TABLE vote
    ADD CONSTRAINT fk_creator_vote
        FOREIGN KEY (creator_id) REFERENCES member (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_course_vote
        FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE;

-- Answer 테이블에 question_id와 user_id에 대한 외래 키 추가
ALTER TABLE answer
    ADD CONSTRAINT fk_question_answer
        FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_teacher_answer
        FOREIGN KEY (teacher_id) REFERENCES member (id) ON DELETE CASCADE;

-- Question 테이블에 author_id에 대한 외래 키 추가
ALTER TABLE question
    ADD CONSTRAINT fk_member_question
        FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE;

-- Question 테이블에 author_id에 대한 외래 키 추가
ALTER TABLE student_status
    ADD CONSTRAINT fk_member_student_status
        FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE;



