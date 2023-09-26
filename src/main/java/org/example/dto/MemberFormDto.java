package org.example.dto;


import org.example.constant.Gender;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.constant.RoleType;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
public class MemberFormDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Length(min = 6, max = 16, message = "비밀번호는 6자 이상 16자 이하로 입력해주세요")
    private String userPassword;

    private String userName;

    private String userPhoneNum;

    @Email(message = "이메일 양식으로 입력해 주세요")
    private String userEmail;

    private String userAddr;

    private Gender userGender;

    private LocalDate userRegDate;

    private LocalDate userBirthday;

    private RoleType userRole;


    // 여기서 강사-학생 구분을 받으면 하나의 테이블에서 두개의 권한을 동시에 컨트롤할 수 있을 것 같다.
    // 그런데 단점은 이제 한개의 테이블에 너무 많은 컬럼들이 들어가기 때문에 데이터의 조회가 어려울 수 있음
    // 그렇다고 조인을 쓰기에는 두개의 테이블이 시큐리티 설정을 다 먹을 수 있는지를 모르겠다.
    // DTO를 나눠서 입력받을 수 있는 방법?
    // 프론트단에서 두개로 나누고 DTO에선 하나의 테이블로 값을 넣는다? -- 가능한가??
    @Builder
    public MemberFormDto(String userId, String userPassword, String userName, String userPhoneNum, String userEmail
    , String userAddr, Gender userGender, LocalDate userRegDate, LocalDate userBirthday, RoleType userRole) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userEmail = userEmail;
        this.userAddr = userAddr;
        this.userGender = userGender;
        this.userRegDate = userRegDate;
        this.userBirthday = userBirthday;
        this.userRole = userRole;
    }
}
