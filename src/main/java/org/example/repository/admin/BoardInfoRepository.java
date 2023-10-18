package org.example.repository.admin;

import org.example.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardInfoRepository extends JpaRepository<BoardInfo, Long> {
}
