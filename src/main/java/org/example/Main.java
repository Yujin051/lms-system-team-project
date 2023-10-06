package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import org.example.entity.*;
import org.example.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class Main implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder PasswordEncoder;
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;
    private final StudentRepository studentRepository;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        memberRepository.save(new Member("admin", PasswordEncoder.encode("123123"), "관리자1", "01022225555", "관리자네집", "admin@admin.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.ADMIN));
//        memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"","","","", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER));
//        memberRepository.save(new Member("student", PasswordEncoder.encode("123123"), "학생1", "01066669999", "학생네집", "student@stud.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.USER));
//        studentRepository.save(new Student(memberRepository.save(new Member("student2", PasswordEncoder.encode("123123"), "김학생", "01066669999", "학생네집", "student@stud.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.USER)),1L, 100L, 17L, 3L));
//        professorRepository.save(new Professor(memberRepository.save(new Member("prof", PasswordEncoder.encode("123123"), "선생1", "01012345678", "서울", "prof@prof.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true));
        lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof", PasswordEncoder.encode("123123"), "이선생", "01012345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "암호학개론", "필수과목", "2022", "2학기", 3L, 11L, 13L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L));
        lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof2", PasswordEncoder.encode("123124"), "김선생", "01013345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "해석학개론", "필수과목", "2022", "2학기", 1L, 13L, 21L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L));
        lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof3", PasswordEncoder.encode("123124"), "김철수", "01013345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "세계사와 사회변화", "필수과목", "2022", "2학기", 3L, 12L, 19L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), false, 35L, 35L, 30L));
        lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof4", PasswordEncoder.encode("123123"), "감선생", "01012345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "수리학", "필수과목", "2022", "2학기", 2L, 11L, 13L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L));
        lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof5", PasswordEncoder.encode("123124"), "수선생", "01013345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "미분적분학", "필수과목", "2022", "2학기", 2L, 13L, 20L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L));
        lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof6", PasswordEncoder.encode("123124"), "목선생", "01013345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "현대대수학", "필수과목", "2022", "2학기", 1L, 18L, 29L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L));
        gradeInfoRepository.save(new GradeInfo(lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof7", PasswordEncoder.encode("123123"), "호선생", "01012345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "암호학개론", "필수과목", "2022", "2학기", 3L, 11L, 13L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L)), studentRepository.save(new Student(memberRepository.save(new Member("student", PasswordEncoder.encode("123123"), "김학생", "01066669999", "학생네집", "student@stud.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.USER)),1L, 100L, 17L, 3L)), "A", 35L, 35L, 30L, true));
        gradeInfoRepository.save(new GradeInfo(lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof8", PasswordEncoder.encode("123123"), "숭선생", "01012345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "암호학개론", "필수과목", "2022", "2학기", 3L, 11L, 13L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L)), studentRepository.save(new Student(memberRepository.save(new Member("student2", PasswordEncoder.encode("123123"), "이학생", "01066669999", "학생네집", "student@stud.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.USER)),1L, 100L, 17L, 3L)), "B", 35L, 35L, 30L, true));
        gradeInfoRepository.save(new GradeInfo(lectInfoRepository.save(new LectInfo(professorRepository.save(new Professor(memberRepository.save(new Member("prof9", PasswordEncoder.encode("123123"), "주선생", "01012345679", "서울", "prof@prosf.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.TEACHER)), "소속기관1", "00은행", "직무1", "111-222-333333", true)), "암호학개론", "필수과목", "2022", "2학기", 3L, 11L, 13L, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 1), LocalDate.of(2022, 6, 3), true, 35L, 35L, 30L)), studentRepository.save(new Student(memberRepository.save(new Member("student3", PasswordEncoder.encode("123123"), "선학생", "01066669999", "학생네집", "student@stud.com", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), Gender.MALE, RoleType.USER)),1L, 100L, 17L, 3L)), "C", 35L, 35L, 30L, true));
    }
}