package org.example.repository;

import org.example.dto.admin.LmsContsDto;
import org.example.entity.LmsConts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LmsContsRepository extends JpaRepository<LmsConts, Long> {
    LmsConts findByContsYout(String Id);

    @Query("select new org.example.dto.admin.LmsContsDto(lc.contsName, lc.contsYout, lc.contsTime, lc.lectNth.lectInfo.lectName) from LmsConts lc" +
            " where lc.contsName Like %:contsName% and lc.lectNth.lectInfo.lectName like %:lectName%")
    List<LmsContsDto> searchByContsNameAndLectName(@Param("contsName") String contsName,
                                                   @Param("lectName")String lectName);
}
