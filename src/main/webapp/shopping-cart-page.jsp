<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%--Thư viện JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng của bạn</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="CSS/shoppingCart.css">
</head>
<body>
<div class="page">
    <div class="breadcrumb-container">
        <div class="breadcrumb">
            <a href="home">Trang chủ</a>
            <span> / </span>
            <span>Giỏ hàng</span>
            <div class="cart-container">
                <h1 class="cart-title">Giỏ hàng</h1>
                <c:forEach var="product" items="${sessionScope.cart.list}">
                    <div class="cart-item" data-price="${product.getPrice()}">
                        <input type="hidden" class="id-product-hidden" value="${product.getId()}"
                               class="cart-item-image">
                        <c:if test="${product.getImg().size()>0}">
                            <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}"
                                 class="cart-item-image">
                        </c:if>
                        <c:if test="${product.getImg().size()<=0}">
                            <img src="Img/ImgProduct/P1.1.jpg" alt="${product.getName()}">
                        </c:if>
                        <div class="cart-item-details">
                            <h3 class="cart-item-name">${product.getName()}</h3>
                            <p class="cart-item-weight">1kg</p>
                            <p class="cart-item-price">${product.getPrice()}VND</p>
                        </div>
                        <div class="cart-item-controls">
                            <div class="quantity-controls">
                                <button class="quantity-btn minus-btn"
                                        onclick="updateQuantity(${product.getId()}, 'decrease')">-
                                </button>
                                <span class="quantity-display"
                                      data-product-id="${product.getId()}">${product.getQuantity()}</span>
                                <button class="quantity-btn plus-btn"
                                        onclick="updateQuantity(${product.getId()}, 'increase')">+
                                </button>
                            </div>
                            <form action="remove-cart" method="POST">
                                <input type="hidden" name="productID" value="${product.id}">
                                <button type="submit" class="delete-btn">×</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
                <div class="cart-summary">
                    <div class="cart-total">
                        <div class="total-label">Tổng tiền</div>
                        <div class="total-products-count"
                             id="total-products-count">${sessionScope.cart !=null ? sessionScope.cart.totalQuantity:0}
                        </div>
                        <div class="total-amount" id="total-price">${sessionScope.cart.total}</div>
                    </div>
                </div>
                <button class="checkout-btn">Tiến hành đặt hàng</button>
                <div class="button-container">
                    <button class="continue-shopping-btn"><i class="fas fa-arrow-left"></i> Tiếp tục mua hàng</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        // Hàm AJAX để gửi yêu cầu cập nhật số lượng
        function updateQuantity(productId, action) {
            $.ajax({
                url: 'update-cart',
                type: 'POST',
                data: {
                    productId: productId,
                    action: action
                },
                success: function (response) {
                    var newQuantity = response.newQuantity;
                    var totalQuantity = response.totalQuantity;
                    var totalPrice = response.totalPrice;
                    if (newQuantity > 0) {
                        $('span.quantity-display[data-product-id="' + productId + '"]').text(newQuantity);
                    }
                    if (totalQuantity > 0) {
                        $('#total-products-count').text(totalQuantity + ' sản phẩm');
                    }
                    if (totalPrice > 0) {
                        $('#total-price').text(totalPrice + ' VND');
                    }
                },
                error: function (xhr, status, error) {
                    alert("Có lỗi xảy ra: " + error);
                }
            });
        }

        function updateValueTotal() {
            let total = 0;
            let totalQuantity = 0;
            products.forEach(product => {
                let price = parseInt(product.getAttribute('data-price'));
                let quantity = parseInt(product.querySelector('.quantity-display').innerText);
                total += price * quantity;
                totalQuantity += quantity;
            });
            document.getElementById('total-price').innerText = total;
            document.getElementById('total-products-count').innerText = totalQuantity;
        }

        document.querySelector(".checkout-btn").addEventListener('click', event => {
            console.log("checkout");
            window.location.href = "order-page-load";
        });
        document.querySelector(".continue-shopping-btn").addEventListener('click', event => {
            window.location.href = "home";
        });
    </script>
</div>
</body>

</html>
