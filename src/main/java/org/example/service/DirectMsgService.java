package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.DirectMsgDto;
import org.example.entity.DirectMsg;
import org.example.entity.Member;
import org.example.repository.DirectMsgPagingRepository;
import org.example.repository.DirectMsgRepository;
import org.example.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 임승범
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectMsgService {

    private final DirectMsgRepository directMsgRepository;
    private final MemberRepository memberRepository;
    private final DirectMsgPagingRepository directMsgPagingRepository;

    // 여러명에게 쪽지 쓰기
    public List<DirectMsg> saveAll(DirectMsgDto directMsgDto , Principal principal){
        // 목록 가져오기
        List<String> loginIds = directMsgDto.getToSome();
        // 보낼 메세지 리스트 만들기
        List<DirectMsg> directMsgs = new ArrayList<>();
        // 발신자 설정(사용자 자신)
        Member sendId = memberRepository.findByUserId(principal.getName());
        // 보낼 대상의 수만큼.
        loginIds.forEach(loginId -> {
            // 발신자 id 찾아와서 설정
            Member recvId = memberRepository.findByUserId(loginId);
            // 각각 dto에 설정 해준다.
            directMsgDto.setRecvId(recvId);
            directMsgDto.setSendId(sendId);
            // entity로 생성시키고 담는다.
            DirectMsg msg = directMsgDto.toEntity();
            directMsgs.add(msg);
        });

        List<DirectMsg> msgs = directMsgRepository.saveAll(directMsgs);

        return msgs;
    }

    // 한명에게 쪽지 쓰기
    public DirectMsg save(DirectMsgDto directMsgDto , Principal principal){

        String loginId = directMsgDto.getToSome().get(0);
        // 발신자 설정(사용자 자신)
        Member sendId = memberRepository.findByUserId(principal.getName());
        // 발신자 id 찾아와서 설정
        Member recvId = memberRepository.findByUserId(loginId);
        // 각각 dto에 설정 해준다.
        directMsgDto.setRecvId(recvId);
        directMsgDto.setSendId(sendId);
        // entity로 생성시킨다.
        DirectMsg directMsg = directMsgDto.toEntity();
        // db에 저장하도록.
        DirectMsg msg = directMsgRepository.save(directMsg);

        return msg;
    }

    // 전체 페이지 가져오기
    public Page<DirectMsg> getAllMsg(Principal principal , Pageable pageable){

        Member myMember = memberRepository.findByUserId(principal.getName());
        Page<DirectMsg> msgPages = directMsgPagingRepository.findByMyPage(myMember , pageable);

        return msgPages;
    }


}
