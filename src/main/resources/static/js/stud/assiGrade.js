// 성적입력 테이블
let gradeDetail = document.querySelector('#detail')

// 작성하기 버튼 클릭 이벤트 추가
const detailBtn = document.querySelectorAll('.ajax_button')


detailBtn.forEach(function (element) {
    element.addEventListener('click', () => {
        // 값 가져오기
        const lectId = element.getAttribute('data-lect-id')
        console.log("렉트아이디는?", lectId);


        $.ajax({
            url: '/prof/assiGrade/write',
            method: 'POST',
            data : {
                lectId: lectId
            },
            success: function (response) {
                const lectCheck = response[0].lectCheck;
                const lectAssign = response[0].lectAssign;
                const lectTest = response[0].lectTest;

                console.log(response.toString())
                console.log("lectCheck 값은", lectCheck);
                $('#lectCheck').text("출석(" + lectCheck + ")")

                console.log(response.toString())
                console.log("lectAssign 값은", lectAssign);
                $('#lectAssign ').text("과제(" + lectAssign + ")")

                console.log(response.toString())
                console.log("lectTest 값은", lectTest);
                $('#lectTest').text("시험(" + lectTest + ")")

                var userName = $('#userName');
                userName.empty();
                $.each(response, function (index, EnteringGradeDto){
                    userName.append('<td style="display: block">' + EnteringGradeDto.userName + '<td>');
                });

                var userEmail = $('#userEmail');
                userEmail.empty();
                $.each(response, function (index, EnteringGradeDto){
                    userEmail.append('<td style="display: block">' + EnteringGradeDto.userEmail + '<td>');
                });

                var checkScore = $('#checkScore');
                checkScore.empty();
                $.each(response, function (index, EnteringGradeDto){
                    var inputElement = '<td style="display: block"><input type="text" value="' + EnteringGradeDto.checkScore + '"></td>';
                    checkScore.append(inputElement);
                });

                var assignScore = $('#assignScore');
                assignScore.empty();
                $.each(response, function (index, EnteringGradeDto){
                    var inputElement = '<td style="display: block"><input type="text" value="' + EnteringGradeDto.assignScore + '"></td>';
                    assignScore.append(inputElement);
                });

                var testScore = $('#testScore');
                testScore.empty();
                $.each(response, function (index, EnteringGradeDto){
                    var inputElement = '<td style="display: block"><input type="text" value="' + EnteringGradeDto.testScore + '"></td>';
                    testScore.append(inputElement);
                });

                var grade = $('#grade');
                grade.empty();
                $.each(response, function (index, EnteringGradeDto){
                    grade.append('<td style="display: block">' + EnteringGradeDto.grade + '<td>');
                });

                gradeDetail.style.display = 'block';
            },
            error: function () {


            }
        })

    })
})