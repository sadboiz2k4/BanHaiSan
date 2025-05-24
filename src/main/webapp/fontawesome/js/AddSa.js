
    // Auto calculate total salary
    $(document).ready(function() {
    $('input[name="baseSalary"], input[name="deduction"], input[name="bonus"]').on('input', function() {
        const baseSalary = parseFloat($('input[name="baseSalary"]').val()) || 0;
        const deduction = parseFloat($('input[name="deduction"]').val()) || 0;
        const bonus = parseFloat($('input[name="bonus"]').val()) || 0;
        const total = baseSalary - deduction + bonus;
        $('input[name="totalSalary"]').val(total);
    });
});

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
        text: "Thêm tiền lương thành công",
        icon: "success",
    }).then(() => {
        window.location.href = "<c:url value='customer'/>";
    });
})
    .catch(error => {
    swal("Lỗi!", "Có lỗi xảy ra khi thêm tiền lương: " + error.message, "error");
});

