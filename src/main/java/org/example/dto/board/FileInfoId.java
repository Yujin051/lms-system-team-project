package org.example.dto.board;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoId implements Serializable {


    private Long fileSeq;
    private Long fileNo;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "file_seq" , updatable = false)


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FileInfoId that = (FileInfoId) o;
//        return fileNo == that.fileNo &&
//                fileSeq == that.fileSeq;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fileNo, fileSeq);
//    }

}
