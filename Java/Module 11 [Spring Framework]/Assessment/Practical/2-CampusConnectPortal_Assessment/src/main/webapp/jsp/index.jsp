<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus Connect Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            color: #333;
            margin: 0;
            padding: 40px 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 80vh;
        }
        .container {
            background-color: #ffffff;
            border: 1px solid #cccccc;
            border-radius: 8px;
            padding: 30px;
            max-width: 500px;
            width: 100%;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
        }
        h2 {
            color: #1a1a1a;
            margin-top: 0;
            margin-bottom: 15px;
        }
        p {
            font-size: 14px;
            color: #666666;
            margin-bottom: 25px;
            line-height: 1.5;
        }
        .btn-group {
            display: flex;
            gap: 15px;
            justify-content: center;
        }
        .btn {
            display: inline-block;
            text-decoration: none;
            padding: 10px 20px;
            font-size: 14px;
            font-weight: bold;
            border-radius: 4px;
            cursor: pointer;
            border: 1px solid #007bff;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-secondary {
            background-color: #ffffff;
            color: #007bff;
        }
        .btn-secondary:hover {
            background-color: #f0f7ff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Campus Connect Portal</h2>
        <p>Welcome to the Campus Connect Portal. Manage your profile, view upcoming campus events, and register for new opportunities.</p>
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login Here</a>
            <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary">Register Here</a>
        </div>
    </div>
</body>
</html>
