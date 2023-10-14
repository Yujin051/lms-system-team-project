package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.FileInfo;
import org.example.repository.FileInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author 임승범
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    // 모든 파일 저장
    public Long saveAllFile(List<FileInfo> fileInfoList){
        // db에 저장되 있는 마지막 fileNo 값 가져오기
        Long lastFileInfoId = fileInfoRepository.findMaxFileNo();
        // 만약 마지막 fileNo가 없는 초기상황일때 값 지정.
        if(lastFileInfoId == null){
            lastFileInfoId = 0L;
        }
//        int length = fileInfoList.toArray().length; 확인용
//        log.info("length::{}",length);
        // 반복문을 돌리며 각 파일 안에 번호지정.
        for(int i=0; i<fileInfoList.toArray().length; i++){

            Long k = (long)i+1;

            fileInfoList.get(i).setFileNo(lastFileInfoId+1L);
            fileInfoList.get(i).setFileSeq(k);
            fileInfoRepository.save(fileInfoList.get(i));
        }

        return lastFileInfoId+1L;
    }

    // id로 seq 기록된 최댓값까지 레코드 가져오기

    public List<FileInfo> findFileInfoList(Long fileNo){
        return fileInfoRepository.findByFileNo(fileNo);
    }

    public FileInfo findByFileNoAndFileSeq(Long fileNo , Long fileSeq){
        return fileInfoRepository.findByFileNoAndFileSeq(fileNo , fileSeq);
    }




}
