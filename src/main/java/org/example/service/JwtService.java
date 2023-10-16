package org.example.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@Getter
public class JwtService {

    // 임시 JWT 시크릿키
    String secret = "c2VDcmV0U2NlS2V5U2NlcnRzY3JldHlrZXlrZXlrZXljZXNlU0VjcmV0a2V5a1NlY2tlU2VjcmV0S2V5";

    public String createJwt(String accessToken, String secretKey) {
        // 이진으로 변경
        byte[] secretKeyBytes = secretKey.getBytes();
        // deprecated된 서명 방식 해결 - Key 사용
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);

        // 생성된 jwt 반환
        return Jwts.builder().setSubject("googleAccessToken")
                .claim("accessToken", accessToken)
                .signWith(key)
                .compact();
    }
}
