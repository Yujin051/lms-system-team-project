window.onload = function () {
    const viewButtons = document.querySelectorAll('.view_btn');

    viewButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            const subtr = document.querySelector("#subwrap");

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
        modalBody.style.display = 'block';
        modalBody.classList.add("active");
    });

    closeButton.addEventListener("click", function() {
        modalBody.classList.remove("active");
        modalBody.style.display = "none";
    });
};



