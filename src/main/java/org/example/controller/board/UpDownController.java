package org.example.controller.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.example.dto.board.UploadFileDto;
import org.example.dto.board.UploadResultDto;
import org.example.entity.FileInfo;
import org.example.repository.FileInfoRepository;
import org.example.service.BoardInfoService;
import org.example.service.BoardService;
import org.example.service.FileInfoService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UpDownController {

    private final String uploadPath = "C:\\upload";
    private final FileInfoService fileInfoService;

    @PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDto> upload(UploadFileDto uploadFileDto){

        log.info("uploadFileDto::{}",uploadFileDto);

        if(uploadFileDto.getFiles() != null){

            List<UploadResultDto> list = new ArrayList<>();
            List<FileInfo> fileInfoList = new ArrayList<>();

            // dto안의 파일들 수만큼 반복문
            uploadFileDto.getFiles().forEach(multipartFile -> {
                // 파일의 원본이름 저장
                String originName = multipartFile.getOriginalFilename();
                log.info(originName);
                // 랜덤갑 생성, 저장경로에 uui를 붙여서 중복방지용으로.
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath , uuid + "_" + originName);

                try{
                    multipartFile.transferTo(savePath); // 실제 파일 저장
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                // fileInfo 데이터 만들어주기
                fileInfoList.add(FileInfo.builder()
                                .orgFileName(originName)
                                .saveFileName(uuid + "_" + originName)
                                .filePath(savePath.toString())
                                .fileSize(multipartFile.getSize())
                                .build());

                log.info("fileInfoList::{}",fileInfoList);

                list.add(UploadResultDto.builder()
                        .uuid(uuid)
                        .fileName(originName)
                        .build()
                );
            }); // end each
            // DB에 만든 데이터 넣고 id 값 받아온다
            Long finalId = fileInfoService.saveAllFile(fileInfoList);

            list.forEach(uploadResultDto -> {
                uploadResultDto.setId(finalId);
            });

            return list;
        } // end if
        return null;
    }

    // 파일 조회
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){

        log.info("Get요청 /download/filename >>> viewFileGet() 실행됨");

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type" , Files.probeContentType(resource.getFile().toPath()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(resource);
    }

    @GetMapping("/download/file/{fileNo}/{fileSeq}")
    public void downloadFile(
            @PathVariable(name = "fileNo") Long fileNo ,
            @PathVariable(name = "fileSeq") Long fileSeq ,
            HttpServletResponse response) throws ServletException , IOException{

        log.info("Get요청 /download/file/" + fileNo + "/" + fileSeq + " >>> downloadFile()실행됨.");

        FileInfo file = fileInfoService.findByFileNoAndFileSeq(fileNo , fileSeq);

        String orgFileName = file.getOrgFileName(); // 오리지널 파일이름
        String filePath = file.getFilePath();   // 저장된경로와 파일이름

        File downfile = new File(filePath); // 다운로드 파일 하나 만든다.

        byte fileByte[] = FileUtil.readAsByteArray(downfile);   // byte 배열에 담고

        response.setContentType("application/octet-stream");    // 설정
        response.setContentLength(fileByte.length);

        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(orgFileName,"UTF-8") +"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        // 버퍼에 쓰고 모두 강제 흘려보낸다.
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }


}
