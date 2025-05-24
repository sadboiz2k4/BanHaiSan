<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Báo Cáo Doanh Thu | Quản trị Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="<c:url value='fontawesome/css/admin.css'/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
</head>

<body onload="time()" class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header">
    <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
    <ul class="app-nav">
        <li><a class="app-nav__item" href="<c:url value='index.jsp'/>"><i class='bx bx-log-out bx-rotate-180'></i></a></li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <img class="app-sidebar__user-avatar" src="<c:url value='Img/Admin/avt.jpg'/>" width="50px" alt="User Image">
        <div>
            <p class="app-sidebar__user-name"><b>${sessionScope.admin.fullname}</b></p>
            <p class="app-sidebar__user-designation">Chào mừng bạn trở lại</p>
        </div>
    </div>
    <hr>
    <ul class="app-menu">
        <ul class="app-menu">
            <li><a class="app-menu__item " href="<c:url value='dashboard'/>">
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
            <li><a class="app-menu__item active" href="<c:url value='revenue'/>">
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
                    <li class="breadcrumb-item"><a href="revenue"><b>Bảng Cáo Doanh Thu</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
        </div>
    </div>

    <!-- Statistics Widgets -->
    <div class="row">
        <div class="col-md-6 col-lg-3">
            <div class="widget-small primary coloured-icon">
                <i class='icon bx bxs-user fa-3x'></i>
                <div class="info">
                    <h4>Tổng Nhân viên</h4>
                    <p><b>${revenueStats.totalEmployees} nhân viên</b></p>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="widget-small info coloured-icon">
                <i class='icon bx bxs-purchase-tag-alt fa-3x'></i>
                <div class="info">
                    <h4>Tổng sản phẩm</h4>
                    <p><b>${revenueStats.totalProducts} sản phẩm</b></p>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="widget-small warning coloured-icon">
                <i class='icon fa-3x bx bxs-shopping-bag-alt'></i>
                <div class="info">
                    <h4>Tổng đơn hàng</h4>
                    <p><b>${revenueStats.totalOrders} đơn hàng</b></p>
                </div>
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-md-6 col-lg-3">
            <div class="widget-small primary coloured-icon">
                <i class='icon fa-3x bx bxs-chart'></i>
                <div class="info">
                    <h4>Tổng thu nhập</h4>
                    <p><b><fmt:formatNumber value="${revenueStats.totalIncome}" type="currency" currencySymbol="đ"/></b></p>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="widget-small info coloured-icon">
                <i class='icon fa-3x bx bxs-user-badge'></i>
                <div class="info">
                    <h4>Nhân viên mới</h4>
                    <p><b>${revenueStats.newEmployees} nhân viên</b></p>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3">
            <div class="widget-small warning coloured-icon">
                <i class='icon fa-3x bx bxs-tag-x'></i>
                <div class="info">
                    <h4>Hết hàng</h4>
                    <p><b>${revenueStats.outOfStockProducts} sản phẩm</b></p>
                </div>
            </div>
        </div>

    </div>
    <!-- Best Selling Products -->
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <h3 class="tile-title">SẢN PHẨM BÁN CHẠY</h3>
                <div class="tile-body">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>Mã sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá tiền</th>
                            <th>Danh mục</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${bestSellers}" var="product">
                            <tr>
                                <td>${product.productID}</td>
                                <td>${product.nameProduct}</td>
                                <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/></td>
                                <td>${product.categoryName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Total Orders -->
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <h3 class="tile-title">TỔNG ĐƠN HÀNG</h3>
                <div class="tile-body">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>ID đơn hàng</th>
                            <th>Khách hàng</th>
                            <th>Đơn hàng</th>
                            <th>Số lượng</th>
                            <th>Tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.customerName}</td>
                                <td>${order.productName}</td>
                                <td>${order.quantity}</td>
                                <td><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="đ"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div>
                    <h3 class="tile-title">SẢN PHẨM ĐÃ HẾT</h3>
                </div>
                <div class="tile-body">
                    <table class="table table-hover table-bordered" id="outOfStockTable">
                        <thead>
                        <tr>
                            <th>Mã sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Ảnh</th>
                            <th>Số lượng</th>
                            <th>Tình trạng</th>
                            <th>Giá tiền</th>
                            <th>Danh mục</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${outOfStockProducts}">
                            <tr>
                                <td>${product.productID}</td>
                                <td>${product.nameProduct}</td>
                                <td>
                                    <img src="<c:url value=''/>" alt="Product Image" width="100px">
                                </td>
                                <td>${product.stock}</td>
                                <td><span class="badge bg-danger">Hết hàng</span></td>
                                <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"/></td>
                                <td>${product.categoryName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- Charts -->
    <div class="row">
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title">DỮ LIỆU HÀNG THÁNG</h3>
                <div class="embed-responsive embed-responsive-16by9">
                    <canvas class="embed-responsive-item" id="lineChartDemo"></canvas>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title">THỐNG KÊ DOANH SỐ</h3>
                <div class="embed-responsive embed-responsive-16by9">
                    <canvas class="embed-responsive-item" id="barChartDemo"></canvas>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Essential javascripts -->
<script src="<c:url value='fontawesome/js/Admin/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/popper.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/bootstrap.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/main.js'/>"></script>
<script src="<c:url value='fontawesome/js/plugins/pace.min.js'/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script type="text/javascript" src="<c:url value='fontawesome/js/plugins/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='fontawesome/js/plugins/dataTables.bootstrap.min.js'/>"></script>
<!-- Chart initialization script -->
<script type="text/javascript">
    // ... chart initialization code ...
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
    var chartData = JSON.parse('${dataChart}');

    // Format the data for charts
    var months = chartData.map(item => item.month);
    var revenues = chartData.map(item => item.revenue);
    var orderCounts = chartData.map(item => item.totalOrders);

    // Line Chart for Revenue
    var ctxLine = document.getElementById('lineChartDemo').getContext('2d');
    var lineChart = new Chart(ctxLine, {
        type: 'line',
        data: {
            labels: months,
            datasets: [{
                label: 'Doanh thu theo tháng',
                data: revenues,
                borderColor: 'rgb(75, 192, 192)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                tension: 0.1,
                fill: true
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        callback: function(value, index, values) {
                            return value.toLocaleString('vi-VN', {
                                style: 'currency',
                                currency: 'VND'
                            });
                        }
                    }
                }]
            },
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        return tooltipItem.yLabel.toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });
                    }
                }
            }
        }
    });

    // Bar Chart for Orders
    var ctxBar = document.getElementById('barChartDemo').getContext('2d');
    var barChart = new Chart(ctxBar, {
        type: 'bar',
        data: {
            labels: months,
            datasets: [{
                label: 'Số đơn hàng theo tháng',
                data: orderCounts,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgb(54, 162, 235)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1
                    }
                }]
            }
        }
    });
</script>

<!-- DataTables initialization -->
<script type="text/javascript">
    $(document).ready(function() {
        // Initialize DataTables for the out of stock products table
        $('#outOfStockTable').DataTable({
            "language": {
                "lengthMenu": "Hiển thị _MENU_ sản phẩm mỗi trang",
                "zeroRecords": "Không tìm thấy sản phẩm nào",
                "info": "Hiển thị trang _PAGE_ của _PAGES_",
                "infoEmpty": "Không có sản phẩm nào",
                "infoFiltered": "(lọc từ _MAX_ sản phẩm)",
                "search": "Tìm kiếm:",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Sau",
                    "previous": "Trước"
                }
            },
            "pageLength": 5,
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "Tất cả"]]
        });
    });

    // Time display function
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
    }

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }
</script>

<!-- Auto-refresh page data every 5 minutes -->
<script>
    setTimeout(function(){
        window.location.reload();
    }, 300000); // 5 minutes in milliseconds
</script>
</body>
</html>