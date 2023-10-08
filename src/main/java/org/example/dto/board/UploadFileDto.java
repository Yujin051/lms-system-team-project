package org.example.dto.board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto {

    private List<MultipartFile> files;

}
