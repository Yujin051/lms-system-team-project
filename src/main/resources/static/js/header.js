// 각 서브메뉴들
let subMenu1 = document.getElementById('sub_menu1');
let subMenu2 = document.getElementById('sub_menu2');
let subMenu3 = document.getElementById('sub_menu3');
// header의 메뉴들
const menuItem1 = document.querySelector('.menu-item:nth-child(1)');
const menuItem2 = document.querySelector('.menu-item:nth-child(2)');
const menuItem3 = document.querySelector('.menu-item:nth-child(3)');

// click 이벤트 추가
menuItem1.addEventListener('click' , () => toggleSubMenu(subMenu1));
menuItem2.addEventListener('click' , () => toggleSubMenu(subMenu2));
menuItem3.addEventListener('click' , () => toggleSubMenu(subMenu3));

function toggleSubMenu(subMenu) {
    // 모든 서브메뉴를 숨김
    subMenu1.style.display = 'none';
    subMenu2.style.display = 'none';
    subMenu3.style.display = 'none';

    // 클릭한 메뉴 아이템과 연결된 서브메뉴를 보이도록 함
    subMenu.style.display = 'block';
}

// 767px 이하일 경우 햄버거바 클릭시 header 메뉴 토글 display
const toggleBtn = document.querySelector('.header_toggle');
const menu = document.getElementById('menu');

//  토클방식으로 active class 추가/제거
toggleBtn.addEventListener('click' , () => {
    menu.classList.toggle('active');
});
