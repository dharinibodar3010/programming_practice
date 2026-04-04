<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .login-container {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.2);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .login-container h2 {
            color: #2c3e50;
            margin-bottom: 10px;
            font-size: 28px;
        }

        .login-container p {
            color: #7f8c8d;
            margin-bottom: 30px;
        }

        .form-group {
            text-align: left;
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #34495e;
            font-weight: 600;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            box-sizing: border-box; /* Prevents input from overflowing */
            transition: border-color 0.3s;
        }

        .form-group input:focus {
            border-color: #F37254;
            outline: none;
        }

        .btn-login {
            background-color: #F37254;
            color: white;
            border: none;
            padding: 14px;
            width: 100%;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
            margin-top: 10px;
        }

        .btn-login:hover {
            background-color: #e66a4c;
        }

        .error-msg {
            color: #e74c3c;
            background: #fdeaea;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .footer-links {
            margin-top: 25px;
            font-size: 14px;
            color: #7f8c8d;
        }

        .footer-links a {
            color: #F37254;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2>Welcome Back</h2>
        <p>Please enter your details to login</p>

        <%-- Display error message if login fails --%>
        <% if(request.getAttribute("error") != null) { %>
            <div class="error-msg">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email" placeholder="Enter Your Email" required>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter Password" required>
            </div>

            <button type="submit" class="btn-login">Login to Dashboard</button>
        </form>

        <div class="footer-links">
            Don't have an account? <a href="register.jsp">Sign Up</a>
        </div>
    </div>

</body>
</html>