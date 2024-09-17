-- 기존 테이블 삭제
drop table if exists User restrict;
drop table if exists Profile_image restrict;
drop table if exists Course restrict;
drop table if exists Classroom restrict;
drop table if exists Seat restrict;
drop table if exists Student_Course restrict;



-- User (사용자) 테이블 생성
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 사용자 고유 식별자 (Primary Key)
    name VARCHAR(100) NOT NULL,                      -- 사용자 이름
    email VARCHAR(100) UNIQUE NOT NULL,              -- 이메일 주소 (고유)
    phone VARCHAR(15) UNIQUE NOT NULL,               -- 전화번호 (고유)
    registration_date Date Not Null,                          -- 가입 날짜
    user_type ENUM('STUDENT', 'TEACHER', 'ROLE_ADMIN') NOT NULL,  -- 사용자 유형 (수강생, 강사, 관리자)
    profile_image_id INT NOT NULL,                   -- 프로필 사진 ID
    experience TEXT                                  -- 강사만 사용할 수 있는 경력 정보
);

-- Profile_image (프로필 사진) 테이블 생성
CREATE TABLE Profile_image (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 프로필 사진 고유 식별자 (Primary Key)
    picture_url VARCHAR(255)                         -- 프로필 사진 URL
);

-- Course (강의) 테이블 생성
CREATE TABLE Course (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 강의 고유 식별자 (Primary Key)
    title VARCHAR(100) NOT NULL,                     -- 강의 제목
    description TEXT NOT NULL,                       -- 강의 설명
    start_date DATE NOT NULL,                        -- 강의 시작 날짜
    end_date DATE NOT NULL,                          -- 강의 종료 날짜
    TEACHER_id INT,                               -- 강사 ID
    classroom_id INT                                 -- 강의실 ID
);

-- Classroom (강의실) 테이블 생성
CREATE TABLE Classroom (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 강의실 고유 식별자 (Primary Key)
    name VARCHAR(100) NOT NULL,                      -- 강의실 이름
    capacity INT NOT NULL,                           -- 강의실 수용 인원
    is_occupied BOOLEAN DEFAULT FALSE                -- 강의실 점유 여부 (기본값: 비어 있음)
);

-- Seat (좌석) 테이블 생성
CREATE TABLE Seat (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 좌석 고유 식별자 (Primary Key)
    classroom_id INT,                                -- 강의실 ID
    seat_number VARCHAR(10) NOT NULL,                -- 좌석 번호
    seat_presence BOOLEAN DEFAULT TRUE,              -- 좌석 존재 여부(기본값: 있음)
    is_occupied BOOLEAN DEFAULT FALSE,               -- 좌석 점유 여부 (기본값: 비어 있음)
    user_id INT                                      -- 좌석에 배정된 사용자 ID
);

-- Student_Course (수강생-강의) 테이블 생성
CREATE TABLE Student_Course (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 수강생-강의 고유 식별자 (Primary Key)
    student_id INT,                                  -- 수강생 ID
    course_id INT,                                   -- 강의 ID
    enrollment_date DATE                             -- 수강 등록 날짜
);

-- User 테이블에 profile_image_id에 대한 외래 키 추가
ALTER TABLE User
ADD CONSTRAINT fk_profile_image
FOREIGN KEY (profile_image_id) REFERENCES Profile_image(id);

-- Course 테이블에 instructor_id와 classroom_id에 대한 외래 키 추가
ALTER TABLE Course
ADD CONSTRAINT fk_TEACHER
FOREIGN KEY (TEACHER_id) REFERENCES User(id) ON DELETE SET NULL,
ADD CONSTRAINT fk_classroom
FOREIGN KEY (classroom_id) REFERENCES Classroom(id);

-- Seat 테이블에 classroom_id와 user_id에 대한 외래 키 추가
ALTER TABLE Seat
ADD CONSTRAINT fk_classroom_seat
FOREIGN KEY (classroom_id) REFERENCES Classroom(id) ON DELETE CASCADE,
ADD CONSTRAINT fk_user_seat
FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE SET NULL;

-- Student_Course 테이블에 student_id와 course_id에 대한 외래 키 추가
ALTER TABLE Student_Course
ADD CONSTRAINT fk_student
FOREIGN KEY (student_id) REFERENCES User(id) ON DELETE CASCADE,
ADD CONSTRAINT fk_course
FOREIGN KEY (course_id) REFERENCES Course(id);


-- Profile_image 더미 데이터 삽입 (고유번호 포함)
INSERT INTO Profile_image (id, picture_url)
VALUES
(1, 'https://example.com/images/user1.png'),
(2, 'https://example.com/images/user2.png'),
(3, 'https://example.com/images/user3.png');

-- User 더미 데이터 삽입 (고유번호 포함)
INSERT INTO User (id, name, email, phone, registration_date, user_type, profile_image_id, experience)
VALUES
(1, '홍길동', 'hong@example.com', '010-1234-5678', '2023-01-01', 'STUDENT', 1, NULL),
(2, '김철수', 'kim@example.com', '010-2345-6789', '2023-02-01', 'TEACHER', 2, '10년 이상 강의 경력'),
(3, '이영희', 'lee@example.com', '010-3456-7890', '2023-03-01', 'ROLE_ADMIN', 3, NULL);

-- Classroom 더미 데이터 삽입 (고유번호 포함)
INSERT INTO Classroom (id, name, capacity, is_occupied)
VALUES
(1, '101호', 40, FALSE),
(2, '102호', 30, TRUE);

-- Course 더미 데이터 삽입 (고유번호 포함)
INSERT INTO Course (id, title, description, start_date, end_date, TEACHER_id, classroom_id)
VALUES
(1, '데이터베이스 기초', '데이터베이스 설계와 관리에 대한 입문 강좌', '2023-03-01', '2023-06-30', 2, 1),
(2, '웹 프로그래밍', 'HTML, CSS, JavaScript를 활용한 웹 개발', '2023-03-15', '2023-07-15', 2, 2);

-- Seat 더미 데이터 삽입 (고유번호 포함)
INSERT INTO Seat (id, classroom_id, seat_number, seat_presence, is_occupied, user_id)
VALUES
(1, 1, 'A1', TRUE, FALSE, NULL),
(2, 1, 'A2', TRUE, TRUE, 1),
(3, 2, 'B1', TRUE, TRUE, 1);

-- Student_Course 더미 데이터 삽입 (고유번호 포함)
INSERT INTO Student_Course (id, student_id, course_id, enrollment_date)
VALUES
(1, 1, 1, '2023-02-20'),
(2, 1, 2, '2023-02-25');