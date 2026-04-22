<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.util.EmailUtil" %>
<%
    // Get context params for email
    String smtpHost = getServletContext().getInitParameter("smtpHost");
    String smtpPort = getServletContext().getInitParameter("smtpPort");
    String fromEmail = getServletContext().getInitParameter("user");
    String emailPassword = getServletContext().getInitParameter("pass");
%>
<%
    String message = "";
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodie_app", "root", "");
            
            // Check in users table
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("userName", rs.getString("fullname"));
                session.setAttribute("userEmail", rs.getString("email"));
                session.setAttribute("userRole", "customer");
                
                response.sendRedirect("index.jsp");
                return;
            } else {
                // Check in admin table if not found in users
                PreparedStatement psAdmin = con.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
                psAdmin.setString(1, email);
                psAdmin.setString(2, password);
                ResultSet rsAdmin = psAdmin.executeQuery();
                
                if (rsAdmin.next()) {
                    session.setAttribute("adminId", rsAdmin.getInt("id"));
                    session.setAttribute("userName", rsAdmin.getString("username"));
                    session.setAttribute("userRole", "admin");
                    response.sendRedirect("dashboard.jsp");
                    return;
                } else {
                    message = "Invalid email or password!";
                }
            }
            con.close();
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <div class="auth-container">
        <!-- Left Side: Banner -->
        <div class="auth-banner">
            <div class="auth-banner-overlay">
                <h2>Welcome Back!</h2>
                <p>Discover the best food and drinks in your city.</p>
            </div>
        </div>

        <!-- Right Side: Form -->
        <div class="auth-form-wrapper">
            <div class="auth-form glass-panel">
                <div style="text-align: center; margin-bottom: 2rem;">
                    <a href="index.jsp" class="logo" style="justify-content: center; font-size: 2rem;">
                        <i class="fa-solid fa-utensils"></i> Foodie
                    </a>
                </div>
                
                <h3>Sign In</h3>
                <p>Please login to your account to continue.</p>

                <% if(!message.equals("")) { %>
                    <div style="background: rgba(220, 53, 69, 0.1); color: #dc3545; padding: 10px; border-radius: 8px; margin-bottom: 20px; font-size: 0.9rem; text-align: center; border: 1px solid rgba(220, 53, 69, 0.2);">
                        <i class="fa-solid fa-circle-exclamation" style="margin-right: 8px;"></i> <%= message %>
                    </div>
                <% } %>

                <% if(request.getParameter("msg") != null && request.getParameter("msg").equals("success")) { %>
                    <div style="background: rgba(46, 196, 182, 0.1); color: #2ec4b6; padding: 10px; border-radius: 8px; margin-bottom: 20px; font-size: 0.9rem; text-align: center; border: 1px solid rgba(46, 196, 182, 0.2);">
                        <i class="fa-solid fa-circle-check" style="margin-right: 8px;"></i> Registration successful! You can now login.
                    </div>
                <% } %>

                <form action="login.jsp" method="post">
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email" placeholder=" " required>
                        <label for="email" class="form-label">Email Address</label>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder=" " required>
                        <label for="password" class="form-label">Password</label>
                    </div>

                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                        <label style="display: flex; align-items: center; gap: 8px; color: var(--text-muted); font-size: 0.9rem;">
                            <input type="checkbox" name="remember"> Remember Me
                        </label>
                        <a href="#" style="color: var(--primary); font-size: 0.9rem; font-weight: 500;">Forgot Password?</a>
                    </div>

                    <button type="submit" class="btn btn-primary auth-btn">Login</button>
                </form>

                <p style="text-align: center; margin-top: 30px; margin-bottom: 0;">
                    Don't have an account? <a href="register.jsp" style="color: var(--primary); font-weight: 600;">Sign up</a>
                </p>
            </div>
        </div>
    </div>

</body>
</html>