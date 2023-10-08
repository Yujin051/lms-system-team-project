package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.board.FileInfoId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Table(name="file_info")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(FileInfoId.class)
public class FileInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_no", updatable = false)
    private Long fileNo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public FileInfo(String orgFileName, String saveFileName, String filePath, Long fileSize) {
        this.orgFileName = orgFileName;
        this.saveFileName = saveFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
