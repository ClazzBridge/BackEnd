-- 기존 테이블 삭제
drop table if exists profile_image restrict;
drop table if exists classroom restrict;
drop table if exists seat restrict;
drop table if exists user_list restrict;





-- User (사용자) 테이블 생성
CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 사용자 고유 식별자 (Primary Key)
    member_id VARCHAR(100) UNIQUE NOT NULL,             -- 사용자 계정
    password VARCHAR(100) NOT NULL,                  -- 사용자 비밀번호
    name VARCHAR(100) NOT NULL,                      -- 사용자 이름
    email VARCHAR(100) UNIQUE NOT NULL,              -- 이메일 주소 (고유)
    phone VARCHAR(15) UNIQUE NOT NULL,               -- 전화번호 (고유)
    registration_date DATE,                          -- 가입 날짜
    member_type ENUM('ROLE_STUDENT', 'ROLE_TEACHER', 'ROLE_ADMIN') NOT NULL,  -- 사용자 유형 (수강생, 강사, 관리자)
    profile_image_id INT NOT NULL,                   -- 프로필 사진 ID
    experience TEXT,                                 -- 강사만 사용할 수 있는 경력 정보
    git_url VARCHAR(100),                            -- git 주소
    bio TEXT                                         -- 수강생 자기소개 정보
);

-- Profile_image (프로필 사진) 테이블 생성
CREATE TABLE profile_image (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 프로필 사진 고유 식별자 (Primary Key)
    picture_url VARCHAR(255)                         -- 프로필 사진 URL
);

-- Seat (좌석) 테이블 생성
CREATE TABLE seat (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 좌석 고유 식별자 (Primary Key)
    classroom_id INT,                                -- 강의실 ID
    seat_number VARCHAR(10) NOT NULL,                -- 좌석 번호
    is_exist BOOLEAN DEFAULT TRUE,                   -- 좌석 존재 여부(기본값: 있음)
    is_occupied BOOLEAN DEFAULT FALSE,               -- 좌석 점유 여부 (기본값: 비어 있음)
    is_understand BOOLEAN DEFAULT FALSE,             -- 이해도 측정 여부(기본값: 비어 있음)
    member_id INT                                    -- 좌석에 배정된 사용자 ID
);

-- Classroom (강의실) 테이블 생성
CREATE TABLE classroom (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 강의실 고유 식별자 (Primary Key)
    name VARCHAR(100) NOT NULL,                      -- 강의실 이름
    capacity INT NOT NULL,                           -- 강의실 수용 인원
    is_occupied BOOLEAN DEFAULT FALSE                -- 강의실 점유 여부 (기본값: 비어 있음)
);



-- user_list 테이블에 profile_image_id에 대한 외래 키 추가
ALTER TABLE user_list
ADD CONSTRAINT fk_profile_image
FOREIGN KEY (profile_image_id) REFERENCES profile_image(id);


-- Seat 테이블에 classroom_id와 user_id에 대한 외래 키 추가
ALTER TABLE seat
ADD CONSTRAINT fk_classroom_seat
FOREIGN KEY (classroom_id) REFERENCES classroom(id) ON DELETE CASCADE,
ADD CONSTRAINT fk_member_seat
FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE SET NULL;



-- Profile_image 더미 데이터 삽입 (고유번호 포함)
INSERT INTO profile_image (id, picture_url)
VALUES
(1, 'https://i.ytimg.com/vi/L3LR1DIz0Rg/maxresdefault.jpg'),
(2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWwMr-YD5a_SLFkxN05JCbXqS44QiwnVt6GA&s'),
(3, 'https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/11/urban-20231115105709412694.jpg'),
(4, 'https://i.ytimg.com/vi/L3LR1DIz0Rg/maxresdefault.jpg'),
(5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWwMr-YD5a_SLFkxN05JCbXqS44QiwnVt6GA&s'),
(6, 'https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/11/urban-20231115105709412694.jpg'),
(7, 'https://cdn.pixabay.com/photo/2023/09/13/07/29/ghost-8250317_640.png'),
(8, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png'),
(9, 'https://cdn.pixabay.com/photo/2023/09/13/07/29/ghost-8250317_640.png'),
(10, 'https://cdn.pixabay.com/photo/2023/09/13/07/29/ghost-8250317_640.png'),
(11, 'https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/11/urban-20231115105709412694.jpg'),
(12, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png'),
(13, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png'),
(14, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWwMr-YD5a_SLFkxN05JCbXqS44QiwnVt6GA&s'),
(15, 'https://i.ytimg.com/vi/L3LR1DIz0Rg/maxresdefault.jpg'),
(16, 'https://cdn.pixabay.com/photo/2023/09/13/07/29/ghost-8250317_640.png'),
(17, 'https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/11/urban-20231115105709412694.jpg'),
(18, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png'),
(19, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png'),
(20, 'https://stickershop.line-scdn.net/stickershop/v1/product/23796452/LINEStorePC/main.png?v=1'),
(21, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png'),
(22, 'https://cdn.pixabay.com/photo/2023/09/13/07/29/ghost-8250317_640.png'),
(23, 'https://i.ytimg.com/vi/L3LR1DIz0Rg/maxresdefault.jpg'),
(24, 'https://www.urbanbrush.net/web/wp-content/uploads/edd/2023/11/urban-20231115105709412694.jpg'),
(25, 'https://ditoday.com/wp-content/uploads/2022/09/%EB%A5%B4%ED%83%84.png');

-- User 더미 데이터 삽입 (고유번호 포함)
INSERT INTO member (id, member_id, password, name, email, phone, registration_date, member_type, profile_image_id, experience, git_url, bio)
VALUES
(1, 'woo12', '1q2w3e4r!', '이우성', 'woo@example.com', '010-1234-1234', '2023-01-01', 'STUDENT', 1, NULL, 'https://github.com/woo', '저는 이우성 입니다.'),
(2, 'lee12', '1q2w3e4r!', '이건학', 'lee@example.com', '010-2345-2345', '2023-01-01', 'STUDENT', 2, NULL, 'https://github.com/lee', '저는 이건학 입니다.'),
(3, 'sin14', '1q2w3e4r!', '신승민', 'sin@example.com', '010-3456-3456', '2023-01-01', 'STUDENT', 3, NULL, 'https://github.com/sin', '저는 신승민 입니다.'),



INSERT INTO classroom (id, name, capacity, is_occupied)
VALUES
(1, '1강의실', 30, FALSE),
(2, '2강의실', 25, TRUE),
(3, '3강의실', 20, FALSE);


-- Seat 더미 데이터 삽입 (고유번호 포함)
INSERT INTO seat (id, classroom_id, seat_number, is_exist, is_occupied, is_understand, member_id)
VALUES
(1, 1, '01', FALSE, FALSE, FALSE, null),
(2, 1, '02', TRUE, TRUE, TRUE, 1),
(3, 1, '03', TRUE, TRUE, TRUE, 8),
(4, 1, '04', FALSE, FALSE, FALSE, null),
(5, 1, '05', FALSE, FALSE, FALSE, null),
(6, 1, '06', FALSE, FALSE, FALSE, null),
(7, 1, '07', FALSE, FALSE, FALSE, null),
(8, 1, '08', FALSE, FALSE, FALSE, null),
(9, 1, '09', FALSE, FALSE, FALSE, null),
(10, 1, '10', FALSE, FALSE, FALSE, null),
(11, 1, '11', FALSE, FALSE, FALSE, null),
(12, 1, '12', FALSE, FALSE, FALSE, null),
(13, 1, '13', FALSE, FALSE, FALSE, null),
(14, 1, '14', FALSE, FALSE, FALSE, null),
(15, 1, '15', FALSE, FALSE, FALSE, null),
(16, 1, '16', FALSE, FALSE, FALSE, null),
(17, 1, '17', FALSE, FALSE, FALSE, null),
(18, 1, '18', FALSE, FALSE, FALSE, null),
(19, 1, '19', FALSE, FALSE, FALSE,null),
(20, 1, '20', FALSE, FALSE, FALSE, null),
(21, 1, '21', FALSE, FALSE, FALSE, null),
(22, 1, '22', FALSE, FALSE, FALSE, null),
(23, 1, '23', FALSE, FALSE, FALSE, null),
(24, 1, '24', FALSE, FALSE, FALSE, null),
(25, 1, '25', FALSE, FALSE, FALSE, null);





