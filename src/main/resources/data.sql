
insert into member(user_id , user_pw , user_name , user_phonenum , user_regdate , user_addr , user_birthday , user_email , user_gender , user_role , img_original , img_saved) values
('admin1' , '$2a$10$BNEf0bp3OvIoAL9kIAStP.pyoMnNIpdjFuUnv2SvATm75s6YrM1HG' , '관리자1','01022225555' , '2023-1-1' , '관리자네집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'ADMIN', 'noimg2.png' ,'noimg2.png'),
('student1' , '$2a$10$BNEf0bp3OvIoAL9kIAStP.pyoMnNIpdjFuUnv2SvATm75s6YrM1HG' , '김학생','01022225555' , '2023-1-1' , '학생1집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'USER' , 'noimg2.png' ,'noimg2.png'),
('student2' , '$2a$10$BNEf0bp3OvIoAL9kIAStP.pyoMnNIpdjFuUnv2SvATm75s6YrM1HG' , '이학생','01021225555' , '2023-1-1' , '학생2집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'USER' , 'noimg2.png' ,'noimg2.png'),
('prof1' , '$2a$10$BNEf0bp3OvIoAL9kIAStP.pyoMnNIpdjFuUnv2SvATm75s6YrM1HG' , '김선생','01023225555' , '2023-1-1' , '선생1집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'TEACHER' , 'noimg2.png' ,'noimg2.png'),
('prof2' , '$2a$10$BNEf0bp3OvIoAL9kIAStP.pyoMnNIpdjFuUnv2SvATm75s6YrM1HG' , '이선생','01024225555' , '2023-1-1' , '선생2집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'TEACHER' , 'noimg2.png' ,'noimg2.png'),
('prof3' , '$2a$10$BNEf0bp3OvIoAL9kIAStP.pyoMnNIpdjFuUnv2SvATm75s6YrM1HG' , '신선생','01024225555' , '2023-1-1' , '선생2집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'TEACHER' , 'noimg2.png' ,'noimg2.png');

insert into student(member_id, stud_grade, stud_maxcr, stud_nowcr, stud_crecpl) values
(2, 2, 18, 0, 11),
(3, 3, 16, 0, 15);

insert into professor(member_id, prof_agency, prof_work, prof_bank, prof_account, is_active) values
(4, '소속기관1', '직무1', '하나은행', '1111-1111', true),
(5, '소속기관1', '직무1', '하나은행', '1111-1111', true),
(6, '소속기관1', '직무1', '하나은행', '1111-1111', true);


insert into lect_info(prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum, lect_maxnum, enroll_start, enroll_end, lect_start,  lect_end, is_active, lect_assign, lect_check, lect_test, is_record) values
(1, '미분적분학', '필수과목', '2023', '2학기', 2, 15, 30, NOW(), NOW(), NOW(), NOW(), true, 5, 45, 50, false),
(1, '미분기하학', '필수과목', '2023', '2학기', 3, 17, 25, NOW(), NOW(), NOW(), NOW(), true, 35, 25, 40, false),
(1, '암호학', '필수과목', '2023', '2학기', 2, 13, 20, NOW(), NOW(), NOW(), NOW(), true, 35, 40, 25, false),
(1, '해석학', '필수과목', '2023', '2학기', 2, 13, 25, NOW(), NOW(), NOW(), NOW(), true, 10, 45, 45, false),
(2, '기계학', '필수과목', '2023', '2학기', 3, 10, 20, NOW(), NOW(), NOW(), NOW(), true, 35, 40, 25, false),
(2, '동물학', '필수과목', '2023', '2학기', 3, 13, 20, NOW(), NOW(), NOW(), NOW(), true, 35, 40, 25, false),
(2, '몰라학', '필수과목', '2023', '2학기', 2, 11, 25, NOW(), NOW(), NOW(), NOW(), true, 35, 40, 25, false),
(2, '화학', '필수과목', '2023', '2학기', 2, 12, 20, NOW(), NOW(), NOW(), NOW(), true, 35, 40, 25, false),
(2, '화학2', '필수과목', '2023', '2학기', 3, 15, 20, NOW(), NOW(), NOW(), NOW(), true, 25, 40, 35, false),
(3, '물리학', '필수과목', '2023', '2학기', 3, 13, 20, NOW(), NOW(), NOW(), NOW(), true, 20, 40, 40, false),
(3, '양자학', '필수과목', '2023', '2학기', 2, 29, 30, NOW(), NOW(), NOW(), NOW(), true, 30, 30, 40, false),
(3, '부력학', '필수과목', '2023', '2학기', 2, 30, 30, NOW(), NOW(), NOW(), NOW(), true, 10, 50, 40, false),
(3, '표면장력학', '필수과목', '2023', '1학기', 2, 23, 30, NOW(), NOW(), NOW(), NOW(), false, 10, 50, 40, false),
(3, '유력학', '필수과목', '2022', '2학기', 2, 23, 30, NOW(), NOW(), NOW(), NOW(), false, 15, 35, 50, false),
(3, '우주학', '필수과목', '2021', '2학기', 2, 29, 30, NOW(), NOW(), NOW(), NOW(), false, 15, 45, 40, false),
(3, '종이학', '필수과목', '2022', '1학기', 2, 30, 30, NOW(), NOW(), NOW(), NOW(), false, 30, 60, 10, false);

insert into stud_lect_apply(lect_id, stud_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(4, 1),
(4, 2),
(5, 1),
(6, 1),
(7, 2),
(8, 1),
(9, 2),
(10, 2),
(11, 1),
(12, 1),
(13, 1),
(13, 2),
(14, 1),
(14, 2),
(15, 1),
(15, 2),
(16, 1);




insert into grade_info(apply_id, grade, check_score, assign_score, test_score) values
                                                                                                     (1,  'D', 25, 21, 19),
                                                                                                     (2,  'A', 25, 33, 13),
                                                                                                     (3,  'B', 25, 33, 22),
                                                                                                     (4,  'A', 25, 33, 21),
                                                                                                     (5,  'A', 25, 33, 21),
                                                                                                     (6,  'C', 25, 35, 24),
                                                                                                     (7,  'A', 25, 36, 20),
                                                                                                     (8,  'D', 25, 35, 20),
                                                                                                     (9,  'A', 25, 31, 20),
                                                                                                     (10, 'A', 25, 33, 20),
                                                                                                     (11, 'D', 25, 33, 20),
                                                                                                     (12, 'A', 25, 33, 20),
                                                                                                     (13, 'A', 25, 33, 20),
                                                                                                     (14, 'D', 25, 33, 20),
                                                                                                     (15, 'C', 25, 33, 20),
                                                                                                     (16, 'A', 25, 33, 20),
                                                                                                     (17, 'D', 25, 33, 20),
                                                                                                     (18, 'A', 25, 33, 20),
                                                                                                     (19, 'A', 25, 33, 20);

insert into sem_grade(stud_id, sem_year, sem_sem, sem_crecpl, sem_avg_crecpl) values
                                                                                  (1, '2023', '2학기', 17, 2.6),
                                                                                  (1, '2023', '1학기', 14, 1.9),
                                                                                  (1, '2022', '2학기', 13, 2.5),
                                                                                  (1, '2022', '1학기', 19, 4.0),
                                                                                  (2, '2023', '2학기', 13, 3.8),
                                                                                  (2, '2023', '1학기', 15, 4),
                                                                                  (2, '2022', '2학기', 13, 2.9),
                                                                                  (2, '2021', '2학기', 14, 3);

insert into lect_plan(plan_id, lect_id, plan_des, plan_book) values
(1, 1, '1번 강의에 대한 설명', '교재 1번'),
(2, 2, '2번 강의에 대한 설명', '교재 2번'),
(3, 3, '3번 강의에 대한 설명', '교재 3번'),
(4, 4, '4번 강의에 대한 설명', '교재 4번'),
(5, 5, '5번 강의에 대한 설명', '교재 5번');

insert into assign_info(lect_id, assign_name, assign_detail, assign_start, assign_end, is_active, is_submit, origin_filename, saved_filename) values
(1, '과제1', '과제 1 설명', NOW(), NOW(), true, false, 'lostname.txt', 'lostname.txt'),
(1, '과제2', '과제 2 설명', NOW(), NOW(), true, false, 'lostname.txt', 'lostname.txt'),
(2, '과제3', '과제 3 설명', NOW(), NOW(), true, false, 'lostname.txt', 'lostname.txt'),
(3, '과제4', '과제 4 설명', NOW(), NOW(), true, false, 'lostname.txt', 'lostname.txt');


insert into board_info (board_type , board_name) values ('학생' , '학생');
insert into board_info (board_type , board_name) values ('질문건의' , '질문건의');
insert into board_info (board_type , board_name) values ('공지사항' , '공지사항');
insert into board_info (board_type , board_name) values ('교원' , '교원');


INSERT INTO board_article (board_id, article_title, article_cont, article_view, article_at, is_locked, is_deleted , member_id)
VALUES
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1),
    (3, '최초 로그인 이후 반드시 비밀번호 재설정 바랍니다.', '관리자는 분명히 경고 했습니다...', 0, NOW(), FALSE, FALSE , 1);

INSERT INTO board_article (board_id, article_title, article_cont, article_view, article_at, is_locked, is_deleted , member_id) values
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2),
(1, '억울하면 어쩌겠나, 걷어찰 수 있는 거라곤 비싼 컴퓨터 밖에 없는 걸.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 2);


INSERT INTO board_article (board_id, article_title, article_cont, article_view, article_at, is_locked, is_deleted , member_id) values
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3),
(2, '이의 있소 대체 뭘 어쩌자는건지...', '안녕하세요.', 0, NOW(), FALSE, FALSE, 3);



INSERT INTO board_article (board_id, article_title, article_cont, article_view, article_at, is_locked, is_deleted , member_id) values
(4, '안녕하세요.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 4),
(4, '인사드립니다.', '안녕하세요.', 0, NOW(), FALSE, FALSE, 5),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6),
(4, '교사 게시글 작성', '안녕하세요.', 0, NOW(), FALSE, FALSE, 6);
