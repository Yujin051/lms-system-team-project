package org.example.dto.board;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoId implements Serializable {

    @Column(name = "file_no")
    private Long fileNo;

    @Column(name = "file_seq")
    private Long fileSeq;



}
