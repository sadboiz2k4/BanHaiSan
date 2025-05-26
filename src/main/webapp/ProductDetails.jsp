<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.nameProduct}</title>
    <link rel="stylesheet" href="<c:url value='/fontawesome/css/ProductsDetails.css'/>" />
    <script src="<c:url value='fontawesome/js/ProductDetails.js'/>"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page">
    <!-- Header section -->
    <jsp:include page="JspComForHome/header.jsp"/>

    <div class="breadcrumb-container">
        <div class="breadcrumb">
            <a href="<c:url value='/home'/>">Trang chủ</a>
            <span> / </span>
            <a href="<c:url value='/product-details?productID=${product.productID}'/>">
                ${product.nameProduct}
            </a>
        </div>
    </div>
    <div class="product-detail">
        <div class="product-gallery">
            <div class="main-image">
                <img src='${product.imgURL}'/>" alt="${product.nameProduct}">
            </div>
            <%--      <div class="thumbnail-container">--%>
            <%--        <c:forEach var="image" items="${productImages}">--%>
            <%--          <div class="thumbnail">--%>
            <%--            <img src="<c:url value='${image}'/>" alt="${product.nameProduct}" onclick="updateMainImage(this.src)">--%>
            <%--          </div>--%>
            <%--        </c:forEach>--%>
            <%--      </div>--%>
        </div>
        <!-- Product Info -->
        <div class="product-info">
            <h1 class="product-title">${product.nameProduct}</h1>
            <div class="favorite-icon">
                <i class="fa fa-heart"></i>
            </div>
            <div class="product-views">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
                    <circle cx="12" cy="12" r="3"></circle>
                </svg>
                <span class="view-count">${product.countView}</span>
            </div>
            <div><strong>Mã SP:</strong> ${product.productID}</div>
            <div class="product-price">
                <span class="current-price format-price" >
                    ${product.price - product.discountPrice}
                </span>
            </div>

            <div class="product-specs">
                <div><strong>Quy cách:</strong> ${product.type}</div>
                <div><strong>Xuất xứ:</strong> ${product.origin}</div>
            </div>

            <div class="size-options">
                <button class="size-option active" data-size="2 Kg">${product.type}</button>
            </div>


            <!-- Quantity Control -->
            <div class="quantity-control">
                <span>Số lượng:</span>
                <div class="quantity-buttons">
                    <button class="quantity-btn" onclick="decrementQuantity()">-</button>
                    <span id="quantityValue" class="quantity-value">1</span>
                    <button class="quantity-btn" onclick="incrementQuantity()">+</button>
                </div>
            </div>

            <button class="add-to-cart" onclick="addToCart(${product.productID})">Thêm vào giỏ</button>
        </div>
    </div>
    <!-- Product Description -->
    <div class="product-description">
        <p>${product.descriptionDetails}</p>
    </div>
    <!-- Product Reviews -->
    <div class="product-reviews">
        <h2>Đánh giá sản phẩm</h2>
        <div class="star-rating">
            <c:forEach begin="1" end="5" var="i">
                <span class="star" data-value="${i}">★</span>
            </c:forEach>
            <span class="rating-text">Chọn đánh giá</span>
        </div>

        <textarea id="comment-input" placeholder="Nội dung bình luận"></textarea>
        <button class="submit-button" onclick="submitReview(${product.productID})">Gửi bình luận</button>

        <!-- Reviews List -->
        <div class="comments-list">
            <c:forEach var="review" items="${product.reviews}">
                <div class="review-item">
                    <div class="review-header">
                        <div class="rating">
                            <c:forEach begin="1" end="${review.rating}" var="i">★</c:forEach>
                        </div>
                        <div class="review-date">
                            <f:formatDate value="${review.createDate}" pattern="dd/MM/yyyy"/>
                        </div>
                    </div>
                    <div class="review-content">${review.reviewContent}</div>
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- Related Products Section -->
    <div class="product-recomand product-tag">
        <div class="title-product-cover title-recomand">
            <span>SẢN PHẨM LIÊN QUAN</span>
        </div>
        <div class="row-product">
            <c:forEach var="relatedProduct" items="${relatedProducts}">
                <div class="product-card">
                    <div class="product-image">
                        <a href="<c:url value='product-details?productID=${relatedProduct.productID}'/>">
                            <c:url value="${relatedProduct.imgURL}" var="relatedProductImage"/>
                            <img src="${relatedProductImage}" alt="${relatedProduct.nameProduct}">
                        </a>
                    </div>
                    <div class="product-info">
                        <h3 class="product-name">${relatedProduct.nameProduct}</h3>
                        <p class="product-price">
                            <f:formatNumber value="${relatedProduct.discountPrice}" type="currency" currencySymbol=""/>đ

                        </p>
                    </div>
                    <div class="product-footer">
                        <button class="add-to-carts" onclick="addToCart(${relatedProduct.productID})">
                            <p>Thêm vào giỏ</p>
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <jsp:include page="JspComForHome/footer.jsp"/>
</div>
<script>
    // Replace the existing favorite icon click handler with this code
    document.querySelector('.favorite-icon').addEventListener('click', function() {
        const heartIcon = this.querySelector('i');
        const productId = ${product.productID}; // This will be replaced with the actual product ID from JSP

        $.ajax({
            url: "add-to-favorite",
            type: "POST",
            data: {
                productID: productId
            },
            success: function(response) {
                if(response.success) {
                    if(response.action === "added") {
                        heartIcon.style.color = '#ff0000';
                        heartIcon.classList.add('active');
                        showNotification("Đã thêm vào danh sách yêu thích");
                    } else {
                        heartIcon.style.color = '#000000';
                        heartIcon.classList.remove('active');
                        showNotification("Đã xóa khỏi danh sách yêu thích");
                    }
                } else {
                    if(response.message === "Please login to add favorites") {
                        window.location.href = "login"; // Redirect to login page
                    } else {
                        showNotification(response.message);
                    }
                }
            },
            error: function() {
                showNotification("Có lỗi xảy ra, vui lòng thử lại sau");
            }
        });
    });

    // Add this to your existing JavaScript code to check initial favorite status
    function checkFavoriteStatus(productId) {
        $.ajax({
            url: "check-favorite-status",
            type: "POST",
            data: {
                productID: productId
            },
            success: function(response) {
                if(response.isFavorite) {
                    const heartIcon = document.querySelector('.favorite-icon i');
                    heartIcon.style.color = '#ff0000';
                    heartIcon.classList.add('active');
                }
            }
        });
    }

    // Call this when page loads
    document.addEventListener('DOMContentLoaded', function() {
        checkFavoriteStatus(${product.productID});
    });
    // Quantity control with AJAX update
    function incrementQuantity() {
        let quantityInput = document.querySelector('.quantity-value');
        let currentQuantity = parseInt(quantityInput.value);
        quantityInput.value = currentQuantity + 1;
        updateQuantityDisplay();
    }

    function decrementQuantity() {
        let quantityInput = document.querySelector('.quantity-value');
        let currentQuantity = parseInt(quantityInput.value);
        if (currentQuantity > 1) {
            quantityInput.value = currentQuantity - 1;
            updateQuantityDisplay();
        }
    }

    function updateQuantityDisplay() {
        const quantityValue = document.querySelector('.quantity-value').value;
        document.querySelector('.quantity-display').textContent = quantityValue;
    }

    // Add to cart functionality with AJAX
    function addToCart(productId) {
        const quantitySpan = document.getElementById("quantityValue");
        let quantity = 1;

        if (quantitySpan) {
            const parsedQuantity = parseInt(quantitySpan.textContent.trim());
            quantity = isNaN(parsedQuantity) || parsedQuantity <= 0 ? 1 : parsedQuantity;
        }

        $.ajax({
            url: "add-cart-detail",
            type: "POST",
            dataType: "json",
            data: {
                productID: productId,
                quantity: quantity
            },
            success: function(res) {
                if (res.status === "login") {
                    showNotification("Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng.");
                    setTimeout(() => {
                        window.location.href = "/login.jsp";
                    }, 1500);
                } else if (res.status === "success") {
                    const cartBadge = document.querySelector('.cart-badge');
                    if (cartBadge) {
                        let currentCount = parseInt(cartBadge.textContent || '0');
                        cartBadge.textContent = currentCount + quantity;
                    }
                    showNotification("Đã thêm sản phẩm vào giỏ hàng");

                    // Reset lại số lượng về 1
                    if (quantitySpan) {
                        quantitySpan.textContent = "1";
                    }
                } else {
                    showNotification(res.message || "Có lỗi xảy ra khi thêm vào giỏ hàng");
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX Error:", xhr.responseText);
                showNotification("Có lỗi xảy ra khi thêm vào giỏ hàng");
            }
        });
    }

    // function addToCart(productId) {
    //   // Get the discount price from the current price element
    //   const discountPrice = document.querySelector('.current-price').textContent.trim().replace('đ', '');
    //
    //   $.ajax({
    //     url: "add-cart",
    //     type: "POST",
    //     data: {
    //       productID: productId,
    //       discountPrice: discountPrice
    //     },
    //     success: function(response) {
    //       if(response === "success") {
    //         // Update cart badge
    //         const cartBadge = document.querySelector('.cart-badge');
    //         if (cartBadge) {
    //           let currentCount = parseInt(cartBadge.textContent || '0');
    //           cartBadge.textContent = currentCount + 1;
    //         }
    //         // Show success message
    //         showNotification("Có lỗi xảy ra khi thêm vào giỏ hàng");
    //       } else {
    //         showNotification("Đã thêm sản phẩm vào giỏ hàng");
    //       }
    //     },
    //     error: function(xhr, status, error) {
    //       console.error("AJAX Error:", xhr.responseText);
    //       showNotification("Có lỗi xảy ra khi thêm vào giỏ hàng");
    //     }
    //   });
    // }

    // Rating system with AJAX update
    let selectedRating = 0;

    document.querySelectorAll('.star').forEach((star, index) => {
        star.addEventListener('click', function() {
            selectedRating = index + 1;
            updateStars(selectedRating);
            document.querySelector('.rating-text').textContent = `${selectedRating} sao`;
        });

        star.addEventListener('mouseover', function() {
            updateStars(index + 1);
        });

        star.addEventListener('mouseout', function() {
            updateStars(selectedRating);
        });
    });

    function updateStars(rating) {
        document.querySelectorAll('.star').forEach((star, index) => {
            star.style.color = index < rating ? '#ffd700' : '#ccc';
        });
    }

    // Submit review with AJAX
    function submitReview(productId) {
        if (selectedRating === 0) {
            showNotification('Vui lòng chọn số sao đánh giá');
            return;
        }

        const content = document.getElementById('comment-input').value.trim();
        if (!content) {
            showNotification('Vui lòng nhập nội dung đánh giá');
            return;
        }

        $.ajax({
            url: "add-review",
            type: "POST",
            data: {
                productId: productId,
                rating: selectedRating,
                content: content
            },
            success: function(response) {
                if(response.success) {
                    // Add new review to the list without page reload
                    addNewReview({
                        rating: selectedRating,
                        content: content,
                        date: new Date().toLocaleDateString()
                    });
                    // Clear form
                    document.getElementById('comment-input').value = '';
                    selectedRating = 0;
                    updateStars(0);
                    showNotification('Cảm ơn bạn đã đánh giá sản phẩm!');
                } else {
                    showNotification('Có lỗi xảy ra, vui lòng thử lại sau.');
                }
            },
            error: function() {
                showNotification('Có lỗi xảy ra, vui lòng thử lại sau.');
            }
        });
    }

    // Helper function to add new review to the list
    function addNewReview(review) {
        const reviewsList = document.querySelector('.comments-list');
        const newReview = document.createElement('div');
        newReview.className = 'review-item';
        newReview.innerHTML = `
        <div class="review-header">
            <div class="rating">
                ${'★'.repeat(review.rating)}
            </div>
            <div class="review-date">${review.date}</div>
        </div>
        <div class="review-content">${review.content}</div>
    `;
        reviewsList.insertBefore(newReview, reviewsList.firstChild);
    }

    // Helper function to show notifications
    function showNotification(message) {
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.textContent = message;
        document.body.appendChild(notification);

        setTimeout(() => {
            notification.remove();
        }, 3000);
    }

    // Helper function to update cart badge
    function updateCartBadge(quantity) {
        const cartBadge = document.querySelector('.cart-badge');
        if (cartBadge) {
            const currentCount = parseInt(cartBadge.textContent || '0');
            cartBadge.textContent = currentCount + quantity;
        }
    }


</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const formatter = new Intl.NumberFormat('vi-VN');
        document.querySelectorAll('.format-price').forEach(span => {
            const rawValue = parseFloat(span.innerText.replace(/[^0-9.-]+/g, ""));
            if (!isNaN(rawValue)) {
                span.innerText = formatter.format(rawValue) + ' VND';
            }
        });
    });
</script>
</body>
</html>