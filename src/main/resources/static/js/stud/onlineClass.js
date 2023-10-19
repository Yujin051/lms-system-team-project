window.onload = function () {
    const viewButtons = document.querySelectorAll('.view_btn');

    viewButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const subtr = this.parentElement.parentElement.nextElementSibling;

            if (subtr.style.display === 'none' || subtr.style.display === '') {
                subtr.style.display = 'flex';
            } else {
                subtr.style.display = 'none';
            }
        });
    });

    const learnBtn = document.querySelector(".learn_link");
    const modalBody = document.querySelector('.modal_body');
    const closeButton = document.querySelector('.modal_exit_icon_box');
    learnBtn.addEventListener('click', function (event) {
        event.preventDefault();
        modalBody.style.display = 'block';
        modalBody.classList.add("active");
    });

    closeButton.addEventListener("click", function() {
        modalBody.classList.remove("active");
        setTimeout(function() {
            modalBody.style.display = "none";
        }, 400); // 애니메이션 시간 (0.4초) 이후에 모달을 숨김
    });
};



