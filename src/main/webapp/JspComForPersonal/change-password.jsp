<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Đổi mậu khẩu</title>
    <style>

        .container-accountForm {
            width: 100%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            font-family: Arial, sans-serif;
        }

        /* Tiêu đề */
        .container-accountForm h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.8rem;
            color: #333;
        }

        /* Các nhãn */
        .container-accountForm label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #555;
        }

        /* Input và button */
        .container-accountForm input[type="password"],
        .container-accountForm button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        .container-accountForm button {
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .container-accountForm button:hover {
            background-color: #0056b3;
        }

        .container-accountForm .gender {
            display: flex;
            gap: 20px;
            align-items: center;
        }

        .container-accountForm .gender input {
            margin-right: 5px;
        }

    </style>
</head>
<body>
<div class="container-accountForm" >
    <h2>Đổi mật khẩu</h2>
    <form id="accountForm" action="change-password" method="post">
        <label for="old-password">Mật khẩu cũ</label>
        <input type="password" id="old-password" name="oldPassword" placeholder="Mật khẩu cũ" >

        <label for="new-password">Mật khẩu mới</label>
        <input type="password" id="new-password" name="newPassword" placeholder="Mật khẩu mới">

        <label for="re-new-password">Nhập lại mật khẩu mới</label>
        <input type="password" id="re-new-password" name="reNewPassword" placeholder="Nhập lại mật khẩu mới" >

        <button class="submit-btn-update-ai" type="submit">Cập nhật</button>
    </form>
</div>
</body>
</html>
