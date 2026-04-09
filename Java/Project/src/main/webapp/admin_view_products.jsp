<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.AdminModel, com.model.ProductModel, com.dao.Dao, java.util.List" %>
<%
    AdminModel admin = (AdminModel) session.getAttribute("adminSession");
    if(admin == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
    List<ProductModel> productList = Dao.viewproducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Products - E-Commerce Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<style>
    body { background-color: #f8f9fa; }
    .sidebar { min-height: 100vh; background: #212529; color: white; padding-top: 20px; }
    .sidebar a { color: rgba(255,255,255,0.6); text-decoration: none; display: block; padding: 12px 20px; transition: 0.2s; }
    .sidebar a:hover, .sidebar a.active { color: white; background: rgba(255,255,255,0.1); border-left: 4px solid #0d6efd; }
    .product-img { width: 80px; height: 80px; object-fit: cover; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
    .table-container { background: #fff; border-radius: 20px; overflow: hidden; box-shadow: 0 10px 40px rgba(0,0,0,0.05); }
    .table thead { background: #f8f9fa; border-bottom: 2px solid #eee; }
    .table th { padding: 20px; font-weight: 600; font-size: 14px; text-transform: uppercase; letter-spacing: 1px; color: #6c757d; }
    .table td { padding: 20px; vertical-align: middle; }
    .badge-category { padding: 8px 15px; border-radius: 50px; font-weight: 600; font-size: 12px; }
</style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar p-0 fixed-top" style="height: 100vh; position: sticky;">
            <div class="text-center mb-4 px-3">
                <h5 class="fw-bold mb-0">Admin Panel</h5>
                <small class="text-muted">Explore Inventory</small>
            </div>
            <a href="admin_dashboard.jsp"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
            <a href="admin_view_products.jsp" class="active"><i class="bi bi-box-seam me-2"></i> View Products</a>
            <a href="admin_add_product.jsp"><i class="bi bi-plus-circle me-2"></i> Add Product</a>
            <a href="admin_queries.jsp"><i class="bi bi-chat-dots me-2"></i> User Queries</a>
            <a href="admin_logout.jsp" class="text-danger mt-5"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
        </div>

        <div class="col-md-10 p-5">
            <div class="d-flex justify-content-between align-items-center mb-5">
                <div>
                    <h2 class="fw-bold m-0 text-dark">Product Inventory</h2>
                    <span class="text-muted">Total <%= productList.size() %> items in stock</span>
                </div>
                <a href="admin_add_product.jsp" class="btn btn-primary px-4 rounded-pill shadow-sm"><i class="bi bi-plus-lg me-2"></i> Add New Product</a>
            </div>

            <div class="table-container">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Product Details</th>
                            <th>Category</th>
                            <th class="text-center">Price</th>
                            <th class="text-end">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if(productList != null && !productList.isEmpty()) { 
                            for(ProductModel p : productList) { %>
                        <tr>
                            <td>
                                <% if(p.getP_image() != null) { %>
                                    <img src="data:image/jpeg;base64,<%= p.getP_image() %>" class="product-img" alt="product">
                                <% } else { %>
                                    <div class="product-img bg-light d-flex align-items-center justify-content-center"><i class="bi bi-image"></i></div>
                                <% } %>
                            </td>
                            <td>
                                <div class="fw-bold text-dark"><%= p.getP_name() %></div>
                                <div class="text-muted small" style="max-width: 300px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><%= p.getP_des() %></div>
                            </td>
                            <td>
                                <span class="badge badge-category bg-soft-primary"><%= p.getCategory() != null ? p.getCategory() : "General" %></span>
                            </td>
                            <td class="text-center fw-bold text-dark">
                                $<%= p.getP_price() %>
                            </td>
                            <td class="text-end">
                                <a href="admin_edit_product.jsp?id=<%= p.getId() %>" class="btn btn-outline-primary btn-sm rounded-circle p-2 mx-1" title="Edit">
                                    <i class="bi bi-pencil"></i>
                                </a>
                                <a href="AdminDeleteProductServlet?id=<%= p.getId() %>" class="btn btn-outline-danger btn-sm rounded-circle p-2 mx-1" title="Delete" onclick="return confirm('Delete this product permanently?')">
                                    <i class="bi bi-trash3"></i>
                                </a>
                            </td>
                        </tr>
                        <% } } else { %>
                        <tr>
                            <td colspan="5" class="text-center py-5">
                                <i class="bi bi-inbox text-muted display-1 mb-3"></i>
                                <p class="lead text-muted">No products found. Start adding some!</p>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
