
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>list promo</title>
</head>
<body>
    <ul class="promo-container">
    <c:if test="${listPromo != null}">
        <c:forEach var="promo" items="${listPromo}">
            <li class="promo-search">${promo}</li>
        </c:forEach>
    </c:if>
    </ul>
<script>
   liPromo = document.querySelectorAll('.promo-search');
</script>
</body>
</html>
