package org.example.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.constant.Gender;

@Data
@AllArgsConstructor
public class ApplyStudentDto {
    private Long studId;
    private String userName;
    private String userId;
    private String userEmail;
    private String lectSubject;
    private Gender userGender;
}
