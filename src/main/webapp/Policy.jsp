<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="author" content="Ly Thai Minh Khang">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chính Sách & Quy Định Chung</title>
    <link rel="stylesheet" href="<c:url value='/fontawesome/css/policy.css'/>" />

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
            <span>Chính Sách và Quy Định Chung</span>
            <div class="policy-content">
                <h1>Chính sách & quy định chung</h1>
                <div class="company-info">
                    <p>Đây là website giới thiệu và bán những sản phẩm online do công ty TNHH OCEAN SEAFOOD cung cấp.</p>
                    <p>Một số quy định chính sách chung của OCEAN SEAFOOD</p>
                </div>

                <section class="order-guide">
                    <h2>I. Hướng dẫn đặt hàng</h2>

                    <div class="order-method">
                        <h3>1. Đặt hàng qua điện thoại:</h3>
                        <p>Ban vui lòng gọi qua số <span class="phone-number">0900112233</span> để được tư vấn sản phẩm và đặt hàng. Thời gian nhận đơn hàng qua Hotline từ 7h- 22h tất cả những ngày trong tuần.</p>
                    </div>

                    <div class="order-method">
                        <h3>2. Đặt hàng qua website:</h3>
                        <div class="order-steps">
                            <p>Bước 1: Tìm kiếm sản phẩm trong ô tìm kiếm hoặc lựa chọn sản phẩm bạn muốn mua trong danh mục tại webiste: https://daohaisan.vn/</p>
                            <p>Bước 2: Bấm vào sản phẩm bạn muốn mua và xem kỹ thông tin sản phẩm, nếu bạn quyết định mua hàng thì bấm vào nút Mua Ngay hoặc thêm vào giỏ hàng.</p>
                            <p>Bước 3: Bấm vào nút thanh toán và điền thông tin nhận hàng để OCEAN SEAFOOD liên hệ giao hàng cho bạn.</p>
                            <p>Bước 4: Chờ xác nhận giao hàng, kiểm tra và nhận hàng.</p>
                        </div>
                    </div>
                </section>

                <section class="delivery-policy">
                    <h2>II. Giao Nhận Hàng</h2>
                    <div class="delivery-time">
                        <h3>1. Thời gian giao hàng:</h3>
                        <ul>
                            <li>Thông thường OCEAN SEAFOOD sẽ giao hàng cho bạn trong vòng 4h kể từ khi xác nhận thành công đơn hàng</li>
                            <li>Thời gian xử lý đơn hàng từ 8h-19h30 tất cả những ngày trong tuần</li>
                            <li>Những đơn hàng ở các tỉnh thành khác TPHCM sẽ được báo chính xác cụ thể thời gian giao hàng khi khách đặt hàng</li>
                        </ul>
                    </div>

                    <div class="delivery-fee">
                        <h3>2. Phí giao hàng:</h3>
                        <ul>
                            <li>Miễn phí giao hàng nội thành TPHCM với đơn hàng từ 500,000đ trở lên</li>
                            <li>Phí 20,000đ cho những đơn hàng nội thành TPHCM dưới 500,000đ</li>
                            <li>Đơn hàng tỉnh khác sẽ được báo phí cụ thể sau khi liên hệ với đơn vị vận chuyển</li>
                        </ul>
                    </div>
                    <div class="delivery-scope">
                        <h3>3. Phạm vi giao hàng:</h3>
                        <ul>
                            <li>OCEAN SEAFOOD có thể giao hàng toàn quốc. Những đơn hàng tại nội thành TP.HCM sẽ do nhân viên của OCEAN SEAFOOD trực tiếp giao. Ở những tỉnh thành khác, bạn vui lòng thanh toán trước bằng chuyển khoản. Khi nhận được thanh toán, OCEAN FOOD sẽ cho chuyển hàng cho bạn qua đường xe hoặc đường hàng không.</li>
                        </ul>
                    </div>

                    <div class="delivery-direct">
                        <h3>4. Mua hàng trực tiếp tại cửa hàng OCEAN SEAFOOD:</h3>
                        <ul>
                            <li>Bạn có thể xem và mua hàng trực tiếp tại cửa hàng của OCEAN SEAFOOD 0900112233 để OCEAN SEAFOOD phục vụ bạn một cách tốt nhất.</li>
                            <li>Thời gian mở cửa : 8h-20h tất cả các ngày trong tuần.</li>
                            <li>Tuy nhiên bạn cần liên hệ trước khi đến qua số điện thoại: 0900112233</li>
                        </ul>
                    </div>
                </section>

                <section class="payment-methods">
                    <h2>III. Hình Thức Thanh Toán</h2>

                    <div class="cod-payment">
                        <h3>1. Thanh toán khi nhận hàng:</h3>
                        <p>Đối với những khách hàng thuộc nội thành TPHCM, OCEAN SEAFOOD sẽ thu tiền khi giao hàng cho bạn.</p>
                    </div>

                    <div class="bank-transfer">
                        <h3>2. Thanh toán chuyển khoản:</h3>
                        <p>Đối với những khách hàng ngoại thành TPHCM hoặc ở các tỉnh thành khác, bạn cần thanh toán bằng chuyển khoản vào tài khoản của OCEAN FOOD. OCEAN FOOD sẽ tiến hành chuyển hàng cho bạn ngay sau khi nhận được tiền.</p>
                    </div>
                </section>

                <section class="return-policy">
                    <h2>IV. Chính sách đổi trả - Hoàn tiền</h2>
                    <ul>
                        <li>OCEAN SEAFOOD có chính sách bán và giao hàng tận nơi, do đó ngay khi nhận được sản phẩm Quý Khách vui lòng kiểm tra kĩ gói hàng xem có đúng mặt hàng và số lượng mình đã đặt mua hay không. Nếu xảy ra sai sót không đúng mặt hàng và số lượng đã đặt Quý Khách được quyền không nhận hàng và đồng thời không phải trả bất kì chi phí nào.</li>
                        <li>Trường hợp không hài lòng về mẫu mã hoặc quy cách sản phẩm do OCEAN SEAFOOD mô tả không đúng Quý Khách có quyền đổi hoặc trả lại hàng.</li>
                        <li>Sau khi đã nhận sản phẩm nếu xảy ra các vấn đề liên quan đến chất lượng, Quý Khách có thể có thể chụp hình sản phẩm hoặc giữ lại mẫu sản phẩm và gửi ngay phản hồi qua Hotline hoặc Fanpage OCEAN FOOD trong vòng 24h sau khi nhận hàng. OCEAN FOOD sẽ thu hồi và đổi trả ngay sản phẩm mới hoặc hoàn trả lại tiền cho Quý Khách.</li>
                    </ul>
                </section>

                <div class="contact-info">
                    <p>Mọi chi tiết quý khách hàng có thể gọi Hotline <span class="phone-number">0900112233</span> để được tư vấn và giải đáp thắc mắc.</p>
                </div>
            </div>

        </div>
        <jsp:include page="/JspComForHome/footer.jsp"/>
    </div>



</html>