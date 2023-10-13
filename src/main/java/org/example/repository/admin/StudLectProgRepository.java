package org.example.repository.admin;

import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudLectProgRepository extends JpaRepository<StudLectProg, Long> {

    @Query("SELECT NEW org.example.dto.admin.StudLectProgDto " +
            "(sp.magId) " +
            "FROM StudLectProg sp")
    StudLectProgDto findMagId();
}
