function showSuccessMessage() {
    let toast = document.getElementById("success-toast");
    toast.style.display = "block";
    setTimeout(function () {
        toast.style.display = "none";
    }, 500);
}

document.querySelectorAll('.product-card').forEach(card => {
    card.querySelector('.product-image').addEventListener('click', () => {
        let idProduct = card.querySelector('.idProduct').value;
        window.location.href = 'product-details?productID=' + idProduct;
    });
    card.querySelector('.product-info').addEventListener('click', () => {
        let idProduct = card.querySelector('.idProduct').value;
        window.location.href = 'product-details?productID=' + idProduct;
    });
    card.querySelector('.product-footer>.add-to-cart').addEventListener('click', () => {
        let idProduct = card.querySelector('.idProduct').value;
        console.log(idProduct);
        $.ajax({
            type: 'POST',
            url: 'add-cart',
            data: {productID: idProduct},
            dataType: 'json',
            success: function (response) {
                if (response.status === "login") {
                    alert("Bạn cần đăng nhập để thêm vào giỏ hàng!");
                    window.location.href = 'login.jsp';
                } else if (response.status === "success") {
                    alert(response.message);
                    showAddToCartMessage(response);
                } else {
                    alert("Có lỗi xảy ra: " + response.message);
                }
            },
            error: function (error) {
                console.error("Lỗi khi gọi servlet:", error);
            }
        });
    })
});

let container = document.querySelector('.top-list-img');
let items = document.querySelectorAll('.top-list-img .items');
let dot = document.querySelectorAll('.dot-banner-img');
let buttonPrev = document.querySelector('.button-redirect-left');
let buttonNext = document.querySelector('.button-redirect-right');

let active = 0;
let lengItems = items.length;
const resetDuration = 10000;

startInterval();
buttonNext.onclick = function () {
    if (active < lengItems - 1) {
        active++;
    } else {
        active = 0;
    }
    reloadSlider(active);
    updateDots();
    stopInterval();
    timmeOutInterval();

}

buttonPrev.onclick = function () {
    if (active > 0) {
        active--;
    } else {
        active = lengItems - 1;
    }
    reloadSlider(active);
    updateDots();
    stopInterval();
    timmeOutInterval();
}

function reloadSlider(active) {
    let checkLeft = items[active].offsetLeft;
    container.style.left = -checkLeft + 'px';
}

function updateDots() {
    dot.forEach((d, index) => {
        d.classList.toggle('active', index === active);
    });
}

function startInterval() {
    autoSlide = setInterval(() => {
        if (active < lengItems - 1) {
            active++;
        } else {
            active = 0;
        }
        reloadSlider(active);
        updateDots();
    }, 5000);
}

function stopInterval() {
    clearInterval(autoSlide);
}

function timmeOutInterval() {
    setTimeout(() => {
        startInterval();
    }, resetDuration);
}


let containerFB = document.querySelector('.list-img-foot-banner');
let itemsFB = document.querySelectorAll('.list-img-foot-banner .items-fb');
let dotFB = document.querySelectorAll('.dot-fb');
let buttonPrevFB = document.querySelector('.prev-button-fb');
let buttonNextFB = document.querySelector('.next-button-fb');

let activeFB = 0;
let lengItemsFB = itemsFB.length;
const resetDurationFB = 9000;

startIntervalFB();

buttonNextFB.onclick = function () {
    if (activeFB < lengItemsFB - 1) {
        activeFB++;
    } else {
        activeFB = 0;
    }
    reloadSliderFB(activeFB);
    updateDotsFB();
    stopIntervalFB();
    timmeOutIntervalFB();
}

buttonPrevFB.onclick = function () {
    if (activeFB > 0) {
        activeFB--;
    } else {
        activeFB = lengItemsFB - 1;
    }
    reloadSliderFB(activeFB);
    updateDotsFB();
    stopIntervalFB();
    timmeOutIntervalFB();
}

function reloadSliderFB(activeFB) {
    let checkLeftFB = itemsFB[activeFB].offsetLeft;
    containerFB.style.left = -checkLeftFB + 'px';
}

function updateDotsFB() {
    dotFB.forEach((d, index) => {
        d.classList.toggle('active-fb', index === activeFB);
    });
}

function startIntervalFB() {
    autoSlideFB = setInterval(() => {
        if (activeFB < lengItemsFB - 1) {
            activeFB++;
        } else {
            activeFB = 0;
        }
        reloadSliderFB(activeFB);
        updateDotsFB();
    }, 5000);
}

function stopIntervalFB() {
    clearInterval(autoSlideFB);
}

function timmeOutIntervalFB() {
    setTimeout(() => {
        startIntervalFB();
    }, resetDurationFB);
}

document.querySelectorAll('.drop-menu > .sub-menu').forEach((subMenu, subtt) => {
    subMenu.addEventListener('click', () => {
        window.location.href = 'loadProductFromCategories?category=' + (subtt + 1);
    });
});
