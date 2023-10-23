<div align=left><h1>💻LMS 학적관리시스템💻</h1></div>

스프링 부트 + JPA LMS 학적관리시스템

<div align=left><h1>프로젝트 소개</h1></div>
교육 및 학습환경을 관리하고 지원하는 LMS학적 관리 시스템 입니다.

<div align=left><h1>📚 STACKS</h1></div>

<div align=left><h3>📕 Environment</h3></div>

<div>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
  <img src="https://img.shields.io/badge/kakaotalk-FFCD00?style=for-the-badge&logo=kakaotalk&logoColor=white">
</div>

<div align=left><h3>📙 Development</h3></div>

<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <img src="https://img.shields.io/badge/jpa-FF6F00?style=for-the-badge&logo=jpa&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
</div>

<div align=left><h1>맴버 구성 및 역할 분담</h1></div>

**😺팀장 권혁인** : 강사관리, 강좌 관리, 수강신청 조회, 유튜브 등록

**😺팀원 임휘재** : 학생관리 조회, 온라인강의수강현황 조회, 전체성적관리 조회, 게시글 작성(담당용) CRUD, 게시판 정보관리 CRUD, 
유튜브 API를 활용한 유튜브 재생 제어기능

**😺팀원 신동은** : 

**😺팀원 이현석** : 

**😺팀원 신민기** : 

**😺팀원 임승범** : 

<div align=left><h1>💻 화면 구성</h1></div>

<div align=center>
    <img width="30%" src="https://github.com/Yujin051/lms-system-team-project/assets/105468233/c40db232-1d89-4d43-8332-43c18ae4c0a8.png"/>
    <img width="30%" src="https://github.com/Yujin051/lms-system-team-project/assets/105468233/76e446ca-c9a9-4c53-92df-7c97db162087.png"/>
    <img width="30%" src="https://github.com/Yujin051/lms-system-team-project/assets/105468233/f0d6ace9-24f8-4a05-b6f4-de71873ab497.png"/>
</div>

<div align=center>
    <img width="30%" src="https://github.com/Yujin051/lms-system-team-project/assets/105468233/9e358461-d33c-4366-882c-ec12a0c5eb89.png"/>
    <img width="30%" src="https://github.com/Yujin051/lms-system-team-project/assets/105468233/bb041fa8-bdf6-4e53-996f-f3244e06d4d6.png"/>
    <img width="30%" src="https://github.com/Yujin051/lms-system-team-project/assets/105468233/ea159a7f-fd5d-48be-ad71-fae830f64d95.png"/>
</div>

<div align=left><h1>💡 주요 기능</h1></div>

<div align=left><h2>👲 관리자 페이지</h2></div>

### 학생관리
- AJAX를 사용한 비동기 통신을 통해 디비와 통신하여 프론트에서 학생 정보를 검색 및 조회 가능
- 비동기 통신으로 그리드에 갱신된 정보를 출력하는 기능

### 강사관리
- AJAX를 사용한 비동기 통신을 통해 디비와 통신하여 프론트에서 학생 정보를 검색 및 조회 가능
- 비동기 통신으로 그리드에 갱신된 정보를 출력하는 기능

### 강좌관리
- AJAX를 사용한 비동기 통신을 통해 디비와 통신하여 프론트에서 학생 정보를 검색 및 조회 가능
- AJAX를 사용해 프론트에서 입력한 정보를 서버로 넘겨서 DB에 업데이트 하는 기능
- 수정한 내용을 전달하여 디비에 등록하고 비동기 통신을 통해 새로운 데이터로 그리드를 갱신하는 기능
- 그리드UI에서 해당 강좌를 선택한 후 '삭제'버튼을 누르면 이 정보를 서버로 전송하여 해당 데이터를 디비에서 삭제하고, 그 결과를 비동기 통해 그리드를 업데이트하여 반영

### 수강신청관리
- AJAX를 사용한 비동기 통신을 통해 디비와 통신하여 프론트에서 학생 정보를 검색 및 조회 가능

### 온라인강의콘텐츠관리
- 구글 OAuth인증을 통한 유튜브 API의 사용 권한 부여와 부여된 권한을 이용하여 유튜브 동영상을 서버 단위에서 업로드를 할 수 있는 로직 구현
- API 통신을 이용한 동영상 정보 업데이트기능과 학습시간을 실시간으로 갱신하는 기능을 구현

### 온라인강의정보관리
- AJAX를 사용해 디비와 비동기 통신을 하여 조회하고 업데이트하는 CRUD기능

### 온라인강의수강현황
- AJAX를 사용한 비동기 통신을 통해 디비와 통신하여 프론트에서 학생 정보를 검색 및 조회 가능

### 전체성적관리
- AJAX를 사용한 비동기 통신을 통해 디비와 통신하여 프론트에서 학생 정보를 검색 및 조회 가능

### 게시글 작성(담당용)
- AJAX를 사용해 디비와 비동기 통신을 하여 조회하고 업데이트하는 CRUD기능

### 게시판 정보관리
- AJAX를 사용해 디비와 비동기 통신을 하여 조회하고 업데이트하는 CRUD기능

<div align=left><h2>👦 학생/강사 페이지</h2></div>

### 회원가입 및 로그인 기능
- 스프링 시큐리티를 사용
- 권한에 따라 로그인 시 리다이렉션되는 루트 페이지를 설정
- 자기 권한에 맞는 페이지만 접근이 가능






<div align=left><h1>프로젝트 회고</h1></div>

### 😃keep

### 😥problem

### 😎Try


