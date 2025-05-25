<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">

<head>
  <title>Thêm Đơn Hàng | Quản trị Admin</title>
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
  <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
  <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
</head>
<body class="app sidebar-mini rtl">
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
    <li><a class="app-menu__item active" href="<c:url value='order-admin'/>">
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
  <div class="app-title">
    <ul class="app-breadcrumb breadcrumb">
      <li class="breadcrumb-item"><a href="<c:url value='order-admin'/>">Danh sách đơn hàng</a></li>
      <li class="breadcrumb-item">Thêm đơn hàng</li>
    </ul>
    <div id="clock"></div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <h3 class="tile-title">Tạo mới đơn hàng</h3>
        <div class="tile-body">
          <form class="row" action="/addorder" method="POST">
            <div class="form-group col-md-4">
              <label class="control-label">ID đơn hàng</label>
              <input class="form-control" type="text" name="orderId" placeholder="Tự động phát sinh nếu không nhập">
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Tên khách hàng</label>
              <input class="form-control" type="text" name="customerName" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Số điện thoại khách hàng</label>
              <input class="form-control" type="number" name="customerPhone" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Địa chỉ khách hàng</label>
              <input class="form-control" type="text" name="customerAddress" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Tên sản phẩm</label>
              <input class="form-control" type="text" name="productName" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Số lượng</label>
              <input class="form-control" type="number" name="quantity" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Tổng tiền</label>
              <input class="form-control" type="number" name="totalPrice" required>
            </div>
            <div class="form-group col-md-4">
              <label for="status" class="control-label">Tình trạng</label>
              <select class="form-control" id="status" name="status" required>
                <option value="">-- Chọn tình trạng --</option>
                <option value="pending">Chờ thanh toán</option>
                <option value="shipping">Đang giao hàng</option>
                <option value="completed">Hoàn thành</option>
                <option value="cancelled">Đã hủy</option>
              </select>
            </div>
            <div class="form-group col-md-12">
              <label class="control-label">Ghi chú đơn hàng</label>
              <textarea class="form-control" rows="4" name="note"></textarea>
            </div>
            <div class="form-group col-md-12">
              <button class="btn btn-save" type="submit">Lưu lại</button>
              <a class="btn btn-cancel" href="<c:url value='order-admin'/>">Hủy bỏ</a>
            </div>
          </form>
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
    // Submit form with fetch API
    fetch(this.action, {
      method: 'POST',
      body: new FormData(this)
    })
            .then(response => {
              if (!response.ok) {
                throw new Error('Network response was not ok');
              }
              return response.text();
            })
            .then(() => {
              swal({
                title: "Thành công!",
                text: "Thêm đơn hàng thành công",
                icon: "success",
              }).then(() => {
                window.location.href = "<c:url value='customer'/>";
              });
            })
            .catch(error => {
              swal("Lỗi!", "Có lỗi xảy ra khi thêm đơn hàng: " + error.message, "error");
            });

</script>
</body>
</html>