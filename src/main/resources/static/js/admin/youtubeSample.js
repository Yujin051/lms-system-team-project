//player라는 id를 가진 div에 플레이어 호출
function onYouTubeIframeAPIReady() {
    player = new YT.Player('player', {
        width: '900',
        height: '506',
        videoId: YTB_URL,
        playerVars: {
            disablekb: 1,  //키보드 입력 제한
            rel: 0
        },
        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange,
            'onPlaybackRateChange': onPlayerPlaybackRateChange
        }
    });
}

//마지막에 보고 있던 시간으로 이동 후 재생
function onPlayerReady(event) {
    event.target.loadVideoById(YTB_URL, FNL_POSI);
    event.target.playVideo();

    RUN_TM = event.target.getDuration()-5; //현재 재생위치에서 -5초뺀값
    PROG_RT = (MAX_POSI / RUN_TM) * 100; //진행률

    updatePosition();
}

//시간마다 반복할 함수 저장용
let popupInterval;
let recordInterval;
let finishInterval;
function onPlayerStateChange(event){
    if(event.data == YT.PlayerState.PLAYING){
        RUN_TM = player.getDuration() - 5;
        PROG_RT = (MAX_POSI / RUN_TM) * 100;
        MAX_POSI = gfn_isNull(MAX_POSI) ? ls_LM0751P_max_posi : MAX_POSI;

        if(event.target.getCurrentTime() > Number(MAX_POSI) + 1){
            event.target.seekTo(MAX_POSI);
        }


        if(event.target.getCurrentTime() >= RUN_TM ){
            player.pauseVideo();
            player.seekTo(FNL_POSI);
        }

        if(popupInterval) clearInterval(popupInterval);
        if(recordInterval) clearInterval(recordInterval);
        if(finishInterval) clearInterval(finishInterval);

        popAtPosition();
        popupInterval = setInterval(popAtPosition,1000);
        finishPosition();
        finishInterval = setInterval(finishPosition,1000);

        //5초마다 MAX_POSI와 현재 시간을 저장한다
        recordInterval = setInterval(updatePosition,5000);

    }

    //일시정지중에는 반복을 멈춘다
    //일시정지한 시간을 기록한다
    if(event.data == YT.PlayerState.PAUSED){
        clearInterval(popupInterval);
        clearInterval(recordInterval);
        clearInterval(finishInterval);
        if(event.target.getCurrentTime() <= MAX_POSI+5){
            updatePosition();
        }
    }

    //영상이 끝나더라도 이전으로 되돌려서 일시정지. 사용자가 영상의 끝으로 이동하는것을 따로 막기위해 필요하다
    if(event.data == YT.PlayerState.ENDED){
        event.target.seekTo(event.target.getDuration()- 1);
        event.target.pauseVideo();
    }
}

//시간기록
function updatePosition(){
    FNL_POSI = player.getCurrentTime();
    MAX_POSI = MAX_POSI > FNL_POSI ? MAX_POSI : FNL_POSI;
    PROG_RT = RUN_TM >= MAX_POSI ? (MAX_POSI / RUN_TM).toFixed(4) * 100 : 100;

    requestPost("<c:url value='/OnLect/record.do' />",new URLSearchParams([
        ["HIS_SEQ", gfn_str(ls_his_seq)],
        ["NTH_NO", ls_LM0751P_nth_no],
        ["LECT_NO", ls_LM0751P_lect_no],
        ["FNL_POSI", FNL_POSI],
        ["MAX_POSI", MAX_POSI],
        ["PROG_RT", PROG_RT],
        ["INS_PGM_ID", ls_pgmId]
    ])).then(json => {
        if(gfn_isNull(ls_his_seq)){
            ls_his_seq = json["O_RESULT_VALUE"];
        }
    });

    requestPost("<c:url value='/sessionExtend.do' />");

}

function fn_callback(){

}

//정지시간(CTRL_TM)에 정지 및 팝업
function popAtPosition(){
    if(Math.floor(player.getCurrentTime()) == CTRL_TM
        && MAX_POSI < CTRL_TM){
        let confirmed = false;
        player.pauseVideo();
    }
}

//영상 끝나기 x초전에 정지
function finishPosition(){
    if(Math.floor(player.getCurrentTime()) >= RUN_TM){
        player.pauseVideo();
    }
}

//재생속도가 변경될 때 1을 초과하면 1로 변경
function onPlayerPlaybackRateChange(event){
    if (event.target.getPlaybackRate() > 1){
        event.target.setPlaybackRate(1);
    }
}