
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <title>Title</title>
</head>
<body>
<c:forEach var="product" items="${listProductFS}">
  <div class="product-flash-sale product-card product-spe">
    <div class="product-image">
      <c:if test="${product.getImg().size()>0}">
        <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
      </c:if>
      <c:if test="${product.getImg().size()<=0}">
        <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
      </c:if>
      <span class="promo-badge">FLASH SALE</span>
    </div>
    <div class="product-info">
      <h3 class="product-name">${product.getName()}</h3>
      <p class="product-price black-content">${product.getPrice()}</p>
      <p class="product-price">${product.getDiscountPrice()}</p>
    </div>
    <div class="product-footer">
      <button class="add-to-cart">
        <i class="fa fa-shopping-cart"></i>
      </button>
      <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
    </div>
  </div>
</c:forEach>

<div class="product-flash-sale product-flash-sale-11 product-card">
  <span>Ấn vào xem thêm để xem nhiều hơn</span>
</div>
</body>
</html>
