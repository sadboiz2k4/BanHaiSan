<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn hàng</title>
    <link rel="stylesheet" href="CSS/orderPersonal.css">
</head>
<body>
    <div class="title">
        <span>Đơn hàng</span>
    </div>
    <div class="filter-order">
        <button id="btn-showAll">Tất cả</button>
        <button id="btn-pending">Đang xử lý</button>
        <button id="btn-shipping">Đang giao hàng</button>
        <button id="btn-complete">Đã giao thành công</button>
        <button id="cancle">Đã hủy</button>
    </div>
    <div class="container-order">
        <c:forEach var="order" items="${listOrder}">
            <div class="order">
                <div class="id" id="${order.getOrderId()}">
                    <span>${order.getOrderId()}</span>
                </div>
                <div class="infor-basic">
                    <div class="date">
                        <span>Ngày đặt: </span><span>${order.getDateCreate().toDateString()}</span>
                    </div>
                    <div class="total-quantity-products">
                        <span>Số sản phẩm: </span> <span>${order.quantityProduct()}</span>
                    </div>
                </div>
                <div class="status-total">
                    <div class="status">
                        <span>Trạng thái: </span> <span class="status-new">${order.getStatus()}</span>
                    </div>
                    <div class="total">
                        <span>Thành tiền: </span> <span class="total">${order.totalMoney()}</span>
                    </div>
                </div>
                <div class="button-cancel-order">
                    <button>Hủy</button>
                </div>
            </div>
        </c:forEach>
    </div>

</body>
</html>
