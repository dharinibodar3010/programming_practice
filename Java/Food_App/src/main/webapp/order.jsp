<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="java.util.List" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String orderIdStr = request.getParameter("id");
    if (orderIdStr == null) {
        response.sendRedirect("order-history.jsp");
        return;
    }
    int orderId = Integer.parseInt(orderIdStr);
    
    // Fetch Order details (using a simple query or existing list logic)
    // For simplicity, we'll fetch items directly
    List<OrderItemModel> items = Dao.getItemsByOrderId(orderId);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .order-details-container {
            padding: 60px 0;
            max-width: 800px;
            margin: 0 auto;
        }
        .order-card {
            background: var(--surface-color);
            padding: 40px;
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-sm);
        }
        .order-header {
            border-bottom: 2px solid var(--border-color);
            padding-bottom: 20px;
            margin-bottom: 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .items-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .items-table th {
            text-align: left;
            padding: 15px;
            border-bottom: 2px solid var(--border-color);
            color: var(--text-muted);
        }
        .items-table td {
            padding: 15px;
            border-bottom: 1px solid var(--border-color);
        }
        .total-row {
            margin-top: 30px;
            text-align: right;
            font-size: 1.5rem;
            font-weight: 700;
            color: var(--text-dark);
        }
    </style>
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="container order-details-container">
        <a href="order-history.jsp" style="display: inline-flex; align-items: center; gap: 8px; color: var(--primary); margin-bottom: 20px; text-decoration: none;">
            <i class="fa-solid fa-arrow-left"></i> Back to History
        </a>

        <div class="order-card">
            <div class="order-header">
                <div>
                    <h2 style="margin-bottom: 5px;">Order #ORD-<%= orderId %></h2>
                    <p style="color: var(--text-muted);">Thank you for ordering with us!</p>
                </div>
                <div class="status-badge" style="background: rgba(46,196,182,0.1); color: #2ec4b6; padding: 8px 15px; border-radius: 20px; font-weight: 600;">
                    Successfully
                </div>
            </div>

            <h3>Ordered Items</h3>
            <table class="items-table">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        double subtotal = 0;
                        if (items != null) {
                            for (OrderItemModel item : items) {
                                ProductModel p = Dao.getProductById(item.getProductId());
                                if (p != null) {
                                    double lineTotal = item.getPrice() * item.getQuantity();
                                    subtotal += lineTotal;
                    %>
                    <tr>
                        <td>
                            <div style="display: flex; align-items: center; gap: 15px;">
                                <img src="<%= p.getImageUrl() %>" style="width: 50px; height: 50px; border-radius: 8px; object-fit: cover;">
                                <b><%= p.getName() %></b>
                            </div>
                        </td>
                        <td>₹<%= (int)item.getPrice() %></td>
                        <td>x<%= item.getQuantity() %></td>
                        <td>₹<%= (int)lineTotal %></td>
                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </tbody>
            </table>

            <div class="total-row">
                <span style="font-size: 1rem; color: var(--text-muted); font-weight: 400;">Total Amount Paid:</span> 
                ₹<%= (int)subtotal %>
            </div>
            
            <div style="margin-top: 40px; text-align: center; color: var(--text-muted); font-size: 0.9rem;">
                <p><i class="fa-solid fa-circle-check" style="color: #2ec4b6; margin-right: 5px;"></i> Your order has been received and is being prepared.</p>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
