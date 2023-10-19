INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('관리자', NOW(), 'MALE', '010-1132-8788', 'admin1@naver.com', '서울', 'ADMIN', 'admin111', 'admin111');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('홍길동', NOW(), 'MALE', '010-1234-5678', 'ghdrlfehd@naver.com', 'asdqw', 'USER', 'asd', '111');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('이순신', NOW(), 'MALE', '010-7978-2132', 'dltnstls@naver.com', 'asdqw', 'USER', 'asd1', '513');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('스폰지밥', NOW(), 'MALE', '010-9997-6546', 'tmvhswlqkq@naver.com', 'asdqw', 'USER', 'asd13', '624234');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('뚱이', NOW(), 'MALE', '010-8795-7912', 'Enddl@naver.com', 'asdqw', 'USER', 'asd1341', '632');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('징징이', NOW(), 'MALE', '010-8878-9993', 'wldwlddl@naver.com', 'asdqw', 'USER', 'asd133151', '666');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('다람이', NOW(), 'FEMALE', '010-2212-1131', 'ekfkadl@naver.com', 'asdqw', 'USER', 'asd1151', '111223');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('신민기', NOW(), 'MALE', '010-6798-7952', 'tlsalsrl@naver.com', '김포', 'USER', 'tlsalsrl0421', 'asdasd1');
INSERT INTO member (user_name, user_birthday, user_gender, user_phonenum, user_email,
                    user_addr, user_role, user_id, user_pw)
VALUES ('선생2', NOW(), 'FEMALE', '010-1132-8788', 'teacher1@naver.com', '서울', 'TEACHER', 'teacher111', 'teacher111');




INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (27, 1, 1, 11, 34);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (12, 2, 2, 22, 34);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (11, 3, 1, 12, 34);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (31, 4, 2, 15, 34);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (23, 5, 1, 18, 34);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (28, 6, 2, 20, 34);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr, stud_maxcr)
values (5, 7, 1, 22, 34);


INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (1, '2023', '1학기', 'A');
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (2, '2023', '1학기', 'B');
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (3, '2023', '1학기', 'B');
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (4, '2023', '1학기', 'A');
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (5, '2023', '1학기', 'A');
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (6, '2023', '1학기', 'C');
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_rating)
values (7, '2023', '1학기', 'D');

INSERT INTO professor(id, is_active)
values (8, 1);

insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, '자바를 배우자', '자바', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 1);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, '자바스크립트를 배우자', '자바스크립트', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 1);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, '스프링 프레임워크를 배우자', '스프링 프레임워크', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 0);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, '스프링부트를 배우자', '스프링부트', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 0);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, 'mybatis를 배우자', 'mybatis', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 1);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, 'jpa를 배우자', 'jpa', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 1);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, 'sql을 배우자', 'sql', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', 0);


INSERT INTO board_info(board_name, board_type)
values ('게시판이름1', '건의사항');
INSERT INTO board_info(board_name, board_type)
values ('게시판이름2', '공지사항');
INSERT INTO board_info(board_name, board_type)
values ('게시판이름3', '학생');
INSERT INTO board_info(board_name, board_type)
values ('게시판이름4', '교원');
INSERT INTO board_info(board_name, board_type)
values ('게시판이름5', '과정');
INSERT INTO board_info(board_name, board_type)
values ('게시판이름6', '수업게시판');

INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (1, 1, '게시글제목1', '게시글내용11', 10024, now(), 0, 1, 1);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (2, 2, '게시글제목2', '게시글내용22', 5505, now(), 1, 1, 2);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (3, 2, '게시글제목3', '게시글내용3123', 111, now(), 1, 0, 3);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (4, 4, '게시글제목4', '게시글내용sadf', 22, now(), 1, 0, 4);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (5, 4, '게시글제목5', '게시글내용qweqwe1', 1315, now(), 1, 0, 5);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (6, 5, '게시글제목6', '게시글내용aqeqe1', 513, now(), 1, 0, 6);

INSERT INTO stud_lect_apply(stud_id, lect_id)
values (1, 1);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (2, 2);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (3, 3);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (4, 4);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (5, 5);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (6, 6);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (3, 4);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (3, 5);
INSERT INTO stud_lect_apply(stud_id, lect_id)
values (3, 6);

/* 수강생차시진도 */
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (1, 1, 1, 'A', 95, 95, 89, 1);
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (2, 2, 2, 'B', 88, 81, 79, 1);
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (3, 3, 2, 'B', 81, 77, 98, 1);
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (4, 4, 4, 'B', 80, 81, 82, 1);


/* 수강생차시진도 */
INSERT INTO stud_lect_prog(fnl_posi, max_posi, is_checked, progress, check_date)
values (0, 0, false, 0, '2000-01-01');


/* 강의콘텐츠 정보 */
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout)
values ('asd', 'bbsa1', 1, 'asds');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout)
values ('qwdqd', '2as', 2, 'xzv');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout)
values ('qgfada', 'ds3', 3, 'xzc');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout)
values ('4qqdqw', 'dasd4', 4, '4wq');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout)
values ('qgq', '5ssdasda', 5, '5wqe');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout)
values ('fgqwdqwd', '6sad', 6, '6sad');
insert into lms_conts(conts_name, conts_detail, conts_time)
values ('자바', '자바입니다.', 1123);

/* 강의 차시 정보 */
insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (1, 1, 'html/css', 1);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (2, 2, 'javascript', 2);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (3, 3, 'java', 3);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (4, 4, 'sql', 4);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (5, 5, 'spring boot', 5);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (6, 6, 'jpa', 6);
