// 돌아가기 버튼 이벤트 , 전페이지로.
if (backBtn){
    backBtn.addEventListener('click' , ()=>{
        window.history.back();
    });
}

// 메세지 삭제
function deleteMsg(id) {

    console.log('click');

    let data = {
        id : id
    };

    let xhr = new XMLHttpRequest();
    xhr.open('POST','/board/msg/delete' , true);
    xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                alert('삭제 처리 되었습니다.');
                window.history.back();
            }
            else{
                alert('삭제 처리 실패 : firetrap5319@gmail.com 으로 문의 바랍니다.');
            }
        }
    };
    xhr.send(JSON.stringify(data));
}

// 답장
function reMsg(recvId){

    console.log(recvId);

    let data = {
        recvId : recvId
    };
    let xhr = new XMLHttpRequest();
    xhr.open('POST','' , true);
    xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                alert('삭제 처리 되었습니다.');
                window.history.back();
            }
            else{
                alert('삭제 처리 실패 : firetrap5319@gmail.com 으로 문의 바랍니다.');
            }
        }
    };
    xhr.send(JSON.stringify(data));
}

// window.onload = function (){
//
//     // let time =
//
//     let data = {
//
//     }
//
//     let xhr = new XMLHttpRequest();
//     xhr.open('POST' , '' , true);
//     xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
//     xhr.onreadystatechange = ()=>{
//         if(xhr.readyState === 4){
//             if(xhr.status === 200){
//                 alert('성공');
//             }
//             else{
//                 alert('실패');
//             }
//         }
//     };
//     // xhr.send(JSON.stringify(data));
// }