<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>Books - Book Store</title>
    <link rel="stylesheet" href="${ctx}/css/style.css">
</head>
<body>
<div class="navbar">
    <h1>&#128218; Book Store Management System</h1>
    <div>Welcome, <b>${sessionScope.user}</b> <a href="${ctx}/logout">Logout</a></div>
</div>

<div class="container">
    <c:if test="${not empty msg}"><div class="msg">${msg}</div></c:if>

    <div class="stats">
        <div class="stat"><b><fmt:formatNumber value="${stats[0]}" maxFractionDigits="0"/></b>Total Titles</div>
        <div class="stat"><b><fmt:formatNumber value="${stats[1]}" maxFractionDigits="0"/></b>Books in Stock</div>
        <div class="stat"><b>&#8377; <fmt:formatNumber value="${stats[2]}" maxFractionDigits="2"/></b>Inventory Value</div>
    </div>

    <div class="card">
        <div class="toolbar">
            <form action="${ctx}/books" method="get">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" placeholder="Search title, author, category" value="${param.keyword}">
                <button class="btn" type="submit">Search</button>
                <a class="btn gray" href="${ctx}/books">Reset</a>
            </form>
            <a class="btn green" href="${ctx}/books?action=new">+ Add Book</a>
        </div>

        <table>
            <tr><th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Price (&#8377;)</th><th>Qty</th><th>Actions</th></tr>
            <c:forEach var="b" items="${books}">
                <tr>
                    <td>${b.id}</td>
                    <td>${b.title}</td>
                    <td>${b.author}</td>
                    <td>${b.category}</td>
                    <td><fmt:formatNumber value="${b.price}" minFractionDigits="2"/></td>
                    <td>${b.quantity}</td>
                    <td>
                        <a class="btn" href="${ctx}/books?action=edit&id=${b.id}">Edit</a>
                        <a class="btn red" href="${ctx}/books?action=delete&id=${b.id}"
                           onclick="return confirm('Delete this book?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty books}"><tr><td colspan="7" style="text-align:center">No books found.</td></tr></c:if>
        </table>
    </div>
</div>
</body>
</html>
