package org.example.repository;

import org.example.dto.admin.MemberDto;
import org.example.dto.admin.StudentDto;
import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);

    @Query("SELECT NEW org.example.dto.admin.StudentDto" +
            "(m.userName, m.userId, m.userBirthday, m.userGender, m.userEmail, " +
            "s.studGrade) " +
            "FROM Member m JOIN Student s JOIN LectInfo l join Professor p")
    List<StudentDto> findGradeStudInfo2();

//    @Query("SELECT NEW org.example.dto.admin.StudentDto ()"
//    List<StudentDto> findLectureInfoBySem()

    @Query("select s.studId FROM Student s Join s.member m where m.id = s.member.id"
            + " and m.userName = :name and s.studGrade = :grade")
    Long findStudentIdByNameAndGrade(@Param("name") String name,
                                     @Param("grade") String grade);



    @Query("SELECT NEW org.example.dto.admin.MemberDto" +
            "(l.lectId, l.lectName, l.lectStart, l.lectEnd, l.lectCredit, l.isActive," +
            "m.userName, s.studId) " +
            "FROM Member m JOIN Professor p ON m.professor.id = p.id " +
            "JOIN LectInfo l ON p.id = l.professor.id JOIN Student s ON s.studId = m.student.studId " +
            "WHERE s.studId = :studId")
    List<MemberDto> gradesByCourse(@Param("studId") Long studId);
}
