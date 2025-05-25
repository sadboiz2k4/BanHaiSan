<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--    <link rel="stylesheet" href="/PageHome/assets/fontawesome-free-6.6.0-web/css/all.min.css">--%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OCEAN - NHÀ CUNG CẤP SỈ LẺ TẤT CẢ CÁC LOẠI HẢI SẢN CHÂT LƯỢNG CAO - OCEAN -ACCOUNT</title>
    <link rel="stylesheet" href="CSS/search.css">k
</head>
<body>
<div class="page">
    <%@include file="JspComForHome/header.jsp"%>
    <div id="success-toast" class="toast">
        <p>Thao tác thành công!</p>
    </div>
    <div class="body-page">
        <!-- [Bước 2.2.3.a.2] Hiển thị thông báo lỗi khi từ khóa tìm kiếm rỗng  -->
        <c:if test="${not empty errorMessage}">
            <div style="color: red; font-weight: bold; margin: 10px 0;">
                    ${errorMessage}
            </div>
        </c:if>
        <!-- [Bước 2.2.10.a] Hiển thị thông báo khi không tìm thấy sản phẩm -->
        <c:if test="${not empty infoMessage}">
            <div style="color: #ff6600; font-weight: bold; margin: 10px 0;">
                    ${infoMessage}
            </div>
        </c:if>
        <div class="container-product-find-filter-col">
            <!-- Bảng lọc sản phẩm -->
            <div class="filter-col">
                <div class="title">
                    <span><i class="fa-solid fa-filter"></i></span><span>Lọc Sản Phẩm</span>
                </div>

                <%-- [Bước 2.2.9]Lọc theo danh mục --%>
                <div class="type">
                    <span>Loại Hải Sản</span><br>
                    <div>
                        <c:forEach var="categories" items="${listCategories}">
                            <input class="input-checkbox-categories" type="checkbox" name="type" id="type${categories}" value="${categories}">
                            <label for="type${categories}">${categories}</label><br>
                        </c:forEach>
                    </div>
                </div>
                <%-- Lọc theo giá --%>
                <div class="price-range">
                    <span>Giá</span><br>
                    <div class="min-max-price">
                        <input type="number" name="min-price" id="min-price" placeholder="Giá thấp nhất">
                        <input type="number" name="max-price" id="max-price" placeholder="Giá cao nhất">
                    </div>
                    <div class="btn-sub">
                        <button class="submit">
                            <span>Áp dụng</span>
                        </button>
                    </div>
                </div>
                <%-- Lọc theo ưu đãi --%>
                <div class="offers-specials">
                    <span>Ưu Đãi</span><br>
                    <input type="checkbox" name="offers-specials" id="offers-specials1" value="1">
                    <label for="offers-specials1">Khuyến mãi</label><br>
                    <input type="checkbox" name="offers-specials" id="offers-specials2" value="3">
                    <label for="offers-specials2">Hàng mới</label><br>
                </div>
            </div>
            <!-- Vùng hiển thị sản phẩm -->
            <div class="container-product-f-r">
                <div class="filter-row">
                    <div class="connection e-filter-row active">
                        <span>Liên Quan</span>
                    </div>
                    <div class="hot-sale e-filter-row">
                        <span>Bán Chạy</span>
                    </div>
                    <div class="sort-price e-filter-row up">
                        <span>Giá</span>
                        <span class="icon"><i class="fa-solid fa-up-long"></i></span>
                    </div>
                </div>

                <%-- [Bước 2.2.10] Khu vực hiển thị sản phẩm --%>
                <div class="container-product">
                    <%--
                      Nếu danh sách sản phẩm rỗng, hiển thị thông báo "Không tìm thấy...".
                      Ngược lại, hiển thị danh sách sản phẩm.
                   --%>
                    <c:choose>
                        <c:when test="${empty ListProductSearchAll}">
                            <%-- [Bước 2.2.10.a] Trường hợp không tìm thấy sản phẩm nào --%>
                            <div class="not-found-message">
                                <h3>Không tìm thấy sản phẩm nào phù hợp với từ khóa của bạn.</h3>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%-- [Bước 2.2.10] Trường hợp có sản phẩm, hiển thị danh sách sản phẩm --%>
                            <c:forEach var="product" items="${ListProductSearchAll}">
                                <div class="product-card">
                                    <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                                    <div class="product-image">
                                        <c:if test="${product.getImg().size()>0}">
                                            <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                                        </c:if>
                                        <c:if test="${product.getImg().size()<=0}">
                                            <img src="Img/ImgProduct/P1.1.jpg" alt="${product.getName()}">
                                        </c:if>
                                        <span class="promo-badge">100% TƯƠI</span>
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
                        </c:otherwise>
                    </c:choose>
                    <%-- Kết thúc phần hiển thị sản phẩm tìm kiếm --%>
                </div>

                <%-- Phân trang --%>
                <div class="btn-change-pro">
                    <button class="action-prev"><i class="fa-solid fa-caret-left"></i></button>
                    <button class="action number active start">1</button>
                    <button class="action number">2</button>
                    <button class="action number">3</button>
                    <button class="action number">4</button>
                    <button class="action number end">5</button>
                    <button class="no-action">...</button>
                    <button class="action-next"><i class="fa-solid fa-caret-right"></i></button>
                </div>

            </div>
        </div>
    </div>
    <script>
        <!-- JavaScript xử lý tương tác người dùng -->
        document.querySelectorAll('.product-card').forEach(card => {
            card.querySelector('.product-image').addEventListener('click', () => {
                let idProduct = card.querySelector('.idProduct').value;
                window.location.href = 'product-details?productID='+idProduct;
            });
            card.querySelector('.product-info').addEventListener('click', () => {
                let idProduct = card.querySelector('.idProduct').value;
                window.location.href = 'product-details?productID='+idProduct;
            });
            card.querySelector('.product-footer>.add-to-cart').addEventListener('click',() =>{
                let idProduct = card.querySelector('.idProduct').value;
                console.log(idProduct);
                $.ajax({
                    url: 'add-product-to-shopping-cart',
                    method: 'POST',
                    data: {
                        productID: idProduct
                    },
                    success: function(response) {
                        if(response === 'login'){
                            window.location.href = 'login.jsp';
                        }
                        showSuccessMessage();
                    },
                    error: function(error) {
                        console.error("Lỗi khi gọi servlet:", error);
                    }
                });
            })
        });
    </script>
    <script>
        let totalPage = ${totalPage};
    </script>
    <script src="js/search.js"></script>


</div>
</body>
</html>

