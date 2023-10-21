package org.example.repository.admin;

import org.example.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminBoardInfoRepository extends JpaRepository<BoardInfo, Long> {
}
