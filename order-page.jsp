<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt hàng</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="CSS/orderPage.css">
</head>
<body>
    <div class="page">
        <%@include file="JspComForHome/toast.jsp"%>
        <div class="link-page">
            <span><a style="font-size: 16px;color: black; text-decoration: none; margin: 10px" href="index.jsp">Trang chủ</a></span>\Đặt hàng
        </div>
        <div class="header-page" style="font-size: 35px; height: 100px; align-content: center; text-align: center">
            <span>ĐẶT HÀNG</span>
        </div>
        <div class="body-page">
            <div class="address">
                <div class="title" style="display: flex; justify-content: space-between; padding-right: 10px">
                    <span>Địa chỉ nhận hàng</span>

                    <div class="btn-change-address">
                        <button style="color: black"><i class="fa-solid fa-chevron-right"></i></button>
                    </div>
                </div>
                <div class="container-address">
                    <input type="hidden" class="id-hidden-address" value="${addressPrimary.getAddressUserID()}">
                    <div class="icon">
                        <i class="fa-solid fa-location-dot"></i>
                    </div>
                    <div class="name-phone-number">
                        <div class="name">
                            <span>${addressPrimary.getFullNameReceiver()}</span>
                        </div>
                        <div class="phone-number">
                            <span>${addressPrimary.getPhoneReceiver()}</span>
                        </div>
                    </div>
                    <div class="address-detail">
                        <p><span>${addressPrimary.getAddress().getStreet()}</span>,
                            <span>${addressPrimary.getAddress().getWardOrCommune()}</span>,
                            <span>${addressPrimary.getAddress().getDistrict()}
                            </span>, <span>${addressPrimary.getAddress().getCityOrProvince()}</span>
                        </p>
                    </div>
                </div>
                <dialog class="dialog-address" >
                    <div class="title">
                        <span>Chọn địa chỉ</span>
                    </div>
                    <div class="body-diaglog">
                        <c:forEach var="address" items="${listAddressUser}">
                            <div class="container-address address-dialog">
                                <input type="hidden" class="id-hidden-address" value="${address.getAddressUserID()}">
                                <div class="icon">
                                    <i class="fa-solid fa-location-dot"></i>
                                </div>
                                <div class="name-phone-number">
                                    <div class="name">
                                        <span>${address.getFullNameReceiver()}</span>
                                    </div>
                                    <div class="phone-number">
                                        <span>${address.getPhoneReceiver()}</span>
                                    </div>
                                </div>
                                <div class="address-detail">
                                    <p><span>${address.getAddress().getStreet()}</span>,
                                        <span>${address.getAddress().getWardOrCommune()}</span>,
                                        <span>${address.getAddress().getDistrict()}
                                        </span>, <span>${address.getAddress().getCityOrProvince()}</span>
                                    </p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="btn" style="display: flex; justify-items: center; gap: 100px">
                        <button class="cancel">Hủy</button>
                        <button class="add-address">Thêm</button>
                    </div>
                    <script>
                        document.querySelector('.add-address').addEventListener('click', function(){
                            window.location.href = 'add-address';
                        });
                    </script>
                </dialog>
                <div class="bg-dark">
                </div>
                <script>
                    document.querySelector('.btn-change-address>button').addEventListener('click', function(){
                        document.querySelector('.bg-dark').style.display = 'block';
                        document.querySelector('.dialog-address').style.display = 'block';
                    });
                    document.querySelectorAll('.address-dialog').forEach(function(item){
                        item.addEventListener('click', function(){
                            document.querySelector('.container-address').innerHTML = item.innerHTML;
                            document.querySelector('.bg-dark').style.display = 'none';
                            document.querySelector('.dialog-address').style.display = 'none';
                        });
                    });
                    document.querySelector('.btn>.cancel').addEventListener('click', function(){
                        document.querySelector('.bg-dark').style.display = 'none';
                        document.querySelector('.dialog-address').style.display = 'none';
                    });
                </script>
            </div>
            <div class="product-order">
                <div class="title">
                    <div class="field-title big-text">
                        <span>Sản Phẩm</span>
                    </div>
                    <div class="field-title name-product">
                        <span>Tên Sản Phẩm</span>
                    </div>
                    <div class="field-title type-product">
                        <span>Loại Sản Phẩm</span>
                    </div>
                    <div class="field-title quantity">
                        <span>Số Lượng</span>
                    </div>
                    <div class="field-title unit-price">
                        <span>Đơn Giá</span>
                    </div>
                    <div class="field-title total-amount">
                        <span>Thành Tiền</span>
                    </div>
                </div>
                <div class="container-product ">
                    <c:forEach var="product" items="${listProduct}" >
                        <div class="product product-card">
                            <input type="hidden" class="id-hidden-product" value="${product.getId()}">
                            <div class="img-product">
                                <c:if test="${product.getImg().size()>0}">
                                    <img src="Img/ImgProduct/${product.getImg().get(0)}" alt="${product.getName()}">
                                </c:if>
                                <c:if test="${product.getImg().size()<=0}">
                                    <img src="Img/ImgProduct/P1.1.jpg" alt="${product.getName()}">
                                </c:if>
                            </div>
                            <div class="name-product">
                                <span>${product.getName()}</span>
                            </div>
                            <div class="type-product">
                                <span>${product.getType()}</span>
                            </div>
                            <div class="quantity quantity-calc">
                                <span>${mapProductIDWQuantity.get(product.getId())}</span>
                            </div>
                            <div class="unit-price unit-price-calc ">
                                <span>${product.getDiscountPrice()}</span>
                            </div>
                            <div class="total-amount total-amount-calc">
                                <span>${product.getDiscountPrice()*mapProductIDWQuantity.get(product.getId())}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="ship-type">
                <div class="title">
                    <span>Phương thức vận chuyển: </span>
                    <div class="change-ship-type">
                        <button style="border: none; background-color: #f8f8f8;
                        font-size: 25px; color: #ec0808; border-radius: 3px">Thay đổi...</button>
                    </div>
                </div>
                <div class="container-ship-type">
                    <div class="ship-type-1 main-ship-type">
                        <input type="hidden" class="id-hidden-ship" value="${shipPrimary.getShipID()}">
                        <div class="img">
                            <img src="https://xaynhanghean.com/images/news/shiphang2.jpg" alt="ship">
                        </div>
                        <div class="name-ship-type">
                            <span class="name">${shipPrimary.getShipName()}</span>
                            <p class="commit">
                                ${shipPrimary.getShipDescription()}
                            </p>
                        </div>
                        <div class="time-ship">
                            Đơn vị vận chuyển: <span class="time">${shipPrimary.getCarrier()} </span><br>
                        </div>
                        <div class="price-ship">
                            <span>${shipPrimary.getShipPrice()}</span>
                        </div>
                    </div>
                </div>
            </div>
            <dialog class="dialog-ship-type" style="padding: 0" >
                <div class="title" style="font-size:25px;height: 50px; color: white; background-color: #2e9ed5;
                        text-align: center; align-content: center">
                    <span>Chọn loại giao hàng</span>
                </div>
                <div class="body-diaglog" style="margin-top: 20px;height: 550px" >
                    <c:forEach var="ship" items="${listShip}">
                        <div class="ship-type-1 product-comp-change" style="cursor: pointer">
                            <input type="hidden" class="id-hidden-ship" value="${ship.getShipID()}">
                            <div class="img">
                                <img src="https://xaynhanghean.com/images/news/shiphang2.jpg" alt="ship">
                            </div>
                            <div class="name-ship-type">
                                <span class="name">${ship.getShipName()}</span>
                                <p class="commit">
                                        ${ship.getShipDescription()}
                                </p>
                            </div>
                            <div class="time-ship">
                                Đơn vị vận chuyển:<br> <span class="time">${ship.getCarrier()} </span><br>
                            </div>
                            <div class="price-ship" style="padding-right: 10px">
                                <span>${ship.getShipPrice()}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="btn-cancel-dialog" style="text-align: center; align-content: center">
                    <button style="width: 100px; height: 50px; font-size: 20px; border: none; cursor: pointer;
                    border-radius: 4px; background-color: #ec0808; color: white">Hủy</button>
                </div>
            </dialog>
            <div class="message-cust">
                <div class="title">
                    <span>Lời nhắn</span>
                </div>
                <div class="massage">
                    <input type="text" name="massageCust" id="massageCust" placeholder="Lưu ý cho cửa hàng.....">
                </div>
            </div>
            <div class="voucher">
                <div class="title">
                    <span>Mã giảm giá</span>
                </div>
                <div class="input-voucher">
                    <input type="text" name="voucherInput" id="voucher-input" placeholder="Nhập mã voucher...">
                    <button></button>
                </div>
            </div>
            <div class="pay-type">
                <div class="title" style="display: flex; justify-content: space-between">
                    <span>Phương thức thanh toán</span>
                    <div class="btn-change-type-pay" >
                        <button>Thay đổi...</button>
                    </div>
                </div>
                <div class="container-pay-type">
                    <input type="hidden" class="id-payment-main" value="${paymentPrimary.getPaymentID()}">
                    <div class="img-pay">
                        <img src="${paymentPrimary.getImgURL()}" alt="">
                    </div>
                    <div class="name">
                        <span>${paymentPrimary.getPaymentName()}</span>
                    </div>

                </div>
                <div class="all-pay-type">
                    <dialog class="dialog-pay-type">
                        <div class="title" style="text-align: center; align-content: center; font-size: 25px
                                            ;height: 50px">
                            <span>Chọn phương thức thanh toán</span>
                        </div>
                        <div class="body-diaglog">
                            <c:forEach var="payment" items="${listPayment}">
                                <div class="container-pay-type">
                                    <input type="hidden" class="id-payment-main" value="${payment.getPaymentID()}">
                                    <div class="img-pay">
                                        <img src="${payment.getImgURL()}" alt="">
                                    </div>
                                    <div class="name">
                                        <span>${payment.getPaymentName()}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="btn-cancel-pay" style="text-align: center">
                            <button>Hủy</button>
                        </div>
                    </dialog>
                </div>
            </div>
            <script>
                document.querySelector('.btn-change-type-pay').addEventListener('click', function(){
                    document.querySelector('.bg-dark').style.display = 'block';
                    document.querySelector('.dialog-pay-type').style.display = 'block';
                });
                document.querySelectorAll('.body-diaglog>.container-pay-type').forEach(function(item){
                    item.addEventListener('click', function(){
                        document.querySelector('.container-pay-type').innerHTML = item.innerHTML;
                        document.querySelector('.bg-dark').style.display = 'none';
                        document.querySelector('.dialog-pay-type').style.display = 'none';
                    });
                });
                document.querySelector('.btn-cancel-pay').addEventListener('click', function(){
                    document.querySelector('.bg-dark').style.display = 'none';
                    document.querySelector('.dialog-pay-type').style.display = 'none';
                });
            </script>
            <div class="total">
                <div class="container-total">
                    <div class="total-product">
                        <div class="total-product-header">
                            <span>Sản phẩm</span>
                        </div>
                        <div class="total-product-money">
                            <span>${totalPrice}</span>
                        </div>
                    </div>
                    <div class="total-ship">
                        <div class="total-product-header">
                            <span>Vận chuyển</span>
                        </div>
                        <div class="total-ship-money">
                            <span>${shipPrimary.getShipPrice()}</span>
                        </div>
                    </div>
                    <div class="total-voucher">
                        <div class="total-product-header">
                            <span>Voucher</span>
                        </div>
                        <div class="total-voucher-money">
                            <span>0</span>
                        </div>
                    </div>
                    <div class="total-all">
                        <div class="total-product-header">
                            <span>Thành tiền</span>
                        </div>
                        <div class="total-all-money">
                            <span>${finalTotal}</span>
                        </div>
                        <script>
                            updateTotal();
                            function updateTotal(){
                                let ftotal = parseInt(document.querySelector('.total-all-money > span').innerHTML);
                                let fship = parseInt(document.querySelector('.total-ship-money > span').innerHTML);
                                let fproduct = parseInt(document.querySelector('.total-product-money > span').innerHTML);
                                let fvoucher = parseInt(document.querySelector('.total-voucher-money > span').innerHTML);
                                let totalFinalFirst = ftotal;
                                document.querySelector('.total-all-money > span').innerHTML = fproduct + fship;
                            }
                        </script>
                    </div>
                </div>
            </div>
            <div class="btn-accept">
                <button>Xác nhận</button>
            </div>
        </div>
        <script>
            const totalFinalFirst = parseInt(document.querySelector('.total-all-money > span').innerHTML) || 0;
            document.querySelector('#voucher-input').addEventListener('change',function (){
               $.ajax({
                     url: 'voucher-check',
                     type: 'POST',
                     data: {
                          voucherCode: $('#voucher-input').val()
                     },
                   success: function (response) {
                       $('.total-voucher-money').html(response);
                          updateTotalByVouher();
                   }

               })
            });
            function updateTotalByVouher(){
                const totalMoneyElement = document.querySelector('.total-all-money > span');
                const voucherMoneyElement = document.querySelector('#voucher-discount-amount');

                if (totalMoneyElement && voucherMoneyElement) {
                    const totalMoney = parseInt(totalMoneyElement.innerHTML) || 0;
                    const voucherMoney = parseInt(voucherMoneyElement.innerHTML) || 0;
                    if(voucherMoney<=0){
                        voucherMoneyElement.innerHTML = 0;
                        totalMoneyElement.innerHTML = totalFinalFirst;
                    }else {
                        console.log(totalMoney);
                        console.log(voucherMoney);
                        totalMoneyElement.innerHTML = totalMoney - voucherMoney;
                    }
                } else {
                    console.error('Không tìm thấy phần tử cần thiết để cập nhật số tiền.');
                }
            }
        </script>

        <script>
            document.querySelector('.change-ship-type>button').addEventListener('click', function(){
                document.querySelector('.bg-dark').style.display = 'block';
                document.querySelector('.dialog-ship-type').style.display = 'block';
            });
            document.querySelectorAll('.product-comp-change').forEach(function(item){
                item.addEventListener('click', function(){
                    document.querySelector('.main-ship-type').innerHTML = item.innerHTML;
                    document.querySelector('.bg-dark').style.display = 'none';
                    document.querySelector('.dialog-ship-type').style.display = 'none';
                    document.querySelector('.total-ship-money').innerHTML = item.querySelector('.price-ship>span').innerHTML;
                    updateTotal();
                });
            });
            document.querySelector('.btn-cancel-dialog>button').addEventListener('click', function(){
                document.querySelector('.bg-dark').style.display = 'none';
                document.querySelector('.dialog-ship-type').style.display = 'none';
            });
        </script>
        <script src="js/showMesssage.js" defer></script>
        <script>
            document.querySelector('.btn-accept>button').addEventListener('click', function(){
                const idAddress = document.querySelector('.id-hidden-address').value;
                console.log(idAddress);
                const idShip = document.querySelector('.id-hidden-ship').value;
                console.log(idShip);
                const idPayment = document.querySelector('.id-payment-main').value;
                console.log(idPayment);
                const massage = document.querySelector('#massageCust').value;
                console.log(massage);
                const voucher = document.querySelector('#voucher-input').value;
                console.log(voucher)
                $.ajax({
                    url: 'proceed-to-order',
                    type: 'POST',
                    data: {
                        idAddress: idAddress,
                        idShip: idShip,
                        idPayment: idPayment,
                        massage: massage,
                        voucherCode: voucher
                    },
                    success: function (response) {
                        if (response === 'success') {
                            showSuccessMessage();
                            window.location.href = 'home';
                        } else {
                            document.querySelector('.success-toast>p').innerHTML = 'Đặt hàng thất bại';
                            showSuccessMessage();
                        }
                    }
                });
            });
        </script>
    </div>
</body>
</html>
