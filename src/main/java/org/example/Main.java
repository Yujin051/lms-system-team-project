package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import org.example.entity.*;
import org.example.repository.*;
import org.example.repository.admin.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class Main implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder PasswordEncoder;
    private final SemGradeRepository semGradeRepository;
    private final BoardInfoRepository boardInfoRepository;
    private final BoardArticleRepository boardArticleRepository;
    private final StudLectApplyRepository studLectApplyRepository;
    private final StudLectProgRepository studLectProgRepository;
    private final LmsContsRepository lmsContsRepository;
    private final LectInfoRepository lectInfoRepository;
    private final GradeInfoRepository gradeInfoRepository;
    private final LectNthRepository lectNthRepository;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // member
//        memberRepository.save(new Member("admin",PasswordEncoder.encode("123123"),"관리자1","010-2222-5555","관리자네집","admin@admin.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.ADMIN));
//        Member member1 = memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"선생1","010-1234-5678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER));
//        memberRepository.save(new Member("student",PasswordEncoder.encode("123123"),"학생1","010-6666-9999","학생네집","student@stud.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.USER));
//        Member member2 = memberRepository.save(new Member("ghdrlfehd",PasswordEncoder.encode("123123"),"홍길동","010-1234-5679","서울","ghdrlfehd2@stud.com", LocalDate.of(2023,2,1),LocalDate.of(2023,2,2), Gender.MALE,RoleType.USER));
//        Member member3 = memberRepository.save(new Member("dltnstls",PasswordEncoder.encode("123123"),"이순신","010-7978-2132","부산","dltnstls@stud.com", LocalDate.of(2023,3,1),LocalDate.of(2023,3,2), Gender.MALE,RoleType.USER));
//        Member member4 = memberRepository.save(new Member("tmvhswlqkq",PasswordEncoder.encode("123123"),"스폰지밥","010-9997-6546","인천","tmvhswlqkq@stud.com", LocalDate.of(2023,4,1),LocalDate.of(2023,4,2), Gender.MALE,RoleType.USER));
//        Member member5 = memberRepository.save(new Member("Enddl",PasswordEncoder.encode("123123"),"뚱이","010-8795-7912","서울","Enddl@stud.com", LocalDate.of(2023,5,1),LocalDate.of(2023,5,2), Gender.MALE,RoleType.USER));
//        Member member6 = memberRepository.save(new Member("wldwlddl",PasswordEncoder.encode("123123"),"징징이","010-8878-9993","천안","wldwlddl@stud.com", LocalDate.of(2023,6,1),LocalDate.of(2023,6,2), Gender.MALE,RoleType.USER));
//        Member member7 = memberRepository.save(new Member("ekfkadl",PasswordEncoder.encode("123123"),"다람이","010-2212-1131","안양","ekfkadl@stud.com", LocalDate.of(2023,7,1),LocalDate.of(2023,7,2), Gender.FEMALE,RoleType.USER));
//        Member member8 = memberRepository.save(new Member("professor2",PasswordEncoder.encode("123123"),"선생2","010-1132-8788","서울","professor2@stud.com", LocalDate.of(2023,8,1),LocalDate.of(2023,8,2), Gender.FEMALE,RoleType.USER));
//
//        // professor
//        professorRepository.save(new Professor(member1,"소속기관1","00은행","직무1","111-222-333333",true));
//        Professor professor1 =  professorRepository.save(new Professor(memberRepository.save(new Member("prof3", PasswordEncoder.encode("123123"), "선생3", "010-4342-5732", "인천", "prog3@naver.com", LocalDate.of(2023,3,3),LocalDate.of(2023,3,30), Gender.FEMALE,RoleType.TEACHER)), "중앙정보처리학원", "국민은행", "스프링 부트 강의", "213-476-765224", true));
//
//        // student
//        Student student1 = studentRepository.save(new Student(member2, 1L, 34L, 11L, 27L));
//        Student student2 = studentRepository.save(new Student(member3, 2L, 34L, 22L, 12L));
//        Student student3 = studentRepository.save(new Student(member4, 1L, 34L, 12L, 11L));
//        Student student4 = studentRepository.save(new Student(member5, 2L, 34L, 15L, 5L));
//        Student student5 = studentRepository.save(new Student(member6, 1L, 34L, 18L, 10L));
//        Student student6 = studentRepository.save(new Student(member7, 2L, 34L, 20L, 9L));
//        Student student7 = studentRepository.save(new Student(member8, 1L, 34L, 22L, 18L));
//
//        // semGrade
//        semGradeRepository.save(new SemGrade(student1, "2023", "1학기", "A"));
//        semGradeRepository.save(new SemGrade(student2, "2023", "1학기", "B"));
//        semGradeRepository.save(new SemGrade(student3, "2023", "1학기", "B"));
//        semGradeRepository.save(new SemGrade(student4, "2023", "1학기", "B"));
//        semGradeRepository.save(new SemGrade(student5, "2023", "1학기", "A"));
//        semGradeRepository.save(new SemGrade(student6, "2023", "1학기", "C"));
//        semGradeRepository.save(new SemGrade(student7, "2023", "1학기", "A"));
//
//        // lectInfo
//        LectInfo lectInfo1 =  lectInfoRepository.save(new LectInfo(professor1, "자바를 배우자", "자바", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//        LectInfo lectInfo2 =  lectInfoRepository.save(new LectInfo(professor1, "자바스크립트를 배우자", "자바스크립트", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//        LectInfo lectInfo3 =  lectInfoRepository.save(new LectInfo(professor1, "스프링 프레임워크를 배우자", "스프링 프레임워크", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//        LectInfo lectInfo4 =  lectInfoRepository.save(new LectInfo(professor1, "스프링 부트를 배우자", "스프링 부트", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//        LectInfo lectInfo5 =  lectInfoRepository.save(new LectInfo(professor1, "mybatis를 배우자", "mybatis", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//        LectInfo lectInfo6 =  lectInfoRepository.save(new LectInfo(professor1, "jpa를 배우자", "jpa", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//        LectInfo lectInfo7 =  lectInfoRepository.save(new LectInfo(professor1, "sql를 배우자", "sql", "2023", "1학기", 3L, 0L, 30L,
//                LocalDateTime.of(2023, 1, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 1, 31, 0, 0, 0),
//                LocalDateTime.of(2023, 2, 1, 0, 0, 0),
//                LocalDateTime.of(2023, 3, 1, 0, 0, 0), true));
//
//        // boardInfo
//        BoardInfo boardInfo1 =  boardInfoRepository.save(new BoardInfo("게시판 이름1", "건의사항"));
//        BoardInfo boardInfo2 =  boardInfoRepository.save(new BoardInfo("게시판 이름2", "공지사항"));
//        BoardInfo boardInfo3 =  boardInfoRepository.save(new BoardInfo("게시판 이름3", "학생"));
//        BoardInfo boardInfo4 =  boardInfoRepository.save(new BoardInfo("게시판 이름4", "교원"));
//        BoardInfo boardInfo5 =  boardInfoRepository.save(new BoardInfo("게시판 이름5", "과정"));
//        BoardInfo boardInfo6 =  boardInfoRepository.save(new BoardInfo("게시판 이름6", "수업게시판"));
//
//        // boardArticle
//        boardArticleRepository.save(new BoardArticle(boardInfo1, "게시글제목1", "게시글내용1", 10024L, LocalDate.of(2023, 1, 1), true,false, member2, 1L));
//        boardArticleRepository.save(new BoardArticle(boardInfo2, "게시글제목2", "게시글내용2", 5505L, LocalDate.of(2023, 2, 1), true,true, member2, 2L));
//        boardArticleRepository.save(new BoardArticle(boardInfo3, "게시글제목3", "게시글내용3", 111L, LocalDate.of(2023, 3, 1), false,false, member2, 3L));
//        boardArticleRepository.save(new BoardArticle(boardInfo4, "게시글제목4", "게시글내용4", 22L, LocalDate.of(2023, 4, 1), true,false, member2, 4L));
//        boardArticleRepository.save(new BoardArticle(boardInfo5, "게시글제목5", "게시글내용5", 1315L, LocalDate.of(2023, 5, 1), true,true, member2, 5L));
//        boardArticleRepository.save(new BoardArticle(boardInfo6, "게시글제목6", "게시글내용6", 513L, LocalDate.of(2023, 6, 1), true,false, member2, 6L));
//
//        // studLectApply
//        StudLectApply studLectApply1 = studLectApplyRepository.save(new StudLectApply(student1, lectInfo1));
//        StudLectApply studLectApply2 = studLectApplyRepository.save(new StudLectApply(student2, lectInfo2));
//        StudLectApply studLectApply3 = studLectApplyRepository.save(new StudLectApply(student3, lectInfo3));
//        StudLectApply studLectApply4 = studLectApplyRepository.save(new StudLectApply(student4, lectInfo4));
//        StudLectApply studLectApply5 = studLectApplyRepository.save(new StudLectApply(student5, lectInfo5));
//        StudLectApply studLectApply6 = studLectApplyRepository.save(new StudLectApply(student6, lectInfo6));
//        StudLectApply studLectApply7 = studLectApplyRepository.save(new StudLectApply(student3, lectInfo4));
//        StudLectApply studLectApply8 = studLectApplyRepository.save(new StudLectApply(student3, lectInfo5));
//        StudLectApply studLectApply9 = studLectApplyRepository.save(new StudLectApply(student3, lectInfo7));
//
//        // gradeInfo
//        gradeInfoRepository.save(new GradeInfo(studLectApply1,"A", 95L, 95L, 89L, true));
//        gradeInfoRepository.save(new GradeInfo(studLectApply2,"B", 88L, 81L, 79L, false));
//        gradeInfoRepository.save(new GradeInfo(studLectApply2,"B", 81L, 77L, 98L, false));
//        gradeInfoRepository.save(new GradeInfo(studLectApply3,"B", 80L, 81L, 82L, true));
//        gradeInfoRepository.save(new GradeInfo(studLectApply5,"A", 91L, 95L, 90L, true));
//
//        // studLectProg
//        studLectProgRepository.save(new StudLectProg(0, 0, false, LocalDateTime.of(2023,1,1, 0,0,0), 0));
//
//        // lmsConts
//        LmsConts lmsConts1 = lmsContsRepository.save(new LmsConts("컨텐츠 이름1", "컨텐츠 내용1", 1, "SGFWasfdQ2"));
//        LmsConts lmsConts2 = lmsContsRepository.save(new LmsConts("컨텐츠 이름2", "컨텐츠 내용2", 2, "ASDGnhsf31"));
//        LmsConts lmsConts3 = lmsContsRepository.save(new LmsConts("컨텐츠 이름3", "컨텐츠 내용3", 3, "WEGW1AS3dx"));
//        LmsConts lmsConts4 = lmsContsRepository.save(new LmsConts("컨텐츠 이름4", "컨텐츠 내용4", 4, "sdSADDF23Z"));
//        LmsConts lmsConts5 = lmsContsRepository.save(new LmsConts("컨텐츠 이름5", "컨텐츠 내용5", 5, "FGHDdsfsd2"));
//        LmsConts lmsConts6 = lmsContsRepository.save(new LmsConts("컨텐츠 이름6", "컨텐츠 내용6", 6, "WE2312SDAs"));
//
//        // lectNth
//        lectNthRepository.save(new LectNth(lectInfo1, lmsConts1, "HTML/CSS", 1L));
//        lectNthRepository.save(new LectNth(lectInfo2, lmsConts2, "JAVASCRIPT", 2L));
//        lectNthRepository.save(new LectNth(lectInfo3, lmsConts3, "JAVA", 3L));
//        lectNthRepository.save(new LectNth(lectInfo4, lmsConts4, "SQL", 4L));
//        lectNthRepository.save(new LectNth(lectInfo5, lmsConts5, "SPRING BOOT", 5L));
//        lectNthRepository.save(new LectNth(lectInfo6, lmsConts6, "JPA", 6L));

    }


}