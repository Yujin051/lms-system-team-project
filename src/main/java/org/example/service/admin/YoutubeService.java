package org.example.service.admin;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class YoutubeService {

    // 사용자 상수들
    private static final String REDIRECT_URI = "http://localhost/youtubeAuthToken"; // 등록한 리디렉션 URI
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube",
            "https://www.googleapis.com/auth/youtube.upload"); // 필요한 스코프, 유튜브 계정과 업로드 권한
    private static final String FILE_DIRECTORY = "C:\\app\\java\\lms-system-team-project\\src\\main\\resources\\OAuthClientSecret\\AccessToken";

    private static final GsonFactory GSON_FACTORY = new GsonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    // 사용할 client secret 의존성
    private GoogleClientSecrets clientSecrets;

    // 인증 코드 기반 액세스 토큰 얻는 서비스
    // 스코프 용도에 따라서 분리해서 동작하게 만들어야 될 것 같은데 일단은 하나로 통합(2개 스코프)
    public Credential createAndStoreCredential(String authCode, Principal principal) {

        log.info("code : {}", authCode);
        File dataDir = new File(FILE_DIRECTORY);

        try {
            // 사용자 인증 정보 저장할 저장소 설정
            FileDataStoreFactory fileDataStoreFactory = new FileDataStoreFactory(dataDir);

            // clientSecret 확인
            log.info("clientSecrets : {}", clientSecrets);
            log.info("dataDir : {}", dataDir);

            // 인증 절차 flow
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, GSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory(fileDataStoreFactory)
                    .setAccessType("offline")
                    .build();

            GoogleTokenResponse tokenResponse = flow
                    .newTokenRequest(authCode)
                    .setRedirectUri(REDIRECT_URI)
                    .execute();

            log.info("flow : {}", flow);
            log.info("tokenResponse : {}", tokenResponse);

            Credential credential = flow.createAndStoreCredential(tokenResponse, principal.getName());

            return credential;
        } catch (GoogleJsonResponseException e) {
            // Google API 서버에서 오류 응답을 받을 때의 처리
            e.printStackTrace();
            e.getDetails();
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }
        return null;
    }

    public void uploadVideo() {
        
    }



    // 기본 설정 초기화
    // @PostCostruct는 의존성 주입이 완료된 후 한 번 실행됨.
    @PostConstruct
    public void setDefault() {
        // 사용할 clientSecret 탐색하기
        String clientSecretUrl = "C:\\app\\java\\lms-system-team-project\\src\\main\\resources\\OAuthClientSecret\\client_secrets.json";
        // 경로에 파일이 없다면 에러메세지 출력?
        Objects.requireNonNull(clientSecretUrl, "classpath:OAuthClientSecret/client_secrets.json 파일이 없습니다.");

        try(Reader reader = new FileReader(clientSecretUrl)){
            // Client secret 가져오기
            this.clientSecrets = GoogleClientSecrets.load(GSON_FACTORY, reader);

            // client_secrets.json 내부에 필요한 값이 존재하지 않는다면 예외처리
            if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                    || clientSecrets.getDetails().getClientSecret().startsWith("Enter")) {
                String msg =  "https://console.developers.google.com/project/_/apiui/credential 에서 Client ID와 secret를 찾아서"
                        + "src/main/resources/OAuthClientSecret/client_secrets.json 에 넣어주세요";
                log.error(msg);
                throw new IOException(msg);
            }

        } catch(IOException e) {
            // 이 값 없이 API는 동작할 수 없으므로 RuntimeException
            throw new RuntimeException(e);
        }
    }
}
