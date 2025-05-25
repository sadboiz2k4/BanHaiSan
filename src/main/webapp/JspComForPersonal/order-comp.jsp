<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OD</title>
    <link rel="stylesheet" href="CSS/orderPersonal.css">
</head>
<body>
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
  <script src="js/orderComp.js"></script>
</body>
</html>
