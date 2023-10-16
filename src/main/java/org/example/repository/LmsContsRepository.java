package org.example.repository;

import org.example.entity.LectNth;
import org.example.entity.LmsConts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LmsContsRepository extends JpaRepository<LmsConts, Long> {

}
