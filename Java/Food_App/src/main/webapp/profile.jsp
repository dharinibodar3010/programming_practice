<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int userId = (Integer) session.getAttribute("userId");
    UserModel user = Dao.getUserById(userId);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .profile-container {
            padding: 60px 0;
            display: flex;
            gap: 40px;
            align-items: flex-start;
            flex-wrap: wrap;
        }

        .profile-sidebar {
            flex: 1;
            min-width: 300px;
            background: var(--surface-color);
            border-radius: var(--radius-lg);
            padding: 40px 30px;
            box-shadow: var(--shadow-sm);
            text-align: center;
        }

        .profile-main {
            flex: 2;
            min-width: 300px;
            display: flex;
            flex-direction: column;
            gap: 30px;
        }

        .profile-pic-container {
            position: relative;
            width: 150px;
            height: 150px;
            margin: 0 auto 20px;
        }

        .profile-pic {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            object-fit: cover;
            border: 4px solid var(--border-color);
        }

        .edit-pic-btn {
            position: absolute;
            bottom: 5px;
            right: 5px;
            background: var(--primary);
            color: white;
            width: 35px;
            height: 35px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            border: 2px solid white;
            transition: var(--transition);
        }

        .edit-pic-btn:hover {
            transform: scale(1.1);
        }

        .profile-sidebar h2 {
            font-size: 1.5rem;
            color: var(--text-dark);
            margin-bottom: 5px;
        }

        .profile-sidebar p {
            color: var(--text-muted);
            font-size: 1rem;
            margin-bottom: 30px;
        }

        .profile-menu {
            display: flex;
            flex-direction: column;
            gap: 10px;
            text-align: left;
        }

        .profile-menu-item {
            display: flex;
            align-items: center;
            gap: 15px;
            padding: 12px 20px;
            border-radius: var(--radius-md);
            color: var(--text-dark);
            font-weight: 500;
            transition: var(--transition);
            cursor: pointer;
        }

        .profile-menu-item.active, .profile-menu-item:hover {
            background: rgba(255, 107, 53, 0.1);
            color: var(--primary);
        }

        .profile-menu-item i {
            width: 20px;
        }

        .content-card {
            background: var(--surface-color);
            border-radius: var(--radius-lg);
            padding: 40px;
            box-shadow: var(--shadow-sm);
        }

        .content-card h3 {
            font-size: 1.5rem;
            margin-bottom: 25px;
            color: var(--text-dark);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .input-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        @media(max-width: 768px) {
            .input-grid {
                grid-template-columns: 1fr;
            }
        }

        .address-card {
            border: 1px solid var(--border-color);
            border-radius: var(--radius-md);
            padding: 20px;
            position: relative;
        }

        .address-tag {
            position: absolute;
            top: -12px;
            left: 20px;
            background: var(--surface-color);
            padding: 0 10px;
            font-size: 0.85rem;
            font-weight: 600;
            color: var(--primary);
        }
        
    </style>
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>

    <div class="container profile-container">
        
        <!-- Sidebar -->
        <aside class="profile-sidebar glass-panel">
            <div class="profile-pic-container">
                <img src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=200&auto=format&fit=crop" alt="Profile Picture" class="profile-pic">
                <div class="edit-pic-btn" title="Change Picture">
                    <i class="fa-solid fa-camera"></i>
                </div>
            </div>
            <h2><%= user != null ? user.getFullname() : "User" %></h2>
            <p><%= user != null ? user.getEmail() : "" %></p>

            <div class="profile-menu">
                <div class="profile-menu-item active">
                    <i class="fa-solid fa-user"></i> Personal Info
                </div>
                <div class="profile-menu-item">
                    <i class="fa-solid fa-location-dot"></i> Saved Addresses
                </div>
                <div class="profile-menu-item">
                    <i class="fa-solid fa-credit-card"></i> Payment Methods
                </div>
                <div class="profile-menu-item">
                    <i class="fa-solid fa-bell"></i> Notifications
                </div>
                <a href="logout.jsp" class="profile-menu-item" style="color: #dc3545; margin-top: 20px; text-decoration: none;">
                    <i class="fa-solid fa-right-from-bracket"></i> Logout
                </a>
            </div>
        </aside>

        <!-- Main Content -->
        <main class="profile-main">
            
            <!-- Personal Info -->
            <div class="content-card glass-panel">
                <h3>Personal Information <button class="btn btn-outline" style="padding: 6px 16px; font-size: 0.9rem;">Edit</button></h3>
                <form>
                        <div class="form-group">
                            <input type="text" class="form-control" id="fullname" value="<%= user != null ? user.getFullname() : "" %>" readonly>
                            <label for="fullname" class="form-label" style="top: -10px; font-size: 0.8rem; color: var(--primary);">Full Name</label>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="email" value="<%= user != null ? user.getEmail() : "" %>" readonly>
                            <label for="email" class="form-label" style="top: -10px; font-size: 0.8rem; color: var(--primary);">Email Address</label>
                        </div>
                        <div class="form-group">
                            <input type="tel" class="form-control" id="phone" value="<%= user != null ? user.getPhone() : "" %>" readonly>
                            <label for="phone" class="form-label" style="top: -10px; font-size: 0.8rem; color: var(--primary);">Phone Number</label>
                        </div>
                </form>
            </div>

            <!-- Saved Addresses -->
            <div class="content-card glass-panel">
                <h3>Saved Addresses <button class="btn btn-primary" style="padding: 6px 16px; font-size: 0.9rem;"><i class="fa-solid fa-plus"></i> Add New</button></h3>
                
                <div style="display: flex; flex-direction: column; gap: 20px;">
                    <div class="address-card">
                        <span class="address-tag">Home</span>
                        <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                            <div>
                                <p style="font-weight: 600; margin-bottom: 5px; color: var(--text-dark);">Rahul Sharma</p>
                                <p style="color: var(--text-muted); font-size: 0.95rem; line-height: 1.5;">B-104, Sunrise Apartments,<br>CG Road, Navrangpura,<br>Ahmedabad, Gujarat - 380009</p>
                                <p style="color: var(--text-muted); font-size: 0.95rem; margin-top: 10px;"><i class="fa-solid fa-phone"></i> +91 9876543210</p>
                            </div>
                            <div style="display: flex; gap: 10px;">
                                <button style="background:none; border:none; color: var(--primary); cursor: pointer;"><i class="fa-solid fa-pen"></i></button>
                                <button style="background:none; border:none; color: #dc3545; cursor: pointer;"><i class="fa-solid fa-trash"></i></button>
                            </div>
                        </div>
                    </div>

                    <div class="address-card">
                        <span class="address-tag">Office</span>
                        <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                            <div>
                                <p style="font-weight: 600; margin-bottom: 5px; color: var(--text-dark);">Rahul Sharma</p>
                                <p style="color: var(--text-muted); font-size: 0.95rem; line-height: 1.5;">Unit 401, Tech Park Towers,<br>SG Highway,<br>Ahmedabad, Gujarat - 380015</p>
                                <p style="color: var(--text-muted); font-size: 0.95rem; margin-top: 10px;"><i class="fa-solid fa-phone"></i> +91 9876543210</p>
                            </div>
                            <div style="display: flex; gap: 10px;">
                                <button style="background:none; border:none; color: var(--primary); cursor: pointer;"><i class="fa-solid fa-pen"></i></button>
                                <button style="background:none; border:none; color: #dc3545; cursor: pointer;"><i class="fa-solid fa-trash"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </main>

    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
