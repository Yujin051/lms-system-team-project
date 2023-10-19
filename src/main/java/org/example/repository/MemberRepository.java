package org.example.repository;

import org.example.dto.admin.MemberDto;
import org.example.dto.admin.StudentDto;
import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);

    @Query("SELECT NEW org.example.dto.admin.StudentDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "s.studGrade) " +
            "FROM Member m JOIN Student s JOIN LectInfo l join Professor p")
    List<StudentDto> findGradeStudInfo2();

//    @Query("SELECT NEW org.example.dto.admin.StudentDto ()"
//    List<StudentDto> findLectureInfoBySem()

//    @Query("select s.studId FROM Student s Join s.member m where m.id = s.member.id"
//            + " and m.userName = :name and s.studGrade = :grade")
//    Long findStudentIdByNameAndGrade(@Param("name") String name,
//                                     @Param("grade") String grade);

}
