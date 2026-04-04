<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.UsersModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Secure Course Payment</title>
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
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
        .payment-card {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }
        .logo {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
            margin-bottom: 20px;
        }
        .course-info {
            background: #f8fafc;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 25px;
            border: 1px solid #e2e8f0;
        }
        .course-info h3 {
            margin: 0;
            font-size: 18px;
            color: #64748b;
        }
        .amount {
            font-size: 32px;
            font-weight: 800;
            color: #1e293b;
            margin: 10px 0;
        }
        .btn-pay {
            background-color: #F37254; /* Razorpay Orange */
            color: white;
            border: none;
            padding: 15px 30px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            transition: background 0.3s ease;
        }
        .btn-pay:hover {
            background-color: #e66a4c;
        }
        .secure-text {
            margin-top: 15px;
            font-size: 12px;
            color: #94a3b8;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 5px;
        }
    </style>
</head>
<body>

    <%
        String cId = request.getParameter("courseId");
        String amountStr = request.getParameter("amount");
        
        int amountInPaise = 0;
        String displayAmount = "0";

        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                displayAmount = amountStr;
                amountInPaise = Integer.parseInt(amountStr) * 100;
            } catch (NumberFormatException e) {
                displayAmount = "0";
            }
        }

        UsersModel u = (UsersModel) session.getAttribute("user");
    %>

    <div class="payment-card">
        <div class="logo">🎓 E-Learning</div>
        
        <div class="course-info">
            <h3>Course Enrollment</h3>
            <div class="amount">₹<%= displayAmount %></div>
            <p style="color: #94a3b8; font-size: 14px;">Course ID: <%= (cId != null) ? cId : "N/A" %></p>
        </div>

        <form id="paymentForm" action="PaymentServlet" method="post">
            <input type="hidden" name="courseId" value="<%= (cId != null) ? cId : "" %>">
            <input type="hidden" name="amount" value="<%= displayAmount %>">
            <input type="hidden" name="razorpay_payment_id" id="razorpay_payment_id">
            
            <button type="button" id="rzp-button1" class="btn-pay">Proceed to Pay</button>
        </form>

        <div class="secure-text">
            🔒 SSL Secured | Powered by Razorpay
        </div>
    </div>

    <script>
        var options = {
            "key": "rzp_test_SYu5g0dpspSt6h", // Your Test Key
            "amount": "<%= amountInPaise %>",
            "currency": "INR",
            "name": "E-Learning System",
            "description": "Course Registration Fee",
            "image": "https://cdn.razorpay.com/logo.png",
            "handler": function (response) {
                document.getElementById('razorpay_payment_id').value = response.razorpay_payment_id;
                document.getElementById('paymentForm').submit();
            },
            "prefill": {
                "name": "<%= (u != null) ? u.getName() : "" %>",
                "email": "<%= (u != null) ? u.getEmail() : "" %>"
            },
            "theme": {
                "color": "#F37254"
            }
        };

        document.getElementById('rzp-button1').onclick = function (e) {
            var rzp1 = new Razorpay(options);
            rzp1.open();
            e.preventDefault();
        }
    </script>
</body>
</html>