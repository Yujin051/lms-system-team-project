insert into  member (user_id, user_pw, user_role)
values ('1', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('2', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('3', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('4', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('5', '1234','TEACHER');
insert into  member (user_id, user_pw, user_role)
values ('6', '1234','TEACHER');

insert into teacher (member_id) values(1);
insert into teacher (member_id) values(2);
insert into teacher (member_id) values(3);
insert into teacher (member_id) values(4);
insert into teacher (member_id) values(5);
insert into teacher (member_id) values(6);

insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('1', '1', 1, '1');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('2', '2', 2, '2');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('3', '3', 3, '3');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('4', '4', 4, '4');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('5', '5', 5, '5');
insert into lms_conts(conts_name, conts_detail, conts_time, conts_yout) values ('6', '6', 6, '6');

insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (1, '집게리아', '집게사장', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (2, '집게리아2', '집게사장2', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (3, '집게리아3', '집게사장3', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (4, '집게리아4', '집게사장4', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (5, '집게리아5', '집게사장5', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);
insert into lect_info (prof_id, lect_name, lect_subject, lect_year, lect_sem, lect_credit, lect_nownum,
                       lect_maxnum, enroll_start, enroll_end, lect_start, lect_end, is_active)
VALUES (6, '집게리아6', '집게사장6', '2023', '1학기', 3, 0, 30, '2023-01-01', '2023-01-31', '2023-02-01', '2023-06-30', true);




insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (1, 1, '집게사장', 1);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (2, 2, '집게사장2', 2);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (3, 3, '집게사장3', 3);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (4, 4, '집게사장4', 4);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (5, 5, '집게사장5', 5);

insert into lect_nth (lect_id, conts_no, nth_name, nth_sequence)
values (6, 6, '집게사장6', 6);