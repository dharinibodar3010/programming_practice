<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login - E-Commerce</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        background: linear-gradient(135deg, #1e1e2f 0%, #2d2d44 100%);
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .login-card {
        background: rgba(255, 255, 255, 0.1);
        backdrop-filter: blur(10px);
        border: 1px solid rgba(255, 255, 255, 0.1);
        border-radius: 20px;
        padding: 40px;
        width: 100%;
        max-width: 400px;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        color: white;
    }
    .login-card h2 {
        text-align: center;
        margin-bottom: 30px;
        font-weight: 600;
        letter-spacing: 1px;
    }
    .form-control {
        background: rgba(255, 255, 255, 0.05);
        border: 1px solid rgba(255, 255, 255, 0.2);
        color: white;
        border-radius: 10px;
        padding: 12px;
    }
    .form-control:focus {
        background: rgba(255, 255, 255, 0.1);
        border-color: #6c63ff;
        color: white;
        box-shadow: none;
    }
    .btn-login {
        background: #6c63ff;
        border: none;
        padding: 12px;
        border-radius: 10px;
        font-weight: 600;
        width: 100%;
        margin-top: 20px;
        transition: 0.3s;
    }
    .btn-login:hover {
        background: #5b54d6;
        transform: translateY(-2px);
    }
    .error-msg {
        color: #ff4d4d;
        text-align: center;
        font-size: 14px;
        margin-bottom: 15px;
    }
</style>
</head>
<body>

<div class="login-card">
    <h2>Admin Login</h2>
    
    <% String error = (String) request.getAttribute("error");
       if(error != null) { %>
        <div class="error-msg"><%= error %></div>
    <% } %>

    <form action="AdminLoginServlet" method="post">
        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" name="username" class="form-control" required placeholder="Enter admin username">
        </div>
        <div class="mb-4">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control" required placeholder="Enter password">
        </div>
        <button type="submit" class="btn btn-login">Login Access</button>
    </form>
    
    <div class="text-center mt-3">
        <a href="index.jsp" style="color: rgba(255,255,255,0.6); text-decoration: none; font-size: 13px;">Back to Site</a>
    </div>
</div>

</body>
</html>
