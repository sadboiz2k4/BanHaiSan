<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Thêm địa chỉ</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="CSS/addAdress.css">
</head>
<body>
    <div class="page">
        <%@ include file="JspComForHome/header.jsp"%>
        <div class="body-page">
            <div class="form-add-address">
                <form action="add-address" method="POST">
                    <div class="container-form">
                        <h1>
                            Thêm địa chỉ mới
                        </h1>
                        <div class="form-group">
                            <label for="name">Họ và tên</label>
                            <input type="text" id="name" name="name" placeholder="Nhập họ và tên">
                        </div>
                        <div class="form-group">
                            <label for="phone">Số điện thoại</label>
                            <input type="text" id="phone" name="phone" placeholder="Nhập số điện thoại">
                        </div>
                        <div class="form-group group-city">
                            <label for="city">Tỉnh/Thành phố:</label>
                            <select id="city" name="province">
                                <option value="">-- Chọn Tỉnh/Thành phố --</option>
                            </select>
                        </div>
                        <div class="form-group group-district">
                            <label for="district">Quận/Huyện:</label>
                            <select id="district" name="district" disabled>
                                <option value="">-- Chọn Quận/Huyện --</option>
                            </select>
                        </div>
                        <div class="form-group group-ward">
                            <label for="ward">Phường/Xã:</label>
                            <select id="ward" name="ward" disabled>
                                <option value="">-- Chọn Phường/Xã --</option>
                            </select>
                        </div>
                        <div class="form-group group-street">
                            <label for="street">Đường:</label>
                            <select id="street" name="street" disabled>
                                <option value="">-- Chọn Đường --</option>
                            </select>
                        </div>
                        <div class="form-group group-street">
                            <label for="address-type">Loại địa chỉ:</label>
                            <select id="address-type" name="addressType" >
                                <option value="Nhà">Nhà</option>
                                <option value="Công ty">Công ty</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="address">Địa chỉ cụ thể:</label>
                            <input type="text" id="address" name="address" placeholder="Nhập địa chỉ cụ thể">
                        </div>
                        <div class="form-group set-default">
                            <input type="hidden" name="default-address" value="false" />
                            <input class="checkbox" type="checkbox" id="address1" name="default-address" value="true" />
                            <label class="no-width" for="address1">Đặt làm địa chỉ mặc định</label>
                        </div>
                        <div class="form-group btn-sub">
                            <button type="submit">Thêm</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="js/addAddress.js"></script>
</body>
</html>
