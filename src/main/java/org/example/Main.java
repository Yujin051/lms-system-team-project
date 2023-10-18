package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.example.repository.MemberRepository;
import org.example.repository.ProfessorRepository;
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
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        memberRepository.save(new Member("admin",PasswordEncoder.encode("123123"),"관리자1","01022225555","관리자네집","admin@admin.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.ADMIN));
////        memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"","","","", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER));
//        memberRepository.save(new Member("student",PasswordEncoder.encode("123123"),"학생1","01066669999","학생네집","student@stud.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.USER));
//        professorRepository.save(new Professor(memberRepository.save(new Member("prof",PasswordEncoder.encode("123123"),"선생1","01012345678","서울","prof@prof.com", LocalDate.of(2023,1,1),LocalDate.of(2023,1,1), Gender.MALE,RoleType.TEACHER)),"소속기관1","00은행","직무1","111-222-333333",true));
    }


}