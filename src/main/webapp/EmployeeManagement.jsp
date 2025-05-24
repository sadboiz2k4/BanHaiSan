<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Quản Lý nhân viên | Quản trị Admin</title>
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
    <div class="row">
        <div class="col-md-12">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="EmployeeManagement.jsp"><b>Danh Sách Nhân Viên</b></a></li>
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
                            <a class="btn btn-add btn-sm" href="<c:url value='addEmployee'/>" title="Thêm">
                                <i class="fas fa-plus"></i> Tạo mới
                            </a>
                        </div>
                    </div>

                    <table class="table table-hover table-bordered js-copytextarea" id="sampleTable">
                        <thead>
                        <tr>
                            <th width="10"><input type="checkbox" id="all"></th>
                            <th>ID nhân viên</th>
                            <th width="150">Họ và tên</th>
                            <th width="300">Địa chỉ</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>SĐT</th>
                            <th>Chức vụ</th>
                            <th width="100">Tính năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td width="10"><input type="checkbox" name="check1" value="1"></td>
                                <td>#${employee.employeeID}</td>
                                <td>${employee.lastName} ${employee.firstName}</td>
                                <td>${employee.address}</td>
                                <td><fmt:formatDate value="${employee.birthDate}" pattern="dd/MM/yyyy"/></td>
                                <td>${employee.gender}</td>
                                <td>${employee.phoneNumber}</td>
                                <td>${employee.position}</td>
                                <td>
                                    <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                                            onclick="deleteEmployee(${employee.employeeID})">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp"
                                            data-employee-id="${employee.employeeID}">
                                        <i class="fas fa-edit"></i>
                                    </button>
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

<!-- Employee Edit Modal -->
<div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Cập nhật thông tin nhân viên</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="<c:url value='updateEmployee'/>" method="POST" id="updateEmployeeForm">
                    <input type="hidden" id="employeeId" name="employeeId">
                    <div class="form-group">
                        <label for="firstName">Tên</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Họ</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Địa chỉ</label>
                        <input type="text" class="form-control" id="address" name="address" required>
                    </div>
                    <div class="form-group">
                        <label for="birthDate">Ngày sinh</label>
                        <input type="date" class="form-control" id="birthDate" name="birthDate" required>
                    </div>
                    <div class="form-group">
                        <label for="gender">Giới tính</label>
                        <select class="form-control" id="gender" name="gender" required>
                            <option value="Nam">Nam</option>
                            <option value="Nữ">Nữ</option>
                            <option value="Khác">Khác</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Số điện thoại</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
                    </div>
                    <div class="form-group">
                        <label for="position">Chức vụ</label>
                        <input type="text" class="form-control" id="position" name="position" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Essential javascripts -->
<script src="<c:url value='fontawesome/js/Admin/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/popper.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/bootstrap.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/Admin/main.js'/>"></script>
<script src="<c:url value='fontawesome/js/plugins/pace.min.js'/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script src="<c:url value='fontawesome/js/plugins/jquery.dataTables.min.js'/>"></script>
<script src="<c:url value='fontawesome/js/plugins/dataTables.bootstrap.min.js'/>"></script>

<script>
    $('#sampleTable').DataTable();

    function deleteEmployee(employeeId) {
        swal({
            title: "Cảnh báo",
            text: "Bạn có chắc chắn là muốn xóa nhân viên này?",
            buttons: ["Hủy bỏ", "Đồng ý"],
        })
            .then((willDelete) => {
                if (willDelete) {
                    window.location.href = "<c:url value='deleteEmployee'/>?id=" + employeeId;
                }
            });
    }

    // Add event listeners to all edit buttons
    $(document).ready(function() {
        $('.edit').click(function() {
            // Get the current row data
            const row = $(this).closest('tr');
            const employeeId = $(this).data('employee-id') || row.find('td:eq(1)').text().replace('#', '');
            const fullName = row.find('td:eq(2)').text().trim();
            const address = row.find('td:eq(3)').text().trim();
            const birthDateStr = row.find('td:eq(4)').text().trim();
            const gender = row.find('td:eq(5)').text().trim();
            const phoneNumber = row.find('td:eq(6)').text().trim();
            const position = row.find('td:eq(7)').text().trim();

            // Split full name into first name and last name
            // Assuming the format is "Last Name First Name"
            const nameArray = fullName.split(' ');
            const firstName = nameArray.pop(); // Last word is the first name
            const lastName = nameArray.join(' '); // Rest is the last name

            // Convert date from DD/MM/YYYY to YYYY-MM-DD for input date format
            let birthDate = '';
            if (birthDateStr) {
                const parts = birthDateStr.split('/');
                if (parts.length === 3) {
                    birthDate = `${parts[2]}-${parts[1]}-${parts[0]}`;
                }
            }

            // Populate the modal form with employee data
            $('#employeeId').val(employeeId);
            $('#firstName').val(firstName);
            $('#lastName').val(lastName);
            $('#address').val(address);
            $('#birthDate').val(birthDate);
            $('#gender').val(gender);
            $('#phoneNumber').val(phoneNumber);
            $('#position').val(position);

            // Show the modal
            $('#ModalUP').modal('show');
        });
    });

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