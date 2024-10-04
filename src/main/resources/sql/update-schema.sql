ALTER TABLE answer
    DROP FOREIGN KEY fk_question_Answer;

ALTER TABLE answer
    DROP FOREIGN KEY fk_teacher_Answer;

ALTER TABLE question
    DROP FOREIGN KEY fk_user_id_Question;

DROP TABLE answer;

DROP TABLE question;

DROP TABLE user;

-- User (사용자) 테이블 생성
CREATE TABLE user
(
    id        INT AUTO_INCREMENT PRIMARY KEY,                     -- 사용자 고유 식별자 (Primary Key)
    name      VARCHAR(100)                              NOT NULL, -- 사용자 이름
    user_type ENUM ('STUDENT', 'TEACHER', 'ROLE_ADMIN') NOT NULL  -- 사용자 유형 (수강생, 강사, 관리자)
);

-- Question (질문) 테이블 생성
CREATE TABLE question
(
    id             int AUTO_INCREMENT PRIMARY KEY,     -- 질문 고유 식별자 (Primary Key)
    user_id        INT  NOT NULL,                      -- 작성자 ID
    content        TEXT NOT NULL,                      -- 질문 제목
    create_date    DATETIME DEFAULT CURRENT_TIMESTAMP, -- 질문 기재 시간
    is_solved      BOOLEAN  DEFAULT FALSE,             -- 답변 완료 여부
    is_recommended BOOLEAN  DEFAULT FALSE              -- 강사님의 질문 추천 여부
);

-- Answer (답변) 테이블 생성
CREATE TABLE answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,      -- 답변 고유 식별자 (Primary Key)
    question_id INT  NOT NULL,                       -- 질문 ID
    teacher_id  INT  NOT NULL,                       -- 작성자 ID
    content     TEXT NOT NULL,                       -- 답변 내용
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 답변 작성 시각
    updated_at  DATETIME ON UPDATE CURRENT_TIMESTAMP -- 답변 수정 시각
);

-- StudentStatus (학생 상태) 테이블 생성
CREATE TABLE student_status
(
    id               INT AUTO_INCREMENT PRIMARY KEY, -- 학생 상태 고유 식별자 (Primary Key)
    is_understanding BOOLEAN DEFAULT FALSE,          -- 이해 여부
    is_hand_raised   BOOLEAN DEFAULT FALSE,          -- 손 들기 여부
    member_id        INT NOT NULL                    -- 멤버 ID
);

-- Answer 테이블에 question_id와 user_id에 대한 외래 키 추가
ALTER TABLE answer
    ADD CONSTRAINT fk_question_Answer
        FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_teacher_Answer
        FOREIGN KEY (teacher_id) REFERENCES user (id);

-- Question 테이블에 author_id에 대한 외래 키 추가
ALTER TABLE question
    ADD CONSTRAINT fk_user_id_Question
        FOREIGN KEY (user_id) REFERENCES user (id);

-- Question 테이블에 author_id에 대한 외래 키 추가
ALTER TABLE student_status
    ADD CONSTRAINT fk_user_id_Question
        FOREIGN KEY (member_id) REFERENCES user (id);



-- User 더미 데이터 생성
-- insert into User values(1, 'djdj12', '1111', '김창호',  'user1@test.com', '010-1111-1111', '2024-03-03', 'Teacher', 1, '사자의 왕 백수', 'www.dd.com', 'sexy boy');
-- insert into User values(2, 'dkdk22', '2222', '김양호',  'user2@test.com', '010-1111-2222', '2024-04-04', 'Student', 1, '홀리나이트', 'www.dd.com', 'sexy boy');
-- insert into User values(3, 'wowbow', '3333', '김광진',  'user3@test.com', '010-1111-3333', '2024-05-05', 'Student', 1, '전사', 'www.dd.com', 'sexy boy');
insert into user
values (1, '김창호', 'TEACHER');
insert into user
values (2, '김양호', 'STUDENT');
insert into user
values (3, '김광진', 'STUDENT');

-- Question (질문) 더미 데이터 생성
insert into question
values (1, 2, 'String이 무엇인가요', '2024-03-03 13:00:00', true, false);
insert into question
values (2, 2, 'int가 무엇인가요', '2024-03-03 14:03:25', true, true);
insert into question
values (3, 3, 'boolean이 무엇인가요', '2024-03-03 15:15:33', false, false);

-- Answer (답변) 더미 데이터 생성
insert into answer
values (1, 1, 1, '나도 몰라요', '2024-05-05 15:03:26', '2024-05-06 15:03:03');
insert into answer
values (2, 2, 1, '니가 공부해라', '2024-08-05 20:08:11', '2024-08-06 20:16:03');
insert into answer
values (3, 3, 1, '월급을 더 주세요', '2024-09-05 09:03:26', '2024-09-06 15:03:03');

-- Seat (좌석) 더미 데이터 생성
insert into student_status
values (1, false, false, 2);
insert into student_status
values (2, false, false, 3);