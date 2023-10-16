package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.board.FileInfoId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * (수정사항)
 *  복합키 사용을 위하여 @idClass를 사용.
 *  FileInfoId 클래스를 사용하고 있습니다.
 * @author  임승범
 */

@NoArgsConstructor
@Table(name="file_info")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(FileInfoId.class)
@ToString
public class FileInfo {

    @Id
    @Column(name="file_no", updatable = false)
    private Long fileNo;

    @Id
    @Column(name="file_seq", updatable = false)
    private Long fileSeq;

//    @EmbeddedId
//    private FileInfoId fileInfoId;

    @Column(name="org_file_name")
    private String orgFileName;

    @Column(name="save_file_name")
    private String saveFileName;

    @Column(name="file_path")
    private String filePath;

    @Column(name="file_size")
    private Long fileSize;

    @Builder
    public FileInfo(Long fileNo , Long fileSeq , String orgFileName, String saveFileName, String filePath, Long fileSize) {
        this.fileNo = fileNo;
        this.fileSeq = fileSeq;
        this.orgFileName = orgFileName;
        this.saveFileName = saveFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
