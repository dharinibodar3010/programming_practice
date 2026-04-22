<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="java.util.List" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int userId = (Integer) session.getAttribute("userId");
    List<CartModel> cartItems = Dao.getCartItems(userId);
    double subtotal = 0;
    for (CartModel item : cartItems) {
        ProductModel p = Dao.getProductById(item.getProductId());
        if (p != null) subtotal += p.getPrice() * item.getQuantity();
    }
    double delivery = 0;
    double tax = 0;
    double total = subtotal;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    <style>
        .payment-container {
            padding: 60px 0;
            display: flex;
            gap: 40px;
            align-items: flex-start;
        }

        .payment-methods {
            flex: 2;
            background: var(--surface-color);
            padding: 40px;
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-sm);
        }

        .order-summary {
            flex: 1;
            background: var(--surface-color);
            padding: 40px;
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-sm);
            position: sticky;
            top: 100px;
        }

        .payment-methods h3, .order-summary h3 {
            font-size: 1.5rem;
            color: var(--text-dark);
            margin-bottom: 25px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .payment-option {
            border: 1px solid var(--border-color);
            border-radius: var(--radius-md);
            padding: 20px;
            margin-bottom: 20px;
            cursor: pointer;
            transition: var(--transition);
        }

        .payment-option:hover {
            border-color: var(--primary);
            background: rgba(255, 107, 53, 0.02);
        }

        .payment-option.active {
            border-color: var(--primary);
            background: rgba(255, 107, 53, 0.05);
        }

        .payment-header {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .payment-radio {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            border: 2px solid var(--border-color);
            position: relative;
            display: inline-block;
        }

        .payment-option.active .payment-radio {
            border-color: var(--primary);
        }

        .payment-option.active .payment-radio::after {
            content: '';
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 10px;
            height: 10px;
            background: var(--primary);
            border-radius: 50%;
        }

        .payment-title {
            font-size: 1.1rem;
            font-weight: 500;
            color: var(--text-dark);
            flex: 1;
        }

        .payment-icons {
            display: flex;
            gap: 10px;
            font-size: 1.5rem;
            color: var(--text-muted);
        }

        .payment-details {
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px dashed var(--border-color);
            display: none;
        }

        .payment-option.active .payment-details {
            display: block;
            animation: fadeIn 0.3s ease;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-5px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* Input specific for card */
        .card-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }

        .summary-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
            color: var(--text-muted);
            font-size: 1rem;
        }

        .summary-total {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 2px dashed var(--border-color);
            font-size: 1.25rem;
            font-weight: 700;
            color: var(--text-dark);
        }

        @media(max-width: 900px) {
            .payment-container {
                flex-direction: column;
            }
            .payment-methods, .order-summary {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>

    <div class="container payment-container">
        
        <!-- Payment Methods -->
        <div class="payment-methods">
            <div style="text-align: center; padding: 20px 0;">
                <i class="fa-solid fa-lock" style="font-size: 3rem; color: var(--secondary); margin-bottom: 20px;"></i>
                <h3>Secure Checkout powered by Razorpay</h3>
                <p style="color: var(--text-muted); margin-bottom: 30px;">
                    We support all major credit/debit cards, UPI, Netbanking, and Wallets. Your transaction is safe and encrypted.
                </p>
                
                <div class="payment-icons" style="justify-content: center; margin-bottom: 40px; font-size: 2.5rem; gap: 20px;">
                    <i class="fa-brands fa-cc-visa" style="color: #1a1f71;"></i>
                    <i class="fa-brands fa-cc-mastercard" style="color: #eb001b;"></i>
                    <i class="fa-brands fa-google-pay" style="color: #5f6368;"></i>
                    <i class="fa-solid fa-building-columns" style="color: #6c757d;"></i>
                </div>

                <button type="button" onclick="payWithRazorpay()" class="btn btn-primary" style="width: 100%; padding: 18px; font-size: 1.3rem; border-radius: var(--radius-md);">
                    <i class="fa-solid fa-shield-check" style="margin-right: 10px;"></i> Pay ₹<%= (int)total %> Now
                </button>
                
                <p style="margin-top: 20px; font-size: 0.85rem; color: var(--text-muted);">
                    <i class="fa-solid fa-circle-info"></i> By clicking pay, you agree to our terms of service.
                </p>
            </div>
        </div>

        <!-- Order Summary -->
        <div class="order-summary">
            <h3><i class="fa-solid fa-receipt" style="color: var(--primary);"></i> Order Summary</h3>
            
            <div style="margin-bottom: 20px;">
                <%
                    for (CartModel item : cartItems) {
                        ProductModel p = Dao.getProductById(item.getProductId());
                        if (p != null) {
                %>
                <div style="display: flex; gap: 15px; margin-bottom: 15px;">
                    <img src="<%= p.getImageUrl() %>" style="width: 60px; height: 60px; border-radius: 8px; object-fit: cover;">
                    <div>
                        <h4 style="color: var(--text-dark); margin-bottom: 5px;"><%= p.getName() %></h4>
                        <p style="color: var(--text-muted); font-size: 0.9rem;">Qty: <%= item.getQuantity() %> x ₹<%= (int)p.getPrice() %></p>
                    </div>
                    <div style="margin-left: auto; font-weight: 600; color: var(--text-dark);">₹<%= (int)(p.getPrice() * item.getQuantity()) %></div>
                </div>
                <%
                        }
                    }
                %>
            </div>

            <div class="summary-total">
                <span>Total Amount</span>
                <span style="color: var(--primary);">₹<%= (int)total %></span>
            </div>
        </div>

    </div>

    <%@ include file="footer.jsp" %>

    <script>
        function payWithRazorpay() {
            var options = {
                "key": "rzp_test_SYu5g0dpspSt6h", // Razorpay Test Key ID
                "amount": "<%= (int)(total * 100) %>", // Amount in paise (100 paise = 1 INR)
                "currency": "INR",
                "name": "Foodie App",
                "description": "Food Order Payment",
                "image": "https://cdn-icons-png.flaticon.com/512/3443/3443393.png",
                "handler": function (response){
                    // This function runs after successful payment
                    window.location.href = "success.jsp?payment_method=Razorpay&payment_id=" + response.razorpay_payment_id;
                },
                "prefill": {
                    "name": "<%= session.getAttribute("userName") %>",
                    "email": "<%= session.getAttribute("userEmail") %>"
                },
                "theme": {
                    "color": "#FF6B35"
                }
            };
            var rzp = new Razorpay(options);
            rzp.open();
        }
    </script>
</body>
</html>
