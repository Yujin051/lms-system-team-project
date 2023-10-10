package org.example.repository;

import jakarta.persistence.NamedStoredProcedureQuery;
import org.example.dto.ProfessorDto;
import org.example.entity.Member;
import org.example.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    // 강사정보 전체 조회
    @Query("select new org.example.dto.ProfessorDto(m.userName, m.userPhoneNum, m.userEmail, "
            + "m.userBirthday, m.userGender, p.isActive, p.profWork )"
            + "from Professor p join p.member m where p.member.id = m.id")
    List<ProfessorDto> findProfessorDtos();

    // 검색조건 통하여 강사 조회
    @Query("select new org.example.dto.ProfessorDto(m.userName, m.userPhoneNum, m.userEmail, "
            + "m.userBirthday, m.userGender, p.isActive, p.profWork )"
            + "from Professor p join p.member m where p.member.id = m.id and p.isActive"
            + " = :active and p.profWork LIKE %:subject% and m.userName LIKE %:name%")
    List<ProfessorDto> findProfessorDtosByConditions(@Param("active") boolean active,
                                                     @Param("subject") String subject,
                                                     @Param("name") String name);

    // 강사 상세정보 조회
    @Query("select new org.example.dto.ProfessorDto(p.id, m.userName, m.userBirthday, "
            + "m.userGender, m.userPhoneNum, m.userAddr, m.id, m.userEmail, p.isActive, "
            + "p.profAcOwner, p.profBank, p.profAccount, p.profAgency) from Professor p "
            + "join p.member m where m.id = p.member.id and p.profWork = :work and "
            + "p.member.userName = :name")
    ProfessorDto findProfessorDetail(@Param("work") String work,
                                     @Param("name") String name);

}
