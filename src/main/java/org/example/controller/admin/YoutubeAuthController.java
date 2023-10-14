package org.example.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.admin.YoutubeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class YoutubeAuthController {

    private final YoutubeService youtubeService;

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
        log.info("코드 : {}", code);
        String accessToken = youtubeService.getCredential(code, principal);
        log.info("액세스토큰 : {}", accessToken);

        return "/admin/youTube_registration";
    }

    // 동영상 업로드 컨트롤러
    @PostMapping("/youtubeUpload")
    public void youtubeUpload() {

    }
}
