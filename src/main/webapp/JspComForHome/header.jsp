<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Search Bar</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="CSS/header.css">
</head>
<body>
<div class="header header-page">
    <div class="header-top">
        <img src="Img/Banner/TH.jpg" alt="Thông tin khuyến mãi đặc biệt">
    </div>

    <div class="header-bottom">
        <div class="logo logo-header-page logo-ocean-seafood">
            <a href="home"><img src="Img/Logo/logo1.png" alt=""></a>
        </div>
        <div class="search-bar">
            <div class="search">
                <form action="search-all">
                    <input type="search" class="search-bar-input" placeholder="Tìm kiếm ............." name="valueSearch" autocomplete="off">
                    <button type="submit" class="search-bar-button"><i class="fa-solid fa-magnifying-glass"></i></button>
                </form>
            </div>
            <div class="promo">
            </div>
        </div>

        <div class="number-phone-banner-ship">
            <div class="number-phone">
                <span>0900112233</span>
            </div>
            <div class="banner-ship">
                <span>GIAO NHANH 2H</span>
            </div>
        </div>
        <div class="account-shopping-cart">
            <div class="account">
                <div class="icon-text-account">
                    <a class="account-header account-header-icon icon-person" href="LoadPersonal">
                        <i class="fa-solid fa-user"></i>
                    </a>
                    <div class="text-account">
                        <span class="login-or-name-user">Đăng nhập</span>
                    </div>
                </div>
                <ul class="account-menu">
                    <li class="account-menu-item">
                        <a class="link" href="register-new-account.jsp">Đăng ký</a>
                    </li>
                    <li class="account-menu-item">
                        <a class="link" href="login">Đăng nhập</a>
                    </li>
                </ul>
            </div>
            <div class="shopping-cart">
                <div class="container">
                    <a class="shopping-cart-icon" href="show-cart"><i class="fa-solid fa-cart-shopping"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let isLogin =${isLogin};
    let name = '${user.getFirstName()}${user.getLastName()}';

</script>
<script src="js/header.js" defer></script>
</body>
</html>
