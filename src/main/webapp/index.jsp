<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="CSS/home.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>OCEAN - NHÀ CUNG CẤP SỈ LẺ TẤT CẢ CÁC LOẠI HẢI SẢN CHÂT LƯỢNG CAO - OCEAN -HOMPAGE </title>
</head>
<body>
<div class="page">
    <jsp:include page="/JspComForHome/header.jsp"/>
    <div class="banner-main carousel baner-page banner-">
        <div class="header-banner-main">
            <div class="banner-header">
                <div class="component-banner-header menu">
                    <div class="icon-text">
                        <div class="icon">
                            <i class="fa-solid fa-bars"></i>
                        </div>
                        <div class="text">
                            <span>DANH MỤC</span>
                        </div>
                    </div>
                    <ul class="drop-menu">
                        <c:forEach items="${listCategory}" var = "o">
                            <li class="sub-menu">${o.cname}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="component-banner-header infor-return-goods">
                    <div class="img-banner">
                        <img src="Img/HomeImg/img-return.jpg" alt="image return goods">
                    </div>
                    <div class="text">
                        <a href="${pageContext.request.contextPath}/Return"><span>ĐỔI TRẢ HÀNG NHANH CHÓNG</span></a>
                    </div>
                </div>
                <div class="component-banner-header infor-ship-2h">
                    <div class="img-banner">
                        <img src="Img/HomeImg/img-ship-hang.png" alt="image ship">
                    </div>
                    <div class="text">
                        <a href="${pageContext.request.contextPath}/DeliveryAndReceipt"><span>GIAO HÀNG ĐƠN 200.000Đ</span></a>
                    </div>
                </div>
                <div class="component-banner-header infor-store-system">
                    <div class="img-banner">
                        <img src="Img/HomeImg/img-chuoi-cua-hang-1.jpg" alt="Image store system">
                    </div>
                    <div class="text">
                        <a href="${pageContext.request.contextPath}/store-system"><span>HỆ THỐNG CỬA HÀNG</span></a>
                    </div>
                </div>
                <div class="component-banner-header infor-program-vip">
                    <div class="img-banner">
                        <img src="Img/HomeImg/vip.jpeg" alt="vip">
                    </div>
                    <div class="text">
                        <a href="${pageContext.request.contextPath}/VIP"><span>CHƯƠNG TRÌNH KH THÂN THIẾT</span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="banner-body">
            <div class="banner-body-left">
                <!-- Banner chứa nội dung chính lớn nhất/ có slider -->
                <div class="banner-body-left-component top">
                    <div class="container">
                        <div class="top-list-img">
                            <div class="items">
                                <img src="Img/Banner/BM1.1.jpg" alt="Giảm giá ">
                            </div>
                            <div class="items">
                                <img src="Img/Banner/BM1.2.jpg" alt="Giảm giá ">
                            </div>
                            <div class="items">
                                <img src="Img/Banner/BM1.3.jpg" alt="Giảm giá ">
                            </div>
                            <div class="items">
                                <img src="Img/Banner/BM1.4.jpg" alt="Giảm giá ">
                            </div>
                        </div>
                        <div class="button-redirect">
                            <button class="button-redirect-left button-redirect-all"><i class="fa-solid fa-chevron-left"></i></button>
                            <button class="button-redirect-right button-redirect-all"><i class="fa-solid fa-chevron-right"></i></button>
                        </div>
                        <ul class="container-dots">
                            <li class="dot-banner-img active"></li>
                            <c:forEach var="i" begin="1" end="${BannerMain1.size()}">
                                <li class="dot-banner-img"></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <!-- Chia banner thành 2 phần banner nhỏ/ không slider -->
                <div class="banner-body-left-component bottom-component">
                    <div class="bottom-left bottom">
                        <a href="">
                            <img src="Img/Banner/BM2.jpg">
                        </a>
                    </div>
                    <div class="bottom-right bottom">
                        <a href="">
                            <img src="Img/Banner/BM3.jpg">
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="banner-body-right">
            <div class="banner-body-right-component top">
                <a href="">
                    <img src="Img/Banner/BM4.jpg">
                </a>
            </div>
            <div class="banner-body-right-component bottom">
                <a href="">
                    <img src="Img/Banner/BM5.jpg">
                </a>
            </div>
        </div>
    </div>
    <div class="banner-foot banner-hot-new">
        <div class="container-list-img">
            <div class="list-img-foot-banner">
                <div class="items-fb items">
                    <a href="">
                        <img src="Img/Banner/BS1.jpg">
                    </a>
                </div>
                <div class="items-fb items">
                    <a href="">
                        <img src="Img/Banner/BS2.jpg">
                    </a>
                </div>
                <div class="items-fb items">
                    <a href="">
                        <img src="Img/Banner/BS3.jpg">
                    </a>
                </div>
            </div>
        </div>
        <div class="button-fbanner">
            <button class="prev-button-fb"><i class="fa-solid fa-chevron-left"></i></button>
            <button class="next-button-fb"><i class="fa-solid fa-chevron-right"></i></button>
        </div>
        <ul class="dots-fbanner">
            <li class="dot-fb active-fb"></li>
            <c:forEach var="i" begin="1" end="${BannerSmall.size()}">
                <li class="dot-fb"></li>
            </c:forEach>
        </ul>
    </div>
    <div class="product-all container-product">
        <div class="flash-sale product-tag">
            <div class="title-flash-sale">
                <div class="component-title-flash-sale text-title-flash-sale">
                    <i class="fa-solid fa-bolt"></i>
                    <span>FLASH SALE</span>
                </div>
                <div class="component-title-flash-sale time-title-flash-sale">
                    <div class="time-title-component text">
                        <span>Kết thúc sau: </span>
                    </div>
                    <div class="time-title-component clock">
                        <div class="clock-component hour">
                            <span>24</span>
                        </div>
                        <div class="clock-component minute">
                            <span>59</span>
                        </div>
                        <div class="clock-component second">
                            <span>59</span>
                        </div>
                    </div>
                </div>
                <script>
                    let countdown = () => {
                        fetch("CountdownFlashSale")
                            .then(response => response.json())
                            .then(data => {
                                let hour = document.querySelector('.time-title-component>.hour span');
                                let minute = document.querySelector('.time-title-component>.minute span');
                                let second = document.querySelector('.time-title-component>.second span');

                                if (!hour || !minute || !second) {
                                    console.error('Một hoặc nhiều selector không tìm thấy phần tử DOM.');
                                    stopIntervalFS();
                                }

                                if (parseInt(data.hours) <= 0 && parseInt(data.minutes) <= 0 && parseInt(data.seconds) <= 0) {
                                    hour.innerHTML = "00";
                                    minute.innerHTML = "00";
                                    second.innerHTML = "00";
                                    stopIntervalFS();
                                    loadFlashSaleData();
                                }

                                hour.innerHTML = data.hours > 9 ? data.hours : '0' + data.hours;
                                minute.innerHTML = data.minutes > 9 ? data.minutes : '0' + data.minutes;
                                second.innerHTML = data.seconds > 9 ? data.seconds : '0' + data.seconds;
                            })
                            .catch(error => console.error("Error fetching array:", error));
                    };

                    countdown();
                    let countdownTime = setInterval(countdown, 1000);
                    function stopIntervalFS() {
                        clearInterval(countdownTime);
                    }
                </script>
                <div class="component-title-flash-sale more-title-flash-sale more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="product-card-flash-sale">
                <div class="container-product-flash-sale row-product">
                </div>
                <div class="button-flash-sale">
                    <button class="prev-button-flash-sale"><i class="fa-solid fa-chevron-left"></i></button>
                    <button class="next-button-flash-sale"><i class="fa-solid fa-chevron-right"></i></button>
                </div>
                <script>
                    function loadFlashSaleData() {
                        $.ajax({
                            url: 'FlashSaleLoadProduct',
                            method: 'GET',
                            success: function(response) {
                                $('.container-product-flash-sale').html(response);
                                updateSliderItems();
                                countdown();
                            },
                            error: function(error) {
                                console.error("Lỗi khi gọi servlet:", error);
                            }
                        });

                    }
                    loadFlashSaleData();
                    // setInterval(loadFlashSaleData,1000)
                </script>
                <script>
                    function updateSliderItems() {
                        let containerFLS = document.querySelector('.container-product-flash-sale');
                        let itemsFLS = document.querySelectorAll(' .product-flash-sale');
                        let buttonPrevFLS = document.querySelector('.prev-button-flash-sale');
                        let buttonNextFLS = document.querySelector('.next-button-flash-sale');
                        let lengItemsFLS = itemsFLS.length;
                        let activeFLS = 0;
                        const resetDurationFLS = 12000;
                        startIntervalFLS();
                        buttonNextFLS.onclick = function () {
                            if (activeFLS < lengItemsFLS - 1) {
                                activeFLS++;
                            } else {
                                activeFLS = 0;
                            }
                            reloadSliderFLS(activeFLS);
                            stopIntervalFLS();
                            timmeOutIntervalFLS();
                        }

                        buttonPrevFLS.onclick = function () {
                            if (activeFLS > 0) {
                                activeFLS--;
                            } else {
                                activeFLS = lengItemsFLS - 1;
                            }
                            reloadSliderFLS(activeFLS);
                            stopIntervalFLS();
                            timmeOutIntervalFLS();
                        }

                        function reloadSliderFLS(activeFLS) {
                            let checkLeftFLS = itemsFLS[activeFLS].offsetLeft;
                            containerFLS.style.left = -checkLeftFLS + 'px';
                        }

                        function startIntervalFLS() {
                            autoSileFLS = setInterval(() => {
                                if (activeFLS < lengItemsFLS - 1) {
                                    activeFLS++;
                                } else {
                                    activeFLS = 0;
                                }
                                reloadSliderFLS(activeFLS);
                            }, 10000);
                        }

                        function stopIntervalFLS() {
                            clearInterval(autoSileFLS);
                        }

                        function timmeOutIntervalFLS() {
                            setTimeout(() => {
                                startIntervalFLS();
                            }, resetDurationFLS);
                        }
                    }
                </script>
            </div>
        </div>
        <style>
            .container-product-categories{
                display: flex;
                flex-wrap: wrap;
            }
        </style>
        <div class="best-saler product-tag">
            <div class="title-product-cover title-best-saler">
                <span>BÁN CHẠY NHẤT</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductBS}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct" name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price">${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="product-recomand product-tag">
            <div class="title-product-cover title-recomand">
                <span>GỢI Ý CHO BẠN</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductForYou}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price">${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="banner-mid-air">
            <img src="Img/Banner/BMi1.jpg" alt="">
        </div>

        <div class="product-promotion product-tag">
            <div class="title-product-cover title-promo-product">
                <span>ĐANG KHUYẾN MÃI</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductOS}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price">${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="product-tom-cua-ghe product-tag">
            <div class="title-product-cover title-tom-cua-ghe title-basic">
                <span>Tôm, Cua, Ghẹ Các Loại</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductCrabShrimp}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price">${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="product-ngao-so-oc product-tag">
            <div class="title-product-cover title-ngao-so-oc title-basic">
                <span>Ngao, Sò, Ốc Các Loại</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductClamsMusselsSnails}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price">${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="banner-mid-air">
            <img src="Img/Banner/BMi2.jpg" alt="">
        </div>

        <div class="product-dong-lanh product-tag">
            <div class="title-product-cover title-dong-lanh title-basic">
                <span>HẢI SẢN ĐÔNG LẠNH</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductFronzen}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price">${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="product-gia-vi product-tag">
            <div class="title-product-cover title-gia-vi title-basic">
                <span>GIA VỊ</span>
                <div class="more-title">
                    <span>Xem thêm...</span>
                </div>
            </div>
            <div class="container-product-categories">
                <c:forEach var="product" items="${listProductSpice}" >
                    <div class="product-flash-sale product-card">
                        <input type="hidden" class="idProduct"  name="idProduct" value="${product.getId()}">
                        <div class="product-image">
                            <c:if test="${product.getImg().size()>0}">
                                <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                            </c:if>
                            <c:if test="${product.getImg().size()<=0}">
                                <img src="Img/ImgProduct/p1.1.jpg" alt="${product.getName()}">
                            </c:if>
                            <span class="promo-badge">BEST SALE</span>
                        </div>
                        <div class="product-info">
                            <h3 class="product-name">${product.getName()}</h3>
                            <p class="product-price black-content">${product.getPrice()}</p>
                            <p class="product-price discount-price-value" >${product.getDiscountPrice()}</p>
                        </div>
                        <div class="product-footer">
                            <button class="add-to-cart">
                                <i class="fa fa-shopping-cart"></i>
                            </button>
                            <div class="promo-offer">Mua 1 Tặng 1 Nước Chấm</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="success-toast" class="toast">
        <p>Thao tác thành công!</p>
    </div>

    <%@ include file="JspComForHome/footer.jsp"%>
    <script src="js/home.js"></script>
</div>
</body>
</html>