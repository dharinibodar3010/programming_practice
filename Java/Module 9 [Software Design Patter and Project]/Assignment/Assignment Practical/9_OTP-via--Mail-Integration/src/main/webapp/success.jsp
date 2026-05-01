<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Security: if no session data, redirect to register
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    if (name == null || email == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    // Clear session after account creation
    session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Created Successfully!</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #1a1a2e, #16213e, #0f3460);
            font-family: 'Segoe UI', sans-serif;
        }
        .card {
            background: rgba(255,255,255,0.05);
            backdrop-filter: blur(12px);
            border: 1px solid rgba(255,255,255,0.1);
            border-radius: 20px;
            padding: 50px 40px;
            width: 100%;
            max-width: 440px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.4);
            text-align: center;
            animation: fadeIn 0.6s ease;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to   { opacity: 1; transform: translateY(0); }
        }
        .checkmark {
            width: 80px; height: 80px;
            background: linear-gradient(135deg, #00ff88, #00d4ff);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 2.5rem;
            margin: 0 auto 24px;
            box-shadow: 0 0 30px rgba(0,255,136,0.4);
            animation: pop 0.5s ease 0.3s both;
        }
        @keyframes pop {
            from { transform: scale(0); }
            to   { transform: scale(1); }
        }
        h2 { color: #fff; font-size: 1.8rem; margin-bottom: 10px; }
        p.sub { color: #aaa; font-size: 0.95rem; margin-bottom: 28px; }
        .user-info {
            background: rgba(0,255,136,0.08);
            border: 1px solid rgba(0,255,136,0.25);
            border-radius: 12px;
            padding: 16px 20px;
            margin-bottom: 28px;
        }
        .user-info p { color: #ccc; font-size: 0.9rem; margin-bottom: 6px; }
        .user-info p:last-child { margin-bottom: 0; }
        .user-info strong { color: #00ff88; }
        .step-badge {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            margin-bottom: 28px;
        }
        .step { width: 32px; height: 6px; border-radius: 3px; }
        .step.done { background: #00ff88; }
        a.btn {
            display: block;
            padding: 13px;
            background: linear-gradient(135deg, #00d4ff, #0057ff);
            border-radius: 10px;
            color: #fff;
            font-weight: 600;
            font-size: 1rem;
            text-decoration: none;
            transition: opacity 0.3s, transform 0.2s;
        }
        a.btn:hover { opacity: 0.9; transform: translateY(-1px); }
    </style>
</head>
<body>
    <div class="card">
        <div class="step-badge">
            <div class="step done"></div>
            <div class="step done"></div>
            <div class="step done"></div>
        </div>
        <div class="checkmark">✔</div>
        <h2>Account Created!</h2>
        <p class="sub">Step 3: Email verified &amp; registration complete 🎉</p>

        <div class="user-info">
            <p>👤 Name: <strong><%= name %></strong></p>
            <p>📧 Email: <strong><%= email %></strong></p>
            <p>✅ Status: <strong>Verified &amp; Active</strong></p>
        </div>

        <a class="btn" href="index.jsp">← Register Another Account</a>
    </div>
</body>
</html>
