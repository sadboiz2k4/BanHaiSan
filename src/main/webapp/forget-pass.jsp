
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LẤY LẠI MẬT KHẨU</title>
    <link rel="stylesheet" href="CSS/forgetPassword.css">
</head>
<body>
<div class="page">
  <div class="body-page">
    <form class="form-login" action="forget-password" method="post">
      <div class="account-form">
        <div class="container">
          <div class="title-form">
            <span>LẤY LẠI MẬT KHẨU</span>
          </div>
          <div class="introduction-form">
            <p class="paragraph">
              <span class="boid-text blue-text">OCEAN SEAFOOD</span> xin chào nếu bạn đã quên mật khẩu vui lòng điền đúng thông tin để lấy lại mật khẩu. <br> Vui lòng không sử dụng tính năng này cho tài khoản không phải của quý khách
            </p>
          </div>
          <div class="input-login">
            <input class="input-numberphone input" type="text" name="email" id="email" placeholder="Nhập email" required>
            <p>
              Vui lòng kiểm tra thông tin trước khi gửi yêu cầu
            </p>
          </div>
          <div class="button-form" >
            <button type="submit" class="button-send open-dialog-ec">Gửi</button>
          </div>
          <div class="text-foot-form">
            <p>
              Bạn chưa có tài khoản? <a href="/PageAccountRegister/accountRegister.html">Đăng ký</a> tại đây <br>
              <a href="forgetPassWord.html">Quên mật khẩu?</a>
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
</body>
</html>
