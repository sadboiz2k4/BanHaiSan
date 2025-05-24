
    document.querySelector('.logout').addEventListener('click', function(){
    $.ajax({
        url: 'logout',
        type: 'GET',
        success: function (response) {
            window.location.href = 'home';
        }
    });
});

    $.ajax({
    url: 'account-infor',
    type: 'GET',
    success: function (response) {
    $('.infor').html(response);
}
});
    document.querySelector('.chang-password').addEventListener('click', function(){
    $.ajax({
        url: 'change-password',
        type: 'GET',
        success: function (response) {
            $('.infor').html(response);
        }
    });
});
    document.querySelector('.all-infor-personal').addEventListener('click', function(){
    $.ajax({
        url: 'account-infor',
        type: 'GET',
        data:{

        },
        success: function (response) {
            $('.infor').html(response);
        }
    });
});

    //point and promotions
    document.querySelector('.bonus-point-and-voucher').addEventListener('click', function(){
    $.ajax({
        url: 'point-and-promotions',
        type: 'GET',
        success: function (response) {
            $('.infor').html(response);
        }
    });
});

    //address
    document.querySelector('.address').addEventListener('click', function(){
    $.ajax({
        url: 'address-user-page',
        type: 'GET',
        success: function (response) {
            $('.infor').html(response);
        }
    });
});

    //order
    document.querySelector('.order').addEventListener('click', function(){
    $.ajax({
        url: 'order-personal',
        type: 'GET',
        success: function (response) {
            $('.infor').html(response);
        }
    });
});

    //interest
    document.querySelector('.interest').addEventListener('click', function(){
    $.ajax({
        url: 'favorite-products',
        type: 'GET',
        success: function (response) {
            $('.infor').html(response);
        }
    });
});

    document.querySelectorAll('.menu-component').forEach(function(menu){
    menu.addEventListener('click', function(){
        document.querySelectorAll('.menu-component').forEach(function(menu){
            menu.classList.remove('active-menu');
        });
        menu.classList.add('active-menu');
    });
});

