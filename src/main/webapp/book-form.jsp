<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>${empty book ? 'Add' : 'Edit'} Book</title>
    <link rel="stylesheet" href="${ctx}/css/style.css">
</head>
<body>
<div class="navbar">
    <h1>&#128218; Book Store Management System</h1>
    <div>Welcome, <b>${sessionScope.user}</b> <a href="${ctx}/logout">Logout</a></div>
</div>

<div class="container" style="max-width:560px">
    <div class="card">
        <h2>${empty book ? 'Add New Book' : 'Edit Book'}</h2>
        <c:if test="${not empty error}"><div class="error">${error}</div></c:if>

        <form action="${ctx}/books" method="post">
            <input type="hidden" name="action" value="${empty book ? 'insert' : 'update'}">
            <c:if test="${not empty book}"><input type="hidden" name="id" value="${book.id}"></c:if>

            <label>Title</label>
            <input type="text" name="title" value="${book.title}" required>
            <label>Author</label>
            <input type="text" name="author" value="${book.author}" required>
            <label>Category</label>
            <input type="text" name="category" value="${book.category}">
            <label>Price (&#8377;)</label>
            <input type="number" step="0.01" min="0" name="price" value="${book.price}" required>
            <label>Quantity</label>
            <input type="number" min="0" name="quantity" value="${book.quantity}" required>

            <button class="btn green" type="submit">${empty book ? 'Save' : 'Update'}</button>
            <a class="btn gray" href="${ctx}/books">Cancel</a>
        </form>
    </div>
</div>
</body>
</html>
