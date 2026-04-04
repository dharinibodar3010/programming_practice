<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Successful | E-Learning</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .success-card {
            background: white;
            padding: 50px 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 450px;
            text-align: center;
        }

        /* Success Icon Styling */
        .check-icon {
            width: 80px;
            height: 80px;
            background-color: #27ae60;
            color: white;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 40px;
            margin: 0 auto 25px;
            box-shadow: 0 4px 15px rgba(39, 174, 96, 0.3);
        }

        .success-card h2 {
            color: #2c3e50;
            margin-bottom: 10px;
            font-size: 26px;
        }

        .success-card p {
            color: #7f8c8d;
            font-size: 16px;
            line-height: 1.6;
            margin-bottom: 30px;
        }

        /* Dashboard Button */
        .btn-dashboard {
            background-color: #2c3e50;
            color: white;
            padding: 14px 30px;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 600;
            font-size: 16px;
            transition: all 0.3s ease;
            display: inline-block;
            border: none;
            cursor: pointer;
        }

        .btn-dashboard:hover {
            background-color: #1a252f;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        .order-id {
            margin-top: 25px;
            font-size: 13px;
            color: #bdc3c7;
        }
    </style>
</head>
<body>

    <div class="success-card">
        <div class="check-icon">✓</div>
        
        <h2>Payment Successful!</h2>
        <p>Congratulations! You have successfully registered for the course.</p>

        <a href="dashboard.jsp" class="btn-dashboard">Go to Dashboard</a>

        <div class="order-id">
            Transaction ID: <%= (request.getParameter("razorpay_payment_id") != null) ? request.getParameter("razorpay_payment_id") : "N/A" %>
        </div>
    </div>

</body>
</html>