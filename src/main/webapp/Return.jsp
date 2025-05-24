<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="CSS/styleReturn.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Chính sách đổi trả</title>
</head>
<body>
<div class="page">
    <%@ include file="JspComForHome/header.jsp"%>
    <div class="body-page">
        <div class="link-anchor">
            <a href="/PageHome/index.html">Trang chủ</a>
            <span>></span>
            <a href="#">Chính Sách Đổi Trả Hàng</a>
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
                    <li><a href="/DeliveryAndReceipt/DeliveryAndReceipt.html">Giao nhận hàng</a></li>
                    <li><a href="">Hướng dẫn đặt hàng</a></li>
                    <li><a href="Return.html">Chính sách đổi trả hàng</a></li>
                    <li><a href="#">Chính Sách Giải Quyết Khiếu Nại</a></li>
                </ul>
            </div>

            <div class="content">
                <h2>CHÍNH SÁCH ĐỔI TRẢ HÀNG</h2>
                <h3>I. THỜI GIAN XỬ LÝ KHI CÓ YÊU CẦU</h3>
                <p>Trong vòng <strong>4H</strong> kể từ khi nhận được phản hồi của Khách Hàng vào thời gian mà Đảo Hải Sản xử lý đơn hàng. Nếu phản hồi vào ngoài giờ, Đảo Hải Sản sẽ xử lý vào thời gian làm việc tiếp theo. Thời gian bộ phận DVKH tiếp nhận thông tin là 8H-21H (T2-CN).</p>

                <h3>II. CHÍNH SÁCH ĐỔI TRẢ - HOÀN TIỀN</h3>
                <p>Vì Đảo Hải Sản cung cấp các loại hải sản Tươi, Sống, Đông Lạnh là các loại thực phẩm đòi hỏi yêu cầu về vận chuyển, bảo quản với điều kiện rất nghiêm ngặt, chính vì vậy việc đổi trả hàng theo đó cũng được quy định cho các trường hợp sau:</p>

                <ul>
                    <li>Sai hoặc thiếu sản phẩm: Nếu sai hàng Đảo Hải Sản sẽ tiến hành xác nhận và đổi lại hàng cho bạn. Trường hợp thiếu hàng Đảo Hải Sản sẽ giao thêm cho bạn. Đảo Hải Sản sẽ cố gắng xử lý sớm nhất có thể hoặc trong vòng <strong>2H làm việc</strong>.</li>
                    <li>Hàng không đúng mô tả:
                        <ul>
                            <li>Hàng Sống: Giao tới hàng không còn sống, Cua, Ghẹ hoặc Tôm bị gãy càng... sản phẩm không đúng như hình dạng của nó.</li>
                            <li>Hàng Tươi: Giao không đúng quy cách tươi</li>
                            <li>Hàng đông lạnh bị rã đông hoàn toàn, không đảm bảo chất lượng sản phẩm.</li>
                        </ul>
                    </li>
                    <li>Đổi trả sản phẩm do lỗi chất lượng: Nếu sản phẩm sử dụng mà không như chất lượng cam kết thì Đảo Hải Sản sẽ giải quyết theo <a href="#">"Chính Sách Giải Quyết Khiếu Nại"</a>.</li>
                </ul>

                <h3>Điều kiện đổi trả hàng:</h3>
                <ul>
                    <li>Đối với Hải Sản Sống, Hải Sản Tươi: Khách hàng vui lòng kiểm tra kỹ hàng trước khi nhận. Đảo Hải Sản không giải quyết cho các trường hợp đổi trả hàng sau khi khách hàng nhận.</li>
                    <li>Đối với Hải Sản Đông Lạnh: Sau khi nhận hàng, khách chưa sử dụng có thể đổi trả hàng trong vòng <strong>24 tiếng</strong> với điều kiện hàng khách trả phải bảo quản đúng nhiệt độ trong túi đông lạnh đã được giao.</li>
                    <li>Trường hợp khách đã sử dụng và cảm thấy chất lượng không tốt muốn đổi trả thì Đảo Hải Sản sẽ giải quyết theo <a href="#">"Chính Sách Giải Quyết Khiếu Nại"</a>.</li>
                </ul>

                <h3>III. CÁC HÌNH THỨC ĐỔI TRẢ & HOÀN TIỀN</h3>
                <ul>
                    <li>Đổi sản phẩm cùng loại hoặc quy đổi sản phẩm mới, phần chênh lệch khách hàng và Đảo Hải Sản thanh toán với nhau.</li>
                    <li>Hoàn vào <strong>TÀI KHOẢN ĐIỂM</strong> khi khách hàng để mua hàng vào những lần sau.</li>
                    <li>Hoàn tiền bằng <strong>QUỸ GỬI VOUCHER</strong> thì khách hàng sẽ được nhận ưu đãi mua hàng vào lần sau.</li>
                    <li>Hoàn tiền bằng hình thức chuyển khoản, Đảo Hải Sản xử lý mọi khoản phí hoàn tiền trong vòng <strong>2H làm việc</strong>.</li>
                </ul>
            </div>
        </div>
    </div>
    <%@ include file="JspComForHome/footer.jsp"%>
    <script src="js/home.js"></script>
</div>
</body>
</html>