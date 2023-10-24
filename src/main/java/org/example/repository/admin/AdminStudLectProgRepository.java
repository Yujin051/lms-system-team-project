package org.example.repository.admin;

import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminStudLectProgRepository extends JpaRepository<StudLectProg, Long> {

    // 수강생차시진도 기본키, 최대재생시간, 최종재생시간 조회
    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.maxPosi, sp.fnlPosi) " +
            "FROM StudLectProg sp")
    StudLectProgDto findMagId();

    StudLectProg findByMagId(Long id);

    // 수강생차시진도 조회
    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId, sp.fnlPosi, sp.maxPosi, sp.isChecked, sp.checkDate) " +
            "FROM StudLectProg sp")
    List<StudLectProgDto> findStudLectProg();
}
