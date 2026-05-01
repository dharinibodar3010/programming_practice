<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Successful</title>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg: #0f172a;
            --success: #22c55e;
            --text: #f8fafc;
        }
        body {
            background: var(--bg);
            color: var(--text);
            font-family: 'Outfit', sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background: rgba(255, 255, 255, 0.05);
            padding: 50px;
            border-radius: 24px;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            max-width: 500px;
        }
        .icon {
            font-size: 64px;
            color: var(--success);
            margin-bottom: 20px;
        }
        h1 { margin: 0 0 10px; }
        p { color: #94a3b8; margin-bottom: 30px; }
        .details {
            background: rgba(0, 0, 0, 0.2);
            padding: 15px;
            border-radius: 12px;
            text-align: left;
            font-family: monospace;
            margin-bottom: 30px;
        }
        .btn {
            background: #3b82f6;
            color: white;
            padding: 12px 30px;
            border-radius: 10px;
            text-decoration: none;
            font-weight: 600;
            transition: 0.3s;
        }
        .btn:hover { background: #2563eb; }
    </style>
</head>
<body>
    <div class="container">
        <div class="icon">✔</div>
        <h1>Payment Successful!</h1>
        <p>Thank you for your purchase. Your transaction has been completed successfully.</p>
        
        <div class="details">
            <div>Transaction ID: <%= request.getParameter("id") %></div>
            <div>Status: <%= request.getParameter("status") %></div>
        </div>

        <a href="index.jsp" class="btn">Back to Store</a>
    </div>
</body>
</html>
