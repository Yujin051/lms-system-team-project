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

    }); // addevent end
}); // foreach end
