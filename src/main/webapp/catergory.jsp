<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Sản phẩm theo danh mục</title>
    <link rel="stylesheet" href="CSS/catergory.css">
  <script src="js/ProductCart.js"></script>
</head>
<body>
  <div class="page">
    <%@ include file="JspComForHome/header.jsp"%>
    <%@ include file="JspComForHome/toast.jsp"%>
    <div class="container-product" style="margin-top: 163px">
        <div class="product-list">
            <h2>Sản phẩm theo danh mục: ${category}</h2>
            <div class="product-list-container">
              <c:forEach var="product" items="${listProduct}" >
                <div class="product-flash-sale product-card">
                  <input type="hidden" id="idProduct" name="idProduct" value="${product.getId()}">
                  <div class="product-image">
                    <c:if test="${product.getImg().size()>0}">
                      <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                    </c:if>
                    <c:if test="${product.getImg().size()<=0}">
                      <img src="Img/ImgProduct/P1.1.jpg" alt="${product.getName()}">
                    </c:if>
                    <span class="promo-badge">CATERGORIS</span>
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
            </div>
        </div>
    </div>
    <%@ include file="JspComForHome/footer.jsp"%>
  </div>
  <script src="js/ProductCart.js"></script>
</body>
</html>
