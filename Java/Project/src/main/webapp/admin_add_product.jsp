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
<title>Add Product - E-Commerce Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<style>
    body { background-color: #f8f9fa; min-height: 100vh; }
    .card-add { border: none; border-radius: 20px; box-shadow: 0 10px 40px rgba(0,0,0,0.05); }
    .form-label { font-weight: 500; font-size: 14px; color: #495057; }
    .form-control, .form-select { border-radius: 10px; padding: 12px; border: 1px solid #dee2e6; }
    .form-control:focus { box-shadow: 0 0 0 4px rgba(13, 110, 253, 0.1); }
    .btn-submit { padding: 12px 30px; border-radius: 10px; font-weight: 600; }
    .sidebar { min-height: 100vh; background: #212529; color: white; padding-top: 20px; }
    .sidebar a { color: rgba(255,255,255,0.6); text-decoration: none; display: block; padding: 12px 20px; }
    .sidebar a:hover, .sidebar a.active { color: white; background: rgba(255,255,255,0.1); border-left: 4px solid #0d6efd; }
</style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar Copy -->
        <div class="col-md-2 sidebar p-0">
             <div class="text-center mb-4 px-3">
                <h5 class="fw-bold mb-0">Admin Panel</h5>
                <small class="text-muted">v1.0</small>
            </div>
            <a href="admin_dashboard.jsp"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
            <a href="admin_view_products.jsp"><i class="bi bi-box-seam me-2"></i> View Products</a>
            <a href="admin_add_product.jsp" class="active"><i class="bi bi-plus-circle me-2"></i> Add Product</a>
            <a href="admin_queries.jsp"><i class="bi bi-chat-dots me-2"></i> User Queries</a>
            <a href="admin_logout.jsp" class="text-danger mt-5"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
        </div>

        <div class="col-md-10 p-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="fw-bold m-0"><i class="bi bi-plus-lg me-2"></i> Add New Product</h2>
                <a href="admin_dashboard.jsp" class="btn btn-outline-secondary btn-sm rounded-pill"><i class="bi bi-arrow-left"></i> Back</a>
            </div>

            <div class="card card-add p-4 p-md-5">
                <form action="AdminAddProductServlet" method="post" enctype="multipart/form-data">
                    <div class="row g-4">
                        <div class="col-md-6">
                            <label class="form-label">Product Name</label>
                            <input type="text" name="p_name" class="form-control" placeholder="e.g. Premium Cotton T-Shirt" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Price ($)</label>
                            <input type="number" step="0.01" name="p_price" class="form-control" placeholder="e.g. 29.99" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Category</label>
                            <select name="category" class="form-select" required>
                                <option value="" disabled selected>Select Category</option>
                                <option value="Men">Men</option>
                                <option value="Women">Women</option>
                                <option value="Kids">Kids</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Product Image</label>
                            <input type="file" name="p_image" class="form-control" accept="image/*" required>
                        </div>
                        <div class="col-12">
                            <label class="form-label">Description</label>
                            <textarea name="p_des" class="form-control" rows="4" placeholder="Enter product detailed description..." required></textarea>
                        </div>
                        <div class="col-12 text-end mt-4">
                            <button type="reset" class="btn btn-light me-2 px-4 rounded-pill">Reset</button>
                            <button type="submit" class="btn btn-primary btn-submit px-5 rounded-pill">Save Product</button>
                        </div>
                    </div>
                </form>
            </div>
            
            <% String msg = (String) request.getAttribute("msg");
               if(msg != null) { %>
                <div class="alert alert-success mt-4 rounded-3"><i class="bi bi-check-circle-fill me-2"></i> <%= msg %></div>
            <% } %>
            <% String error = (String) request.getAttribute("error");
               if(error != null) { %>
                <div class="alert alert-danger mt-4 rounded-3"><i class="bi bi-exclamation-triangle-fill me-2"></i> <%= error %></div>
            <% } %>
        </div>
    </div>
</div>

</body>
</html>
