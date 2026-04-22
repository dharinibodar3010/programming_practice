<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%
    String message = "";
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String fullname = request.getParameter("fullname");
        if (fullname == null) {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            fullname = (fname != null ? fname : "") + " " + (lname != null ? lname : "");
        }
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement psCheck = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodie_app", "root", "");
            
            // Check if email already exists
            psCheck = con.prepareStatement("SELECT id FROM users WHERE email = ?");
            psCheck.setString(1, email);
            rs = psCheck.executeQuery();
            
            if (rs.next()) {
                message = "Email already registered! Try logging in.";
            } else {
                // Instead of inserting, redirect to OTP sending page
                String encodedFullname = java.net.URLEncoder.encode(fullname, "UTF-8");
                String redirectUrl = "otpsend.jsp?email=" + email + "&fullname=" + encodedFullname + "&phone=" + phone + "&password=" + password;
                response.sendRedirect(redirectUrl);
                return;
            }
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (psCheck != null) try { psCheck.close(); } catch (SQLException e) {}
            if (psInsert != null) try { psInsert.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }
    }
%>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Register - Foodie</title>
                <link rel="stylesheet" href="css/style.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
                <style>
                    .register-form {
                        max-width: 600px;
                    }

                    .form-row {
                        display: flex;
                        gap: 15px;
                    }

                    .form-row>div {
                        flex: 1;
                    }

                    @media (max-width: 600px) {
                        .form-row {
                            flex-direction: column;
                            gap: 0;
                        }
                    }
                </style>
            </head>

            <body>

                <div class="auth-container">
                    <div class="auth-banner">
                        <div class="auth-banner-overlay">
                            <h2>Join the Family!</h2>
                            <p>Create an account to track your orders and get exclusive offers.</p>
                            <div style="margin-top: 2rem;">
                                <i class="fa-solid fa-truck-fast"
                                    style="font-size: 3rem; margin-bottom: 1rem; color: white; opacity: 0.9;"></i>
                                <p style="font-size: 1rem;">Fastest Delivery Guaranteed</p>
                            </div>
                        </div>
                    </div>

                    <div class="auth-form-wrapper">
                        <div class="auth-form glass-panel register-form">
                            <div style="text-align: center; margin-bottom: 2rem;">
                                <a href="index.jsp" class="logo" style="justify-content: center; font-size: 2rem;">
                                    <i class="fa-solid fa-utensils"></i> Foodie
                                </a>
                            </div>

                            <h3>Create Account</h3>
                            <p>Join Foodie and start ordering your favorites today.</p>

                            <% if(!message.equals("")) { %>
                                <div
                                    style="background: rgba(220, 53, 69, 0.1); color: #dc3545; padding: 10px; border-radius: 8px; margin-bottom: 20px; font-size: 0.9rem; text-align: center; border: 1px solid rgba(220, 53, 69, 0.2);">
                                    <i class="fa-solid fa-circle-exclamation" style="margin-right: 8px;"></i>
                                    <%= message %>
                                </div>
                                <% } %>

                                    <form action="register.jsp" method="post" id="registerForm">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="fullname" name="fullname"
                                                placeholder=" " required value="<%= request.getParameter("fullname") != null ? request.getParameter("fullname") : "" %>">
                                            <label for="fullname" class="form-label">Full Name</label>
                                        </div>

                                        <div class="form-group">
                                            <input type="email" class="form-control" id="email" name="email"
                                                placeholder=" " required value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
                                            <label for="email" class="form-label">Email Address</label>
                                        </div>

                                        <div class="form-group">
                                            <input type="tel" class="form-control" id="phone" name="phone"
                                                placeholder=" " required pattern="[0-9]{10}"
                                                title="Please enter a valid 10-digit phone number"
                                                value="<%= request.getParameter("phone") != null ? request.getParameter("phone") : "" %>">
                                            <label for="phone" class="form-label">Phone Number</label>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group">
                                                <input type="password" class="form-control" id="password"
                                                    name="password" placeholder=" " required minlength="6">
                                                <label for="password" class="form-label">Password</label>
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control" id="confirm_password"
                                                    name="confirm_password" placeholder=" " required>
                                                <label for="confirm_password" class="form-label">Confirm
                                                    Password</label>
                                            </div>
                                        </div>

                                        <button type="submit" class="btn btn-primary auth-btn">Create Account</button>
                                    </form>

                                    <p style="text-align: center; margin-top: 30px; margin-bottom: 0;">
                                        Already have an account? <a href="login.jsp"
                                            style="color: var(--primary); font-weight: 600;">Sign In</a>
                                    </p>
                        </div>
                    </div>
                </div>

                <script>
                    const form = document.getElementById('registerForm');
                    form.addEventListener('submit', function (e) {
                        const password = document.getElementById('password').value;
                        const confirmPassword = document.getElementById('confirm_password').value;
                        if (password !== confirmPassword) {
                            e.preventDefault();
                            alert("Passwords do not match!");
                        }
                    });
                </script>
            </body>

            </html>