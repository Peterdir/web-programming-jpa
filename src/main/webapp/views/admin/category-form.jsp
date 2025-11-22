<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm / Sửa Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
<div class="container mt-4">
    <h3 class="fw-bold mb-3">
        <c:choose>
            <c:when test="${not empty category}">Chỉnh sửa danh mục</c:when>
            <c:otherwise>Thêm mới danh mục</c:otherwise>
        </c:choose>
    </h3>

    <form method="post" action="${pageContext.request.contextPath}/admin/category">
        <input type="hidden" name="id" value="${category.categoryId}" />

        <div class="mb-3">
            <label class="form-label">Tên danh mục</label>
            <input type="text" class="form-control" name="categoryName" value="${category.categoryName}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Mã danh mục</label>
            <input type="text" class="form-control" name="categoryCode" value="${category.categoryCode}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Tên ảnh (hoặc URL)</label>
            <input type="text" class="form-control" name="images" value="${category.images}">
        </div>

        <div class="form-check mb-3">
            <input type="checkbox" class="form-check-input" id="status" name="status" value="true" <c:if test="${category.status}">checked</c:if>>
            <label for="status" class="form-check-label">Hoạt động</label>
        </div>

        <div class="d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/admin/category" class="btn btn-secondary">Quay lại</a>
            <button type="submit" class="btn btn-success">Lưu</button>
        </div>
    </form>
</div>
</body>
</html>
