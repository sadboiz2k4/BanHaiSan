<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 1/14/2025
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Đăng kí tài khoản mới</title>
    <link rel="stylesheet" href="CSS/registerNewAccount.css">
</head>
<body>
<div class="page">
    <div class="body-page">
        <form class="form-login" action="register-new-account" method="post">
            <div class="account-form">
                <div class="container">
                    <div class="title-form">
                        <span>ĐĂNG KÝ</span>
                    </div>
                    <div class="introduction-form">
                        <p class="paragraph">
                            <span class="boid-text blue-text">OCEAN SEAFOOD</span> xin chào, để phục vụ bạn tốt hơn cùng với những ưu đãi riêng, bạn vui lòng đăng ký tài khoản trước khi mua hàng nhé!
                        </p>
                    </div>
                    <div style="align-content: center;text-align: center">
                        <span style="color: red">${error}</span>
                    </div>
                    <div class="input-login">
                        <input class="input" type="text" name="username" id="username" placeholder="Nhập tên đăng nhập mới" required>
                        <input class="input-numberphone input" type="email" name="email" id="email" placeholder="Nhập email" required>
                        <input class="input-password input" type="password" name="password" id="password" placeholder="Nhập mật khẩu mới" required>
                        <input class="input-password-2 input" type="password" name="confirmPassword" id="confirmPassword" placeholder="Nhập lại mật khẩu" required>
                    </div>
                    <div class="button-form">
                        <button class="btn-register" type="submit">Đăng ký</button>
                    </div>
                    <div class="text-foot-form">
                        <p>
                            Bạn chưa có tài khoản? <a href="accountRegister.html">Đăng ký</a> tại đây <br>
                            <a href="/PageFogetPassword/forgetPassWord.html">Quên mật khẩu?</a>
                        </p>
                    </div>
                    <div class="change-login-method">
                        <div class="text">
                            <span>Hoặc đăng nhập bằng</span>
                        </div>
                        <div class="method-login">
                            <div class="item">
                                <img src="https://www.facebook.com/images/fb_icon_325x325.png" alt="Facebok icon">
                            </div>
                            <div class="item">
                                <img src="http://pluspng.com/img-png/google-logo-png-google-logo-icon-png-transparent-background-1000.png" alt="Google icon">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <dialog class="diaglog-enter-code">
            <!-- <form action=""> -->
            <div class="dialog-enter-code-form">
                <div class="container-dialog">
                    <div class="button-x btn-close close-dialog">
                        <button class="close-dialog button-close button-x">X</button>
                    </div>
                    <div class="title title-dialog">
                        <span>Nhập mã xác nhận</span>
                    </div>
                    <div class="group-form">
                        <label for="passcode">Mã xác nhận</label><br>
                        <input type="text" name="passcode" id="passcode" maxlength="6">
                    </div>
                    <div class="button-chose">
                        <button class="button-accept button-diaglog">Xác nhận</button>
                        <button class="button-cancel close-dialog button-diaglog">Hủy</button>
                    </div>
                </div>
            </div>
            <!-- </form> -->
        </dialog>
        <div class="background-ec">

        </div>
    </div>
</div>
</body>
</html>
