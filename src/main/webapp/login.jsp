
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="CSS/login.css">
</head>
<body>
    <div class="page">
        <div class="body-page">
            <form id="login-form" class="form-login" action="login" method="post">
                <div class="account-form">
                    <div class="container">
                        <div class="title-form">
                            <span>ĐĂNG NHẬP</span>
                        </div>
                        <div class="introduction-form">
                            <p class="paragraph">
                                <span class="boid-text blue-text">OCEAN SEAFOOD</span> xin chào, để phục vụ bạn tốt hơn cùng với những ưu đãi riêng, bạn vui lòng đăng nhập tài khoản trước khi mua hàng nhé!
                            </p>
                        </div>
                        <div class="input-login">
                            <input class="input-numberphone input" type="text" name="uname" id="uname" placeholder="Nhập tên đăng nhập\ SDT\ Email\" value="${uname}">
                            <input class="input-password input" type="password" name="password" id="password" placeholder="Nhập mật khẩu">
                        </div>
                        <div class="g-recaptcha" style="margin-left: 50px;margin-bottom: 15px" data-sitekey="6LfN6AorAAAAANDtnTzsNJ1UUpRdwTB38Rct_FQZ"></div>

                        <div class="button-form">
                            <button type="button" onclick="checkCaptcha()">Đăng nhập</button>
                        </div>
                        <div class="status" style="color:red; height: 30px; font-size: 13px;text-align: center">
                            <p class="error">${error}</p>
                        </div>
                        <div style="color: red;font-size: 13px;text-align: center" id="error"></div>
                        <div class="text-foot-form">
                            <p>
                                Bạn chưa có tài khoản? <a href="register-new-account">Đăng ký</a> tại đây <br>
                                <a href="forget-pass.jsp">Quên mật khẩu?</a>
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
        </div>
    </div>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script type="text/javascript">
        function checkCaptcha() {
            var form = document.getElementById('login-form');
            var error = document.getElementById('error');
            const response = grecaptcha.getResponse();

            if (response) {
                form.submit();

            } else {
                error.textContent = "Vui lòng xác nhận bạn không phải là robot";
            }
        }
    </script>
</body>

</html>
