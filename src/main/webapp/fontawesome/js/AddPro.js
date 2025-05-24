
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

