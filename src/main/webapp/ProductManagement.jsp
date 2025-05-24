<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Quản Lý Sản Phẩm | Quản trị Admin</title>
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
        <li><a class="app-nav__item" href="<c:url value='index.jsp'/>"><i class='bx bx-log-out bx-rotate-180'></i></a></li>
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
            <li><a class="app-menu__item " href="<c:url value='dashboard'/>">
                <i class='app-menu__icon bx bx-tachometer'></i><span class="app-menu__label">Bảng điều khiển</span></a>
            </li>
            <li><a class="app-menu__item " href="<c:url value='employee'/>">
                <i class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lý nhân viên</span></a>
            </li>
            <li><a class="app-menu__item" href="<c:url value='customer'/>">
                <i class='app-menu__icon bx bx-user-voice'></i><span class="app-menu__label">Quản lý khách hàng</span></a>
            </li>
            <li><a class="app-menu__item active" href="<c:url value='product-admin'/>">
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
                    <li class="breadcrumb-item"><a href="ProductManagement.jsp"><b>Quản Lý Sản Phẩm</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="<c:url value='add-product'/>" title="Thêm">
                                <i class="fas fa-plus"></i> Tạo mới
                            </a>
                        </div>
                    </div>
                    <table class="table table-hover table-bordered" id="sampleTable">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" id="all"></th>
                            <th>Mã sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Ảnh</th>
                            <th>Số lượng</th>
                            <th>Tình trạng</th>
                            <th>Giá tiền</th>
                            <th>Danh mục</th>
                            <th>Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td width="10"><input type="checkbox" name="check1" value="${product.productID}"></td>
                                <td>${product.productID}</td>
                                <td>${product.nameProduct}</td>
                                <td>
                                    <c:if test="${not empty product.imgURL}">
                                        <img src="Img/ImgProduct/${product.imgURL}" alt="${product.nameProduct}" width="100px">
                                    </c:if>
                                </td>
                                <td>${product.stock}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${product.stock > 0}">
                                            <span class="badge bg-success">Còn hàng</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger">Hết hàng</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatNumber value="${product.price}" type="currency" currencyCode="VND"/></td>
                                <td>${product.categoryName}</td>
                                <td>
                                    <div class="btn-group btn-group-sm" role="group">
                                        <a href="add-more-info-product?pid=${product.productID}" class="btn btn-primary" type="button" title="Thêm thông tin">
                                            <i class="fas fa-plus"></i>
                                        </a>
                                        <a href="delete?pid=${product.productID}" class="btn btn-primary" type="button" title="Xóa">
                                            <i class="fas fa-trash-alt"></i>
                                        </a>
                                        <a href="loadProduct?pid=${product.productID}" class="btn btn-primary" type="button" title="Sửa">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
<script type="text/javascript">
    $('#sampleTable').DataTable();

    // Time function
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

        if (dd < 10) { dd = '0' + dd }
        if (mm < 10) { mm = '0' + mm }

        today = day + ', ' + dd + '/' + mm + '/' + yyyy;
        tmp = '<span class="date"> ' + today + ' - ' + nowTime + '</span>';

        document.getElementById("clock").innerHTML = tmp;
        clocktime = setTimeout("time()", "1000", "Javascript");

        function checkTime(i) {
            if (i < 10) { i = "0" + i };
            return i;
        }
    }
</script>
</body>
</html>