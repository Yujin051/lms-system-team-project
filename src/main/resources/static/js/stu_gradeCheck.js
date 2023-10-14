// 상세 정보 테이블
let gradeDetail = document.getElementById('detail')

// 상세 정보 버튼 클릭 이벤트 추가
const detailBtn = document.querySelectorAll(".ajax_button")

detailBtn.forEach(function (element){
    element.addEventListener('click', () => {
        // 값 가져오기
        const semYear = element.getAttribute('data-sem-year')
        console.log("셈이어임 : ", semYear);

        const semSem = element.getAttribute('data-sem-sem')
        console.log("셈셈임 : ", semSem);

        // json 키맵형태로 만든다.
        let data = {
            semYear: semYear,
            semSem: semSem
        }




        $.ajax({
            url: '/student/grade/find',
            method: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data : JSON.stringify(data),
            success: function (response) {

                $('#yearAndSem').text(semYear + "년도" + semSem)

                var lecNum = $('#lecNum');
                lecNum.empty();
                $.each(response, function (index, CheckGradeDto){
                    lecNum.append('<td style="display: block; border-bottom: 1px solid black; height: 45px; line-height: 45px;">' + CheckGradeDto.lectId + '<td>');
                });

                var className = $('#className');
                className.empty();
                $.each(response, function (index, CheckGradeDto){
                    className.append('<td style="display: block; border-bottom: 1px solid black; height: 45px; line-height: 45px;">' + CheckGradeDto.lectName + '<td>');
                });

                var classCredit = $('#classCredit');
                classCredit.empty();
                $.each(response, function (index, CheckGradeDto){
                    classCredit.append('<td style="display: block; border-bottom: 1px solid black; height: 45px; line-height: 45px;">' + CheckGradeDto.lectCredit + '<td>');
                });

                var profName = $('#profName');
                profName.empty();
                $.each(response, function (index, CheckGradeDto){
                    profName.append('<td style="display: block; border-bottom: 1px solid black; height: 45px; line-height: 45px;">' + CheckGradeDto.userName + '<td>');
                });

                var semGrade = $('#semGrade');
                semGrade.empty();
                $.each(response, function (index, CheckGradeDto){
                    console.log(CheckGradeDto.lectId)
                    semGrade.append('<td style="display: block; border-bottom: 1px solid black; height: 45px; line-height: 45px;">' + CheckGradeDto.grade + '<td>');
                });

                gradeDetail.style.display = 'block';

            },
            error: function () {

            }
        })

        //비동기 준비 xmlhttpreq
        // let xhr = new XMLHttpRequest();
        // // 어떤 url 어떤 method 쓸지?
        // xhr.open('POST' , '/student/grade' , true);
        // // 컨텐츠타입 정해주기
        // xhr.setRequestHeader('Content-Type' , 'application/json; charset=UTF-8');
        //
        // xhr.onreadystatechange = function (){
        //     if(xhr.readyState === 4){
        //         if(xhr.status == 200){
        //             // 성공했을떼 로직
        //             let article = JSON.parse(xhr.response);
        //             console.log(article);
        //             let newSem = article.semSem;
        //             let newYear = article.semYear
        //             alert('성공')
        //             // 버튼 클릭하면 성적 상세정보 테이블 출력
        //             gradeDetail.style.display = 'block';
        //             let className = document.getElementById('className');
        //             className.innerText = semSem;
        //         }
        //         else{ // 실패 로직
        //             let responseText = xhr.responseText;
        //             console.log(responseText);
        //             alert('실패');
        //         }
        //     }
        // }
        // // 비동기 통신 보내는 곳인데, json으로 바꿔준다. 앞서 키맵 친구를
        // xhr.send(JSON.stringify(data));


    }); // addevent end
}); // foreach end
