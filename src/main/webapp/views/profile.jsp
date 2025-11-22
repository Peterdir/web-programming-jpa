<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<sitemesh:title>Profile</sitemesh:title>

<div class="container mt-4" style="max-width: 600px;">

    <h3 class="mb-4 text-center">Thông tin cá nhân</h3>

    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/profile" 
          method="post" enctype="multipart/form-data" 
          class="border p-4 rounded bg-white shadow-sm">

        <!-- FULLNAME -->
        <div class="mb-3">
            <label class="form-label">Fullname</label>
            <input type="text" 
                   name="fullname" 
                   value="${user.fullname}" 
                   class="form-control" required>
        </div>

        <!-- PHONE -->
        <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" 
                   name="phone" 
                   value="${user.phone}" 
                   class="form-control" required>
        </div>

        <!-- IMAGE PREVIEW -->
        <div class="mb-3">
            <label class="form-label">Ảnh đại diện</label><br>
            
            <img src="${pageContext.request.contextPath}/uploads/${user.images}" 
                 class="img-thumbnail mb-3" 
                 style="width: 150px; height: 150px; object-fit: cover;">
        </div>

        <!-- UPLOAD FILE -->
        <div class="mb-3">
            <label class="form-label">Tải ảnh mới</label>
            <input type="file" name="images" class="form-control">
        </div>

        <button class="btn btn-primary w-100">Cập nhật</button>
    </form>
</div>
