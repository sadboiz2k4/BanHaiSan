<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 1/16/2025
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/CSS/toast.css">
</head>
<body>
<div id="success-toast" class="toast">
  <p>Thao tác thành công!</p>
</div>
<script>
  function showSuccessMessage() {
    let toast = document.getElementById("success-toast");
    toast.style.display = "block";
    setTimeout(function() {
      toast.style.display = "none";
    }, 500);
  }
</script>
</body>
</html>
