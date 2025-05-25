<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Kiểm tra OTP</title>
    <style>
        .diaglog-enter-code {
            padding: 20px;
            margin-top: 200px;
            margin-left: auto;
            margin-right: auto;
            width: 600px;
            height: 300px;
            background-color: #f4f4f4;
            border-radius: 5px;
            border: solid 1px #c4c2c2;
            box-shadow: 0 0 10px #000;
            z-index: 2;
        }

        .button-x {
            font-size: 20px;
            cursor: pointer;
            border-radius: 5px;
            background-color: transparent;
            color: #ffffff;
            overflow: hidden;
        }

        .dialog-enter-code-form {
            width: 100%;
            height: 100%;
        }

        .container-dialog {
            width: 100%;
            height: 100%;
        }

        .container-dialog .title {
            width: 100%;
            height: 50px;
            text-align: center;
            font-size: 20px;
            font-weight: 500;

        }

        .container-dialog .group-form {
            width: 100%;
            height: 100px;
            padding-left: 30px;
            box-sizing: border-box;
        }

        .container-dialog .group-form input {
            width: 90%;
            height: 30px;
            border-radius: 5px;
            border: solid 1px #c4c2c2;
            font-size: 16px;
            padding-left: 10px;

        }

        .button-chose {
            width: 100%;
            height: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 20px;
        }

        .button-chose .button-diaglog {
            width: 90px;
            height: 30px;
            color: #ffffff;
            border: solid 1px #f1f1f1;
            border-radius: 5px;
            cursor: pointer;
        }

        .button-accept {
            background-color: green;
        }

        .button-cancel {
            background-color: red;
        }

        .background-ec {
            width: 100%;
            height: 100%;
            background-color: #2b2a2a9a;
            z-index: 1;
            position: absolute;
            top: 0;
            left: 0;
            display: none;
        }
    </style>
</head>
<body>
<div class="diaglog-enter-code">
    <form action="${pageContext.request.contextPath}/check-otp" method="post">
        <div class="dialog-enter-code-form">
            <div class="container-dialog">
                <div class="button-x">
                    <a href="${pageContext.request.getHeader('Referer')}" style="text-decoration: none;">
                        <button type="button" class="close-dialog button-close button-x" style="background-color: red; float: right">X</button>
                    </a>
                </div>
                <div class="title title-dialog" style="font-size: 25px">
                    <span>Nhập mã xác nhận</span>
                </div>
                <input type="hidden" name="typeCheckOTP" value="${typeCheckOTP}">
                <div class="group-form">
                    <label for="passcode">Mã xác nhận</label><br>
                    <input type="text" name="passcode" id="passcode" maxlength="6">
                </div>
                <div class="button-chose">
                    <button class="button-accept button-diaglog" type="submit">Xác nhận</button>
                    <button class="button-cancel close-dialog button-diaglog" type="submit" name="resendOTP">Gửi lại OTP</button>
                </div>
                <div style="align-content: center;text-align: center">
                    <span style="color: red">${errorOTP}</span>
                </div>
                <div style="align-content: center;text-align: center">
                    <span style="color: green">${successOTP}</span>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
