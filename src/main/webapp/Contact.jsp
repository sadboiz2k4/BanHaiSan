<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="author" content="Ly Thai Minh Khang">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liên Hệ</title>
  <link rel="stylesheet" href="<c:url value='fontawesome/css/Contact.css'/>" />

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
<div class="page">
  <div class="header header-page">
    <div class="header-top">
      <img src="assets/img/top-header-img.png" alt="Thông tin khuyến mãi đặc biệt">
    </div>
    <div class="header-bottom">
      <div class="logo logo-header-page logo-ocean-">
        <img src="Img/Logo/logo1.png" alt="">
      </div>
      <div class="search-bar">
        <input type="text" class="search-bar-input" placeholder="Tìm kiếm .............">
        <button class="search-bar-button"><i class="fa-solid fa-magnifying-glass"></i></button>
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
            <a class="account-header account-header-icon icon-person" href="">
              <i class="fa-solid fa-user"></i>
            </a>
            <div class="text-account">
              <a class="account-header account-header-text" href="">
                <span>Đăng nhập</span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div><div class="page">
  <!-- Header section -->
  <jsp:include page="/JspComForHome/header.jsp"/>
  <div class="breadcrumb-container">
    <div class="breadcrumb">
      <a href="index.jsp">Trang chủ</a>
      <span> / </span>
      <span>Liên Hệ</span>
      <div class="contact-content">
        <h1>Liên Hệ</h1>
        <div class="logo-contact">
          <img src="Img/Logo/logo1.png" alt="OCEAN SEAFOOD Logo">
        </div>
        <div class="contact-info">
          <p>OCEAN SEAFOOD vinh hạnh nhận được sự quan tâm của Quý Khách Hàng, Đối Tác. Mọi thông tin cần liên hệ vui lòng theo thông tin bên dưới</p>
          <p>Địa chỉ văn phòng: 15/10 Phạm Văn Hai, P.1, Quận Tân Bình (đầu hẻm 25 Phạm Văn Hai).</p>
          <p>DVKH: dvkh@ocaenseafood.vn</p>
          <p>Liên hệ hợp tác: ocaenseafood@gmail.com</p>
          <p>Hotline: 0900112233 (8h-21h)</p>
        </div>
      </div>
      <jsp:include page="/JspComForHome/footer.jsp"/>
    </div>

  </div>
</html>

