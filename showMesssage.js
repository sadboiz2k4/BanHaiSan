function showSuccessMessage() {
    let toast = document.getElementById("success-toast");
    toast.innerHTML = response.message;
    toast.style.display = "block";

    setTimeout(function () {
        toast.style.display = "none";
    }, 2000);
}

function showAddToCartMessage(response) {
    let toast = document.getElementById("success-toast");
    toast.innerHTML = response.message;
    toast.style.display = "block";

    setTimeout(function() {
        toast.style.display = "none";
    }, 2000);
}