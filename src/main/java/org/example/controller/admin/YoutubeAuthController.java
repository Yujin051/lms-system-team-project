package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.service.admin.AdminYoutubeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class YoutubeAuthController {

    private final AdminYoutubeService adminYoutubeService;

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

    @GetMapping("/youtubeAuthToken")
    public String youtubeToken(@RequestParam(name = "code") String code, Principal principal) {

        // api에서 응답받은 코드로 세션에 유저 인증 정보 credential 저장
        adminYoutubeService.createAndStoreCredential(code, principal);

        return "redirect:/admin/ytr";
    }
}
