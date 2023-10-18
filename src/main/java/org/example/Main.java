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
import java.time.LocalDateTime;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class Main implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final LectureRepository lectureRepository;
    private final LectApplyRepository lectApplyRepository;
    private final LmsContsRepository lmsContsRepository;
    private final LectNthRepository lectNthRepository;
    private final PasswordEncoder PasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        memberRepository.save(new Member("admin",PasswordEncoder.encode("123123"),"관리자1","01022225555","관리자네집","admin@admin.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.ADMIN));
//        memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"","","","", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER));
        // 멤버객체
        Member member = memberRepository.save(new Member("student",PasswordEncoder.encode("123123"),"학생1","01066669999","학생네집","student@stud.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.USER));

        // 학생객체
        Student student = studentRepository.save(new Student(1L, member, 2L, 19L, 18L, 18L));
        Student student1 = studentRepository.save(new Student(2L, memberRepository.save(new Member("student1",PasswordEncoder.encode("123123"),"학생2","01066669999","학생네집","student@stud.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.USER)), 2L, 19L, 18L, 18L));
        Student student2 = studentRepository.save(new Student(3L,  memberRepository.save(new Member("student2",PasswordEncoder.encode("123123"),"학생3","01066669999","학생네집","student@stud.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.USER)), 2L, 19L, 18L, 18L));

        professorRepository.save(new Professor(memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"선생1","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","직무1","111-222-333333",true));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof1",PasswordEncoder.encode("123123"),"선생2","01012345698","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","언어","111-222-333333",false));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof4",PasswordEncoder.encode("123123"),"선생5","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","언어","111-222-333333",true));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof5",PasswordEncoder.encode("123123"),"선생6","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","과학","111-222-333333",false));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof6",PasswordEncoder.encode("123123"),"선생7","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","과학","111-222-333333",true));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof7",PasswordEncoder.encode("123123"),"선생8","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","과학","111-222-333333",false));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof8",PasswordEncoder.encode("123123"),"선생9","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","수학","111-222-333333",true));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof9",PasswordEncoder.encode("123123"),"선생10","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","수학","111-222-333333",true));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof10",PasswordEncoder.encode("123123"),"선생11","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","사회","111-222-333333",true));
        professorRepository.save(new Professor(memberRepository.save(new Member("prof11",PasswordEncoder.encode("123123"),"선생12","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","사회","111-222-333333",true));

        // 교수객체
        Professor professor = professorRepository.save(new Professor(memberRepository.save(new Member("prof12", PasswordEncoder.encode("123123"),"선생13","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","언어","111-222-333333",true));

        lectureRepository.save(new LectInfo(1L, professorRepository.save(new Professor(memberRepository.save(new Member("prof2", PasswordEncoder.encode("123123"),"선생3","01012345671","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","수학","111-222-333333",true)), "강좌1", "언어", "2023년", "1학기", 3L, 39L, 40L, LocalDateTime.of(2023,8,21,9,00), LocalDateTime.of(2023,8,23,18,00), LocalDateTime.of(2023,9,4,0,0), LocalDateTime.of(2023,12,23,0,0), true, 35L, 35L, 30L, 1));
        lectureRepository.save(new LectInfo(2L, professorRepository.save(new Professor(memberRepository.save(new Member("prof3", PasswordEncoder.encode("123123"),"선생4","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","언어","111-222-333333",true)), "강좌2", "언어", "2023년", "1학기", 3L, 39L, 40L, LocalDateTime.of(2023,8,21,9,00), LocalDateTime.of(2023,8,23,18,00), LocalDateTime.of(2023,9,4,0,0), LocalDateTime.of(2023,12,23,0,0), true, 35L, 35L, 30L, 3));

        //강좌객체
        LectInfo lectInfo = lectureRepository.save(new LectInfo(3L, professor, "강좌3", "언어", "2023년", "1학기", 3L, 39L, 40L, LocalDateTime.of(2023,8,21,9,00), LocalDateTime.of(2023,8,23,18,00), LocalDateTime.of(2023,9,4,0,0), LocalDateTime.of(2023,12,23,0,0), true, 35L, 35L, 30L, 3));

        lectApplyRepository.save(new StudLectApply(1L, student, lectInfo));
        lectApplyRepository.save(new StudLectApply(2L, student1, lectInfo));
        lectApplyRepository.save(new StudLectApply(3L, student2, lectInfo));

        // 컨텐츠 객체
        LmsConts lmsConts = lmsContsRepository.save(new LmsConts(1L, "테스트1", "1111", 130, "ivg3aqwQbus"));
        lmsContsRepository.save(new LmsConts(2L, "테스트2", "두번째업로드", 130, "4Nd4sDZZS6k"));

        // 강좌 차시 객체
        lectNthRepository.save(new LectNth(1L, lectInfo, lmsConts, "강좌3 차시1", 1L));

    }

}