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


INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (27, 1, 1, 11);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (12, 2, 2, 22);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (11, 3, 1, 12);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (31, 4, 2, 15);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (23, 5, 1, 18);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (28, 6, 2, 20);
INSERT INTO student (stud_crecpl, id, stud_grade, stud_nowcr)
values (5, 7, 1, 22);

INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (1, '2023', '1학기', 'A', 99);
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (2, '2023', '1학기', 'B', 77);
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (3, '2023', '1학기', 'B', 76);
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (4, '2023', '1학기', 'A', 98);
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (5, '2023', '1학기', 'A', 88);
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (6, '2023', '1학기', 'C', 88);
INSERT INTO sem_grade(stud_id, sem_year, sem_sem, sem_grade, sem_avg)
values (7, '2023', '1학기', 'D', 22);

INSERT INTO teacher(id)
values (1);

INSERT INTO lect_info(prof_id, lect_name, lect_start, lect_end, lect_credit, is_active)
values (1, '프로그래밍', '2023-10-01', '2023-10-24', 3, true);

