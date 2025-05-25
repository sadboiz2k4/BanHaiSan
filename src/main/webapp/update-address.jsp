<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Thêm địa chỉ</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="CSS/addAdress.css">
</head>
<body>
    <div class="page">
        <%@ include file="JspComForHome/header.jsp"%>
        <div class="body-page">
            <div class="form-add-address">
                <form action="update-address" method="POST">
                    <div class="container-form">
                        <h1>
                            Cập nhật địa chỉ
                        </h1>
                        <input type="hidden" name="id" value="${address.addressUserID}">
                        <div class="form-group">
                            <label for="name">Họ và tên</label>
                            <input type="text" id="name" name="name" value="${address.fullNameReceiver}" placeholder="Nhập họ và tên">
                        </div>
                        <div class="form-group">
                            <label for="phone">Số điện thoại</label>
                            <input type="text" id="phone" name="phone" value="${address.phoneReceiver}" placeholder="Nhập số điện thoại">
                        </div>
                        <div class="form-group group-city">
                            <label for="city">Tỉnh/Thành phố:</label>
                            <select id="city" name="province">
                                <option value="${address.address.cityOrProvince}">${address.address.cityOrProvince}</option>
                            </select>
                        </div>
                        <div class="form-group group-district">
                            <label for="district">Quận/Huyện:</label>
                            <select id="district" name="district">
                                <option value="${address.address.district}">${address.address.district}</option>
                            </select>
                        </div>
                        <div class="form-group group-ward">
                            <label for="ward">Phường/Xã:</label>
                            <select id="ward" name="ward">
                                <option value="${address.address.wardOrCommune}">${address.address.wardOrCommune}</option>
                            </select>
                        </div>
                        <div class="form-group group-street">
                            <label for="street">Đường:</label>
                            <select id="street" name="street">
                                <option value="${address.address.street}">${address.address.street}</option>
                            </select>
                        </div>
                        <div class="form-group group-street">
                            <label for="address-type">Loại địa chỉ:</label>
                            <select id="address-type" name="addressType">
                                <option value="Nhà" ${address.addressType == 'Nhà' ? 'selected' : ''}>Nhà</option>
                                <option value="Công ty" ${address.addressType == 'Công ty' ? 'selected' : ''}>Công ty</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="address">Địa chỉ cụ thể:</label>
                            <input type="text" id="address" name="address" value="${requestScope.addressDetail}" placeholder="Nhập địa chỉ cụ thể">
                        </div>
                        <div class="form-group set-default">
                            <input type="hidden" name="default-address" value="false" />
                            <input class="checkbox" type="checkbox" id="address1" name="default-address" value="true" ${address.primary ? 'checked' : ''} />
                            <label class="no-width" for="address1">Đặt làm địa chỉ mặc định</label>
                        </div>
                        <div class="form-group btn-sub">
                            <button type="submit">Cập nhật</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
<script>
        // Load districts when province is selected
        $('#city').change(function() {
            const provinceId = $(this).val();
            const $district = $('#district');
            const $ward = $('#ward');
            const $street = $('#street');

            // Reset and disable dependent dropdowns
            $district.html('<option value="">-- Chọn Quận/Huyện --</option>').prop('disabled', !provinceId);
            $ward.html('<option value="">-- Chọn Phường/Xã --</option>').prop('disabled', true);
            $street.html('<option value="">-- Chọn Đường --</option>').prop('disabled', true);

            if (provinceId) {
                $.ajax({
                    url: 'district-load-ss',
                    type: 'GET',
                    data: { provinceId: provinceId },
                    success: function(response) {
                        $district.html(response);
                    },
                    error: function(xhr, status, error) {
                        console.error('Error loading districts:', error);
                    }
                });
            }
        });

        // Load wards when district is selected
        $('#district').change(function() {
            const districtId = $(this).val();
            const $ward = $('#ward');
            const $street = $('#street');

            // Reset and disable dependent dropdowns
            $ward.html('<option value="">-- Chọn Phường/Xã --</option>').prop('disabled', !districtId);
            $street.html('<option value="">-- Chọn Đường --</option>').prop('disabled', true);

            if (districtId) {
                $.ajax({
                    url: 'ward-load-ss',
                    type: 'GET',
                    data: { districtId: districtId },
                    success: function(response) {
                        $ward.html(response);
                    },
                    error: function(xhr, status, error) {
                        console.error('Error loading wards:', error);
                    }
                });
            }
        });

        // Load streets when ward is selected
        $('#ward').change(function() {
            const wardId = $(this).val();
            const $street = $('#street');

            // Reset street dropdown
            $street.html('<option value="">-- Chọn Đường --</option>').prop('disabled', !wardId);

            if (wardId) {
                $.ajax({
                    url: 'street-load-ss',
                    type: 'GET',
                    data: { wardId: wardId },
                    success: function(response) {
                        $street.html(response);
                    },
                    error: function(xhr, status, error) {
                        console.error('Error loading streets:', error);
                    }
                });
            }
        });
</script>
</body>
</html>
