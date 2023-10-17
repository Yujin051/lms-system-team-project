package org.example.repository;

import org.example.entity.DirectMsg;
import org.example.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 임승범
 */

@Repository
public interface DirectMsgPagingRepository extends PagingAndSortingRepository<DirectMsg , Long> {

    // 논리삭제 처리 되지 않은 쪽지(나와 관련된) 리스트 가져오기
    @Query("SELECT dm FROM DirectMsg dm WHERE (dm.recvId = :myMember OR dm.sendId = :myMember) AND dm.sendDelYn = false")
    Page<DirectMsg> findByMyPage(@Param("myMember")Member member , Pageable pageable);

}
