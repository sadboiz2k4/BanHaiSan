<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Bảng Điều Khiển | Quản trị Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="<c:url value='fontawesome/css/admin.css'/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
</head>

<body onload="time()" class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header">
    <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <ul class="app-nav">
        <li><a class="app-nav__item" href="<c:url value='home'/>"><i class='bx bx-log-out bx-rotate-180'></i></a></li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="app-sidebar__user-avatar" src="<c:url value='Img/Admin/avt.jpg'/>" width="50px" alt="User Image">
        <div>
            <p class="app-sidebar__user-name"><b>${sessionScope.Employees.fullname}</b></p>
            <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
        </div>
    </div>
    <hr>
    <ul class="app-menu">
        <ul class="app-menu">
            <li><a class="app-menu__item active" href="<c:url value='dashboard'/>">
                <i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Bảng điều khiển</span></a>
            </li>
            <li><a class="app-menu__item " href="<c:url value='employee'/>">
                <i class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lý nhân viên</span></a>
            </li>
            <li><a class="app-menu__item" href="<c:url value='customer'/>">
                <i class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Quản lý khách hàng</span></a>
            </li>
            <li><a class="app-menu__item " href="<c:url value='product-admin'/>">
                <i class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
            </li>
            <li><a class="app-menu__item" href="<c:url value='order-admin'/>">
                <i class='app-menu__icon bx bx-task'></i><span class="app-menu__label">Quản lý đơn hàng</span></a>
            </li>
            <li><a class="app-menu__item" href="<c:url value='salary'/>">
                <i class='app-menu__icon bx bx-dollar'></i><span class="app-menu__label">Bảng kê lương</span></a>
            </li>
            <li><a class="app-menu__item" href="<c:url value='revenue'/>">
                <i class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Báo cáo doanh thu</span></a>
            </li>
        </ul>
    </ul>
</aside>

<main class="app-content">
    <div class="row">
        <div class="col-md-12">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="#"><b>Bảng Điều Khiển</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
        </div>
    </div>

    <!-- Dashboard Stats -->
    <div class="row">
        <div class="col-md-6">
            <div class="widget-small primary coloured-icon">
                <i class='icon bx bxs-user-account fa-3x'></i>
                <div class="info">
                    <h4>Tổng khách hàng</h4>
                    <p><b>${stats.totalCustomers} khách hàng</b></p>
                    <p class="info-tong">Tổng số khách hàng được quản lý.</p>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="widget-small info coloured-icon">
                <i class='icon bx bxs-data fa-3x'></i>
                <div class="info">
                    <h4>Tổng sản phẩm</h4>
                    <p><b>${stats.totalProducts} sản phẩm</b></p>
                    <p class="info-tong">Tổng số sản phẩm được quản lý.</p>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="widget-small warning coloured-icon"><i class='icon bx bxs-shopping-bags fa-3x'></i>
                <div class="info">
                    <h4>Tổng đơn hàng</h4>
                    <p><b>${stats.totalProducts} sản phẩm</b></p>
                    <p class="info-tong">Tổng số hóa đơn bán hàng trong tháng.</p>
                </div>
            </div>
        </div>

        <!-- Recent Orders Table -->
        <div class="col-md-12">
            <div class="tile">
                <h3 class="tile-title">Tình trạng đơn hàng</h3>
                <div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>ID đơn hàng</th>
                            <th>Tên khách hàng</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${recentOrders}" var="order">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.customerName}</td>
                                <td>
                                    <fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="đ"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.status eq 'Pending'}">
                                            <span class="badge bg-info">Chờ xử lý</span>
                                        </c:when>
                                        <c:when test="${order.status eq 'Shipping'}">
                                            <span class="badge bg-warning">Đang vận chuyển</span>
                                        </c:when>
                                        <c:when test="${order.status eq 'Completed'}">
                                            <span class="badge bg-success">Đã hoàn thành</span>
                                        </c:when>
                                        <c:when test="${order.status eq 'Cancelled'}">
                                            <span class="badge bg-danger">Đã hủy</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>
    <!-- Footer -->
    <div class="text-center" style="font-size: 13px">
        <p><b>Ocean Seafood
            <script>document.write(new Date().getFullYear());</script>
        </b></p>
    </div>
</main>


<!-- Essential javascripts -->
<script src="<c:url value='fontawesome/js/Admin/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/popper.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/bootstrap.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/main.js'/>"></script>
<script src="<c:url value='fontawesome/js/plugins/pace.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/plugins/chart.js'/>"></script>

<!-- Chart initialization -->
<script>
    var monthlyData = {
        labels: [1,2,3,4,5,6],
        datasets: [{
            label: "Doanh thu",
            fillColor: "rgba(9, 109, 239, 0.651)",
            strokeColor: "rgb(9, 109, 239)",
            pointColor: "rgb(9, 109, 239)",
            pointStrokeColor: "#fff",
            data: [2,5,6,7,8,9]
        }]
    };

    <c:forEach items="${monthlyRevenue}" var="entry">
    monthlyData.labels.push("${entry.key}");
    monthlyData.datasets[0].data.push(${entry.value});
    </c:forEach>

    var ctxl = $("#lineChartDemo").get(0).getContext("2d");
    var lineChart = new Chart(ctxl).Line(monthlyData);
</script>

<!-- Time function -->
<script>
    function time() {
        var today = new Date();
        var weekday = ["Chủ Nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"];
        var day = weekday[today.getDay()];
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();

        m = checkTime(m);
        s = checkTime(s);
        nowTime = h + " giờ " + m + " phút " + s + " giây";

        if (dd < 10) dd = '0' + dd;
        if (mm < 10) mm = '0' + mm;

        today = day + ', ' + dd + '/' + mm + '/' + yyyy;
        tmp = '<span class="date"> ' + today + ' - ' + nowTime + '</span>';

        document.getElementById("clock").innerHTML = tmp;
        clocktime = setTimeout("time()", "1000", "Javascript");

        function checkTime(i) {
            if (i < 10) i = "0" + i;
            return i;
        }
    }
</script>
</body>
</html>