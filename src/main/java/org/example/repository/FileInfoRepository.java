package org.example.repository;

import org.example.dto.board.FileInfoId;
import org.example.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo , FileInfoId> {

}
