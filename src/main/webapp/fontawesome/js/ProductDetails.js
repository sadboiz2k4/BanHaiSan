
document.addEventListener('DOMContentLoaded', () => {
    const minusBtn = document.querySelector('.quantity-buttons .quantity-btn:first-child');
    const plusBtn = document.querySelector('.quantity-buttons .quantity-btn:last-child');
    const quantityValue = document.querySelector('.quantity-value');

    // Decrease quantity
    minusBtn.addEventListener('click', () => {
        let currentQuantity = parseInt(quantityValue.textContent);
        if (currentQuantity > 1) {
            quantityValue.textContent = currentQuantity - 1;
        }
    });

    // Increase quantity
    plusBtn.addEventListener('click', () => {
        let currentQuantity = parseInt(quantityValue.textContent);
        quantityValue.textContent = currentQuantity + 1;
    });
});
document.addEventListener('DOMContentLoaded', () => {
    const thumbnails = document.querySelectorAll('.thumbnail');
    const mainImage = document.querySelector('.main-image img');

    thumbnails.forEach(thumb => {
        thumb.addEventListener('click', () => {
            // Remove 'active' class from all thumbnails
            thumbnails.forEach(t => t.classList.remove('active'));

            // Add 'active' class to clicked thumbnail
            thumb.classList.add('active');

            // Update main image src with the clicked thumbnail's image src
            mainImage.src = thumb.querySelector('img').src;
        });
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const commentInput = document.getElementById('comment-input');
    const submitButton = document.querySelector('.submit-button');
    const commentContainer = document.createElement('div');
    commentContainer.classList.add('comments-list');

    // Add the comments container right after the comment input section
    const commentSection = document.querySelector('.comment-section');
    commentSection.parentNode.insertBefore(commentContainer, commentSection.nextSibling);

    submitButton.addEventListener('click', function() {
        // Get the comment text
        const commentText = commentInput.value.trim();

        // Check if comment is not empty
        if (commentText === '') {
            alert('Vui lòng nhập nội dung bình luận');
            return;
        }

        // Create comment element
        const commentElement = document.createElement('div');
        commentElement.classList.add('comment');

        // Create timestamp
        const timestamp = new Date().toLocaleString('vi-VN', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });

        // Add comment HTML
        commentElement.innerHTML = `
            <div class="comment-header">
                <span class="comment-author">Khách hàng ẩn danh</span>
                <span class="comment-timestamp">${timestamp}</span>
            </div>
            <div class="comment-body">
                ${commentText}
            </div>
        `;

        // Add the new comment to the top of the comments list
        commentContainer.insertBefore(commentElement, commentContainer.firstChild);

        // Clear the input
        commentInput.value = '';
    });
});
document.addEventListener("DOMContentLoaded", () => {
    const stars = document.querySelectorAll(".star");
    const ratingText = document.querySelector(".rating-text");
    const submitButton = document.querySelector(".submit-button");
    const commentInput = document.getElementById("comment-input");
    const commentsList = document.querySelector(".comments-list");

    let rating = 0;

    // Xử lý chọn sao
    stars.forEach((star) => {
        star.addEventListener("click", () => {
            rating = parseInt(star.getAttribute("data-value")); // Lấy giá trị số sao
            updateStars();
            ratingText.textContent = `${rating} / 5 sao`; // Hiển thị số sao đã chọn
        });

        star.addEventListener("mouseenter", () => {
            const hoverValue = parseInt(star.getAttribute("data-value"));
            updateStars(hoverValue);
        });

        star.addEventListener("mouseleave", () => {
            updateStars();
        });
    });

    // Xử lý gửi bình luận
    submitButton.addEventListener("click", () => {
        const commentText = commentInput.value.trim();

        if (!commentText || rating === 0) {
            alert("Vui lòng chọn sao và nhập nội dung bình luận!");
            return;
        }

        // Tạo phần tử bình luận
        const comment = document.createElement("div");
        comment.classList.add("comment");

        // Thêm phần sao
        const commentStars = document.createElement("div");
        commentStars.classList.add("comment-stars");
        for (let i = 1; i <= 5; i++) {
            const star = document.createElement("span");
            star.textContent = "★";
            if (i <= rating) {
                star.classList.add("selected");
            }
            commentStars.appendChild(star);
        }
        comment.appendChild(commentStars);

        // Thêm nội dung bình luận
        const commentContent = document.createElement("p");
        commentContent.textContent = commentText;
        comment.appendChild(commentContent);

        // Thêm thời gian đăng bình luận
        const timestamp = document.createElement("span");
        const now = new Date();
        timestamp.textContent = `lúc ${now.toLocaleTimeString()} ngày ${now.toLocaleDateString()}`;
        timestamp.classList.add("timestamp");
        comment.appendChild(timestamp);

        // Thêm bình luận vào danh sách
        commentsList.appendChild(comment);

        // Reset input và sao
        commentInput.value = "";
        rating = 0;
        updateStars();
        ratingText.textContent = "Chọn đánh giá";
    });

    // Cập nhật trạng thái sao
    function updateStars(hoverValue = 0) {
        stars.forEach((star) => {
            const value = parseInt(star.getAttribute("data-value"));
            if (value <= (hoverValue || rating)) {
                star.classList.add("selected");
            } else {
                star.classList.remove("selected");
            }
        });
    }
});

// Đảm bảo DOM đã load xong
document.addEventListener('DOMContentLoaded', function() {
    // Lấy element heart icon
    const favoriteIcon = document.querySelector('.favorite-icon');

    // Kiểm tra xem có tìm thấy element không
    if (favoriteIcon) {
        favoriteIcon.addEventListener('click', function() {
            // Toggle class và log để debug
            this.classList.toggle('active');
            console.log('Clicked! Active status:', this.classList.contains('active'));
        });
    } else {
        console.log('Không tìm thấy element với class favorite-icon');
    }
});
// Khởi tạo biến để lưu trữ số lượng sản phẩm trong giỏ hàng
let cartItemCount = 0;

document.addEventListener('DOMContentLoaded', function() {
    // Lấy các elements cần thiết
    const addToCartButton = document.querySelector('.add-to-cart');
    const cartCounter = document.querySelector('.shopping-cart-icon span');
    const quantityValue = document.querySelector('.quantity-value');
    const quantityButtons = document.querySelectorAll('.quantity-btn');

    // Khởi tạo số lượng sản phẩm
    let quantity = 1;

    // Xử lý nút tăng/giảm số lượng
    quantityButtons.forEach(button => {
        button.addEventListener('click', function() {
            if (this.textContent === '+' && quantity < 99) {
                quantity++;
            } else if (this.textContent === '-' && quantity > 1) {
                quantity--;
            }
            quantityValue.textContent = quantity;
        });
    });

    // Xử lý sự kiện thêm vào giỏ hàng
    addToCartButton.addEventListener('click', function() {
        // Cập nhật số lượng trong giỏ hàng
        cartItemCount += quantity;

        // Cập nhật hiển thị số lượng trên icon giỏ hàng
        updateCartCounter();

        // Hiển thị thông báo thành công
        showNotification('Đã thêm sản phẩm vào giỏ hàng!');
    });

    // Xử lý các nút "Thêm vào giỏ" trong phần sản phẩm liên quan
    const relatedProductButtons = document.querySelectorAll('.add-to-carts');
    relatedProductButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Thêm 1 sản phẩm vào giỏ hàng
            cartItemCount++;

            // Cập nhật hiển thị
            updateCartCounter();

            // Hiển thị thông báo
            const productCard = button.closest('.product-card');
            const productName = productCard.querySelector('.product-name').textContent;
            showNotification(`Đã thêm sản phẩm "${productName}" vào giỏ hàng!`);
        });
    });

    // Hàm cập nhật số hiển thị trên icon giỏ hàng
    function updateCartCounter() {
        cartCounter.textContent = cartItemCount;

        // Thêm hiệu ứng highlight khi cập nhật số lượng
        cartCounter.classList.add('update-animation');
        setTimeout(() => {
            cartCounter.classList.remove('update-animation');
        }, 300);
    }

    // Hàm hiển thị thông báo
    function showNotification(message) {
        const notification = document.createElement('div');
        notification.className = 'notification';
        notification.textContent = message;

        document.body.appendChild(notification);

        // Tự động ẩn thông báo sau 2 giây
        setTimeout(() => {
            notification.remove();
        }, 2000);
    }
});

// Thêm styles cho hiệu ứng
const styles = `
    .update-animation {
        animation: bounce 0.3s ease-in-out;
    }

    @keyframes bounce {
        0% { transform: scale(1); }
        50% { transform: scale(1.2); }
        100% { transform: scale(1); }
    }

    .notification {
        position: fixed;
        top: 20px;
        right: 20px;
        background-color: #4CAF50;
        color: white;
        padding: 15px 25px;
        border-radius: 4px;
        z-index: 1000;
        animation: slideIn 0.3s ease-out;
    }

    @keyframes slideIn {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
`;

// Thêm styles vào document
const styleSheet = document.createElement('style');
styleSheet.textContent = styles;
document.head.appendChild(styleSheet);
