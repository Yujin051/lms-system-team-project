package org.example.dto.board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * @author 임승범
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto {

    private List<MultipartFile> files;

}
