<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account | E-Learning</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .registration-card {
            background: white;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
            width: 100%;
            max-width: 400px;
        }
        .registration-card h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #34495e;
            font-weight: 600;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #F37254;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn-submit:hover {
            background-color: #e66a4c;
        }
        .msg {
            text-align: center;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            font-size: 14px;
        }
        .error { background: #fdeaea; color: #e74c3c; }
        .footer-text {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #7f8c8d;
        }
        .footer-text a {
            color: #F37254;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <div class="registration-card">
        <h2>Sign Up</h2>

        <% if(request.getParameter("msg") != null) { %>
            <div class="msg error"><%= request.getParameter("msg") %></div>
        <% } %>

        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label>Full Name</label>
                <input type="text" name="name" placeholder="Enter Your Name" required>
            </div>
            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email" placeholder="Enter Your Email" required>
            </div>
            
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter Password" required>
            </div>
            
            <button type="submit" class="btn-submit">Create Account</button>
        </form>

        <div class="footer-text">
            Already have an account? <a href="index.jsp">Login</a>
        </div>
    </div>

</body>
</html>