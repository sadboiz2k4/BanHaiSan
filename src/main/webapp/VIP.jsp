<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="CSS/stylesVip.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Chương Trình Khách Hàng Thân Thiết</title>
</head>
<body>
<div class="page">
    <%@ include file="JspComForHome/header.jsp"%>
    <div class="body-page">
        <div class="link-anchor">
            <a href="/PageHome/index.html">Trang chủ</a>
            <span>></span>
            <a href="#">Hạng Thành Viên</a>
        </div>
        <div class="container">
            <div class="sidebar">
                <h3 class="menu">THÔNG TIN</h3>
                <ul class="menu">
                    <li><a href="/Recipe/Recipe.html">Góc Ẩm Thực</a></li>
                    <li><a href="/PoliciesAndIncentives/PoliciesAndIncentives.html">Chính Sách / Ưu Đãi</a></li>
                    <li><a href="#">Hệ Thống Cửa Hàng</a></li>
                    <li><a href="VIP.html">Chương Trình KHTT</a></li>
                    <li><a href="/FeedBack/FeedBack.html">FeedBack</a></li>
                </ul>

                <h3 >CHÍNH SÁCH</h3>
                <ul class="menu">
                    <li><a href="/CTV/CTV.html">Đăng Ký Đại Lý/CTV</a></li>
                    <li><a href="/DeliveryAndReceipt/DeliveryAndReceipt.html">Giao nhận hàng</a></li>
                    <li><a href="">Hướng dẫn đặt hàng</a></li>
                    <li><a href="/Return/Return.html">Chính sách đổi trả hàng</a></li>
                    <li><a href="#">Chính Sách Giải Quyết Khiếu Nại</a></li>
                </ul>
            </div>

            <div class="content">
                <main>
                    <section class="khtt">
                        <h2>Chương Trình Khách Hàng Thân Thiết</h2>
                        <p>Chương trình Khách Hàng Thân Thiết là cách chúng tôi tri ân những khách hàng đã đồng hành và tin tưởng sử dụng sản phẩm của Website Hải Sản. Tham gia ngay để nhận những ưu đãi hấp dẫn!</p>

                        <div class="membership-levels">
                            <div class="level">
                                <h3 class="silver">Hạng Bạc</h3>
                                <ul>
                                    <li>Giảm 5% cho mỗi đơn hàng.</li>
                                    <li>Áp dụng cho tổng giá trị mua hàng trên 2 triệu đồng/tháng.</li>
                                </ul>
                            </div>
                            <div class="level">
                                <h3 class="gold">Hạng Vàng</h3>
                                <ul>
                                    <li>Giảm 10% cho đơn hàng từ 2 triệu đồng trở lên.</li>
                                    <li>Ưu tiên xử lý đơn hàng nhanh hơn.</li>
                                    <li>Áp dụng cho tổng giá trị mua hàng trên 5 triệu đồng/tháng.</li>
                                </ul>
                            </div>
                            <div class="level">
                                <h3 class="diamon">Hạng Kim Cương</h3>
                                <ul>
                                    <li>Giảm 15% cho mọi đơn hàng.</li>
                                    <li>Giao hàng miễn phí toàn quốc.</li>
                                    <li>Tặng voucher 500.000 VNĐ mỗi quý.</li>
                                    <li>Áp dụng cho tổng giá trị mua hàng trên 10 triệu đồng/tháng.</li>
                                </ul>
                            </div>
                        </div>

                        <div class="benefits">
                            <h3>Quyền Lợi Tham Gia</h3>
                            <p>Khách hàng tham gia chương trình sẽ được:</p>
                            <ul>
                                <li>Tích lũy điểm thưởng (mỗi 100.000 VNĐ = 1 điểm).</li>
                                <li>Đổi điểm tích lũy lấy quà tặng hoặc giảm giá đơn hàng.</li>
                                <li>Nhận thông báo ưu đãi đặc biệt qua email/sms.</li>
                            </ul>
                        </div>

                        <div class="join">
                            <h3>Tham Gia Ngay</h3>
                            <p>Liên hệ với chúng tôi qua:</p>
                            <ul>
                                <li>Hotline: <strong>1900-888-999</strong></li>
                                <li>Email: <strong>hotro@oceanseafood.com</strong></li>
                            </ul>
                            <p>Hoặc đăng ký trực tuyến để trở thành thành viên của chúng tôi ngay hôm nay!</p>
                            <a href="dang-ky-thanh-vien.html" class="btn">Đăng Ký Ngay</a>
                        </div>
                    </section>
                </main>
                <footer>
                    <p>&copy; 2024 Website OceanSeafood Hải Sản - Tươi Ngon Mỗi Ngày</p>
                </footer>
            </div>
        </div>
    </div>
    <%@ include file="JspComForHome/footer.jsp"%>
    <script src="js/home.js"></script>
</div>
</body>
</html>