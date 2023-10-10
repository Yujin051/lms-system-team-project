package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import org.example.entity.LectInfo;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.LectureRepository;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
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
    private final ProfessorRepository professorRepository;
    private final LectureRepository lectureRepository;
    private final PasswordEncoder PasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        memberRepository.save(new Member("admin",PasswordEncoder.encode("123123"),"관리자1","01022225555","관리자네집","admin@admin.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.ADMIN));
//        memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"","","","", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER));
        memberRepository.save(new Member("student",PasswordEncoder.encode("123123"),"학생1","01066669999","학생네집","student@stud.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.USER));

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

        Professor professor = professorRepository.save(new Professor(memberRepository.save(new Member("prof12", PasswordEncoder.encode("123123"),"선생12","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","언어","111-222-333333",true));

        lectureRepository.save(new LectInfo(1L, professorRepository.save(new Professor(memberRepository.save(new Member("prof2", PasswordEncoder.encode("123123"),"선생3","01012345671","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","수학","111-222-333333",true)), "강좌1", "언어", "2023년", "1학기", 3L, 39L, 40L, LocalDateTime.of(2023,8,21,9,00), LocalDateTime.of(2023,8,23,18,00), LocalDateTime.of(2023,9,4,0,0), LocalDateTime.of(2023,12,23,0,0), true, 35L, 35L, 30L, 1));
        lectureRepository.save(new LectInfo(2L, professorRepository.save(new Professor(memberRepository.save(new Member("prof3", PasswordEncoder.encode("123123"),"선생4","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","언어","111-222-333333",true)), "강좌2", "언어", "2023년", "1학기", 3L, 39L, 40L, LocalDateTime.of(2023,8,21,9,00), LocalDateTime.of(2023,8,23,18,00), LocalDateTime.of(2023,9,4,0,0), LocalDateTime.of(2023,12,23,0,0), true, 35L, 35L, 30L, 3));
        lectureRepository.save(new LectInfo(3L, professor, "강좌3", "언어", "2023년", "1학기", 3L, 39L, 40L, LocalDateTime.of(2023,8,21,9,00), LocalDateTime.of(2023,8,23,18,00), LocalDateTime.of(2023,9,4,0,0), LocalDateTime.of(2023,12,23,0,0), true, 35L, 35L, 30L, 3));
    }

}