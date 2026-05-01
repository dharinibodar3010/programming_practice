<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout | Razorpay Integration</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary: #3399cc;
            --bg: #0f172a;
            --card-bg: rgba(255, 255, 255, 0.05);
            --text: #f8fafc;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Outfit', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--text);
            padding: 20px;
        }

        .container {
            width: 100%;
            max-width: 450px;
            background: var(--card-bg);
            backdrop-filter: blur(12px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 24px;
            padding: 40px;
            box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.5);
            text-align: center;
        }

        .header h1 {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 8px;
            background: linear-gradient(to right, #3399cc, #60a5fa);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .product-card {
            background: rgba(255, 255, 255, 0.03);
            border-radius: 16px;
            padding: 25px;
            margin: 30px 0;
            border: 1px solid rgba(255, 255, 255, 0.05);
        }

        .price {
            font-size: 2.5rem;
            font-weight: 600;
            color: #fff;
            margin: 10px 0;
        }

        .pay-btn {
            width: 100%;
            background: var(--primary);
            color: white;
            border: none;
            padding: 16px;
            border-radius: 12px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
            transition: 0.3s;
            box-shadow: 0 4px 15px rgba(51, 153, 204, 0.3);
        }

        .pay-btn:hover {
            transform: translateY(-2px);
            background: #2b82ad;
        }

        .methods-available {
            margin-top: 25px;
            display: flex;
            justify-content: center;
            gap: 15px;
            opacity: 0.6;
            filter: grayscale(1) invert(1);
        }

        .methods-available img {
            height: 18px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="header">
            <h1>Secure Checkout</h1>
            <p style="color: #94a3b8;">Complete your purchase</p>
        </div>

        <div class="product-card">
            <h3 style="color: #cbd5e1;">Premium Course Bundle</h3>
            <div class="price">₹599.00</div>
            <p style="font-size: 0.85rem; color: #64748b; margin-top: 5px;">Includes Cards, UPI, Netbanking</p>
        </div>

        <!-- Single button to trigger Razorpay -->
        <button id="rzp-button" class="pay-btn">Pay Now</button>

        <div class="methods-available">
            <img src="https://upload.wikimedia.org/wikipedia/commons/e/e1/UPI-Logo-vector.svg" alt="UPI">
            <img src="https://upload.wikimedia.org/wikipedia/commons/5/5e/Visa_Inc._logo.svg" alt="Visa">
            <img src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Mastercard-logo.svg" alt="Mastercard">
        </div>

        <p style="margin-top: 20px; font-size: 0.8rem; color: #64748b;">Protected by Razorpay Secure</p>
    </div>

    <!-- Razorpay SDK -->
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    
    <script>
        var options = {
            "key": "rzp_test_SYu5g0dpspSt6h", 
            "amount": "59900", 
            "currency": "INR",
            "name": "Antigravity Store",
            "description": "Lab Exercise Integration",
            "image": "https://example.com/logo.png",
            "handler": function (response){
                // On success, redirect to success.jsp
                window.location.href = "success.jsp?id=" + response.razorpay_payment_id + "&status=Captured";
            },
            "prefill": {
                "name": "Student",
                "email": "student@example.com",
                "contact": "9999999999"
            },
            "notes": {
                "address": "Office Address"
            },
            "theme": {
                "color": "#3399cc"
            }
        };
        
        var rzp1 = new Razorpay(options);

        rzp1.on('payment.failed', function (response){
            alert("Payment Failed! \nReason: " + response.error.description + "\nCode: " + response.error.code);
            console.error(response.error);
        });
        
        document.getElementById('rzp-button').onclick = function(e){
            rzp1.open();
            e.preventDefault();
        }
    </script>
</body>
</html>
