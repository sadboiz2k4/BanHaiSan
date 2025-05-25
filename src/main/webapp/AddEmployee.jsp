<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Thêm nhân viên | Quản trị Admin</title>
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
<header class="app-header">
  <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
  <ul class="app-nav">
    <li><a class="app-nav__item" href="<c:url value='index.jsp'/>"><i class='bx bx-log-out bx-rotate-180'></i></a></li>
  </ul>
</header>
<body onload="time()" class="app sidebar-mini rtl">
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
      <li><a class="app-menu__item active" href="<c:url value='employee'/>">
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
  <div class="app-title">
    <ul class="app-breadcrumb breadcrumb">
      <li class="breadcrumb-item"><a href="employee">Danh sách nhân viên</a></li>
      <li class="breadcrumb-item"><a href="#">Thêm nhân viên</a></li>
    </ul>
  </div>

  <div class="row">
    <div class="col-md-12">
      <div class="tile">
        <h3 class="tile-title">Tạo mới nhân viên</h3>
        <div class="tile-body">
          <form class="row" id="addEmployeeForm">
            <div class="form-group col-md-4">
              <label class="control-label">ID nhân viên</label>
              <input class="form-control" type="text" name="EmployeeId" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Họ và tên</label>
              <input class="form-control" type="text" name="fullName" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Email</label>
              <input class="form-control" type="email" name="email" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Số điện thoại</label>
              <input class="form-control" type="tel" name="phoneNumber" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Địa chỉ</label>
              <input class="form-control" type="text" name="address" required
                     placeholder="Đường, Phường/Xã, Quận/Huyện, Tỉnh/Thành phố">
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Ngày sinh</label>
              <input class="form-control" type="date" name="birthDate" required>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Giới tính</label>
              <select class="form-control" name="gender" required>
                <option value="">-- Chọn giới tính --</option>
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
              </select>
            </div>
            <div class="form-group col-md-4">
              <label class="control-label">Chức vụ</label>
              <select class="form-control" name="position" required>
                <option value="">-- Chọn chức vụ --</option>
                <option value="Bán hàng">Bán hàng</option>
                <option value="Tư vấn">Tư vấn</option>
                <option value="Dịch vụ">Dịch vụ</option>
                <option value="Thu Ngân">Thu Ngân</option>
                <option value="Quản kho">Quản kho</option>
              </select>
            </div>
            <div class="form-group col-md-12">
              <button class="btn btn-save" type="submit">Lưu lại</button>
              <a class="btn btn-cancel" href="employee">Hủy bỏ</a>
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
  function validateForm() {
    const form = document.getElementById('addEmployeeForm');
    const fullName = form.querySelector('[name="fullName"]').value.trim();
    const email = form.querySelector('[name="email"]').value.trim();
    const phone = form.querySelector('[name="phoneNumber"]').value.trim();

    // Validate full name
    if (!/^[\p{L}\s]{2,50}$/u.test(fullName)) {
      swal("Lỗi!", "Họ tên không hợp lệ", "error");
      return false;
    }

    // Validate email
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      swal("Lỗi!", "Email không hợp lệ", "error");
      return false;
    }

    // Validate phone number
    if (!/^[0-9]{10,11}$/.test(phone)) {
      swal("Lỗi!", "Số điện thoại không hợp lệ", "error");
      return false;
    }

    return true;
  }

  // Update the form submission handler in AddEmployee.jsp
  document.getElementById('addEmployeeForm').addEventListener('submit', function(e) {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    // Create URLSearchParams
    const formData = new FormData(this);
    const searchParams = new URLSearchParams();
    formData.forEach((value, key) => {
      searchParams.append(key, value);
    });

    // Get employee ID from the form
    const employeeId = formData.get('EmployeeId');
    const isUpdate = employeeId && employeeId.trim() !== '';

    fetch('addEmployee', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: searchParams.toString()
    })
            .then(response => response.json())
            .then(data => {
              if (data.success) {
                swal({
                  title: "Thành công!",
                  text: data.message,
                  icon: "success",
                }).then(() => {
                  window.location.href = "employee";
                });
              } else {
                swal("Lỗi!", data.message, "error");
              }
            })
            .catch(error => {
              console.error('Error:', error);
              swal("Lỗi!", "Có lỗi xảy ra khi xử lý yêu cầu", "error");
            });
  });

  // Add function to load employee data for editing
  function loadEmployeeData(employeeId) {
    fetch(`addEmployee?action=get&id=${employeeId}`)
            .then(response => response.json())
            .then(employee => {
              document.querySelector('[name="EmployeeId"]').value = employee.employeeID;
              document.querySelector('[name="fullName"]').value =
                      `${employee.firstName} ${employee.lastName}`.trim();
              document.querySelector('[name="email"]').value = employee.email;
              document.querySelector('[name="phoneNumber"]').value = employee.phoneNumber;
              document.querySelector('[name="address"]').value = employee.address;
              document.querySelector('[name="birthDate"]').value =
                      new Date(employee.birthDate).toISOString().split('T')[0];
              document.querySelector('[name="gender"]').value = employee.gender;
              document.querySelector('[name="position"]').value = employee.position;
            })
            .catch(error => {
              console.error('Error:', error);
              swal("Lỗi!", "Không thể tải thông tin nhân viên", "error");
            });
  }

</script>
</body>
</html>