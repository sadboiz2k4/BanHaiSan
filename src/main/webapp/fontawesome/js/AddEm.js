
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

