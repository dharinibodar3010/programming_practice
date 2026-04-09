<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.AdminModel, com.model.ContactModel, com.dao.Dao, java.util.List" %>
<%
    AdminModel admin = (AdminModel) session.getAttribute("adminSession");
    if(admin == null) {
        response.sendRedirect("admin_login.jsp");
        return;
    }
    List<ContactModel> queryList = Dao.viewcontact();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Queries - E-Commerce Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<style>
    body { background-color: #f8f9fa; }
    .sidebar { min-height: 100vh; background: #212529; color: white; padding-top: 20px; position: sticky; top: 0; }
    .sidebar a { color: rgba(255,255,255,0.6); text-decoration: none; display: block; padding: 12px 20px; transition: 0.2s; }
    .sidebar a:hover, .sidebar a.active { color: white; background: rgba(255,255,255,0.1); border-left: 4px solid #0d6efd; }
    .query-card { background: #fff; border-radius: 15px; border: none; box-shadow: 0 5px 15px rgba(0,0,0,0.02); transition: 0.3s; margin-bottom: 25px; overflow: hidden; border-left: 5px solid #6c757d; }
    .query-card:hover { transform: translateY(-3px); box-shadow: 0 10px 25px rgba(0,0,0,0.05); }
    .query-card.replied { border-left-color: #198754; }
    .query-header { padding: 20px; background: #fdfdfd; display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #f1f1f1; }
    .query-body { padding: 20px; }
    .reply-box { margin-top: 15px; padding: 15px; background: #f8f9fa; border-radius: 10px; border-left: 3px solid #0d6efd; }
    .badge-status { font-size: 11px; padding: 5px 12px; border-radius: 50px; text-transform: uppercase; letter-spacing: 0.5px; }
</style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar p-0">
            <div class="text-center mb-4 px-3">
                <h5 class="fw-bold mb-0">Admin Panel</h5>
                <small class="text-muted">Customer Service</small>
            </div>
            <a href="admin_dashboard.jsp"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
            <a href="admin_view_products.jsp"><i class="bi bi-box-seam me-2"></i> View Products</a>
            <a href="admin_add_product.jsp"><i class="bi bi-plus-circle me-2"></i> Add Product</a>
            <a href="admin_queries.jsp" class="active"><i class="bi bi-chat-dots me-2"></i> User Queries</a>
            <a href="admin_logout.jsp" class="text-danger mt-5"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
        </div>

        <div class="col-md-10 p-5">
            <h2 class="fw-bold mb-4">Customer Inquiries</h2>
            
            <% if(queryList != null && !queryList.isEmpty()) { 
                for(ContactModel q : queryList) { 
                    boolean isReplied = q.getReply() != null && !q.getReply().isEmpty();
            %>
            <div class="query-card <%= isReplied ? "replied" : "" %>">
                <div class="query-header">
                    <div class="d-flex align-items-center">
                        <div class="rounded-circle bg-light d-flex align-items-center justify-content-center p-2 me-3" style="width: 45px; height: 45px;">
                            <i class="bi bi-person text-secondary fs-5"></i>
                        </div>
                        <div>
                            <h6 class="mb-0 fw-bold"><%= q.getFullname() %></h6>
                            <small class="text-muted"><%= q.getEmail() %> | <%= q.getPhone() %></small>
                        </div>
                    </div>
                    <div>
                        <% if(isReplied) { %>
                            <span class="badge bg-success badge-status"><i class="bi bi-check-lg me-1"></i> Replied</span>
                        <% } else { %>
                            <span class="badge bg-warning text-dark badge-status"><i class="bi bi-clock me-1"></i> Pending Reply</span>
                        <% } %>
                    </div>
                </div>
                <div class="query-body">
                    <p class="mb-0 text-dark"><i class="bi bi-quote fs-4 text-primary opacity-25"></i> <%= q.getFeedback() %></p>
                    
                    <% if(isReplied) { %>
                        <div class="reply-box mt-3">
                            <small class="text-primary fw-bold text-uppercase d-block mb-1">Your Reply:</small>
                            <p class="mb-0 small text-muted"><%= q.getReply() %></p>
                        </div>
                    <% } %>

                    <div class="text-end mt-3">
                        <button class="btn btn-outline-primary btn-sm rounded-pill px-4" type="button" data-bs-toggle="collapse" data-bs-target="#replyForm<%= q.getId() %>">
                            <i class="bi bi-reply me-1"></i> <%= isReplied ? "Update Reply" : "Send Reply" %>
                        </button>
                    </div>

                    <!-- Reply Form Collapse -->
                    <div class="collapse mt-3" id="replyForm<%= q.getId() %>">
                        <form action="AdminReplyServlet" method="post" class="p-3 bg-light rounded-4">
                            <input type="hidden" name="id" value="<%= q.getId() %>">
                            <label class="form-label small fw-bold">Compose Message</label>
                            <textarea name="reply" class="form-control mb-3" rows="3" placeholder="Type your response here..." required><%= isReplied ? q.getReply() : "" %></textarea>
                            <div class="text-end">
                                <button type="submit" class="btn btn-primary btn-sm px-4 rounded-pill">Submit Reply</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <% } } else { %>
            <div class="text-center py-5 text-muted">
                <i class="bi bi-chat-left-dots display-1 opacity-25"></i>
                <p class="mt-3">No user queries yet.</p>
            </div>
            <% } %>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
