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
    });

    closeButton.addEventListener('click', function () {
        modalBody.style.display = 'none';
    });
};

const modalIn = keyframes`
  from{
    opacity: 0;
    transform: translateY(-30px);
  }
  to{
    opacity: 1;
    transform: translateY(0);
  }
`;

const modalOut = keyframes`
  from {
    opacity: 1;
    transform: translateY(0);
  }
  to {
    opacity: 0;
    transform: translateY(-30px);
  }
`;

