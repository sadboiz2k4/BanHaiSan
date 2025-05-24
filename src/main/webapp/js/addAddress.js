
    // Load provinces on page load
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
    url: 'province-load-ss',
    type: 'GET',
    success: function(response) {
    $('#city').html(response);
    console.log(response);
},
    error: function(xhr, status, error) {
    console.error('Error loading provinces:', error);
    console.log(xhr);
}
});

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
