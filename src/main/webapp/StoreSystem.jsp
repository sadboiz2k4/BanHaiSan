<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="author" content="Ly Thai Minh Khang">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hệ Thống Cửa Hàng</title>
  <link rel="stylesheet" href="<c:url value='/fontawesome/css/StoreSystem.css'/>" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <script src="fontawesome/js/storesystem.js"></script>
  <!-- Add Google Maps JavaScript API -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_GOOGLE_MAPS_API_KEY&callback=initMap" async defer></script>
</head>
<body>
<div class="page">
  <jsp:include page="/JspComForHome/header.jsp"/>
  <div class="breadcrumb-container">
    <div class="breadcrumb">
      <a href="/PageHome/index.html">Trang chủ</a>
      <span> / </span>
      <span>Hệ Thống Cửa Hàng</span>
    </div>
  </div>
  <div class="store-locator-container">
    <div class="store-list">
      <c:forEach items="${stores}" var="store" varStatus="status">
        <div class="store-item"
             onclick="highlightStore(${status.index})"
             data-latitude="${store.latitude}"
             data-longitude="${store.longitude}">
          <div class="icon-location">📍</div>
          <div class="store-details">
            <strong>${store.name}</strong>
            <p>${store.address}</p>
            <div class="icon-phone">📞 ${store.phoneNumber}</div>
          </div>
        </div>
      </c:forEach>
    </div>
    <div class="map-container">
      <div id="map" style="width: 100%; height: 100%;"></div>
    </div>
  </div>
</div>
</div>
<jsp:include page="/JspComForHome/footer.jsp"/>
</div>
</body>
</html>