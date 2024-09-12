-- 기존 테이블 삭제
drop table if exists Question restrict;
drop table if exists Answer restrict;
drop table if exists User restrict;

-- User (사용자) 테이블 생성
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 사용자 고유 식별자 (Primary Key)
    name VARCHAR(100) NOT NULL,                      -- 사용자 이름
    user_type ENUM('STUDENT', 'TEACHER', 'ROLE_ADMIN') NOT NULL  -- 사용자 유형 (수강생, 강사, 관리자)
);

-- Question (질문) 테이블 생성
CREATE TABLE Question (
    id int AUTO_INCREMENT PRIMARY KEY,               -- 질문 고유 식별자 (Primary Key)
    author_id INT,                                   -- 작성자 ID
    content VARCHAR(100) NOT NULL,                     -- 질문 제목
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 질문 기재 시간
    answer_complete BOOLEAN,                         -- 답변 완료 여부
    recommend BOOLEAN                                -- 강사님의 질문 추천 여부
);

-- Answer (답변) 테이블 생성
CREATE TABLE Answer (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 답변 고유 식별자 (Primary Key)
    question_id INT,                                 -- 질문 ID
    teacher_id INT,                                  -- 작성자 ID
    content TEXT,                                    -- 답변 내용
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,   -- 답변 작성 시각
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 답변 수정 시각
);

-- Answer 테이블에 question_id와 user_id에 대한 외래 키 추가
ALTER TABLE Answer
ADD CONSTRAINT fk_question_Answer
FOREIGN KEY (question_id) REFERENCES Question(id),
ADD CONSTRAINT fk_teacher_Answer
FOREIGN KEY (teacher_id) REFERENCES User(id) ON DELETE SET NULL;

-- Question 테이블에 author_id에 대한 외래 키 추가
ALTER TABLE Question
ADD CONSTRAINT fk_author_Question
FOREIGN KEY (author_id) REFERENCES User(id) ON DELETE SET NULL;

-- User 더미 데이터 생성
-- insert into User values(1, 'djdj12', '1111', '김창호',  'user1@test.com', '010-1111-1111', '2024-03-03', 'Teacher', 1, '사자의 왕 백수', 'www.dd.com', 'sexy boy');
-- insert into User values(2, 'dkdk22', '2222', '김양호',  'user2@test.com', '010-1111-2222', '2024-04-04', 'Student', 1, '홀리나이트', 'www.dd.com', 'sexy boy');
-- insert into User values(3, 'wowbow', '3333', '김광진',  'user3@test.com', '010-1111-3333', '2024-05-05', 'Student', 1, '전사', 'www.dd.com', 'sexy boy');
insert into User values(1, '김창호', 'TEACHER');
insert into User values(2, '김양호', 'STUDENT');
insert into User values(3, '김광진', 'STUDENT');

-- Question (질문) 더미 데이터 생성
insert into Question values(1, 2, 'String이 무엇인가요', '2024-03-03 13:00:00', true, false);
insert into Question values(2, 2, 'int가 무엇인가요', '2024-03-03 14:03:25', true, true);
insert into Question values(3, 3, 'boolean이 무엇인가요', '2024-03-03 15:15:33', false, false);

-- Answer (답변) 더미 데이터 생성
insert into Answer values(1,1,1, '나도 몰라요','2024-05-05 15:03:26', '2024-05-06 15:03:03');
insert into Answer values(2,2,1, '니가 공부해라','2024-08-05 20:08:11', '2024-08-06 20:16:03');
insert into Answer values(3,3,1, '월급을 더 주세요','2024-09-05 09:03:26', '2024-09-06 15:03:03');