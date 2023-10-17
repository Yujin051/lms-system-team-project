package org.example.controller.admin;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.youtube.model.Video;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.LmsConts;
import org.example.repository.LmsContsRepository;
import org.example.service.admin.YoutubeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class YoutubeController {

    private final YoutubeService youtubeService;
    private final LmsContsRepository lmsContsRepository;

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
    public String youtubeToken(@RequestParam(name= "code") String code, Principal principal) {

        // api에서 응답받은 코드로 세션에 유저 인증 정보 credential 저장
        youtubeService.createAndStoreCredential(code, principal);

        return "redirect:/setYoutubeApi";
    }

    // api 인증 후 돌아오는 페이지 -
    @GetMapping("/setYoutubeApi")
    public String youtubeUpload() {
        return "/admin/youTube_registration";
    }

    // 위까지 인증 절차, 아래부터 유튜브 API 서비스 컨트롤러

    // 업로드 로직
    @PostMapping("/youtubeUpload")
    @ResponseBody
    public void upload(@RequestParam(name="file") MultipartFile file,
                       @RequestParam(name="title") String title,
                       @RequestParam(name="detail") String detail) throws IOException {

        // API 통해 비디오 업로드, 업로드된 정보를 가진 video 객체 반환받음
        Video video = youtubeService.uploadVideo(title, detail, file);

        // 이름, 설명, 유튜브 비디오 ID로 DB에 새 컨텐츠 저장
        LmsConts lmsConts = LmsConts.builder()
                .contsName(title)
                .contsDetail(detail)
                .contsYout(video.getId())
                .build();
        lmsContsRepository.save(lmsConts);

        // 테스트용 로그
        log.info("\n================== Returned Video ==================\n");
        log.info("  - Id: " + video.getId());
        log.info("  - Title: " + video.getSnippet().getTitle());
        log.info("  - Tags: " + video.getSnippet().getTags());
        log.info("  - Privacy Status: " + video.getStatus().getPrivacyStatus());
        // 오류나는데?
        // log.info("  - Duration: " + video.getContentDetails().getDuration());
        log.info("  - Video Count: " + video.getStatistics().getViewCount());

    }
}
