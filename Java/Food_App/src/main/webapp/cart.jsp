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
    
    // Handle Actions
    String action = request.getParameter("action");
    if (action != null) {
        if (action.equals("add")) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Dao.addToCart(userId, productId, 1);
        } else if (action.equals("remove")) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            Dao.removeFromCart(cartId);
        } else if (action.equals("update")) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int qty = Integer.parseInt(request.getParameter("quantity"));
            if (qty > 0) {
                Dao.updateCartQuantity(cartId, qty);
            } else {
                Dao.removeFromCart(cartId);
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .cart-wrapper {
            display: flex;
            gap: 40px;
            padding: 40px 0;
            flex-wrap: wrap;
            align-items: flex-start;
        }
        .cart-items {
            flex: 2;
            min-width: 300px;
        }
        .cart-summary {
            flex: 1;
            min-width: 300px;
            position: sticky;
            top: 100px;
        }
        .cart-item {
            display: flex;
            align-items: center;
            padding: 20px;
            background: var(--surface-color);
            border-radius: var(--radius-md);
            box-shadow: var(--shadow-sm);
            margin-bottom: 20px;
            gap: 20px;
        }
        .cart-item img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: var(--radius-sm);
        }
        .cart-item-info {
            flex: 1;
        }
        .cart-item-info h4 {
            font-size: 1.2rem;
            margin-bottom: 5px;
        }
        .cart-item-price {
            color: var(--primary);
            font-weight: 600;
            font-size: 1.1rem;
        }
        .qty-controls {
            display: flex;
            align-items: center;
            gap: 10px;
            margin-top: 10px;
        }
        .qty-controls button {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            border: 1px solid var(--border-color);
            background: transparent;
            cursor: pointer;
        }
        .qty-controls input {
            width: 30px;
            text-align: center;
            border: none;
            font-weight: 600;
            background: transparent;
        }
        .remove-btn {
            color: #dc3545;
            background: transparent;
            border: none;
            cursor: pointer;
            font-size: 1.2rem;
            padding: 10px;
            transition: var(--transition);
        }
        .remove-btn:hover {
            transform: scale(1.1);
        }
        
        .summary-card p {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
            color: var(--text-muted);
        }
        .summary-card .total {
            font-size: 1.3rem;
            font-weight: 700;
            color: var(--text-dark);
            border-top: 1px solid var(--border-color);
            padding-top: 15px;
            margin-top: 15px;
        }
    </style>
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>

    <!-- Cart Area -->
    <div class="container cart-wrapper">
        
        <div class="cart-items">
            <h2 style="margin-bottom: 25px;">Shopping Cart</h2>
            
            <%
                List<CartModel> cartItems = Dao.getCartItems(userId);
                double subtotal = 0;
                if (cartItems != null && !cartItems.isEmpty()) {
                    for (CartModel item : cartItems) {
                        ProductModel p = Dao.getProductById(item.getProductId());
                        if (p != null) {
                            double itemTotal = p.getPrice() * item.getQuantity();
                            subtotal += itemTotal;
            %>
                <div class="cart-item">
                    <img src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>">
                    <div class="cart-item-info">
                        <h4><%= p.getName() %></h4>
                        <div class="cart-item-price">₹<%= (int)p.getPrice() %></div>
                        <div class="qty-controls">
                            <form action="cart.jsp" method="post" style="display: flex; align-items: center; gap: 8px;">
                                <input type="hidden" name="cartId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <button type="submit" name="quantity" value="<%= item.getQuantity() - 1 %>">-</button>
                                <input type="text" value="<%= item.getQuantity() %>" readonly>
                                <button type="submit" name="quantity" value="<%= item.getQuantity() + 1 %>">+</button>
                            </form>
                        </div>
                    </div>
                    <div style="font-weight: bold; font-size: 1.2rem;">₹<%= (int)itemTotal %></div>
                    <form action="cart.jsp" method="post">
                        <input type="hidden" name="cartId" value="<%= item.getId() %>">
                        <input type="hidden" name="action" value="remove">
                        <button type="submit" class="remove-btn"><i class="fa-solid fa-trash"></i></button>
                    </form>
                </div>
            <%
                        }
                    }
                } else {
            %>
                <div style="text-align: center; padding: 50px; background: var(--surface-color); border-radius: var(--radius-lg); box-shadow: var(--shadow-sm);">
                    <i class="fa-solid fa-cart-shopping" style="font-size: 4rem; color: var(--border-color); margin-bottom: 1.5rem;"></i>
                    <p style="font-size: 1.2rem; color: var(--text-muted);">Your cart is empty!</p>
                </div>
            <% } %>
            
            <a href="index.jsp" style="color: var(--primary); display: inline-flex; align-items: center; gap: 8px; margin-top: 20px; font-weight: 500;">
                <i class="fa-solid fa-arrow-left"></i> Continue Shopping
            </a>
        </div>

        <div class="cart-summary glass-panel" style="padding: 30px;">
            <h3 style="margin-bottom: 25px;">Order Summary</h3>
            <div class="summary-card">
                <div class="total" style="display: flex; justify-content: space-between; font-size: 1.25rem; font-weight: 700; color: var(--text-dark); margin-bottom: 25px; padding-top: 15px; border-top: 2px dashed var(--border-color);">
                    <span>Total Amount</span>
                    <span style="color: var(--primary);">₹<%= (int)subtotal %></span>
                </div>
                
                <% if (subtotal > 0) { %>
                    <button type="button" onclick="payWithRazorpay()" class="btn btn-primary" style="width: 100%; padding: 15px; font-size: 1.1rem; text-align: center; border: none; cursor: pointer;">
                        <i class="fa-solid fa-lock" style="margin-right: 10px;"></i> Pay Now
                    </button>
                <% } %>
            </div>
        </div>

    </div>

    <script>
        function payWithRazorpay() {
            var options = {
                "key": "rzp_test_SYu5g0dpspSt6h",
                "amount": "<%= (int)(subtotal * 100) %>",
                "currency": "INR",
                "name": "Foodie App",
                "description": "Order Payment",
                "image": "https://cdn-icons-png.flaticon.com/512/3443/3443393.png",
                "handler": function (response){
                    window.location.href = "success.jsp?payment_method=Razorpay&payment_id=" + response.razorpay_payment_id;
                },
                "prefill": {
                    "name": "<%= session.getAttribute("userName") != null ? session.getAttribute("userName") : "" %>",
                    "email": "<%= session.getAttribute("userEmail") != null ? session.getAttribute("userEmail") : "" %>"
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
