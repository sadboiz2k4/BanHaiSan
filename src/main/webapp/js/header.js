
$(document).ready(function () {
    function loadHeaderTopBanner() {
        $.ajax({
            url: 'BannerHeader',
            method: 'GET',
            success: function (response) {
                $('.header-top').html(response);
            },
            error: function (xhr, status, error) {
                console.error('AJAX request failed: ' + status + ', ' + error);
            }
        });
    }

    // Gọi function để tải banner vào header
    loadHeaderTopBanner();
});
let inputSearch = document.querySelector('.search-bar-input');
let buttonSubmitSearch = document.querySelector('.search-bar-button');
let containerPromo = document.querySelector('.promo');
let vInput;
let liPromo = document.querySelectorAll('.promo-search');
vInput = inputSearch.value;
inputSearch.addEventListener('focus', function () {
    loadListPromo(vInput);
    containerPromo.style.display = 'block';
});

inputSearch.addEventListener('blur', function () {
    setTimeout(() => {
        containerPromo.style.display = 'none';
    }, 200);
});

function loadListPromo(valueSearch) {
    $.ajax({
        url: 'ShowPromo',
        method: 'GET',
        data: {
            valueSearch: valueSearch
        },
        success: function (response) {
            $('.promo').html(response);
            liPromo = document.querySelectorAll('.promo-search');
            addEventPromoLi(liPromo);
        }
    });
}

inputSearch.addEventListener('input', function () {
    vInput = inputSearch.value;
    loadListPromo(vInput);
    liPromo = document.querySelectorAll('.promo-search');
});

function addEventPromoLi(liPromo) {
    liPromo.forEach(li => {
        li.addEventListener('click', function () {
            inputSearch.value = li.textContent;
            containerPromo.style.display = 'none';
            vInput = inputSearch.value;
            console.log(li.textContent);
            loadListPromo(vInput);
        });
    });
}

    if(isLogin){
    document.querySelector('.login-or-name-user').textContent = name;
    document.querySelectorAll('.account-menu-item').forEach(item => {
    item.remove();
});
}else{
    document.querySelector('.account-header-icon').addEventListener('click', function () {
        window.location.href = 'login.jsp';
    });
    document.querySelector('.shopping-cart').addEventListener('click', function () {
    window.location.href = 'login.jsp';
});
}