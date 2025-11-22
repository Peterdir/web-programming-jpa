<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    .navbar-custom {
        background: linear-gradient(90deg, #4e54c8, #8f94fb);
    }
    .navbar-custom .nav-link,
    .navbar-custom .navbar-brand {
        color: #fff !important;
        transition: 0.3s ease;
    }
    .navbar-custom .nav-link:hover,
    .navbar-custom .navbar-brand:hover {
        color: #ffe082 !important;
    }
    .navbar-custom .btn-outline-light:hover {
        background-color: #fff;
        color: #4e54c8;
        border-color: #fff;
    }
    .navbar-custom .dropdown-menu {
        border-radius: 10px;
        border: none;
        box-shadow: 0 3px 10px rgba(0,0,0,0.15);
    }
    .navbar-custom img {
        border: 2px solid #fff;
    }
</style>

<nav class="navbar navbar-expand-lg navbar-custom shadow-sm sticky-top mb-4">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">
            <i class="fas fa-store me-2"></i>My Website
        </a>

        <button class="navbar-toggler text-white" type="button" data-bs-toggle="collapse" data-bs-target="#navbarWeb" aria-controls="navbarWeb" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarWeb">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/user/home">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sản phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Liên hệ</a>
                </li>
            </ul>

            <ul class="navbar-nav ms-auto align-items-center">
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-white d-flex align-items-center" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <c:if test="${not empty sessionScope.account.images}">
                                    <c:url value="/image?fname=${sessionScope.account.images}" var="imgUrlHeader"></c:url>
                                    <img src="${imgUrlHeader}" class="rounded-circle me-2" style="width: 32px; height: 32px; object-fit: cover;">
                                </c:if>
                                <c:if test="${empty sessionScope.account.images}">
                                    <i class="fas fa-user-circle fa-lg me-2"></i>
                                </c:if>
                                <span>${sessionScope.account.fullname}</span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile"><i class="fas fa-id-badge me-2 text-primary"></i>Hồ sơ cá nhân</a></li>
                                <li><a class="dropdown-item" href="#"><i class="fas fa-shopping-cart me-2 text-primary"></i>Đơn hàng</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt me-2"></i>Đăng xuất</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="btn btn-outline-light me-2" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-light text-primary fw-bold" href="${pageContext.request.contextPath}/register">Đăng ký</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
