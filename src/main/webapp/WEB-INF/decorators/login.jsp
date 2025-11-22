<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3 class="text-center mt-4">Đăng nhập</h3>

<div class="container" style="max-width: 400px;">
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post"
          class="border p-4 bg-white rounded shadow-sm">

        <div class="mb-3">
            <label>Email:</label>
            <input type="text" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Password:</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <button class="btn btn-primary w-100">Đăng nhập</button>
    </form>
</div>
