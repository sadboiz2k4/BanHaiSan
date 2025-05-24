<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sản phẩm yêu thích</title>
    <link rel="stylesheet" href="CSS/favoriteProduct.css">
</head>
<body>
    <div class="title">
        <span>Yêu thích</span>
    </div>
    <div class="container-product">
        <c:forEach var="product" items="${listProductFavorite}" >
            <div class="product-flash-sale product-card">
                <div class="product-image">
                    <c:if test="${product.getImg().size()>0}">
                        <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                    </c:if>
                    <c:if test="${product.getImg().size()<=0}">
                        <img src="Img/ImgProduct/P1.1.jpg" alt="${product.getName()}">
                    </c:if>
                    <span class="promo-badge">YÊU THÍCH</span>
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
</body>
</html>
