package org.example.repository;

import org.example.dto.board.FileInfoId;
import org.example.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author 임승범
 */
@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo , FileInfoId> {

    // 가장 높은 수의 id 값 가져오기 (id만)
    @Query("SELECT MAX(fi.fileNo) FROM FileInfo fi")
    Long findMaxFileNo();

    List<FileInfo> findByFileNo(Long fileNo);

    FileInfo findByFileNoAndFileSeq(Long fileNo , Long fileSeq);


}
