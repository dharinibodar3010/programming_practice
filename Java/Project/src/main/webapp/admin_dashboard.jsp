<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.AdminModel" %>
<%
    AdminModel admin = (AdminModel) session.getAttribute("adminSession");
    if(admin == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard - E-Commerce</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<style>
    :root {
        --primary-bg: #f8f9fa;
        --sidebar-bg: #212529;
        --accent-color: #0d6efd;
    }
    body {
        background-color: var(--primary-bg);
        overflow-x: hidden;
    }
    .wrapper {
        display: flex;
        width: 100%;
        align-items: stretch;
    }
    #sidebar {
        min-width: 250px;
        max-width: 250px;
        background: var(--sidebar-bg);
        color: #fff;
        transition: all 0.3s;
        min-height: 100vh;
    }
    #sidebar .sidebar-header {
        padding: 20px;
        background: #1a1e21;
        text-align: center;
        border-bottom: 1px solid #343a40;
    }
    #sidebar ul.components {
        padding: 20px 0;
    }
    #sidebar ul li a {
        padding: 15px 20px;
        font-size: 1.1em;
        display: block;
        color: rgba(255, 255, 255, 0.7);
        text-decoration: none;
        transition: 0.2s;
    }
    #sidebar ul li a:hover {
        color: #fff;
        background: rgba(255, 255, 255, 0.1);
        border-left: 4px solid var(--accent-color);
    }
    #sidebar ul li.active > a {
        color: #fff;
        background: rgba(255, 255, 255, 0.1);
        border-left: 4px solid var(--accent-color);
    }
    #content {
        width: 100%;
        padding: 30px;
        transition: all 0.3s;
    }
    .card-stat {
        border-radius: 15px;
        border: none;
        box-shadow: 0 4px 12px rgba(0,0,0,0.05);
        transition: transform 0.3s;
    }
    .card-stat:hover {
        transform: translateY(-5px);
    }
    .icon-box {
        width: 60px;
        height: 60px;
        border-radius: 12px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 30px;
        margin-bottom: 20px;
    }
    .bg-soft-primary { background: #e7f1ff; color: #0d6efd; }
    .bg-soft-success { background: #e6f9f1; color: #198754; }
    .bg-soft-warning { background: #fff8e6; color: #ffc107; }
</style>
</head>
<body>

<div class="wrapper">
    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h4><i class="bi bi-shield-lock-fill"></i> Admin Panel</h4>
            <small class="text-muted">Welcome, <%= admin.getUsername() %></small>
        </div>

        <ul class="list-unstyled components">
            <li class="active">
                <a href="admin_dashboard.jsp"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
            </li>
            <li>
                <a href="admin_view_products.jsp"><i class="bi bi-box-seam me-2"></i> View Products</a>
            </li>
            <li>
                <a href="admin_add_product.jsp"><i class="bi bi-plus-circle me-2"></i> Add Product</a>
            </li>
            <li>
                <a href="admin_queries.jsp"><i class="bi bi-chat-dots me-2"></i> User Queries</a>
            </li>
            <li>
                <a href="admin_logout.jsp" class="text-danger"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
            </li>
        </ul>
    </nav>

    <!-- Page Content -->
    <div id="content">
        <nav class="navbar navbar-expand-lg navbar-light bg-white rounded-3 shadow-sm mb-4 px-4">
            <span class="navbar-brand mb-0 h1">Dashboard Overview</span>
            <div class="ms-auto">
                <span class="text-muted">Admin: <strong><%= admin.getUsername() %></strong></span>
            </div>
        </nav>

        <div class="row">
            <div class="col-md-4">
                <div class="card card-stat p-4 mb-4">
                    <div class="icon-box bg-soft-primary"><i class="bi bi-box-seam"></i></div>
                    <h5 class="text-muted mb-1">Products</h5>
                    <h3 class="mb-3">Manage Items</h3>
                    <a href="admin_view_products.jsp" class="btn btn-outline-primary btn-sm rounded-pill">View All</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-stat p-4 mb-4">
                    <div class="icon-box bg-soft-success"><i class="bi bi-plus-circle"></i></div>
                    <h5 class="text-muted mb-1">Stock</h5>
                    <h3 class="mb-3">New Product</h3>
                    <a href="admin_add_product.jsp" class="btn btn-outline-success btn-sm rounded-pill">Add Item</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-stat p-4 mb-4">
                    <div class="icon-box bg-soft-warning"><i class="bi bi-chat-dots"></i></div>
                    <h5 class="text-muted mb-1">Messages</h5>
                    <h3 class="mb-3">User Inquiries</h3>
                    <a href="admin_queries.jsp" class="btn btn-outline-warning btn-sm rounded-pill">View Queries</a>
                </div>
            </div>
        </div>

        <!-- Quick Info Card -->
        <div class="card border-0 shadow-sm rounded-4 mt-4 overflow-hidden">
            <div class="card-body p-5 bg-dark text-white position-relative">
                <div class="row align-items-center">
                    <div class="col-md-8">
                        <h2 class="fw-bold mb-3">E-Commerce Management</h2>
                        <p class="text-white-50 lead mb-0">From this dashboard, you can control the inventory, respond to customer feedback, and manage the overall flow of your e-commerce platform.</p>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
