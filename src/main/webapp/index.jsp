<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Book Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container login-box">
    <div class="card">
        <h2 style="text-align:center;color:#1f3a5f">&#128218; Book Store Login</h2>
        <c:if test="${not empty error}"><div class="error">${error}</div></c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label>Username</label>
            <input type="text" name="username" required autofocus>
            <label>Password</label>
            <input type="password" name="password" required>
            <button class="btn" style="width:100%" type="submit">Login</button>
        </form>
        <p style="text-align:center;color:#777;font-size:13px">Demo: admin / admin123</p>
    </div>
</div>
</body>
</html>
