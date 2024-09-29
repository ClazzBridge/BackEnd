-- 기존 테이블 삭제
--drop table if exists profile_image restrict;
--drop table if exists classroom restrict;
drop table if exists seat restrict;
drop table if exists user_list restrict;




-- User (사용자) 테이블 생성
CREATE TABLE user_list (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 사용자 고유 식별자 (Primary Key)
    user_id VARCHAR(100) UNIQUE NOT NULL,             -- 사용자 계정
    password VARCHAR(100) NOT NULL,                  -- 사용자 비밀번호
    name VARCHAR(100) NOT NULL,                      -- 사용자 이름
    email VARCHAR(100) UNIQUE NOT NULL,              -- 이메일 주소 (고유)
    git_url VARCHAR(100),                            -- git 주소
    phone VARCHAR(15) UNIQUE NOT NULL,               -- 전화번호 (고유)
    registration_date DATE,                          -- 가입 날짜
    user_type ENUM('STUDENT', 'TEACHER', 'ROLE_ADMIN') NOT NULL,  -- 사용자 유형 (수강생, 강사, 관리자)
    profile_image_id INT NOT NULL,                   -- 프로필 사진 ID
    experience TEXT,                                 -- 강사만 사용할 수 있는 경력 정보
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
    seat_presence BOOLEAN DEFAULT TRUE,              -- 좌석 존재 여부(기본값: 있음)
    is_occupied BOOLEAN DEFAULT FALSE,               -- 좌석 점유 여부 (기본값: 비어 있음)
    user_id INT                                      -- 좌석에 배정된 사용자 ID
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
ADD CONSTRAINT fk_user_seat
FOREIGN KEY (user_id) REFERENCES user_list(id) ON DELETE SET NULL;



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
INSERT INTO user_list (id, name, user_id, password, email, git_url, phone, registration_date, user_type, profile_image_id, experience, bio)
VALUES
(1, '홍길동', 'hong12', '1q2w3e4r!',' hong@example.com', 'https://github.com/hong',  '010-1234-1234', '2023-01-01', 'STUDENT', 1, NULL, '저는 홍길동 입니다.'),
(2, '김철수', 'kim12', '1q2w3e4r!', 'kim@example.com', 'https://github.com/kim', '010-2345-2345', '2023-01-01', 'STUDENT', 2, '10년 이상 강의 경력', '저는 김철수 입니다.'),
(3, '김장호', 'jang14', '1q2w3e4r!', 'lee@example.com', 'https://github.com/lee', '010-3456-3456', '2023-01-01', 'STUDENT', 3, NULL, '저는 김장호 입니다.'),
(4, '권준성', 'kwon12', '1q2w3e4r!', 'kwon@example.com', 'https://github.com/kwon', '010-4444-4444', '2023-01-01', 'STUDENT', 4, NULL, '저는 권준성 입니다.'),
(5, '강슬기', 'kang12', '1q2w3e4r!', 'kang@example.com', 'https://github.com/kang', '010-5555-5555', '2023-01-01', 'STUDENT', 5, NULL, '저는 강슬기 입니다.'),
(6, '최동인', 'choi12', '1q2w3e4r!', 'dong@example.com', 'https://github.com/dong', '010-6666-6666', '2023-01-01', 'STUDENT', 6, NULL, '저는 최동인 입니다.'),
(7, '임상우', 'im12', '1q2w3e4r!', 'sang@example.com', 'https://github.com/sang', '010-7777-7777', '2023-01-01', 'STUDENT', 7, NULL, '저는 임상우 입니다.'),
(8, '정찬우', 'jung12', '1q2w3e4r!', 'woo@example.com', 'https://github.com/woo', '010-8888-8888', '2023-01-01', 'STUDENT', 8, NULL, '저는 정찬우 입니다.'),
(9, '권기윤', 'woon12', '1q2w3e4r!', 'woon@example.com', 'https://github.com/woon', '010-9999-9999', '2023-01-01', 'STUDENT', 9, NULL, '저는 권기윤 입니다.'),
(10, '장혜정', 'jang12', '1q2w3e4r!', 'jang@example.com', 'https://github.com/jang', '010-1111-1111', '2023-01-01', 'STUDENT', 10, NULL, '저는 장혜정 입니다.'),
(11, '이건학', 'lee12', '1q2w3e4r!', 'hak@example.com', 'https://github.com/hak', '010-1212-1212', '2023-01-01', 'STUDENT', 11, NULL, '저는 이건학 입니다.'),
(12, '김민수', 'min12', '1q2w3e4r!', 'min@example.com', 'https://github.com/min', '010-1313-1313', '2023-01-01', 'STUDENT', 12, NULL, '저는 김민수 입니다.'),
(13, '백현기', 'back12', '1q2w3e4r!', 'back@example.com', 'https://github.com/back', '010-1414-1414', '2023-01-01', 'STUDENT', 13, NULL, '저는 백현기 입니다.'),
(14, '이민석', 'suck12', '1q2w3e4r!', 'suck@example.com', 'https://github.com/suck', '010-1515-1515', '2023-01-01', 'STUDENT', 14, NULL, '저는 이민석 입니다.'),
(15, '최지원', 'won12', '1q2w3e4r!', 'won@example.com', 'https://github.com/won', '010-1616-1616', '2023-01-01', 'STUDENT', 15, NULL, '저는 최지원 입니다.'),
(16, '윤효서', 'seo12', '1q2w3e4r!', 'seo@example.com', 'https://github.com/seo', '010-1717-1717', '2023-01-01', 'STUDENT', 16, NULL, '저는 윤효서 입니다.'),
(17, '신승민', 'sin12', '1q2w3e4r!', 'sin@example.com', 'https://github.com/sin', '010-1818-1818', '2023-01-01', 'STUDENT', 17, NULL, '저는 신승민 입니다.'),
(18, '안창호', 'ho12', '1q2w3e4r!', 'ho@example.com', 'https://github.com/ho', '010-1919-1919', '2023-01-01', 'STUDENT', 18, NULL, '저는 안창호 입니다.'),
(19, '이순신', 'soon12', '1q2w3e4r!', 'soon@example.com', 'https://github.com/soon', '010-2020-2020', '2023-01-01', 'STUDENT', 19, NULL, '저는 이순신 입니다.'),
(20, '백윤기', 'gi12', '1q2w3e4r!', 'gi@example.com', 'https://github.com/gi', '010-2121-2121', '2023-01-01', 'STUDENT', 20, NULL, '저는 백윤기 입니다.'),
(21, '이 학', '2hak12', '1q2w3e4r!', '2hak@example.com', 'https://github.com/2hak', '010-2222-2222', '2023-01-01', 'STUDENT', 21, NULL, '저는 이 학 입니다.'),
(22, '김애스더', 'asder12', '1q2w3e4r!', 'asder@example.com', 'https://github.com/asder', '010-2323-2323', '2023-01-01', 'STUDENT', 22, NULL, '저는 김애스더 입니다.'),
(23, '손흥민', 'son12', '1q2w3e4r!', 'son@example.com', 'https://github.com/son', '010-2424-2424', '2023-01-01', 'STUDENT', 23, NULL, '저는 손흥민 입니다.'),
(24, '이강인', 'gangin12', '1q2w3e4r!', '2gangin@example.com', 'https://github.com/2gangin', '010-2525-2525', '2023-01-01', 'STUDENT', 24, NULL, '저는 이강인 입니다.'),
(25, '홍경민', 'jkmin12', '1q2w3e4r!', 'hkmin@example.com', 'https://github.com/hkmin', '010-2626-2626', '2023-01-01', 'STUDENT', 25, NULL, '저는 홍경민 입니다.');



-- Seat 더미 데이터 삽입 (고유번호 포함)
INSERT INTO seat (id, classroom_id, seat_number, seat_presence, is_occupied, user_id)
VALUES
(1, 1, '01', TRUE, FALSE, 1),
(2, 1, '02', TRUE, TRUE, 2),
(3, 1, '03', TRUE, TRUE, 3),
(4, 1, '04', TRUE, TRUE, 4),
(5, 1, '05', TRUE, TRUE, 5),
(6, 1, '06', TRUE, TRUE, 6),
(7, 1, '07', TRUE, TRUE, 7),
(8, 1, '08', TRUE, TRUE, 8),
(9, 1, '09', TRUE, FALSE, 9),
(10, 1, '10', TRUE, TRUE, 10),
(11, 1, '11', TRUE, TRUE, 11),
(12, 1, '12', TRUE, TRUE, 12),
(13, 1, '13', TRUE, TRUE, 13),
(14, 1, '14', TRUE, TRUE, 14),
(15, 1, '15', TRUE, TRUE, 15),
(16, 1, '16', TRUE, TRUE, 16),
(17, 1, '17', TRUE, TRUE, 17),
(18, 1, '18', TRUE, TRUE, 18),
(19, 1, '19', TRUE, TRUE, 19),
(20, 1, '20', TRUE, TRUE, 20),
(21, 1, '21', TRUE, TRUE, 21),
(22, 1, '22', TRUE, TRUE, 22),
(23, 1, '23', TRUE, TRUE, 23),
(24, 1, '24', FALSE, FALSE, null),
(25, 1, '25', TRUE, TRUE, 25);


INSERT INTO classroom(id, name, capacity, is_occupied)
(1, '1강의실', '01', 30, FALSE),
(2, '2강의실', '01', 25, TRUE),
(3, '3강의실', '01', 20, FALSE);