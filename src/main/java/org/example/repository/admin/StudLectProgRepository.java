package org.example.repository.admin;

import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudLectProgRepository extends JpaRepository<StudLectProg, Long> {

    // 수강생차시진도 PK조회
    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.maxPosi) " +
            "FROM StudLectProg sp")
    StudLectProgDto findMagId();

    // 수강생차시진도 조회
    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.fnlPosi, sp.maxPosi, sp.isChecked, sp.checkDate) " +
            "FROM StudLectProg sp")
    List<StudLectProgDto> findStudLectProg();

    // 수강생차시진도 최대 재생 위치 조회

    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.fnlPosi, sp.maxPosi, sp.isChecked, sp.checkDate) " +
            "FROM StudLectProg sp " +
            "WHERE sp.magId = :magId")
    StudLectProgDto findProg(@Param("magId") Long magId);
}
