<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Footer Section -->
<footer class="footer">
    <div class="container">
        <div class="footer-grid">
            <div class="footer-col">
                <a href="index.jsp" class="logo" style="margin-bottom: 20px; display: inline-flex;">
                    <i class="fa-solid fa-utensils"></i> Foodie
                </a>
                <p>We believe food is not just necessary for survival; it's an experience to be savored. Best quality ingredients, crafted with passion.</p>
                <div class="social-icons-footer">
                    <a href="#"><i class="fa-brands fa-facebook-f"></i></a>
                    <a href="#"><i class="fa-brands fa-twitter"></i></a>
                    <a href="#"><i class="fa-brands fa-instagram"></i></a>
                    <a href="#"><i class="fa-brands fa-youtube"></i></a>
                </div>
            </div>

            <div class="footer-col">
                <h4>Quick Links</h4>
                <ul class="footer-links">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="about.jsp">About Us</a></li>
                    <li><a href="products.jsp">Our Menu</a></li>
                    <li><a href="#">Offers & Combos</a></li>
                </ul>
            </div>

            <div class="footer-col">
                <h4>My Account</h4>
                <ul class="footer-links">
                    <li><a href="login.jsp">Sign In</a></li>
                    <li><a href="cart.jsp">View Cart</a></li>
                    <li><a href="order-history.jsp">Order History</a></li>
                    <li><a href="dashboard.jsp">Dashboard</a></li>
                </ul>
            </div>

            <div class="footer-col">
                <h4>Contact Us</h4>
                <ul class="footer-links" style="color: #999; font-size: 0.95rem;">
                    <li style="display: flex; gap: 10px; margin-bottom: 15px;">
                        <i class="fa-solid fa-location-dot" style="color: var(--primary); margin-top: 5px;"></i>
                        <span>123 Food Street, Culinary District, FK 90210</span>
                    </li>
                    <li style="display: flex; gap: 10px; margin-bottom: 15px;">
                        <i class="fa-solid fa-phone" style="color: var(--primary); margin-top: 5px;"></i>
                        <span>+1 234 567 8900</span>
                    </li>
                    <li style="display: flex; gap: 10px;">
                        <i class="fa-solid fa-envelope" style="color: var(--primary); margin-top: 5px;"></i>
                        <span>support@foodie.com</span>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="footer-bottom">
            <p>&copy; <%= new java.util.Date().getYear() + 1900 %> Foodie Web App. All rights reserved.</p>
        </div>
    </div>
</footer>
