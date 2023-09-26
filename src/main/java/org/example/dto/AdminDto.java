package org.example.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.constant.Gender;
import org.example.constant.RoleType;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class AdminDto {


        private String adminId;

        private String adminPassword;

        private RoleType adminRole;


        // 여기서 강사-학생 구분을 받으면 하나의 테이블에서 두개의 권한을 동시에 컨트롤할 수 있을 것 같다.
        // 그런데 단점은 이제 한개의 테이블에 너무 많은 컬럼들이 들어가기 때문에 데이터의 조회가 어려울 수 있음
        // 그렇다고 조인을 쓰기에는 두개의 테이블이 시큐리티 설정을 다 먹을 수 있는지를 모르겠다.
        // DTO를 나눠서 입력받을 수 있는 방법?
        // 프론트단에서 두개로 나누고 DTO에선 하나의 테이블로 값을 넣는다? -- 가능한가??
        @Builder
        public AdminDto(String adminId, String adminPassword, String adminName, String adminPhoneNum, String adminEmail
                , String adminAddr, Gender adminGender, LocalDate adminRegDate, LocalDate adminBirthday, RoleType adminRole) {
            this.adminId = adminId;
            this.adminPassword = adminPassword;
            this.adminRole = adminRole;
        }
}
