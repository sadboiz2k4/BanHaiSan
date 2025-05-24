<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang cái nhân</title>
    <link rel="stylesheet" href="CSS/personal.css">
</head>
<body>
    <div class="page">
        <%@ include file="JspComForHome/header.jsp"%>
        <div>
            <a href="">Trang chủ</a> / <a>Trang cái nhân</a>
        </div>
        <div class="container-main-infor">
            <div class="menu-personal menu">
                <div class="name-personal">
                    <div class="icon">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="text">
                        <span class="text-top">Tài khoản</span><br>
                        <span class="text-bottom">${customer.getFirstName()}${customer.getLastName()}</span>
                    </div>
                </div>
                <div class="menu-component all-infor-personal active-menu">
                    <div class="icon">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="text">
                        <span>Thông tin tài khoản</span>
                    </div>
                </div>
                <div class="menu-component bonus-point-and-voucher">
                    <div class="icon">
                        <i class="fa-solid fa-crown"></i>
                    </div>
                    <div class="text">
                        <span>Điểm và Khuyến mãi</span>
                    </div>
                </div>
                <div class="menu-component address">
                    <div class="icon">
                        <i class="fa-solid fa-location-dot"></i>
                    </div>
                    <div class="text">
                        <span>Danh sách địa chỉ</span>
                    </div>
                </div>
                <div class="menu-component order">
                    <div class="icon">
                        <i class="fa-solid fa-truck"></i>
                    </div>
                    <div class="text">
                        <span>Đơn hàng</span>
                    </div>
                </div>
                <div class="menu-component interest">
                    <div class="icon">
                        <i class="fa-solid fa-heart"></i>
                    </div>
                    <div class="text">
                        <span>Yêu thích</span>
                    </div>
                </div>
                <div class="menu-component chang-password">
                    <div class="icon">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="text">
                        <span>Đổi mật khẩu</span>
                    </div>
                </div>
                <div class="menu-component logout">
                    <div class="icon">
                        <i class="fa-solid fa-right-from-bracket"></i>
                    </div>
                    <div class="text">
                        <span>Đăng xuất</span>
                    </div>
                </div>
            </div>
            <div class="infor-container infor-personal">
                <div class="infor ">

                </div>
            </div>
        </div>
        <%@ include file="JspComForHome/footer.jsp"%>
        <script src="js/personal.js"></script>
    </div>
</body>
</html>
