<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Điểm và khuyến mãi</title>
    <link rel="stylesheet" href="CSS/pointAndPromotion.css">
</head>
<body>
    <div class="title">
        <span>Điểm thưởng và Khuyến mãi</span>
    </div>
    <div class="note-number-account">
        <div class="note">
            <div class="container-note bonus">
                <i class="fa-solid fa-gift"></i>
                <span>Điểm thưởng</span>
            </div>
            <div class="container-note order-success">
                <i class="fa-solid fa-cart-shopping"></i>
                <span>Đơn hàng thành công</span>
            </div>
            <div class="container-note vip">
                <i class="fa-solid fa-star"></i>
                <span>Xếp hạng</span>
            </div>
        </div>
        <div class="number-account">
            <i class="fa-solid fa-user"></i>
            <span>${customer.getFirstName()} ${customer.getLastName()}</span><br>
            <span class="number-id">${customer.getUserId()}</span>
        </div>
    </div>
    <div class="statistical">
        <div class="point">
            <div class="score">
                <span>${point}</span>
            </div>
            <div class="icon">
                <i class="fa-solid fa-gift"></i>
            </div>
        </div>
        <div class="bill-success">
            <div class="score">
                <span>${quantityOrder}</span>
            </div>
            <div class="icon">
                <i class="fa-solid fa-cart-shopping"></i>
            </div>
        </div>
        <div class="rank-user">
            <div class="score">
                <span>${rank}</span>
            </div>
            <div class="icon">
                <i class="fa-solid fa-star"></i>
            </div>
        </div>
    </div>
    <div class="voucher">
        <div class="title">
            <span>Voucher</span>
        </div>
        <div class="container">
            <div class="filter">
                <div class="title">
                    <span><i class="fa-solid fa-filter"></i></span>
                </div>
                <div class="btn-sort">
                    <button class="sort-discount">Theo % giảm</button>
                    <button class="sort-date">Hạn sử dụng</button>
                </div>
            </div>
            <div class="container-voucher">
                <c:forEach var="voucher" items="${listVoucher}">
                    <div class="voucher-comp">
                        <span>${voucher.getVoucherCode()}</span>
                        <div class="voucher-img">
                            <img src="Img/Voucher/Voucher.webp" alt="img voucher">
                        </div>
                        <div class="voucher-content">
                            <div class="title">
                                <span>${voucher.getVoucherName()}</span>
                            </div>
                            <div class="condition">
                                <p>
                                    Đơn hàng từ ${voucher.getMinOrder()}<br>
                                    Giảm tối đa ${voucher.getMaxDiscount()}%
                                </p>
                            </div>
                            <div class="duration">
                                <span>HSD: ${voucher.getEndDate().toDateString()}</span>
                            </div>
                            <div class="btn-save">
                                <button class="save">Lưu mã</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
