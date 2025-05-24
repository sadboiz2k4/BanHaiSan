<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%--Thư viện JSTL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">--%>
<%--    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>
<%--    <link rel="stylesheet" href="CSS/stylePayment.css">--%>
<%--    <script src="/JS/" defer></script>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Thanh Toán</title>--%>
<%--</head>--%>
<head>
    <link rel="shortcut icon" href="//theme.hstatic.net/1000030244/1001119993/14/favicon.png?v=9013" type="image/png">
    <title>Thanh Toán</title>
    <link rel="stylesheet" href="CSS/payment.css">
    <meta name="description" content="DAOHAISAN - Đơn hàng#1000125773">
    <link href="//theme.hstatic.net/1000030244/1001119993/14/check_out.css?v=9013" rel="stylesheet" type="text/css"
          media="all">
    <script defer="" src="https://stats.hstatic.net/beacon.min.js" hrv-beacon-t="1000030244"></script>
    <script type="text/javascript" async=""
            src="https://app.hstatic.net/display-promotion/script_tag_display_promotion.js?v=1734324321814"></script>
    <script type="text/javascript" async=""
            src="https://hstatic.net/244/1000030244/20/2024/12-16/noname_6fa712dbd12f43eb9fd5b4ffb9446754.js"></script>
    <script type="text/javascript" async=""
            src="https://buyxgety-omni.haravan.com/js/script_tag_production.js?v=1576487994026"></script>
    <script type="text/javascript" async=""
            src="https://app.hstatic.net/socialloginplus/1000030244/script-tag.js?v=1687505664180"></script>
    <script type="text/javascript" async="" src="https://c.o-s.io/258266/tracker.js"></script>
    <script src="https://theme.hstatic.net/1000030244/1001119993/14/main-tracking.js?v=1744309570828"></script>
    <script src="//hstatic.net/0/0/global/jquery.min.js" type="text/javascript"></script>
    <script src="//hstatic.net/0/0/global/api.jquery.js" type="text/javascript"></script>
    <script src="//hstatic.net/0/0/global/jquery.validate.js" type="text/javascript"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2, user-scalable=no">
    <script src="https://app.hstatic.net/displaypromotion/js/jquery3.6.0.min.js"></script>
    <script src="https://app.hstatic.net/displaypromotion/js/tns_2.9.4.min.js"></script>
</head>
<body style="">


<input id="reloadValue" type="hidden" name="reloadValue" value="">
<input id="is_vietnam" type="hidden" value="false">
<input id="is_vietnam_location" type="hidden" value="false">

<div class="banner">
    <div class="wrap">
        <a href="/" class="logo">
            <div class="logo-cus">
                <img src="https://file.hstatic.net/1000030244/file/logo-footer_53381ee73c1047d49f5b14a2425ef6a8.png">
            </div>
        </a>
        <a class="logo-2h" href=""><img
                src="https://file.hstatic.net/1000030244/file/02_52ce44cac9fe4ac3a3183ec685597e25.png"
                alt="logo 2h"></a></div>
</div>

<button class="order-summary-toggle order-summary-toggle-hide">
    <div class="wrap">
        <div class="order-summary-toggle-inner">
            <div class="order-summary-toggle-icon-wrapper">
                <svg width="20" height="19" xmlns="http://www.w3.org/2000/svg" class="order-summary-toggle-icon">
                    <path d="M17.178 13.088H5.453c-.454 0-.91-.364-.91-.818L3.727 1.818H0V0h4.544c.455 0 .91.364.91.818l.09 1.272h13.45c.274 0 .547.09.73.364.18.182.27.454.18.727l-1.817 9.18c-.09.455-.455.728-.91.728zM6.27 11.27h10.09l1.454-7.362H5.634l.637 7.362zm.092 7.715c1.004 0 1.818-.813 1.818-1.817s-.814-1.818-1.818-1.818-1.818.814-1.818 1.818.814 1.817 1.818 1.817zm9.18 0c1.004 0 1.817-.813 1.817-1.817s-.814-1.818-1.818-1.818-1.818.814-1.818 1.818.814 1.817 1.818 1.817z"></path>
                </svg>
            </div>
            <div class="order-summary-toggle-text order-summary-toggle-text-show">
                <span>Hiển thị thông tin đơn hàng</span>
                <svg width="11" height="6" xmlns="http://www.w3.org/2000/svg" class="order-summary-toggle-dropdown"
                     fill="#000">
                    <path d="M.504 1.813l4.358 3.845.496.438.496-.438 4.642-4.096L9.504.438 4.862 4.534h.992L1.496.69.504 1.812z"></path>
                </svg>
            </div>
            <div class="order-summary-toggle-text order-summary-toggle-text-hide">
                <span>Ẩn thông tin đơn hàng</span>
                <svg width="11" height="7" xmlns="http://www.w3.org/2000/svg" class="order-summary-toggle-dropdown"
                     fill="#000">
                    <path d="M6.138.876L5.642.438l-.496.438L.504 4.972l.992 1.124L6.138 2l-.496.436 3.862 3.408.992-1.122L6.138.876z"></path>
                </svg>
            </div>
            <div class="order-summary-toggle-total-recap" data-checkout-payment-due-target="46400000">
                <span class="total-recap-final-price">464,000đ</span>
            </div>
        </div>
    </div>
</button>
<div class="content content-second">
    <div class="wrap">
        <div class="sidebar sidebar-second">
            <div class="sidebar-content">
                <div class="order-summary">
                    <div class="order-summary-sections">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="content">

    <div class="wrap">

        <div class="main">
            <div class="main-header">
                <a href="/" class="logo">
                    <div class="logo-cus">
                        <img src="https://file.hstatic.net/1000030244/file/logo-footer_53381ee73c1047d49f5b14a2425ef6a8.png">

                    </div>
                </a>
            </div>
            <div class="main-content">


                <div class="external_payment_cancel">
                    <div class="section">
                        <div class="section-header os-header">

                            <svg width="50" height="50" xmlns="http://www.w3.org/2000/svg" fill="none" stroke="#000"
                                 stroke-width="2" class="hanging-icon hanging-icon-error xmark">
                                <path class="xmark_circle"
                                      d="M25 49c13.255 0 24-10.745 24-24S38.255 1 25 1 1 11.745 1 25s10.745 24 24 24z"></path>
                                <path class="xmark_check1" d="M16 16l18 18"></path>
                                <path class="xmark_check2" d="M34 16l-18 18"></path>
                            </svg>

                            <div class="os-header-heading">
                                <h2 class="os-header-title">

                                    Thanh toán đang chờ xử lý

                                </h2>
                                <span class="os-order-number">
															Mã đơn hàng #1000125773
														</span>

                            </div>
                        </div>
                    </div>

                    <div class="thank-you-additional-content">
                        <div class="alert-info"><p>ĐẢO&nbsp;sẽ tiến hành xử lý đơn hàng của Bạn. Nếu&nbsp;cảm thấy hài
                            lòng, hãy giúp ĐẢO chia sẻ đến người thân của Bạn&nbsp;nhé. Chúc Bạn có nhiều niềm vui!&nbsp;</p>
                        </div>
                        <script>
                            window.dataLayer = window.dataLayer || [];
                            dataLayer.push({
                                'transactionId': '#1000125773',
                                'transactionAffiliation': 'daohaisan.vn',
                                'transactionTotal': 464000,
                                'transactionTax': 0,
                                'transactionShipping': Haravan.checkout.shipping_rate.price,
                                'transactionProducts': [{
                                    'sku': '1016070388',
                                    'name': 'Bào Ngư Hàn Quốc Sống',
                                    'category': 'HẢI SẢN NGON TOP ĐẦU THẾ GIỚI',
                                    'price': 414000,
                                    'quantity': 6
                                }]
                            });
                            <script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
                        </script>

                        <script>

                            $(document).ready(function () {

                                var noidung = `<br><h3>Thông tin chuyển khoản</h3>
<p>Tên TK: CONG TY TNHH DAO HAI SAN</p>
<p>Số TK: <span class="save-tk" data-tk="3107979979">3107979979</span></p>
<p>Ngân hàng BIDV - Chi nhánh HCM</p>
<p>Vui lòng chuyển khoản theo nội dung sau: MÃ ĐƠN HÀNG - SỐ ĐIỆN THOẠI và gửi thông tin đã thanh toán qua chat Messenger <a href="https://m.me/daohaisanvn">https://m.me/daohaisanvn</a> hoặc thông báo cho DVKH qua Hotline <a href="tel:19000098">19000098</a></p>
<p class="img-qrcode"><img src="https://file.hstatic.net/1000030244/file/5867f38417a7a7f9feb6.jpg" alt="QR thanh toan"/></p>`;

                                if (Haravan.checkout.payment_method_id == 2) {
                                    $('body > div:nth-child(7) > div > div.main > div.main-content > div > div.section.thank-you-checkout-info > div > div > div:nth-child(2) > div > div').append(noidung);

                                    $(document).on('click', '.save-tk', function () {
                                        debugger;
                                        var coupon = $(this).attr('data-tk');
                                        var _this = $(this);
                                        _this.addClass('active');
                                        setTimeout(function () {
                                            _this.removeClass('active');
                                        }, 1000)
                                        navigator.clipboard.writeText(coupon);
                                    })
                                }
                            });

                        </script>

                    </div>

                    <div class="section thank-you-checkout-info">
                        <div class="section-content">
                            <div class="content-box">
                                <div class="content-box-row content-box-row-padding content-box-row-no-border">
                                    <h2>Thông tin đơn hàng</h2>
                                </div>
                                <div class="content-box-row content-box-row-padding">
                                    <div class="section-content">
                                        <div class="section-content-column">
                                            <h3>Thông tin giao hàng</h3>

                                            Nguyễn Hoàng Qui
                                            <br>


                                            0833749081
                                            <br>


                                            <p>


                                                83 đường số 18
                                                <br>


                                                Phường Linh Trung
                                                <br>


                                                Thành phố Thủ Đức
                                                <br>


                                                Hồ Chí Minh
                                                <br>


                                                Vietnam
                                                <br>


                                            </p>


                                            <h3>Phương thức thanh toán</h3>
                                            <p>

                                                Thanh toán online qua cổng VNPay (ATM/Visa/MasterCard/JCB/QR Pay trên
                                                Mobile Banking)

                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="step-footer">

                        <a href="/checkouts/628f7ea7c2a14ce39da596c2aa4fb8a7/payment"
                           class="step-footer-continue-btn btn">
                            <span class="btn-content">Thanh toán lại</span>
                        </a>

                        <p class="step-footer-info">
                            <i class="icon icon-os-question"></i>
                            <span>


														Cần hỗ trợ? <a href="//m.me/daohaisan">Liên hệ chúng tôi</a>
													</span>
                        </p>
                    </div>
                </div>
            </div>

        </div>
        <div class="sidebar">
            <div class="sidebar-content">
                <div class="order-summary order-summary-is-collapsed">
                    <h2 class="visually-hidden">Thông tin đơn hàng</h2>
                    <div class="order-summary-sections">
                        <div class="order-summary-section order-summary-section-product-list"
                             data-order-summary-section="line-items">
                            <table class="product-table">
                                <thead>
                                <tr>
                                    <th scope="col"><span class="visually-hidden">Hình ảnh</span></th>
                                    <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                    <th scope="col"><span class="visually-hidden">Số lượng</span></th>
                                    <th scope="col"><span class="visually-hidden">Giá</span></th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr class="product" data-product-id="1016070388" data-variant-id="1083031413">
                                    <td class="product-image">
                                        <div class="product-thumbnail">
                                            <div class="product-thumbnail-wrapper">
                                                <img class="product-thumbnail-image" alt="Bào Ngư Hàn Quốc Sống"
                                                     src="//product.hstatic.net/1000030244/product/bn_31c802667db84419a4411ea7aab79302_small.jpg">
                                            </div>
                                            <span class="product-thumbnail-quantity" aria-hidden="true">6</span>
                                        </div>
                                    </td>
                                    <td class="product-description">
                                        <span class="product-description-name order-summary-emphasis">Bào Ngư Hàn Quốc Sống</span>

                                        <span class="product-description-variant order-summary-small-text">
																		1 Con
																	</span>

                                    </td>
                                    <td class="product-quantity visually-hidden">6</td>
                                    <td class="product-price">
                                        <span class="order-summary-emphasis">414,000đ</span>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>

                        <div class="order-summary-section order-summary-section-total-lines payment-lines"
                             data-order-summary-section="payment-lines">
                            <table class="total-line-table">
                                <thead>
                                <tr>
                                    <th scope="col"><span class="visually-hidden">Mô tả</span></th>
                                    <th scope="col"><span class="visually-hidden">Giá</span></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="total-line total-line-subtotal">
                                    <td class="total-line-name">Tạm tính</td>
                                    <td class="total-line-price">
															<span class="order-summary-emphasis"
                                                                  data-checkout-subtotal-price-target="41400000">
																414,000đ
															</span>
                                    </td>
                                </tr>
                                <tr class="total-line total-line-shipping">
                                    <td class="total-line-name">Phí vận chuyển</td>
                                    <td class="total-line-price">
															<span class="order-summary-emphasis"
                                                                  data-checkout-total-shipping-target="5000000">
																			50,000đ
															</span>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot class="total-line-table-footer">
                                <tr class="total-line">
                                    <td class="total-line-name payment-due-label">
                                        <span class="payment-due-label-total">Tổng cộng</span>
                                    </td>
                                    <td class="total-line-name payment-due">
                                        <span class="payment-due-currency">VND</span>
                                        <span class="payment-due-price" data-checkout-payment-due-target="46400000">
																464,000đ
															</span>
                                        <span class="checkout_version" display:none="" data_checkout_version="0">
															</span>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {


            var dataDiscount = {
                "data": []
            };

            dataDiscount.data.forEach(function (item) {
                var it = item.discount;
                it.description = getDiscountDescription(it);
                it.highlight_note = getDiscountHighlightNote(it);
                it.enddate = getDiscountExpireDate(it);
            });

            function getDiscountExpireDate(item) {
                var strDate = "HSD: ";
                if (item.enddate != "") {
                    var date = (new Date(item.enddate).toLocaleDateString(Haravan.culture));
                    return strDate.concat(date);
                }
                return "";
            }

            function getDiscountHighlightNote(item) {
                var description = "";
                var applyForObject = "";
                var strDiscountType = "";

                if (item.discount_type == 3) {
                    //shipping
                    if (item.take_type == 1)//amount
                    {
                        var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                        description = "<li>Giảm " + amount + " phí vận chuyển " + "</li>";
                    } else if (item.take_type == 2) //percent
                    {
                        var amount = item.savings / 100;
                        description = "<li>Giảm " + amount + "% phí vận chuyển " + "</li>";
                    }

                } else if (item.discount_type == 4) {
                    //sameprice
                    var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                    description = "<li>Đồng giá " + amount + "</li>";
                } else if (item.discount_type == 5) {
                    //order amount
                    if (item.take_type == 1) {
                        var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                        strDiscountType = " <li>Giảm " + amount + "</li>";
                        description += strDiscountType;
                    } else if (item.take_type == 2) {
                        var amount = item.savings / 100;
                        strDiscountType = "<li>Giảm " + amount + "% giá trị đơn hàng</li>";
                        description += strDiscountType;
                    }

                } else if (item.discount_type == 6) {
                    //product amount
                    if (item.take_type == 1) {
                        var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                        strDiscountType = " <li>Giảm " + amount + "</li>";
                        description += strDiscountType;
                    } else if (item.take_type == 2) {
                        var amount = item.savings / 100;
                        strDiscountType = "<li>Giảm " + amount + "% giá trị sản phẩm</li>";
                        description += strDiscountType;
                    }
                }

                return "<ul>" + description + "</ul>";
            }


            function getDiscountDescription(item) {
                var description = "";
                var resultDiscount = "";
                var str_max_amount_apply = "";
                var applyQuantityOrderValue = "";
                var str_LimitUsedCustomer = "";

                var applyForCustomers = "";
                var applyForProducts = "";
                var applyForProvinces = "";

                if (item.max_amount_apply > 0) {
                    var max_amount_apply = Haravan.formatMoney(item.max_amount_apply, "{{amount}}đ");
                    str_max_amount_apply = "<li>Tối đa " + max_amount_apply + "</li>";

                }

                if (item.customer_limit_used > 0) {
                    str_LimitUsedCustomer = "<li>Mỗi khách hàng chỉ được áp dụng " + item.customer_limit_used + " lần</li>";

                }

                if (item.product_quantity > 0) {
                    var strQuantity = "<li>Mua tối thiểu " + item.product_quantity + " sản phẩm </li>";
                    applyQuantityOrderValue += strQuantity;
                }

                if (item.order_over > 0) {
                    var strAmount = "<li>Mua tối thiểu " + Haravan.formatMoney(item.order_over, "{{amount}}đ") + "</li>";
                    applyQuantityOrderValue += strAmount;
                }

                if (item.province_apply_type == 2) {
                    //product_prerequisite
                    applyForProvinces += "<li>Tỉnh thành áp dụng ";

                    var lstProvinces = "";
                    item.entitled_provinces.data.forEach(function (objData) {
                        var obj = objData.province;
                        var str = obj.province_name + ", ";
                        lstProvinces += str;
                    })
                    if (lstProvinces.length > 2)
                        lstProvinces = lstProvinces.substring(0, lstProvinces.length - 2);
                    applyForProvinces += lstProvinces + " </li>";
                }

                if (item.product_apply_type == 2) {
                    //product_prerequisite
                    applyForProducts += "<li>Sản phẩm ";

                    var lstProducts = "";
                    item.entitled_products.data.forEach(function (objData) {
                        var obj = objData.product;
                        var str = "<a target='blank' href='" + obj.product_url + "'>" + obj.product_title + "</a> <br/>";
                        lstProducts += str;
                    })

                    applyForProducts += lstProducts + " </li>";
                } else if (item.product_apply_type == 3) {
                    //collection_prerequisite
                    applyForProducts += "<li>Nhóm sản phẩm ";

                    var lstCollections = "";
                    item.entitled_collections.data.forEach(function (objData) {
                        var obj = objData.collection;
                        var str = "<a target='blank' href='" + obj.collection_url + "'>" + obj.collection_title + "</a> <br/>";
                        lstCollections += str;
                    })

                    applyForProducts += lstCollections + " </li>";
                } else if (item.product_apply_type == 4) {
                    //variant_prerequisite
                    applyForProducts += "<li>Biến thể của sản phẩm";

                    var lstVariants = "";
                    item.entitled_variants.data.forEach(function (objData) {

                        var obj = objData.variant;
                        var str = "<a target='blank' href='" + obj.product_url + "'>" + obj.product_title + " (" + obj.variant_title + ")" + "</a> <br/>";
                        lstVariants += str;
                    })

                    applyForProducts += lstVariants + " </li>";
                }

                if (item.customer_apply_type == 2) {
                    //customer_prerequisite
                    applyForCustomers = "<li>Khách hàng được chỉ định</li>";
                } else if (item.customer_apply_type == 3) {
                    //customersegment_prerequisite
                    applyForCustomers = "<li>Nhóm khách hàng được chỉ định</li>";
                }


                if (item.discount_type == 4) {
                    //sameprice
                    var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                    resultDiscount = "<li>Đồng giá " + amount + "</li>";

                } else if (item.discount_type == 3) {
                    //shipping
                    if (item.take_type == 1) {
                        //amount
                        var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                        resultDiscount = "<li>Giảm " + amount + " phí vận chuyển" + "</li>";
                        if (item.max_shipping_fee_apply > 0) {
                            var max_shipping_fee_apply = Haravan.formatMoney(item.max_shipping_fee_apply, "{{amount}}đ");
                            var subItem = "<li>Không áp dụng nếu phí vận chuyển vượt quá " + max_shipping_fee_apply + "</li>";
                            resultDiscount = resultDiscount + subItem;
                        }
                    } else {
                        //percentage
                        var amount = item.savings / 100;
                        resultDiscount = "<li>Giảm " + amount + "% phí vận chuyển" + "</li>";
                        if (item.max_shipping_fee_apply > 0) {
                            var max_shipping_fee_apply = Haravan.formatMoney(item.max_shipping_fee_apply, "{{amount}}đ");
                            var subItem = "<li>Không áp dụng nếu phí vận chuyển vượt quá " + max_shipping_fee_apply + "</li>";
                            resultDiscount = resultDiscount + subItem;
                        }

                    }
                } else if (item.discount_type == 5) {
                    //order amount
                    if (item.take_type == 1) {
                        //amount
                        var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                        resultDiscount = "<li>Giảm " + amount + " giá trị đơn hàng" + "</li>";

                    } else {
                        var amount = item.savings / 100;
                        resultDiscount = "<li>Giảm " + amount + "% giá trị đơn hàng" + "</li>";
                    }
                } else if (item.discount_type == 6) {

                    //product amount
                    if (item.take_type == 1) {
                        //amount
                        var amount = Haravan.formatMoney(item.savings, "{{amount}}đ");
                        resultDiscount = "<li>Giảm " + amount + " sản phẩm" + "</li>";
                        var str_on_every_item = "";
                        if (item.on_every_item == 'true') {
                            str_on_every_item = "<li>Áp dụng cho từng sản phẩm trong giỏ hàng</li>";
                        } else {
                            str_on_every_item = "<li>Áp dụng 1 lần cho toàn bộ đơn hàng</li>";
                        }
                        resultDiscount += str_on_every_item;
                    } else {
                        var amount = item.savings / 100;
                        resultDiscount = "<li>Giảm " + amount + "% sản phẩm" + "</li>";
                    }


                }
                description += resultDiscount;
                description += str_max_amount_apply;
                description += applyQuantityOrderValue;
                description += str_LimitUsedCustomer;
                description += applyForCustomers;
                description += applyForProducts;
                description += applyForProvinces;
                return "<ul>" + description + "</ul>";
            }


            function togglePopupCoupons() {
                if ($('.hrv-coupons-popup').hasClass('active-popup') && $('.hrv-coupons-popup-site-overlay').hasClass('active-popup')) {
                    $('.hrv-coupons-popup').removeClass('active-popup');
                    $('.hrv-coupons-popup-site-overlay').removeClass('active-popup');
                    $('[class*="hrv-discount-code-"]').removeClass('open-more');
                    if ($(window).width() < 768) {
                        $('body').css('overflow', '');
                    }
                } else {
                    $('.hrv-coupons-popup').addClass('active-popup');
                    $('.hrv-coupons-popup-site-overlay').addClass('active-popup');

                    if ($(window).width() < 768) {
                        $('body').css('overflow', 'hidden');
                    }
                }
            }

            function renderCoupons() {
                var htmlItemCoupons = "";
                var hiddenClass = "";
                $.each(dataDiscount.data, function (i, v) {
                    var couponItem = v.discount;
                    if (i >= 2) hiddenClass = 'hidden';
                    /*
                    var htmlItemCoupon = `<div class="coupon_item







                    ${hiddenClass}">
		                        <div class="coupon_icon pos-relative">
									<div class="icon-svg">
										<svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 56 56"><defs><style>.cls-1{fill:#10a9f7;}.cls-2{fill:#1c70f7;}.cls-3{fill:#1f26f7;}</style></defs><path class="cls-1" d="M32.67,4.67A7,7,0,0,0,28,6.49a7,7,0,1,0-4.67,12.18h9.34a7,7,0,0,0,0-14ZM21,11.67a2.34,2.34,0,1,1,4.67,0V14H23.33A2.34,2.34,0,0,1,21,11.67ZM32.67,14H30.33V11.67A2.34,2.34,0,1,1,32.67,14Z"/><path class="cls-2" d="M44.54,25.67H11.87A2.33,2.33,0,0,0,9.54,28V49a2.36,2.36,0,0,0,2.36,2.36H44.52A2.35,2.35,0,0,0,46.87,49V28A2.33,2.33,0,0,0,44.54,25.67Z"/><path class="cls-3" d="M42,14H14a7,7,0,0,0-7,7v8.49a.84.84,0,0,0,.84.84H48.16a.84.84,0,0,0,.84-.84V21A7,7,0,0,0,42,14Z"/><path class="cls-1" d="M25.67,14h4.66V51.33H25.67Z"/></svg>
									</div>
									<div class="coupon_body">
										<div class="coupon_head">

											<h3 class="coupon_title"> <span>







                    ${couponItem.name}</span></h3>
											<div class="coupon_desc_short">







                    ${couponItem.highlight_note}</div>
											<div class="coupon_desc">







                    ${couponItem.description}</div>`;
		if(couponItem.description != '') htmlItemCoupon += `<div class="coupon_more">Xem chi tiết</div>`;
			htmlItemCoupon +=				`<div class="coupon_exp">







                    ${couponItem.enddate}</div>
										</div>
										<button class="btn btn_apply_line_coupon" data-code="







                    ${couponItem.name}">Áp dụng</button>
									</div>
								</div>
							  </div>`;
        */

                    var htmlItemCoupon = `<div class="coupon_item ${hiddenClass}">
		                        <div class="coupon_icon pos-relative">
									<div class="coupon_body">

										 <div class="coupon_head">
											<div class="icon-svg">
												<svg width="48" height="48" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
<path d="M28.4325 45.5026C27.418 45.5026 26.5115 44.7611 25.631 44.0411C25.093 43.6006 24.3555 42.9976 23.9995 42.9976C23.644 42.9976 22.9065 43.6006 22.3685 44.0411C21.404 44.8296 20.31 45.7261 19.1055 45.4501C17.8625 45.1676 17.254 43.8606 16.717 42.7081C16.449 42.1316 16.043 41.2601 15.7545 41.1206C15.4485 40.9711 14.4985 41.2016 13.87 41.3526C12.6465 41.6471 11.2605 41.9791 10.2805 41.1976C9.297 40.4121 9.3145 38.9816 9.331 37.7201C9.339 37.0766 9.351 36.1046 9.144 35.8446C8.9395 35.5886 7.993 35.3861 7.3665 35.2521C6.1275 34.9871 4.7245 34.6866 4.1745 33.5466C3.6325 32.4241 4.265 31.1511 4.822 30.0281C5.1115 29.4461 5.5485 28.5656 5.472 28.2281C5.403 27.9256 4.65 27.3411 4.152 26.9541C3.1435 26.1721 2 25.2841 2 24.0001C2 22.7161 3.1435 21.8286 4.1525 21.0456C4.6505 20.6591 5.4035 20.0746 5.4725 19.7716C5.549 19.4341 5.112 18.5536 4.823 17.9711C4.2655 16.8481 3.6335 15.5756 4.176 14.4521C4.726 13.3126 6.129 13.0121 7.3675 12.7471C7.994 12.6131 8.9405 12.4106 9.1445 12.1551C9.3525 11.8946 9.3405 10.9221 9.3325 10.2786C9.3165 9.01706 9.299 7.58706 10.282 6.80156C11.2615 6.01956 12.6485 6.35256 13.8715 6.64656C14.501 6.79756 15.45 7.02606 15.7555 6.87906C16.0445 6.73956 16.45 5.86856 16.7185 5.29156C17.255 4.13856 17.863 2.83206 19.106 2.54956C20.309 2.27656 21.405 3.16956 22.369 3.95856C22.9075 4.39906 23.645 5.00206 24.0005 5.00206C24.356 5.00206 25.0935 4.39906 25.6315 3.95906C26.5965 3.16956 27.69 2.27456 28.895 2.54956C30.1375 2.83206 30.746 4.13906 31.283 5.29206C31.5515 5.86856 31.957 6.74006 32.246 6.87956C32.5525 7.02706 33.501 6.79806 34.1295 6.64706C35.353 6.35306 36.7395 6.02006 37.7195 6.80206C38.703 7.58756 38.6855 9.01756 38.669 10.2796C38.661 10.9231 38.649 11.8956 38.856 12.1551C39.0605 12.4106 40.007 12.6136 40.6335 12.7476C41.8725 13.0126 43.2755 13.3126 43.8255 14.4526C44.3675 15.5756 43.735 16.8486 43.178 17.9716C42.8885 18.5541 42.4515 19.4341 42.528 19.7716C42.597 20.0746 43.35 20.6591 43.848 21.0456C44.8565 21.8286 46 22.7161 46 24.0001C46 25.2841 44.8565 26.1716 43.8475 26.9546C43.3495 27.3411 42.5965 27.9251 42.5275 28.2291C42.451 28.5666 42.888 29.4466 43.177 30.0291C43.7345 31.1521 44.3665 32.4246 43.824 33.5481C43.274 34.6876 41.871 34.9881 40.6325 35.2526C40.006 35.3871 39.0595 35.5896 38.855 35.8451C38.6475 36.1056 38.6595 37.0781 38.6675 37.7216C38.6835 38.9826 38.701 40.4126 37.718 41.1981C36.7385 41.9801 35.3515 41.6466 34.1285 41.3531C33.499 41.2021 32.55 40.9726 32.2445 41.1211C31.9555 41.2601 31.55 42.1316 31.2815 42.7081C30.745 43.8616 30.137 45.1681 28.894 45.4506C28.738 45.4861 28.584 45.5026 28.4325 45.5026ZM15.445 39.0751C15.8595 39.0751 16.257 39.1421 16.6235 39.3186C17.555 39.7676 18.051 40.8331 18.5305 41.8636C18.768 42.3736 19.262 43.4346 19.5485 43.4996C19.815 43.5466 20.685 42.8331 21.102 42.4921C22.001 41.7571 22.93 40.9971 23.9995 40.9971C25.069 40.9971 25.9985 41.7576 26.897 42.4921C27.314 42.8331 28.158 43.5326 28.4515 43.4996C28.737 43.4341 29.2315 42.3736 29.4685 41.8636C29.9475 40.8331 30.4435 39.7686 31.3755 39.3186C32.3235 38.8611 33.4775 39.1396 34.595 39.4081C35.1325 39.5371 36.253 39.8066 36.469 39.6346C36.6885 39.4596 36.674 38.3021 36.667 37.7461C36.6525 36.6001 36.6375 35.4156 37.2915 34.5966C37.9415 33.7826 39.096 33.5356 40.213 33.2966C40.76 33.1796 41.8975 32.9361 42.022 32.6786C42.138 32.4371 41.629 31.4111 41.3845 30.9181C40.872 29.8856 40.3415 28.8176 40.5765 27.7856C40.804 26.7846 41.7275 26.0676 42.62 25.3746C43.103 25.0006 44 24.3046 44 24.0001C44 23.6956 43.103 22.9996 42.621 22.6251C41.728 21.9321 40.8045 21.2156 40.577 20.2141C40.3425 19.1821 40.873 18.1141 41.3855 17.0816C41.63 16.5886 42.1395 15.5631 42.023 15.3211C41.8985 15.0631 40.761 14.8201 40.2145 14.7031C39.0975 14.4641 37.9425 14.2171 37.2925 13.4036C36.639 12.5851 36.654 11.4001 36.6685 10.2546C36.6755 9.69856 36.69 8.54056 36.4705 8.36556C36.2555 8.19356 35.1345 8.46306 34.596 8.59206C33.479 8.86056 32.325 9.13856 31.3765 8.68106C30.445 8.23156 29.949 7.16656 29.4695 6.13656C29.232 5.62656 28.7385 4.56556 28.4515 4.50056C28.1765 4.45306 27.3145 5.16706 26.8975 5.50756C25.999 6.24306 25.07 7.00306 24.0005 7.00306C22.931 7.00306 22.002 6.24256 21.103 5.50756C20.686 5.16656 19.816 4.45606 19.5485 4.50006C19.2625 4.56506 18.768 5.62606 18.531 6.13606C18.0515 7.16606 17.556 8.23106 16.6245 8.68056C15.6765 9.13756 14.5225 8.86056 13.4045 8.59156C12.867 8.46206 11.7455 8.19306 11.5305 8.36456C11.311 8.54006 11.3255 9.69756 11.3325 10.2536C11.347 11.3996 11.362 12.5846 10.708 13.4026C10.058 14.2166 8.9035 14.4636 7.7865 14.7026C7.2395 14.8196 6.102 15.0631 5.9775 15.3206C5.8615 15.5621 6.3705 16.5881 6.615 17.0811C7.1275 18.1136 7.658 19.1816 7.423 20.2136C7.1955 21.2146 6.272 21.9316 5.3795 22.6246C4.897 22.9996 4 23.6956 4 24.0001C4 24.3046 4.897 25.0011 5.379 25.3751C6.272 26.0681 7.1955 26.7841 7.423 27.7856C7.6575 28.8176 7.127 29.8851 6.6145 30.9181C6.37 31.4111 5.8605 32.4366 5.977 32.6786C6.1015 32.9371 7.239 33.1801 7.786 33.2971C8.9025 33.5361 10.0575 33.7831 10.7075 34.5971C11.361 35.4156 11.346 36.6001 11.3315 37.7461C11.3245 38.3021 11.31 39.4601 11.5295 39.6351C11.7445 39.8046 12.8655 39.5376 13.404 39.4081C14.0885 39.2431 14.788 39.0751 15.445 39.0751Z" fill="#F47560"/>
<path d="M18.207 32.207L32.207 18.207C32.5975 17.8165 32.5975 17.1835 32.207 16.793C31.8165 16.4025 31.1835 16.4025 30.793 16.793L16.793 30.793C16.4025 31.1835 16.4025 31.8165 16.793 32.207C16.9885 32.4025 17.244 32.5 17.5 32.5C17.756 32.5 18.0115 32.4025 18.207 32.207ZM19.5 23C17.5705 23 16 21.43 16 19.5C16 17.57 17.5705 16 19.5 16C21.4295 16 23 17.57 23 19.5C23 21.43 21.4295 23 19.5 23ZM19.5 18C18.673 18 18 18.673 18 19.5C18 20.327 18.673 21 19.5 21C20.327 21 21 20.327 21 19.5C21 18.673 20.327 18 19.5 18ZM29.5 33C27.5705 33 26 31.4295 26 29.5C26 27.5705 27.5705 26 29.5 26C31.4295 26 33 27.5705 33 29.5C33 31.4295 31.4295 33 29.5 33ZM29.5 28C28.673 28 28 28.673 28 29.5C28 30.327 28.673 31 29.5 31C30.327 31 31 30.327 31 29.5C31 28.673 30.327 28 29.5 28Z" fill="#F47560"/>
</svg>
											</div>
											<h3 class="coupon_title">
												<span>${couponItem.name}</span>
												<div class="coupon_exp">${couponItem.enddate}</b></div>
											</h3>
										 </div>`;
                    if (couponItem.highlight_note != '') htmlItemCoupon += `<div class="coupon_desc_short">${couponItem.highlight_note}</div>`;
                    if (couponItem.description != '') htmlItemCoupon += `<div class="coupon_desc">${couponItem.description}</div>`;
                    htmlItemCoupon += `			 <div class="coupon_actions">
										    <div class="coupon_more">Xem chi tiết<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" x="0px" y="0px" viewBox="0 0 330 330" style="enable-background:new 0 0 330 330;" xml:space="preserve"><path id="XMLID_225_" d="M325.607,79.393c-5.857-5.857-15.355-5.858-21.213,0.001l-139.39,139.393L25.607,79.393 c-5.857-5.857-15.355-5.858-21.213,0.001c-5.858,5.858-5.858,15.355,0,21.213l150.004,150c2.813,2.813,6.628,4.393,10.606,4.393 s7.794-1.581,10.606-4.394l149.996-150C331.465,94.749,331.465,85.251,325.607,79.393z"></path><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g></svg></div>
										    <button class="btn btn_apply_line_coupon" data-code="${couponItem.name}">Áp dụng</button>
										 </div>
									</div>
								 </div>
							  </div>`;

                    htmlItemCoupons = htmlItemCoupons + htmlItemCoupon;
                });

                if (hiddenClass != '') {
                    var htmlMoreCoupon = `<div class="text-center">
								<button id="show_all_coupon">
									<span>Xem thêm</span>
									<span id="show_all_icon">
									<svg width="12" height="12" viewBox="0 0 12 12" xmlns="http://www.w3.org/2000/svg">
<path d="M11.3337 5.99984L10.3937 5.05984L6.66699 8.77984V0.666504H5.33366V8.77984L1.61366 5.05317L0.666992 5.99984L6.00033 11.3332L11.3337 5.99984Z"/>
</svg>
                                    </span>
								</button>
							  </div>`;
                    htmlItemCoupons += htmlMoreCoupon;
                }
                $('.hrv-discount-code-web').html(htmlItemCoupons);
            }

            $(document).on('click', '#show_all_coupon', function () {
                var coupons = $('.coupon_item').length;
                var parentDOM = $(this).parents('div[class*="hrv-discount-code-"]');
                if (!parentDOM.hasClass('open-more')) {
                    parentDOM.find('.coupon_item').removeClass('hidden');
                    parentDOM.addClass('open-more');
                    $(this).find('span:first-child').html('Thu gọn');
                } else {
                    parentDOM.find('.coupon_item:nth-child(n+3):nth-child(-n+' + coupons + ')').addClass('hidden');
                    parentDOM.removeClass('open-more');
                    $(this).find('span:first-child').html('Xem thêm');
                }
            });

            $('body').on('click', '.order-summary-section-display-discount .hrv-discount-choose-coupons', function () {
                togglePopupCoupons();
                renderCoupons();
            })

            $('body').on('click', '.hrv-coupons-close-popup svg', function () {
                togglePopupCoupons();
            })

            $('body').on('click', '.hrv-discount-code-web .btn_apply_line_coupon', function () {
                var codeCoupons = $(this).data('code');
                togglePopupCoupons();
                $('input[id="discount.code"]').val(codeCoupons).trigger("change");
                $('form#form_discount_add button.field-input-btn.btn.btn-default').removeClass('btn-disabled');
                $('form#form_discount_add button.field-input-btn.btn.btn-default').trigger('click');
            })

            $('body').on('click', '.coupon_layer', function () {
                $(this).siblings('.btn_apply_line_coupon').trigger('click');
            });

            $(document).on('click', '.coupon_more', function () {
                $(this).parent().siblings('.coupon_desc_short').toggleClass('close');
                $(this).toggleClass('open');
            });

            $(document).on('click', '.hrv-coupons-popup-site-overlay', function () {
                togglePopupCoupons();
            });
        })


        var isFirstLoadTime = true;
        var siteKey = "";
        var isUseCaptchaCheckout = false

        if (isUseCaptchaCheckout == true) {

        }


    </script>


    <script>
        window[window.TiktokAnalyticsObject].instance("CHQ6P3RC77U2H86LQ3EG").track("CompletePayment", {
            "pixelMethod": "standard",
            "dynamic_parameter_config": {},
            "eb_version": "v1",
            "is_standard_mode": "1"
        });
    </script>

    <script>
        window[window.TiktokAnalyticsObject].instance("CHQ6P3RC77U2H86LQ3EG").track("InitiateCheckout", {
            "pixelMethod": "standard",
            "dynamic_parameter_config": {},
            "eb_version": "v1",
            "is_standard_mode": "1"
        });
    </script>

    <script type="text/javascript" charset="">!function (d, g, e) {
        d.TiktokAnalyticsObject = e;
        var a = d[e] = d[e] || [];
        a.methods = "page track identify instances debug on off once ready alias group enableCookie disableCookie".split(" ");
        a.setAndDefer = function (b, c) {
            b[c] = function () {
                b.push([c].concat(Array.prototype.slice.call(arguments, 0)))
            }
        };
        for (d = 0; d < a.methods.length; d++) a.setAndDefer(a, a.methods[d]);
        a.instance = function (b) {
            b = a._i[b] || [];
            for (var c = 0; c < a.methods.length; c++) a.setAndDefer(b, a.methods[c]);
            return b
        };
        a.load = function (b, c) {
            var f = "https://analytics.tiktok.com/i18n/pixel/events.js";
            a._i = a._i || {};
            a._i[b] = [];
            a._i[b]._u = f;
            a._t = a._t || {};
            a._t[b] = +new Date;
            a._o = a._o || {};
            a._o[b] = c || {};
            c = document.createElement("script");
            c.type = "text/javascript";
            c.async = !0;
            c.src = f + "?sdkid\x3d" + b + "\x26lib\x3d" + e;
            b = document.getElementsByTagName("script")[0];
            b.parentNode.insertBefore(c, b)
        };
        a.load("CHQ6P3RC77U2H86LQ3EG");
        a.page()
    }(window, document, "ttq");</script>

    <script type="text/javascript" id="" charset="">!function (b, e, f, g, a, c, d) {
        b.fbq || (a = b.fbq = function () {
            a.callMethod ? a.callMethod.apply(a, arguments) : a.queue.push(arguments)
        }, b._fbq || (b._fbq = a), a.push = a, a.loaded = !0, a.version = "2.0", a.queue = [], c = e.createElement(f), c.async = !0, c.src = g, d = e.getElementsByTagName(f)[0], d.parentNode.insertBefore(c, d))
    }(window, document, "script", "https://connect.facebook.net/en_US/fbevents.js");
    fbq("init", "167657550403674");
    fbq("set", "agent", "gtmss", "167657550403674");
    fbq("track", google_tag_manager["rm"]["1985666"](21), {}, {eventID: google_tag_manager["rm"]["1985666"](23)});</script>
    <iframe height="0" width="0" style="display: none; visibility: hidden;"></iframe>
    <iframe height="0" width="0" style="display: none; visibility: hidden;"></iframe>
    <iframe allow="join-ad-interest-group" data-tagging-id="AW-962989924" data-load-time="1744309572178" height="0"
            width="0"
            src="https://td.doubleclick.net/td/rul/962989924?random=1744309572176&amp;cv=11&amp;fst=1744309572176&amp;fmt=3&amp;bg=ffffff&amp;guid=ON&amp;async=1&amp;gtm=45be5491v9116117576z871985666za201zb71985666&amp;gcd=13l3l3l3l1l1&amp;dma=0&amp;tag_exp=102509682~102788824~102803279~102813109~102887800~102926062~102975949~103021830~103027016~103047562~103050889~103051953~103055464&amp;u_w=1536&amp;u_h=864&amp;url=https%3A%2F%2Fdaohaisan.vn%2Fcheckouts%2F628f7ea7c2a14ce39da596c2aa4fb8a7%2Fthank_you&amp;ref=https%3A%2F%2Fdaohaisan.vn%2Fcart&amp;hn=www.googleadservices.com&amp;frm=0&amp;tiba=DAOHAISAN%20-%20%C4%90%C6%A1n%20h%C3%A0ng%231000125773&amp;npa=0&amp;pscdl=noapi&amp;auid=2014023460.1741236782&amp;uaa=x86&amp;uab=64&amp;uafvl=Google%2520Chrome%3B135.0.7049.43%7CNot-A.Brand%3B8.0.0.0%7CChromium%3B135.0.7049.43&amp;uamb=0&amp;uam=&amp;uap=Windows&amp;uapv=10.0.0&amp;uaw=0&amp;fledge=1&amp;_tu=Cg"
            style="display: none; visibility: hidden;"></iframe>
    <iframe allow="join-ad-interest-group" data-tagging-id="AW-962989924/0dilCMONiNoDEOSemMsD"
            data-load-time="1744309572184" height="0" width="0"
            src="https://td.doubleclick.net/td/rul/962989924?random=1744309572180&amp;cv=11&amp;fst=1744309572180&amp;fmt=3&amp;bg=ffffff&amp;guid=ON&amp;async=1&amp;gcl_ctr=31&amp;gtm=45be5491v9116117576z871985666za201zb71985666&amp;gcd=13l3l3l3l1l1&amp;dma=0&amp;tag_exp=102509682~102788824~102803279~102813109~102887800~102926062~102975949~103021830~103027016~103047562~103050889~103051953~103055464&amp;u_w=1536&amp;u_h=864&amp;url=https%3A%2F%2Fdaohaisan.vn%2Fcheckouts%2F628f7ea7c2a14ce39da596c2aa4fb8a7%2Fthank_you&amp;ref=https%3A%2F%2Fdaohaisan.vn%2Fcart&amp;label=0dilCMONiNoDEOSemMsD&amp;hn=www.googleadservices.com&amp;frm=0&amp;tiba=DAOHAISAN%20-%20%C4%90%C6%A1n%20h%C3%A0ng%231000125773&amp;value=0&amp;bttype=purchase&amp;npa=0&amp;pscdl=noapi&amp;auid=2014023460.1741236782&amp;uaa=x86&amp;uab=64&amp;uafvl=Google%2520Chrome%3B135.0.7049.43%7CNot-A.Brand%3B8.0.0.0%7CChromium%3B135.0.7049.43&amp;uamb=0&amp;uam=&amp;uap=Windows&amp;uapv=10.0.0&amp;uaw=0&amp;fledge=1&amp;capi=1&amp;_tu=Cg&amp;ct_cookie_present=0"
            style="display: none; visibility: hidden;"></iframe>
    <iframe allow="join-ad-interest-group" data-tagging-id="AW-962989924/tFNpCOr3hLUZEOSemMsD"
            data-load-time="1744309582089" height="0" width="0"
            src="https://td.doubleclick.net/td/rul/962989924?random=1744309582086&amp;cv=11&amp;fst=1744309582086&amp;fmt=3&amp;bg=ffffff&amp;guid=ON&amp;async=1&amp;gcl_ctr=32&amp;gtm=45be5491v9116117576z871985666za201zb71985666&amp;gcd=13l3l3l3l1l1&amp;dma=0&amp;tag_exp=102509682~102788824~102803279~102813109~102887800~102926062~102975949~103021830~103027016~103047562~103050889~103051953~103055464&amp;u_w=1440&amp;u_h=659&amp;url=https%3A%2F%2Fdaohaisan.vn%2Fcheckouts%2F628f7ea7c2a14ce39da596c2aa4fb8a7%2Fthank_you&amp;ref=https%3A%2F%2Fdaohaisan.vn%2Fcart&amp;label=tFNpCOr3hLUZEOSemMsD&amp;hn=www.googleadservices.com&amp;frm=0&amp;tiba=DAOHAISAN%20-%20%C4%90%C6%A1n%20h%C3%A0ng%231000125773&amp;value=0&amp;bttype=purchase&amp;npa=0&amp;pscdl=noapi&amp;auid=2014023460.1741236782&amp;uaa=x86&amp;uab=64&amp;uafvl=Google%2520Chrome%3B135.0.7049.43%7CNot-A.Brand%3B8.0.0.0%7CChromium%3B135.0.7049.43&amp;uamb=0&amp;uam=&amp;uap=Windows&amp;uapv=10.0.0&amp;uaw=0&amp;fledge=1&amp;capi=1&amp;_tu=Cg&amp;ct_cookie_present=0"
            style="display: none; visibility: hidden;"></iframe>
</body>
</html>