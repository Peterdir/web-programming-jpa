<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý Video</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body { background: #f8f9fa; }
.container { margin-top: 40px; }
.table th, .table td { vertical-align: middle; }
</style>
</head>

<body>
<div class="container">
    <h2 class="mb-4 text-center text-primary">📹 Quản lý Video</h2>

    <div class="d-flex justify-content-between mb-3">
        <form action="${pageContext.request.contextPath}/admin/video" method="get" class="d-flex">
            <input type="text" name="q" value="${keyword}" class="form-control me-2" placeholder="Tìm kiếm video...">
            <button type="submit" class="btn btn-primary">Tìm</button>
        </form>
        <a href="${pageContext.request.contextPath}/admin/video/create" class="btn btn-success">+ Thêm Video</a>
    </div>

    <table class="table table-striped table-bordered align-middle">
        <thead class="table-dark text-center">
            <tr>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Poster</th>
                <th>Lượt xem</th>
                <th>Danh mục</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${videos}">
                <tr>
                    <td class="text-center">${v.videoId}</td>
                    <td>${v.title}</td>
                    <td class="text-center">
                        <c:if test="${not empty v.poster}">
                            <img src="${v.poster}" alt="poster" width="70" class="rounded">
                        </c:if>
                    </td>
                    <td class="text-center">${v.views}</td>
                    <td>${v.category.categoryName}</td>
                    <td class="text-center">
                        <span class="badge ${v.active ? 'bg-success' : 'bg-secondary'}">
                            ${v.active ? 'Hiển thị' : 'Ẩn'}
                        </span>
                    </td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/admin/video/edit?id=${v.videoId}" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/video/delete?id=${v.videoId}" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc muốn xóa video này không?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty videos}">
                <tr><td colspan="7" class="text-center text-muted">Không có video nào được tìm thấy</td></tr>
            </c:if>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">⬅ Quay lại Dashboard</a>
    </div>
</div>
</body>
</html>
