package org.example.service.admin;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.tools.shell.IO;
import org.example.dto.admin.StudLectProgDto;
import org.example.entity.StudLectProg;
import org.example.repository.admin.StudLectProgRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.security.Principal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class YoutubeService {

    private final StudLectProgRepository studLectProgRepository;

    // 사용자 상수들
    private static final String REDIRECT_URI = "http://localhost/youtubeAuthToken"; // 등록한 리디렉션 URI
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/youtube",
            "https://www.googleapis.com/auth/youtube.upload"); // 필요한 스코프, 유튜브 계정과 업로드 권한
    private static final String FILE_DIRECTORY = "C:\\Users\\shins\\OneDrive\\바탕 화면\\lms-system-team-project\\src\\main\\resources\\OAuthClientSecret\\AccessToken";
    private static final GsonFactory GSON_FACTORY = new GsonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    // 사용할 client secret 의존성
    private GoogleClientSecrets clientSecrets;

    // 인증 코드 기반 액세스 토큰 얻는 서비스
    // 스코프 용도에 따라서 분리해서 동작하게 만들어야 될 것 같은데 일단은 하나로 통합(2개 스코프)
    public Credential createAndStoreCredential(String authCode, Principal principal) {
        final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");

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

            session.setAttribute("userCredential", credential);

            return credential;
        } catch (GoogleJsonResponseException e) {
            // Google API 서버에서 오류 응답을 받을 때의 처리
            e.printStackTrace();
            e.getDetails();
        } catch (IOException e) {
            // 예외 처리
            log.info("credential 저장 실패");
            e.printStackTrace();
        }
        return null;
    }

//    // 인증된 유저를 통해서 API 액세스 토큰 가져오는 서비스
//    public String getUserAccessToken(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String jwt = (String) session.getAttribute("googleAccessToken");
//
//        if (jwt != null) {
//            // JWT 디코드
//            String secret = jwtService.getSecret(); // JWT를 생성할 때 사용한 시크릿 키
//            byte[] secretKeyBytes = secret.getBytes();
//
//            // 세션에 저장된 jwt 사용해서 access token 가져오기
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(secretKeyBytes)
//                    .build()
//                    .parseClaimsJws(jwt)
//                    .getBody();
//
//            // JWT 내용 확인
//            String accessToken = claims.get("accessToken", String.class);
//            log.info("accessToken = {}", accessToken);
//
//            return accessToken;
//        } else {
//            return "액세스 토큰 취득에 실패했습니다.";
//        }
//    }

    // 사용자 인증정보 credential 얻기
    public Credential getUserCredential() {
        final HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference("session");
        return (Credential) session.getAttribute("userCredential");
    }

    // API 접근 로직
    public Video uploadVideo(String title, String detail, @NotNull MultipartFile content)
            throws IOException, GoogleJsonResponseException{
        Credential credential = getUserCredential();
        // 유저 인증 정보 credential 이 없다면 에러
        Objects.requireNonNull(credential, "먼저 API 인증을 진행해주세요.");

        try {


            // youtube 객체 생성
            YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, GSON_FACTORY, credential)
                    .setApplicationName("lmsapp")
                    .build();

            // youtube 에 업로드 할 비디오 객체 생성
            Video video = new Video();

            // 업로드한 비디오의 공개 범위, 제목, 설명
            video.setStatus(new VideoStatus().setPrivacyStatus("unlisted"));
            video.setSnippet(new VideoSnippet().setTitle(title).setDescription(detail));

            // 입력받은 비디오를 youtube 객체로 사용할 수 있도록 InputStreamContent로 변환
            InputStreamContent uploadContent =
                    new InputStreamContent(content.getContentType(), content.getInputStream());

            // API 통해서 유튜브에 업로드 시작
            YouTube.Videos.Insert videoInsert = youtube.videos().insert("snippet,status", video, uploadContent);
            Video returnedVideo = videoInsert.execute();

            // 업로드 요청이 성공했다면 해당 비디오 객체 반환
            return returnedVideo;

        } catch (GoogleJsonResponseException e) {
            log.info("구글 API 에러");
            log.info("에러 메세지 : {}", e.getMessage());
            log.info("에러 디테일 : {}", e.getDetails());
            throw e;
        } catch (IOException e) {
            log.info("비디오 업로드 실패");
            throw new RuntimeException(e);
        }
    }

    // 비디오 정보 수정(업데이트)
    public Video updateVideo(String videoId, String title, String detail)
            throws GoogleJsonResponseException, IOException {
        Credential credential = getUserCredential();
        Objects.requireNonNull(credential, "먼저 API 인증을 진행해주세요.");

        try {
            // youtube 객체 생성
            YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, GSON_FACTORY, credential)
                    .setApplicationName("lmsapp")
                    .build();

            // 새 비디오 객체를 생성하고 수정하기 위해 원하는 비디오 아이디 설정
            Video video = new Video();
            video.setId(videoId);

            // 수정할 내용을 담은 비디오 스니펫 생성, 설정
            VideoSnippet videoSnippet = new VideoSnippet();
            videoSnippet.setCategoryId("27"); // 카테고리 id 설정 -> 교육은 27. 없으면 에러남...
            videoSnippet.setTitle(title);
            videoSnippet.setDescription(detail);
            video.setSnippet(videoSnippet);

            YouTube.Videos.Update request = youtube.videos().update("snippet", video);

            Video response = request.execute();
            return response;

        } catch (GoogleJsonResponseException e) {
            throw e;
        } catch (IOException e) {
            log.error("수정할 비디오 정보를 가져올 수 없습니다", e);
            throw new RuntimeException(e);
        }

    }

    // 총 재생 시간 갱신
    public int getVideoTime(String videoId) throws GoogleJsonResponseException, IOException {
        try{
            // 인증정보 가져오기
            Credential credential = getUserCredential();
            Objects.requireNonNull(credential, "먼저 API 인증을 진행해주세요.");

            // 유튜브 객체 생성
            YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, GSON_FACTORY, credential)
                    .setApplicationName("lmsapp")
                    .build();

            // 인증된 계정의 전체 리스트 요청
            YouTube.Videos.List request = youtube.videos().list("contentDetails");

            // 리스트 중에서 얻어올 비디오 하나 선택
            VideoListResponse response = request.setId(videoId).execute();
            List<Video> videos = response.getItems();
            Video returnedVideo = videos.size() != 0 ? videos.get(0) : null;

            // 가져온 비디오에서 총 재생시간을 초 형식으로 파싱하여 리턴
            String duration = returnedVideo.getContentDetails().getDuration();
            log.info(duration);
            Duration vidDuration = Duration.parse(duration);
            return (int) vidDuration.getSeconds();

        } catch (GoogleJsonResponseException e) {
            throw e;
        } catch (IOException e) {
            log.error("비디오 길이 정보를 가져올 수 없습니다", e);
            throw new RuntimeException(e);
        }
    }

    // 비디오 삭제
    public void deleteVideo(String videoId) throws IOException, GoogleJsonResponseException {
        // 인증정보 가져오기
        Credential credential = getUserCredential();
        Objects.requireNonNull(credential, "먼저 API 인증을 진행해주세요.");
        try{
        // 유튜브 객체 생성
        YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, GSON_FACTORY, credential)
                .setApplicationName("lmsapp")
                .build();

        // 유튜브로 비디오 삭제 요청 생성 후 전달
        YouTube.Videos.Delete request = youtube.videos().delete(videoId);
        request.execute();
        } catch(GoogleJsonResponseException e) {
            throw e;
        } catch(IOException e) {
            log.error("삭제할 비디오 정보를 가져올 수 없습니다.");
            throw new RuntimeException(e);
        }
    }



    // 수강생차시진도 기본키 조회
    public StudLectProgDto getFindMagId() {
        return studLectProgRepository.findMagId();
    }

    // 수강생차시진도 모두 조회
    public List<StudLectProgDto> getFindStudLectProg() {
        return studLectProgRepository.findStudLectProg();
    }

    // 최종재생위치(fnlPosi)와 최대재생위치(maxPosi)를 데이터베이스에 저장
    public void savePlayTime(Long magId, double fnlPosi, double maxPosi) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));
        log.info("serfnlPosi : " + fnlPosi);
        log.info("sermaxPosi : " + maxPosi);
        if (maxPosi >= studLectProg.getMaxPosi()) {
            studLectProg.setMaxPosi(maxPosi);
        }
        studLectProg.setFnlPosi(fnlPosi); // 최종 재생 위치 업데이트
        studLectProgRepository.save(studLectProg);
    }

    // 진행률 데이터베이스에 저장
    public double saveProgress(Long magId, double progress) {
        StudLectProg studLectProg = studLectProgRepository.findById(magId)
                .orElseThrow(() -> new IllegalArgumentException("magId 오류"));

        // 만약, 진행률 70프로가 넘으면 출석 상태를 true로 바꾸고 현재 시간 찍히게
        if (progress >= 10 && !studLectProg.getIsChecked()) {
            studLectProg.setIsChecked(true); //출석상태 true로 저장
            LocalDateTime currentDateTime = LocalDateTime.now(); // 현재 날짜 및 시간 얻기

            // 원하는 날짜 및 시간 형식을 포맷
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // String에서 LocalDateTime으로 변환
            String formattedDateTime = currentDateTime.format(formatter);
            LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter);

            studLectProg.setCheckDate(localDateTime); //출석날짜 저장
        }
        log.info("serProgress : " + progress);
        studLectProg.setProgress(progress); //출석률 저장
        studLectProgRepository.save(studLectProg); // 데이터베이스에 저장
        return progress;
    }

    // 기본 설정 초기화
    // @PostCostruct는 의존성 주입이 완료된 후 한 번 실행됨.
    @PostConstruct
    public void setDefault() {
        // 사용할 clientSecret 탐색하기
        String clientSecretUrl = "C:\\Users\\shins\\OneDrive\\바탕 화면\\lms-system-team-project\\src\\main\\resources\\OAuthClientSecret\\client_secrets.json";

        // 경로에 파일이 없다면 에러메세지 출력?
        Objects.requireNonNull(clientSecretUrl, "classpath:OAuthClientSecret/client_secrets.json 파일이 없습니다.");

        try (Reader reader = new FileReader(clientSecretUrl)) {
            // Client secret 가져오기
            this.clientSecrets = GoogleClientSecrets.load(GSON_FACTORY, reader);

            // client_secrets.json 내부에 필요한 값이 존재하지 않는다면 예외처리
            if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                    || clientSecrets.getDetails().getClientSecret().startsWith("Enter")) {
                String msg = "https://console.developers.google.com/project/_/apiui/credential 에서 Client ID와 secret를 찾아서"
                        + "src/main/resources/OAuthClientSecret/client_secrets.json 에 넣어주세요";
                log.error(msg);
                throw new IOException(msg);
            }
        } catch (IOException e) {
            // 이 값 없이 API는 동작할 수 없으므로 RuntimeException
            throw new RuntimeException(e);
        }
    }
}
