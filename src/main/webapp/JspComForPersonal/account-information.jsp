<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>Thông tin tài khoản</title>
    <link rel="stylesheet" href="CSS/acountInformation.css">
  </head>
  <body>
  <div class="container-accountForm" >
    <h2>Thông tin tài khoản</h2>
    <form id="accountForm" action="account-infor" method="post">
      <label for="phone">SDT</label>
      <input type="text" id="phone" name="phone" placeholder="Nhập SDT" value="${customer.getPhone()}" required>

      <label for="email">Email</label>
      <input type="email" id="email" name="email" placeholder="Nhập email" value="${customer.getEmail()}">

      <label for="lastName">Họ và tên đệm</label>
      <input type="text" id="lastName" name="lastName" placeholder="Nhập họ và tên đệm" value="${customer.getFirstName()}" required>

      <label for="firstName">Tên</label>
      <input type="text" id="firstName" name="firstName" placeholder="Nhập tên" value="${customer.getLastName()}" required>

      <label>Giới tính</label>
      <div class="gender">
        <input type="radio" id="female" name="gender" value="false" <c:if test="${!customer.getGender()}">checked</c:if> >
        <label for="female">Nữ</label>

        <input type="radio" id="male" name="gender" value="true" <c:if test="${customer.getGender()}">checked</c:if>>
        <label for="male">Nam</label>
      </div>

      <label for="dob">Ngày sinh (mm/dd/yyyy)</label>
      <input type="date" id="dob" name="dob" value="${customer.getBirthDay().toString()}" required>

      <button class="submit-btn-update-ai" type="submit">Cập nhật</button>
    </form>
  </div>
  </body>
</html>
