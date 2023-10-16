package org.example.controller.admin;

import com.google.api.client.auth.oauth2.Credential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.JwtService;
import org.example.service.admin.YoutubeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class YoutubeAuthController {

    private final YoutubeService youtubeService;
    private final HttpServletRequest request;
    private final JwtService jwtService;

    // 버튼 눌렀을 때 인증 토큰 얻어오는 컨트롤러
    @GetMapping("/youtubeAPIAuth")
    public String youtubeAuth() {
        String redirectUrl = "https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https://www.googleapis.com/auth/youtube " +
                "https://www.googleapis.com/auth/youtube.upload&" +
                "redirect_uri=http://localhost/youtubeAuthToken&" +
                "response_type=code&access_type=offline&" +
                "client_id=278647557012-a4i2nt3vtvkaimsk4g2josu21btucaf1.apps.googleusercontent.com";

        return "redirect:" + redirectUrl;
    }

    // 인증 코드로 액세스 토큰 얻는 컨트롤러
    @GetMapping("/youtubeAuthToken")
    public String youtubeToken(@RequestParam(name = "code") String code, Principal principal) {
        // 현재 웹 사이트에 로그인 되어있는 사용자 정보(관리자)와 구글 인증 정보를 사용하여
        // 액세스 토큰을 생성하고 해당 세션 내부에서 사용할 수 있도록 저장하는 서비스
        Credential credential = youtubeService.createAndStoreCredential(code, principal);

        // JWT 사용을 위한 시크릿 키 가져오기
        String secretKey = jwtService.getSecret();
        // accessToken을 가진 jwt 생성
        String jwt = jwtService.createJwt(credential.getAccessToken(), secretKey);
        // 생성된 jwt 사용하기 위해 세션에 저장
        request.getSession().setAttribute("googleAccessToken", jwt);

        return "redirect:/setYoutubeApi";
    }

    // api 인증 후 돌아오는 페이지 -
    @GetMapping("/setYoutubeApi")
    public String youtubeUpload() {
// 세션에서 JWT 가져오기
        HttpSession session = request.getSession();
        String jwt = (String) session.getAttribute("googleAccessToken");

        if (jwt != null) {
            // JWT 디코드
            String secret = jwtService.getSecret(); // JWT를 생성할 때 사용한 시크릿 키
            byte[] secretKeyBytes = secret.getBytes();

            // 세션에 저장된 jwt 사용해서 access token 가져오기
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKeyBytes)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            // JWT 내용 확인
            String accessToken = claims.get("accessToken", String.class);
            log.info("accessToken = {}", accessToken);
        }

        return "/admin/youTube_registration";
    }

    // 위까지 인증 절차, 아래부터 유튜브 API 서비스 컨트롤러


}
