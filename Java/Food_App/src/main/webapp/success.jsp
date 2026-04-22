<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="com.util.EmailUtil" %>
<%@ page import="java.util.*" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int userId = (Integer) session.getAttribute("userId");
    String paymentMethod = request.getParameter("payment_method");
    String paymentId = request.getParameter("payment_id");
    if (paymentMethod == null) paymentMethod = "Razorpay";
    if (paymentId == null) paymentId = "TXN" + System.currentTimeMillis();

    List<CartModel> cartItems = Dao.getCartItems(userId);
    if (cartItems == null || cartItems.isEmpty()) {
        response.sendRedirect("index.jsp");
        return;
    }

    double subtotal = 0;
    for (CartModel item : cartItems) {
        ProductModel p = Dao.getProductById(item.getProductId());
        if (p != null) subtotal += p.getPrice() * item.getQuantity();
    }
    double totalAmount = subtotal;

    // Place Order
    OrderModel om = new OrderModel();
    om.setUserId(userId);
    om.setTotalAmount(totalAmount);
    om.setPaymentMethod(paymentMethod);
    om.setStatus("Success");
    int orderId = Dao.placeOrder(om);

    if (orderId > 0) {
        // Add Items
        for (CartModel item : cartItems) {
            ProductModel p = Dao.getProductById(item.getProductId());
            if (p != null) {
                OrderItemModel oim = new OrderItemModel();
                oim.setOrderId(orderId);
                oim.setProductId(item.getProductId());
                oim.setQuantity(item.getQuantity());
                oim.setPrice(p.getPrice());
                Dao.addOrderItem(oim);
            }
        }

        // Add Payment
        Dao.addPayment(orderId, paymentId, paymentMethod, totalAmount, "Success");

        // Clear Cart
        Dao.clearCart(userId);

        // Send Payment Email
        UserModel user = Dao.getUserById(userId);
        if (user != null) {
            String smtpHost = getServletContext().getInitParameter("smtpHost");
            String smtpPort = getServletContext().getInitParameter("smtpPort");
            String fromEmail = getServletContext().getInitParameter("user");
            String emailPassword = getServletContext().getInitParameter("pass");

            String subject = "Order Confirmed: #" + orderId + " 🍕";
            String body = "<div style='font-family: Arial, sans-serif; max-width: 600px; border: 1px solid #ddd; border-radius: 8px; overflow: hidden;'>" +
                "<div style='background: #2ec4b6; color: white; padding: 20px; text-align: center;'>" +
                "<h1>Payment Successful!</h1>" +
                "</div>" +
                "<div style='padding: 30px;'>" +
                "<p>Dear <b>" + user.getFullname() + "</b>,</p>" +
                "<p>Your order has been placed successfully and is being prepared!</p>" +
                "<div style='background: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0;'>" +
                "<h3 style='margin-top: 0;'>Order Summary</h3>" +
                "<p><b>Order ID:</b> #" + orderId + "</p>" +
                "<p><b>Total Amount:</b> ₹" + (int)totalAmount + "</p>" +
                "<p><b>Transaction ID:</b> " + paymentId + "</p>" +
                "</div>" +
                "<p>Thank you for choosing <b>Foodie</b>.</p>" +
                "<br><p>Best regards,<br><b>The Foodie Team</b></p>" +
                "</div></div>";
            
            // Send Email in Background Thread to avoid slowing down page redirect
            new Thread(new Runnable() {
                public void run() {
                    try {
                        EmailUtil.sendEmail(user.getEmail(), subject, body, fromEmail, emailPassword, smtpHost, smtpPort);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Success - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .success-container {
            min-height: 80vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px 20px;
        }

        .success-card {
            background: var(--surface-color);
            padding: 50px 40px;
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-md);
            text-align: center;
            max-width: 600px;
            width: 100%;
        }

        .success-animation {
            width: 100px;
            height: 100px;
            background: #2ec4b6;
            color: white;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 3rem;
            margin: 0 auto 30px;
            box-shadow: 0 0 0 15px rgba(46, 196, 182, 0.1);
            animation: pulse 2s infinite;
        }

        @keyframes pulse {
            0% { box-shadow: 0 0 0 0 rgba(46, 196, 182, 0.4); }
            70% { box-shadow: 0 0 0 20px rgba(46, 196, 182, 0); }
            100% { box-shadow: 0 0 0 0 rgba(46, 196, 182, 0); }
        }

        .success-card h2 {
            font-size: 2.2rem;
            color: var(--text-dark);
            margin-bottom: 15px;
        }

        .success-card p {
            color: var(--text-muted);
            font-size: 1.1rem;
            margin-bottom: 40px;
            line-height: 1.6;
        }

        .order-details-box {
            background: var(--bg-color);
            border: 1px dashed var(--border-color);
            border-radius: var(--radius-md);
            padding: 20px;
            margin-bottom: 40px;
            display: flex;
            justify-content: space-around;
        }

        .detail-item p {
            margin: 0;
            color: var(--text-muted);
            font-size: 0.9rem;
            margin-bottom: 5px;
        }

        .detail-item h5 {
            margin: 0;
            color: var(--text-dark);
            font-size: 1.1rem;
            font-weight: 600;
        }

        .action-buttons {
            display: flex;
            gap: 20px;
            justify-content: center;
        }

        @media(max-width: 600px) {
            .order-details-box {
                flex-direction: column;
                gap: 20px;
            }
            .action-buttons {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>

    <div class="container success-container">
        <div class="success-card glass-panel">
            <div class="success-animation">
                <i class="fa-solid fa-check"></i>
            </div>
            
            <h2>Payment Successful!</h2>
            <p>Thank you for your order. We are currently processing it and will deliver it to you blazing fast.</p>
            
            <div class="order-details-box">
                <div class="detail-item">
                    <p>Order Number</p>
                    <h5>#ORD-<%= orderId %></h5>
                </div>
                <div class="detail-item">
                    <p>Payment Amount</p>
                    <h5>₹<%= (int)totalAmount %></h5>
                </div>
                <div class="detail-item">
                    <p>Estimated Delivery</p>
                    <h5>30-40 Mins</h5>
                </div>
            </div>

            <div class="action-buttons">
                <a href="products.jsp" class="btn btn-outline" style="padding: 14px 30px; font-size: 1.1rem;"><i class="fa-solid fa-bag-shopping" style="margin-right: 8px;"></i> Continue Shopping</a>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
