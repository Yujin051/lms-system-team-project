package org.example.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadResultDto {

    private String uuid;

    private String fileName;

    private Long id;

//    @Builder
//    public UploadResultDto(String uuid , String fileName){
//        this.uuid = uuid;
//        this.fileName = fileName;
//    }

    public String getLink(){
        return uuid+"_"+fileName;
    }
}
