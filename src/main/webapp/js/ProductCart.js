function showSuccessMessage() {
    let toast = document.getElementById("success-toast");
    toast.style.display = "block";
    setTimeout(function() {
        toast.style.display = "none";
    }, 500);
}
document.querySelectorAll('.product-card').forEach(card => {
    card.querySelector('.product-image').addEventListener('click', () => {
        let idProduct = card.querySelector('#idProduct').value;
        window.location.href = 'product-details?productID='+idProduct;
    });
    card.querySelector('.product-info').addEventListener('click', () => {
        let idProduct = card.querySelector('#idProduct').value;
        window.location.href = 'product-details?productID='+idProduct;
    });
    card.querySelector('.product-footer>.add-to-cart').addEventListener('click',() =>{
        let idProduct = card.querySelector('#idProduct').value;
        console.log(idProduct);
        $.ajax({
            url: 'add-cart',
            method: 'POST',
            data: {
                productID: idProduct
            },
            success: function(response) {
                console.log(response);
                if(response === 'login'){
                    console.log('login')
                    window.location.href = 'login.jsp';
                }
                showSuccessMessage();
            },
            error: function(error) {
                console.error("Lỗi khi gọi servlet:", error);
            }
        });
    })
});