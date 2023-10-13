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

INSERT INTO teacher(id)
values (8);

INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, '자바를 배우자', '2023-10-01', '2023-10-24', 3, true, '자바');
INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, '자바스크립트를 배우자', '2023-10-01', '2023-10-24', 4, false, '자바스크립트');
INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, '스프링 프레임워크를 배우자', '2023-10-01', '2023-10-24', 4, true, '스프링 프레임워크');
INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, '스프링부트를 배우자', '2023-10-01', '2023-10-24', 4, false, '스프링부트');
INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, 'mybatis를 배우자', '2023-10-01', '2023-10-24', 3, true, 'mybatis');
INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, 'jpa를 배우자', '2023-10-01', '2023-10-24', 2, false, 'jpa');
INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active, lect_subject)
values (1, 'sql을 배우자', '2023-10-01', '2023-10-24', 4, true, 'sql');


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
values (1, 1, '게시글제목1', '게시글내용11', 10024, now(), false, true, 1);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (2, 2, '게시글제목2', '게시글내용22', 5505, now(), true, true, 2);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (3, 2, '게시글제목3', '게시글내용3123', 111, now(), true, false, 3);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (4, 4, '게시글제목4', '게시글내용sadf', 22, now(), true, false, 4);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (5, 4, '게시글제목5', '게시글내용qweqwe1', 1315, now(), true, false, 5);
INSERT INTO board_article(board_id, member_id, article_title, article_cont, article_view,
                          article_at, is_locked, is_deleted, file_num)
values (6, 5, '게시글제목6', '게시글내용aqeqe1', 513, now(), true, false, 6);

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
values (7, 7);

INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (1, 1, 1, 'A', 95, 95, 89, true);
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (2, 2, 2, 'B', 88, 81, 79, true);
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (3, 3, 2, 'B', 81, 77, 98, true);
INSERT INTO grade_info(apply_id, stud_id, lect_id, grade, check_score, assign_score, test_score, is_record)
values (4, 4, 4, 'B', 80, 81, 82, true);


INSERT INTO stud_lect_prog(fnl_posi, is_checked)
values (3, true);

