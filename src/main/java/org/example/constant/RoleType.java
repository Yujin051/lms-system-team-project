package org.example.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    USER("ROLE_USER", "고객"),
    TEACHER("ROLE_TEACHER", "강사"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
