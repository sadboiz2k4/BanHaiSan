<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Chỉnh sửa sản phẩm | Quản trị Admin</title>
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
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value='product-admin'/>">Danh sách sản phẩm</a></li>
            <li class="breadcrumb-item"><a href="#">Thêm sản phẩm</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <h3 class="tile-title">Tạo mới sản phẩm</h3>
                <div class="tile-body">
                    <form class="row" action="loadProduct" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="add">


                        <div class="form-group col-md-3">
                            <label class="control-label">Tên sản phẩm</label>
                            <input value="${detail.name}" class="form-control" type="text" name="productName" required>
                        </div>

                        <div class="form-group col-md-3">
                            <label class="control-label">Số lượng</label>
                            <input value="${stock.stock_quantity}"  class="form-control" type="number" name="stock" required>
                        </div>

                        <div class="form-group col-md-3">
                            <label class="control-label">Tình trạng</label>
                            <select class="form-control" name="status" required>
                                <option value="">-- Chọn tình trạng --</option>
                                <option value="1">Còn hàng</option>
                                <option value="0">Hết hàng</option>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label class="control-label">Danh mục</label>
                            <select class="form-control" name="categoryId" required>
                                <option value="">-- Chọn danh mục --</option>
                                <c:forEach items="${listCategory}" var = "o">
                                    <option value="${o.cid}">${o.cname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label class="control-label">Nhà cung cấp</label>
                            <select class="form-control" name="supplierId" required>
                                <option value="">-- Chọn nhà cung cấp --</option>
                                <c:forEach items="${listSupplier}" var="o">
                                    <option value="${o.sid}">${o.sname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col-md-3">
                            <label class="control-label">Giá bán</label>
                            <input value="${detail.price}" class="form-control" type="number" name="price" required>
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label">Ảnh sản phẩm</label>
                            <div id="myfileupload">
                                <input value="${detail.img}" type="file" id="uploadfile" name="imageFile" onchange="readURL(this);" required/>
                            </div>
                            <div id="thumbbox">
                                <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                                <a class="removeimg" href="javascript:"></a>
                            </div>
                            <div id="boxchoice">
                                <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                <p style="clear:both"></p>
                            </div>
                        </div>

                        <div class="form-group col-md-12">
                            <label class="control-label">Mô tả sản phẩm</label>
                            <textarea value="${detail.description}" class="form-control" name="description" id="description"></textarea>
                            <script>CKEDITOR.replace('description');</script>
                        </div>

                        <div class="form-group col-md-12">
                            <button class="btn btn-save" type="submit">Lưu lại</button>
                            <a class="btn btn-cancel" href="<c:url value='product-admin'/>">Hủy bỏ</a>
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

<!-- Image preview script -->
<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#thumbimage').attr('src', e.target.result);
                $('#thumbimage').show();
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(document).ready(function() {
        $(".Choicefile").bind('click', function() {
            $("#uploadfile").click();
        });

        $(".removeimg").click(function() {
            $("#thumbimage").attr('src', '').hide();
            $("#uploadfile").val('');
        });
    });

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
                text: "Thêm sản phẩm thành công",
                icon: "success",
            }).then(() => {
                window.location.href = "<c:url value='customer'/>";
            });
        })
        .catch(error => {
            swal("Lỗi!", "Có lỗi xảy ra khi thêm sản phẩm: " + error.message, "error");
        });

</script>
</body>
</html>