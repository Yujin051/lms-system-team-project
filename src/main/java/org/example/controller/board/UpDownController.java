package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.board.UploadFileDto;
import org.example.dto.board.UploadResultDto;
import org.example.entity.FileInfo;
import org.example.repository.FileInfoRepository;
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
import java.io.IOException;
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

            fileInfoService.saveAllFile(fileInfoList);

            return list;
        } // end if
        return null;
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){

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


}
