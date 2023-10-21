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
    @Query("SELECT dm FROM DirectMsg dm WHERE (dm.recvId = :myMember AND dm.recvDelYn = false) OR (dm.sendId = :myMember AND dm.sendDelYn = false)")
    Page<DirectMsg> findByMyPage(@Param("myMember")Member member , Pageable pageable);

    // 논리삭제 처리 되지 않은 내가 보낸 쪽지 리스트 가져오기
    @Query("SELECT dm FROM DirectMsg dm WHERE dm.sendId = :myMember AND dm.sendDelYn = false")
    Page<DirectMsg> findMySendPage(@Param("myMember")Member member , Pageable pageable);

    // 논리삭제 처리 되지 않은 내가 받은 쪽지 리스트 가져오기
    @Query("SELECT dm FROM DirectMsg dm WHERE dm.recvId = :myMember AND dm.recvDelYn = false")
    Page<DirectMsg> findMyRecvPage(@Param("myMember")Member member , Pageable pageable);

    // 논리삭제 처리한 나의 쪽지 휴지통 가져오기
    @Query("SELECT dm FROM DirectMsg dm WHERE (dm.recvId = :myMember AND dm.recvDelYn = true) OR (dm.sendId = :myMember AND dm.sendDelYn = true)")
    Page<DirectMsg> findMyTrashPage(@Param("myMember")Member member , Pageable pageable);

    // 메시지 리스트 내용 검색 (전체에서)
    @Query("SELECT dm FROM DirectMsg dm WHERE dm.msgCont LIKE %:searchValue% AND (dm.recvId = :myMember AND dm.recvDelYn = false) OR (dm.sendId = :myMember AND dm.sendDelYn = false)")
    Page<DirectMsg> searchDirectMsgByMsgCont(@Param("searchValue") String searchValue , @Param("myMember") Member myMember , Pageable pageable);

    // 메시지 리스트 제목 검색 (전체에서)
    @Query("SELECT dm FROM DirectMsg dm WHERE dm.msgTitle LIKE %:searchValue% AND (dm.recvId = :myMember AND dm.recvDelYn = false) OR (dm.sendId = :myMember AND dm.sendDelYn = false)")
    Page<DirectMsg> searchDirectMsgByMsgTitle(@Param("searchValue") String searchValue , @Param("myMember") Member myMember , Pageable pageable);

    // 메시지 리스트 받은이 검색 (전체에서)
    @Query("SELECT dm FROM DirectMsg dm WHERE dm.recvId.userName LIKE %:searchValue% AND (dm.sendId = :myMember AND dm.sendDelYn = false)")
    Page<DirectMsg> searchDirectMsgByRecvId(@Param("searchValue") String searchValue , @Param("myMember") Member myMember , Pageable pageable);

}
