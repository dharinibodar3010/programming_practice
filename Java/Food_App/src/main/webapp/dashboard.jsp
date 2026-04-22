<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="java.util.List" %>
<%
    if (session.getAttribute("adminId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String adminName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f4f7fa;
        }

        .dashboard-container {
            display: flex;
            min-height: 100vh;
        }

        /* Sidebar Styles */
        .sidebar {
            width: 260px;
            background: #ffffff;
            box-shadow: 2px 0 10px rgba(0,0,0,0.05);
            display: flex;
            flex-direction: column;
            position: fixed;
            height: 100vh;
            z-index: 100;
        }

        .sidebar-header {
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-bottom: 1px solid var(--border-color);
        }

        .sidebar-header .logo {
            font-size: 1.5rem;
            color: var(--primary);
            text-decoration: none;
            font-weight: 700;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .sidebar-menu {
            padding: 20px 0;
            flex: 1;
        }

        .menu-item {
            display: flex;
            align-items: center;
            padding: 15px 25px;
            color: var(--text-muted);
            text-decoration: none;
            transition: var(--transition);
            font-weight: 500;
            gap: 15px;
        }

        .menu-item:hover, .menu-item.active {
            color: var(--primary);
            background: rgba(255, 107, 53, 0.05);
            border-right: 4px solid var(--primary);
        }

        .menu-item i {
            font-size: 1.1rem;
            width: 20px;
            text-align: center;
        }

        /* Main Content Styles */
        .main-content {
            flex: 1;
            margin-left: 260px;
            display: flex;
            flex-direction: column;
        }

        /* Top Header */
        .top-header {
            background: #ffffff;
            height: 70px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 40px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.02);
            position: sticky;
            top: 0;
            z-index: 99;
        }

        .header-search {
            background: var(--bg-color);
            border-radius: var(--radius-pill);
            padding: 8px 20px;
            display: flex;
            align-items: center;
            width: 300px;
        }

        .header-search input {
            border: none;
            background: transparent;
            outline: none;
            padding-left: 10px;
            width: 100%;
        }

        .admin-profile {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .admin-profile img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
        }

        /* Dashboard Body */
        .dashboard-body {
            padding: 40px;
        }

        .welcome-section {
            margin-bottom: 30px;
        }

        .welcome-section h2 {
            font-size: 1.8rem;
            color: var(--text-dark);
            margin-bottom: 5px;
        }

        .welcome-section p {
            color: var(--text-muted);
        }

        /* Stat Cards */
        .stat-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
            gap: 25px;
            margin-bottom: 40px;
        }

        .stat-card {
            background: #ffffff;
            border-radius: var(--radius-md);
            padding: 25px;
            box-shadow: var(--shadow-sm);
            display: flex;
            align-items: center;
            gap: 20px;
            transition: var(--transition);
        }

        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: var(--shadow-md);
        }

        .stat-icon {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5rem;
        }

        .stat-info h4 {
            color: var(--text-muted);
            font-size: 0.9rem;
            margin-bottom: 5px;
            font-weight: 500;
        }

        .stat-info .value {
            font-size: 1.8rem;
            font-weight: 700;
            color: var(--text-dark);
        }

        /* Recent Orders Table */
        .table-card {
            background: #ffffff;
            border-radius: var(--radius-md);
            box-shadow: var(--shadow-sm);
            padding: 25px;
            overflow-x: auto;
        }

        .table-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .table-header h3 {
            font-size: 1.2rem;
            color: var(--text-dark);
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid var(--border-color);
        }

        th {
            color: var(--text-muted);
            font-weight: 600;
            font-size: 0.9rem;
            text-transform: uppercase;
        }

        td {
            color: var(--text-dark);
            font-size: 0.95rem;
        }

        .status {
            padding: 5px 12px;
            border-radius: var(--radius-pill);
            font-size: 0.8rem;
            font-weight: 600;
            display: inline-block;
        }

        .status.completed {
            background: rgba(46, 196, 182, 0.1);
            color: var(--secondary);
        }

        .status.pending {
            background: rgba(255, 193, 7, 0.1);
            color: #ffb300;
        }

        .status.cancelled {
            background: rgba(220, 53, 69, 0.1);
            color: #dc3545;
        }

        .action-btn {
            background: transparent;
            border: none;
            color: var(--primary);
            cursor: pointer;
            font-size: 1rem;
            transition: var(--transition);
        }

        .action-btn:hover {
            transform: scale(1.1);
        }
    </style>
</head>
<body>

    <div class="dashboard-container">
        
        <!-- Sidebar Navigation -->
        <aside class="sidebar">
            <div class="sidebar-header">
                <a href="index.jsp" class="logo">
                    <i class="fa-solid fa-utensils"></i> Foodie<span style="font-size: 0.9rem; font-weight: 400; color: var(--text-muted);">Admin</span>
                </a>
            </div>
            
            <div class="sidebar-menu">
                <a href="dashboard.jsp" class="menu-item active">
                    <i class="fa-solid fa-border-all"></i> Dashboard
                </a>
                <a href="#" class="menu-item">
                    <i class="fa-solid fa-clipboard-list"></i> Orders
                </a>
                <a href="#" class="menu-item">
                    <i class="fa-solid fa-burger"></i> Products
                </a>
                <a href="#" class="menu-item">
                    <i class="fa-solid fa-users"></i> Customers
                </a>
                <a href="#" class="menu-item">
                    <i class="fa-solid fa-chart-line"></i> Analytics
                </a>
                <a href="#" class="menu-item">
                    <i class="fa-solid fa-gear"></i> Settings
                </a>
            </div>

            <div style="padding: 20px; border-top: 1px solid var(--border-color);">
                <a href="logout.jsp" class="menu-item" style="padding: 10px; color: #dc3545;">
                    <i class="fa-solid fa-right-from-bracket"></i> Logout
                </a>
            </div>
        </aside>

        <!-- Main Content Area -->
        <main class="main-content">
            
            <!-- Top Header -->
            <header class="top-header">
                <div class="header-search">
                    <i class="fa-solid fa-search" style="color: var(--text-muted);"></i>
                    <input type="text" placeholder="Search orders, customers...">
                </div>

                <div class="admin-profile">
                    <div style="position: relative; cursor: pointer; margin-right: 15px;">
                        <i class="fa-regular fa-bell" style="font-size: 1.2rem; color: var(--text-muted);"></i>
                        <span style="position: absolute; top: -5px; right: -5px; background: var(--primary); width: 8px; height: 8px; border-radius: 50%;"></span>
                    </div>
                    <div style="text-align: right; line-height: 1.2;">
                        <span style="display: block; font-weight: 600; font-size: 0.9rem; color: var(--text-dark);"><%= adminName %></span>
                        <span style="font-size: 0.8rem; color: var(--text-muted);">Manager</span>
                    </div>
                    <img src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?q=80&w=200&auto=format&fit=crop" alt="Admin Profile">
                </div>
            </header>

            <!-- Dashboard Body -->
            <div class="dashboard-body">
                <div class="welcome-section">
                    <h2>Dashboard Overview</h2>
                    <p>Welcome back, here's what's happening with your restaurant today.</p>
                </div>

                <!-- Stat Cards -->
                <div class="stat-grid">
                    <div class="stat-card">
                        <div class="stat-icon" style="background: rgba(46, 196, 182, 0.1); color: var(--secondary);">
                            <i class="fa-solid fa-indian-rupee-sign"></i>
                        </div>
                        <div class="stat-info">
                            <h4>Total Revenue</h4>
                            <div class="value">₹ <%= (int)Dao.getTotalRevenue() %></div>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon" style="background: rgba(255, 107, 53, 0.1); color: var(--primary);">
                            <i class="fa-solid fa-cart-shopping"></i>
                        </div>
                        <div class="stat-info">
                            <h4>Total Orders</h4>
                            <div class="value"><%= Dao.getTotalOrdersCount() %></div>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon" style="background: rgba(255, 193, 7, 0.1); color: #ffb300;">
                            <i class="fa-solid fa-pizza-slice"></i>
                        </div>
                        <div class="stat-info">
                            <h4>Active Items</h4>
                            <div class="value"><%= Dao.getAllProducts().size() %></div>
                        </div>
                    </div>

                    <div class="stat-card">
                        <div class="stat-icon" style="background: rgba(13, 110, 253, 0.1); color: #0d6efd;">
                            <i class="fa-solid fa-users"></i>
                        </div>
                        <div class="stat-info">
                            <h4>Total Customers</h4>
                            <div class="value"><%= Dao.getTotalUsersCount() %></div>
                        </div>
                    </div>
                </div>

                <!-- Recent Orders -->
                <div class="table-card">
                    <div class="table-header">
                        <h3>Recent Orders</h3>
                        <a href="#" class="btn btn-primary" style="padding: 6px 16px; font-size: 0.85rem;">View All</a>
                    </div>
                    
                    <table>
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer</th>
                                <th>Date & Time</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<OrderModel> allOrders = Dao.getAllOrders();
                                for (OrderModel o : allOrders) {
                                    UserModel u = Dao.getUserById(o.getUserId());
                                    String stat = o.getStatus().toLowerCase();
                            %>
                            <tr>
                                <td><strong>#ORD-<%= o.getId() %></strong></td>
                                <td><%= u != null ? u.getFullname() : "Unknown" %></td>
                                <td>Recent Order</td>
                                <td>₹<%= (int)o.getTotalAmount() %></td>
                                <td><span class="status <%= stat %>"><%= o.getStatus() %></span></td>
                                <td><button class="action-btn"><i class="fa-solid fa-eye"></i></button></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>

            </div>
        </main>
    </div>

</body>
</html>
