<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.Dao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Foodie Premium Delivery</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <%@ include file="header.jsp" %>

    <!-- About Hero Section -->
    <section class="about-hero">
        <div class="container about-hero-content">
            <h1>Our Story</h1>
            <p>From a small kitchen to a city-wide sensation, we've always believed that food is more than just fuel—it's an experience to be savored.</p>
        </div>
    </section>

    <!-- About Content Section -->
    <section class="about-section container">
        <div class="about-grid">
            <div class="about-image">
                <img src="https://images.unsplash.com/photo-1600565193348-f74bd3c7ccdf?q=80&w=2070&auto=format&fit=crop" alt="Our Chef in Action">
            </div>
            <div class="about-text">
                <h2>Serving Smiles & <span>Premium Quality</span></h2>
                <p>Welcome to Foodie, where passion meets the palate. Our journey started with a simple belief: everyone deserves access to high-quality, delicious meals crafted with love and fresh ingredients.</p>
                <p>We partner with top chefs and local farmers to bring you menus that are not only taste-bud-tingling but also nourishing. Whether you're craving a cheesy pizza, a wholesome thali, or decadent desserts, we ensure every dish delivered to your door is an unforgettable experience.</p>
                <p>Our commitment to excellence extends beyond the kitchen to our lightning-fast delivery and impeccable customer service. Eat what makes you happy, and let us take care of the rest.</p>
                <div style="margin-top: 30px;">
                    <a href="products.jsp" class="btn btn-primary"><i class="fa-solid fa-arrow-right" style="margin-right: 8px;"></i> Explore Menu</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Features Section -->
    <section class="about-section container" style="padding-top: 0;">
        <h2 class="section-title">Why Choose Us?</h2>
        <div class="features-grid">
            <div class="feature-box">
                <i class="fa-solid fa-leaf"></i>
                <h3>Fresh Ingredients</h3>
                <p>We source only the finest, farm-fresh ingredients to craft meals that burst with natural flavor and unparalleled quality.</p>
            </div>
            <div class="feature-box">
                <i class="fa-solid fa-bolt"></i>
                <h3>Lightning Fast</h3>
                <p>Hot, fresh, and on time. Our dedicated delivery crew ensures your cravings are satisfied without the agonizing wait.</p>
            </div>
            <div class="feature-box">
                <i class="fa-solid fa-award"></i>
                <h3>Master Chefs</h3>
                <p>Every dish is designed and prepared by culinary experts who treat food as an art form.</p>
            </div>
            <div class="feature-box">
                <i class="fa-solid fa-headset"></i>
                <h3>24/7 Support</h3>
                <p>Got a question or a special request? Our friendly support team is always awake and ready to help you out.</p>
            </div>
        </div>
    </section>

    <script>
        // Simple search animation demo
        const searchInput = document.getElementById('searchInput');
        searchInput.addEventListener('focus', function() {
            this.parentElement.style.boxShadow = 'var(--shadow-md)';
            this.parentElement.style.borderColor = 'var(--primary)';
        });
        searchInput.addEventListener('blur', function() {
            this.parentElement.style.boxShadow = 'none';
            this.parentElement.style.borderColor = 'var(--border-color)';
        });
    </script>
    
    <%@ include file="footer.jsp" %>
</body>
</html>
