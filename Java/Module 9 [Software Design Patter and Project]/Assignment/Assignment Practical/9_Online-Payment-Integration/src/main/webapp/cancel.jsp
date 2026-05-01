<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Cancelled</title>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg: #0f172a;
            --error: #ef4444;
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
            color: var(--error);
            margin-bottom: 20px;
        }
        h1 { margin: 0 0 10px; }
        p { color: #94a3b8; margin-bottom: 30px; }
        .btn {
            background: rgba(255, 255, 255, 0.1);
            color: white;
            padding: 12px 30px;
            border-radius: 10px;
            text-decoration: none;
            font-weight: 600;
            transition: 0.3s;
            border: 1px solid rgba(255, 255, 255, 0.2);
        }
        .btn:hover { background: rgba(255, 255, 255, 0.2); }
    </style>
</head>
<body>
    <div class="container">
        <div class="icon">✕</div>
        <h1>Payment Cancelled</h1>
        <p>The transaction was cancelled. No money has been deducted from your account.</p>
        
        <a href="index.jsp" class="btn">Try Again</a>
    </div>
</body>
</html>
