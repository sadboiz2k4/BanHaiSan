<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="CSS/stylesDeliveryAndReceipt.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Giao và nhận hàng</title>
</head>
<body>
<div class="page">
    <%@ include file="JspComForHome/header.jsp"%>
        <div class="body-page">
            <div class="link-anchor">
                <a href="/PageHome/index.html">Trang chủ</a>
                <span>></span>
                <a href="#">Phản hồi</a>
            </div>
            <div class="container">
                <div class="sidebar">
                    <h3 class="menu">THÔNG TIN</h3>
                    <ul class="menu">
                        <li><a href="/Recipe/Recipe.html">Góc Ẩm Thực</a></li>
                        <li><a href="/PoliciesAndIncentives/PoliciesAndIncentives.html">Chính Sách / Ưu Đãi</a></li>
                        <li><a href="#">Hệ Thống Cửa Hàng</a></li>
                        <li><a href="/VIP/VIP.html">Chương Trình KHTT</a></li>
                        <li><a href="/FeedBack/FeedBack.html">FeedBack</a></li>
                    </ul>

                    <h3 >CHÍNH SÁCH</h3>
                    <ul class="menu">
                        <li><a href="/CTV/CTV.html">Đăng Ký Đại Lý/CTV</a></li>
                        <li><a href="DeliveryAndReceipt.html">Giao nhận hàng</a></li>
                        <li><a href="">Hướng dẫn đặt hàng</a></li>
                        <li><a href="/Return/Return.html">Chính sách đổi trả hàng</a></li>
                        <li><a href="#">Chính Sách Giải Quyết Khiếu Nại</a></li>
                    </ul>
                </div>

                <div class="content">
                    <main>
                        <section id="intro">
                            <h2>Quy trình Giao và Nhận Hàng</h2>
                            <p>Tại Ocean Seafood, chúng tôi cam kết mang đến hải sản tươi ngon nhất ngay tại cửa nhà bạn, đảm bảo nhanh chóng và an toàn.</p>
                        </section>

                        <section id="steps">
                            <div class="step">
                                <img src="Img/HomeImg/choseproduct.png" alt="Đặt hàng online" />
                                <h3>Bước 1: Đặt hàng</h3>
                                <p>Chọn hải sản yêu thích trên website của chúng tôi và hoàn tất đơn hàng dễ dàng chỉ với vài cú click.</p>
                            </div>

                            <div class="step">
                                <img src="https://hungtruongsa.vn/wp-content/uploads/2024/04/bao-quan-hai-san-mang-di-xa.jpg" alt="Chuẩn bị hàng" />
                                <h3>Bước 2: Chuẩn bị hải sản</h3>
                                <p>Đội ngũ của chúng tôi chọn lọc và đóng gói hải sản tươi ngon nhất với quy trình bảo quản nghiêm ngặt.</p>
                            </div>

                            <div class="step">
                                <img src="https://vantaixelanh.com/wp-content/uploads/2019/08/hai-san-tuoi-song-giao-hang-tan-nha-hanoi.png" alt="Giao hàng nhanh chóng" />
                                <h3>Bước 3: Giao hàng tận nơi</h3>
                                <p>Hải sản sẽ được giao tận nơi trong vòng 2-4 giờ, đảm bảo luôn tươi ngon khi đến tay bạn.</p>
                            </div>

                            <div class="step">
                                <img src="https://phanphoithucpham.com.vn/upload/hinhanh/hai-san-tuoi-song719n.jpg" alt="Nhận hàng tận nơi" />
                                <h3>Bước 4: Nhận hàng và thưởng thức</h3>
                                <p>Nhận hàng tại cửa nhà bạn và thưởng thức những món hải sản chất lượng nhất.</p>
                            </div>
                        </section>

                        <section id="contact">
                            <h2>Liên hệ với chúng tôi</h2>
                            <p>Nếu bạn có bất kỳ câu hỏi nào, hãy liên hệ qua số điện thoại <strong>1800-SEAFOOD</strong> hoặc email <strong>support@oceanseafood.com</strong>.</p>
                        </section>
                    </main>

                    <footer>
                        <p>&copy; 2024 Ocean Seafood. All rights reserved.</p>
                    </footer>
                </div>
            </div>
    <%@ include file="JspComForHome/footer.jsp"%>
    <script src="js/home.js"></script>
</div>
</body>
</html>