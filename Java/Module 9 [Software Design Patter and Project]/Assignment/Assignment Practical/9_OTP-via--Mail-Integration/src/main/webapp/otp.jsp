<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verify OTP</title>
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
            padding: 40px 36px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.4);
        }
        h2 { color: #fff; text-align: center; margin-bottom: 8px; font-size: 1.8rem; }
        p.sub { color: #aaa; text-align: center; margin-bottom: 28px; font-size: 0.9rem; }
        .email-hint {
            background: rgba(0,212,255,0.1);
            border: 1px solid rgba(0,212,255,0.3);
            border-radius: 10px;
            padding: 12px 16px;
            color: #00d4ff;
            font-size: 0.88rem;
            margin-bottom: 22px;
            text-align: center;
        }
        label { color: #ccc; font-size: 0.85rem; display: block; margin-bottom: 6px; }
        input[type=text] {
            width: 100%;
            padding: 14px 16px;
            background: rgba(255,255,255,0.08);
            border: 1px solid rgba(255,255,255,0.15);
            border-radius: 10px;
            color: #fff;
            font-size: 1.4rem;
            text-align: center;
            letter-spacing: 10px;
            margin-bottom: 22px;
            outline: none;
            transition: border 0.3s;
        }
        input[type=text]:focus { border-color: #00d4ff; }
        input[type=submit] {
            width: 100%;
            padding: 13px;
            background: linear-gradient(135deg, #00d4ff, #0057ff);
            border: none;
            border-radius: 10px;
            color: #fff;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: opacity 0.3s, transform 0.2s;
        }
        input[type=submit]:hover { opacity: 0.9; transform: translateY(-1px); }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 16px;
            color: #888;
            text-decoration: none;
            font-size: 0.88rem;
            transition: color 0.3s;
        }
        .back-link:hover { color: #00d4ff; }
        .step-badge {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            margin-bottom: 24px;
        }
        .step { width: 32px; height: 6px; border-radius: 3px; background: rgba(255,255,255,0.15); }
        .step.done { background: #00ff88; }
        .step.active { background: #00d4ff; }
    </style>
</head>
<body>
    <div class="card">
        <div class="step-badge">
            <div class="step done"></div>
            <div class="step active"></div>
            <div class="step"></div>
        </div>
        <h2>📧 Verify OTP</h2>
        <p class="sub">Step 2: Enter the OTP sent to your email</p>

        <%
            String email = (String) session.getAttribute("email");
            if (email != null) {
        %>
        <div class="email-hint">
            OTP sent to: <strong><%= email %></strong>
        </div>
        <% } %>

        <form action="VerifyOtpServlet" method="post">
            <label>Enter 6-Digit OTP</label>
            <input type="text" name="otp" maxlength="6" placeholder="------" required autofocus>
            <input type="submit" value="Verify & Create Account ✔">
        </form>

        <a class="back-link" href="index.jsp">← Back to Registration</a>
    </div>
</body>
</html>