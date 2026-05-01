<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - OTP Verification</title>
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
        h2 {
            color: #fff;
            text-align: center;
            margin-bottom: 8px;
            font-size: 1.8rem;
        }
        p.sub {
            color: #aaa;
            text-align: center;
            margin-bottom: 28px;
            font-size: 0.9rem;
        }
        label {
            color: #ccc;
            font-size: 0.85rem;
            display: block;
            margin-bottom: 6px;
        }
        input[type=text], input[type=email], input[type=password] {
            width: 100%;
            padding: 12px 16px;
            background: rgba(255,255,255,0.08);
            border: 1px solid rgba(255,255,255,0.15);
            border-radius: 10px;
            color: #fff;
            font-size: 1rem;
            margin-bottom: 18px;
            outline: none;
            transition: border 0.3s;
        }
        input:focus { border-color: #00d4ff; }
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
        .step-badge {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            margin-bottom: 24px;
        }
        .step { width: 32px; height: 6px; border-radius: 3px; background: rgba(255,255,255,0.15); }
        .step.active { background: #00d4ff; }
    </style>
</head>
<body>
    <div class="card">
        <div class="step-badge">
            <div class="step active"></div>
            <div class="step"></div>
            <div class="step"></div>
        </div>
        <h2>📝 Register</h2>
        <p class="sub">Step 1: Fill in your details to get OTP</p>

        <form action="SendOtpServlet" method="post">
            <label>Full Name</label>
            <input type="text" name="name" placeholder="Enter your name" required>

            <label>Email Address</label>
            <input type="email" name="email" placeholder="Enter your email" required>

            <label>Password</label>
            <input type="password" name="password" placeholder="Create a password" required>

            <input type="submit" value="Send OTP to Email →">
        </form>
    </div>
</body>
</html>