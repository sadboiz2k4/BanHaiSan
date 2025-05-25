<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="author" content="Ly Thai Minh Khang">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giới Thiệu</title>
    <link rel="stylesheet" href="<c:url value='fontawesome/css/Introduce.css'/>" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
<div class="page">
    <!-- Header section -->
    <jsp:include page="/JspComForHome/header.jsp"/>
    <div class="breadcrumb-container">
        <div class="breadcrumb">
            <a href="/PageHome/index.html">Trang chủ</a>
            <span> / </span>
            <span>Hệ Thống Cửa Hàng</span>
            <div class="introduce-content">
                <section class="intro-section">
                    <h2>Chào mừng đến với OCEAN SEAFOOD</h2>
                    <p>OCEAN SEAFOOD vinh dự cung cấp cho hơn 200,000 Khách Hàng mỗi năm là những hộ gia đình mua hàng thường xuyên với hàng trăm ngàn đơn hàng và là đối tác thân thiết với các hệ thống siêu thị lớn như: Aeon, Lotte, Winmart, Emart, Kingfood và các nhà hàng lớn như White Palace, New World, Pizza4Ps, Gem Center,...</p>
                    <p>Sự Hài Lòng và Niềm Tin của Khách Hàng là điều mà chúng tôi đặt lên hàng đầu trong suốt quá trình kinh doanh, chúng tôi nỗ lực mỗi ngày để cung cấp đến tay khách hàng những sản phẩm NGON NHẤT, SẠCH NHẤT và DỊCH VỤ KHÁCH HÀNG TỐT NHẤT.</p>
                </section>
                <section class="features">
                    <div class="feature-card">
                        <i class="fas fa-fish"></i>
                        <h3>Hải Sản Tươi Sống</h3>
                        <p>Đảm bảo nguồn hải sản tươi sống 100%, được vận chuyển và bảo quản theo tiêu chuẩn cao cấp</p>
                    </div>
                    <div class="feature-card">
                        <i class="fas fa-truck"></i>
                        <h3>Giao Hàng Nhanh 2H</h3>
                        <p>Dịch vụ giao hàng nhanh trong vòng 2 giờ, đảm bảo hải sản đến tay khách hàng luôn tươi ngon</p>
                    </div>
                    <div class="feature-card">
                        <i class="fas fa-certificate"></i>
                        <h3>Cam Kết Chất Lượng</h3>
                        <p>Chính sách đổi trả linh hoạt, cam kết chất lượng với từng sản phẩm</p>
                    </div>
                </section>
                <section class="business-philosophy">
                    <div class="section-title">
                        <h2>TRIẾT LÝ KINH DOANH - TẦM NHÌN, SỨ MỆNH & CÁC CAM KẾT</h2>
                    </div>

                    <div class="philosophy-tabs">
                        <div class="tab-buttons">
                            <button class="tab-btn active" data-tab="vision">TẦM NHÌN</button>
                            <button class="tab-btn" data-tab="mission">SỨ MỆNH</button>
                            <button class="tab-btn" data-tab="inspiration">NIỀM CẢM HỨNG</button>
                        </div>

                        <div class="tab-content">
                            <div class="philosophy-card active" id="vision">
                                <h3>TẦM NHÌN</h3>
                                <p>DẪN ĐẦU THỊ TRƯỜNG HẢI SẢN & SASHIMI TẠI VIỆT NAM VÀO NĂM 2029 VỚI HỆ THỐNG CỬA HÀNG BÁN LẺ PHỦ RỘNG VÀ NHẬN ĐƯỢC NIỀM TIN YÊU LỚN TỪ KHÁCH HÀNG.</p>

                            </div>

                            <div class="philosophy-card" id="mission">
                                <h3>SỨ MỆNH</h3>
                                <p>TẬN TÂM MANG ĐẾN SẢN PHẨM TƯƠI NGON VÀ TRẢI NGHIỆM VƯỢT TRỘI.</p>

                            </div>

                            <div class="philosophy-card" id="inspiration">
                                <h3>"LẤY KHÁCH HÀNG LÀ NIỀM CẢM HỨNG"</h3>
                                <p>Chúng tôi mong muốn mang đến cho Khách Hàng những TRẢI NGHIỆM TUYỆT VỜI NHẤT ở tất cả các khía cạnh, điều đó làm chúng tôi thấy được ý nghĩa công việc, niềm vui và nhiều cảm hứng.</p>

                            </div>
                        </div>
                    </div>
                </section>
                <section class="core-values">
                    <div class="section-title">
                        <div class="section-number">4 GIÁ TRỊ CỐT LÕI & NIỀM TIN</div>
                    </div>
                    <div class="values-grid">
                        <div class="value-card">
                            <div class="value-image">
                                <img src="assets/img/ChinhTruc.jpg" alt="Chính trực">
                            </div>
                            <h3>1. CHÍNH TRỰC</h3>
                            <p>Con người Trung Thực, ngay thẳng, không gian dối, có trách nhiệm, coi trọng lời nói và luôn đặt lợi ích công ty lên trên hết.</p>
                        </div>

                        <div class="value-card">
                            <div class="value-image">
                                <img src="assets/img/CamKet.jpg" alt="Cam kết">
                            </div>
                            <h3>2. CAM KẾT</h3>
                            <p>Chúng tôi tin rằng tính cam kết cao ở mỗi thành viên sẽ giúp chúng tôi tin tưởng nhau hơn, nỗ lực hoàn thành tốt công việc và thực hiện đúng cam kết với các bên liên quan.</p>
                        </div>

                        <div class="value-card">
                            <div class="value-image">
                                <img src="assets/img/ThongSuot.jpg" alt="Thông suốt">
                            </div>
                            <h3>3. THÔNG SUỐT</h3>
                            <p>Tin rằng một hệ thống minh bạch, quy trình rõ ràng, tường tắc sẽ mở giúp mọi người làm việc tốt hơn, chúng tôi xem trọng việc giao tiếp trọn vẹn, chân thành với khách hàng, đối tác và đồng nghiệp.</p>
                        </div>

                        <div class="value-card">
                            <div class="value-image">
                                <img src="assets/img/LuonTienToi.jpg" alt="Luôn tiến tới">
                            </div>
                            <h3>4. LUÔN TIẾN TỚI</h3>
                            <p>Chúng tôi tin rằng phải liên tục cải tiến, học tập và đặt ra những tiêu chuẩn cao hơn để tốt hơn mỗi ngày.</p>
                        </div>
                    </div>
                </section>
                <jsp:include page="/JspComForHome/footer.jsp"/>
            </div>

</html>