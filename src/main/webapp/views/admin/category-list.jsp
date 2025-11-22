<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="fw-bold">Quản lý Danh mục</h3>
        <a href="${pageContext.request.contextPath}/admin/category/create" class="btn btn-primary">+ Thêm danh mục</a>
    </div>

    <c:if test="${empty listCategory}">
        <div class="alert alert-warning">Chưa có danh mục nào!</div>
    </c:if>

    <c:if test="${not empty listCategory}">
        <table class="table table-bordered table-hover bg-white">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tên danh mục</th>
                    <th>Mã danh mục</th>
                    <th>Hình ảnh</th>
                    <th>Trạng thái</th>
                    <th class="text-center" style="width: 120px;">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${listCategory}">
                    <tr>
                        <td>${c.categoryId}</td>
                        <td>${c.categoryName}</td>
                        <td>${c.categoryCode}</td>
                        <td>
                            <c:if test="${not empty c.images}">
                                <img src="${pageContext.request.contextPath}/uploads/${c.images}" style="width: 60px; height: 60px; object-fit: cover;" />
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${c.status}"><span class="badge bg-success">Hoạt động</span></c:when>
                                <c:otherwise><span class="badge bg-secondary">Ẩn</span></c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/admin/category/edit?id=${c.categoryId}" class="btn btn-sm btn-warning">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/category/delete?id=${c.categoryId}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
