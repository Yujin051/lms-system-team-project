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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class YoutubeService {
    private static final String CLIENT_SECRETS_FILE = "/static/OAuthClientSecretToken/client_secrets.json";
    private static final String REDIRECT_URI = "http://localhost/youtubeAuthToken"; // 등록한 리디렉션 URI
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube",
            "https://www.googleapis.com/auth/youtube.upload"); // 필요한 스코프, 유튜브 계정과 업로드 권한
    private static final String FILE_DIRECTORY = "C:\\app\\java\\lms-system-team-project\\src\\main\\resources\\static\\OAuthClientSecretToken\\AccessToken";

    // 인증 코드 기반 액세스 토큰 얻는 서비스
    public String getCredential(String authCode, Principal principal){

        log.info("code : {}", authCode);
        File dataDir = new File(FILE_DIRECTORY);

        try {
            HttpTransport httpTransport = new NetHttpTransport();
            GsonFactory gsonFactory = new GsonFactory();
            FileDataStoreFactory fileDataStoreFactory = new FileDataStoreFactory(dataDir);


            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(gsonFactory, new InputStreamReader(
                    YoutubeService.class.getResourceAsStream(CLIENT_SECRETS_FILE)
            ));

            log.info("clientSecrets : {}", clientSecrets);
            log.info("dataDir : {}", dataDir);

            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, gsonFactory, clientSecrets, SCOPES)
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

//            log.info(credential.getAccessToken());

            return credential.getAccessToken();
        } catch (GoogleJsonResponseException e) {
            // Google API 서버에서 오류 응답을 받을 때의 처리
            e.printStackTrace();
            e.getDetails();
            e.getMessage();
            return "구글 API 에러 발생";
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return "에러 발생";
        }
    }



}
