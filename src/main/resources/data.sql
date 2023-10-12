insert into  member (user_id, user_pw, user_role)
values ('sss1', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('ssss2', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('3sss', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('4sasa', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('5sadasd', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('ssss6', '1234','TEACHER');

insert into teacher (member_id) values(1);
insert into teacher (member_id) values(2);
insert into teacher (member_id) values(3);
insert into teacher (member_id) values(4);
insert into teacher (member_id) values(5);
insert into teacher (member_id) values(6);

insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('asd', 'bbsa1', 1, 'asds');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('qwdqd', '2as', 2, 'xzv');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('qgfada', 'ds3', 3, 'xzc');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('4qqdqw', 'dasd4', 4, '4wq');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('qgq', '5ssdasda', 5, '5wqe');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('fgqwdqwd', '6sad', 6, '6sad');

insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, '가', '가', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (2, '나', '나', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (3, '다', '다', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', false);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (4, '라', '라', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', false);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (5, '마', '마', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (6, '바', '바', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', false);




insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (1, 1, '가', 1);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (2, 2, '나', 2);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (3, 3, '다', 3);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (4, 4, '라', 4);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (5, 5, '마', 5);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (6, 6, '바', 6);

