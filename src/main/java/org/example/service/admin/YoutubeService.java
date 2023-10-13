package org.example.service.admin;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class YoutubeService {
    private static final String CLIENT_SECRETS_FILE = "/static/OAuthClientSecretToken/client_secrets.json";
    private static final String REDIRECT_URI = "http://localhost/youtubeAuthToken"; // 등록한 리디렉션 URI
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/youtube.upload"); // 필요한 스코프


    public String getAccessToken (String authorizationCode){

        log.info("code : {}", authorizationCode);

        try {
            HttpTransport httpTransport = new NetHttpTransport();
            GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(gsonFactory, new InputStreamReader(
                    YoutubeService.class.getResourceAsStream(CLIENT_SECRETS_FILE)
            ));

            log.info("clientSecrets : {}", clientSecrets);

            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, gsonFactory, clientSecrets, SCOPES)
                    .setDataStoreFactory(new MemoryDataStoreFactory())
                    .setAccessType("offline")
                    .build();

            log.info("flow : {}", flow.getAccessType());

            TokenResponse tokenResponse = flow.newTokenRequest(authorizationCode)
                    .setRedirectUri(REDIRECT_URI)
                    .execute();

            log.info("tokenResponse : {}", tokenResponse);

            Credential credential = flow.createAndStoreCredential(tokenResponse, null);

            log.info(credential.getAccessToken());

            return credential.getAccessToken();
        } catch (GoogleJsonResponseException e) {
            // Google API 서버에서 오류 응답을 받을 때의 처리
            e.printStackTrace();
            e.getDetails();
            e.getMessage();
            return null;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

}
