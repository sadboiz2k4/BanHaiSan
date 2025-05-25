
    document.querySelectorAll('.order').forEach(order => {
    let statusElement = order.querySelector('.status-new');
    if (statusElement) {
    let status = statusElement.innerText.trim();
    console.log(`Trạng thái đơn hàng: ${status}`);
    let cancelButton = order.querySelector('.button-cancel-order button');
    if (status === 'Đang xử lý') {
    cancelButton.style.display = 'block';
    cancelButton.addEventListener('click', function() {
    $.ajax({
    url: 'cancel-order',
    type: 'POST',
    data: {
    orderId: order.id
},
    success: function(response) {
    $('.infor').html(response);
}
})
});
} else {
    cancelButton.style.display = 'none';
}
}
});
