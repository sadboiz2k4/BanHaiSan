<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Địa chỉ</title>
    <link rel="stylesheet" href="CSS/addressPersonal.css">
</head>
<body>
<%@ include file="toast.jsp"%>
    <div class="title">
        <span>Danh sách địa chỉ</span>
    </div>
    <div class="container-list-address">
        <div class="header">
            <button class="btn-plus"><i class="fa-solid fa-plus"></i></button>
        </div>
        <c:forEach var="address" items="${listAddressUser}">
            <div class="address address-component" id="${address.getAddressUserID()}">
                <div class="container-infor-address">
                    <div class="sdt">
                        <span>${address.getPhoneReceiver()}</span>
                    </div>
                    <div class="name">
                        <span>${address.getFullNameReceiver()}</span>
                    </div>
                    <div class="address">
                        <span>Địa chỉ: </span> <span>${address.getAddress().getStreet()}, ${address.getAddress().getWardOrCommune()},
                        ${address.getAddress().getDistrict()}, ${address.getAddress().getCityOrProvince()}</span>
                    </div>
                </div>
                <div class="button-close-edit">
                    <div class="button-close-container">
                        <button>X</button>
                    </div>
                    <div class="button-edit-container">
                        <button>Chỉnh sửa</button>
                    </div>
                </div>
                <div class="is-primary">
                    <input type="radio" class="input-is-primary-address" name="isPrimaryAddress" id="${address.getAddressUserID()}"
                        <c:if test="${address.isPrimary()}">checked</c:if> >
                </div>

            </div>
        </c:forEach>
    </div>
    <script src="JS/addressPersonal.js"></script>
</body>
</html>
