<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.ProductModel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clothing Store - Home</title>
<style>
    /* Modern Internal Styles to avoid extra files */
    :root {
        --primary: #032154;
        --accent: #f2db18;
        --white: #ffffff;
    }
    
    /* LATEST UPDATE: 2026-03-31 12:05 AM */
    .hero-banner {
        position: relative;
        height: 80vh;
        background-color: #032154;
        background-image: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://images.unsplash.com/photo-1441986300917-64674bd600d8?auto=format&fit=crop&q=80&w=1920');
        background-size: cover;
        background-position: center center;
        background-repeat: no-repeat;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        color: var(--white);
        margin-bottom: 50px;
        overflow: hidden;
    }

    .hero-content h1 {
        font-size: 5rem;
        font-weight: 900;
        text-transform: uppercase;
        margin: 0;
        animation: slideDown 1s ease-out;
        color: #f2db18;
        text-shadow: 2px 2px 10px rgba(0,0,0,0.8);
    }

    .hero-content span {
        color: var(--accent);
        font-size: 2rem;
        display: block;
        margin-top: 10px;
        animation: pulse 2s infinite;
    }

    .shop-btn {
        margin-top: 30px;
        display: inline-block;
        padding: 15px 40px;
        background: var(--accent);
        color: #000;
        text-decoration: none;
        font-weight: bold;
        border-radius: 50px;
        transition: 0.3s;
        text-transform: uppercase;
    }

    .shop-btn:hover {
        background: #e5cf16;
        transform: scale(1.05);
    }

    /* Category Grid */
    .category-section {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
        padding: 0 5%;
        margin-bottom: 50px;
    }

    .category-card {
        position: relative;
        height: 400px;
        overflow: hidden;
        border-radius: 15px;
        cursor: pointer;
    }

    .category-card img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        transition: 0.5s;
    }

    .category-card:hover img {
        transform: scale(1.1);
    }

    .category-label {
        position: absolute;
        bottom: 20px;
        left: 20px;
        background: rgba(0,0,0,0.7);
        padding: 10px 20px;
        color: #fff;
        border-radius: 5px;
        font-weight: bold;
    }

    /* Animations */
    @keyframes slideDown {
        from { transform: translateY(-50px); opacity: 0; }
        to { transform: translateY(0); opacity: 1; }
    }

    @keyframes pulse {
        0%, 100% { transform: scale(1); opacity: 1; }
        50% { transform: scale(1.1); opacity: 0.8; }
    }

    @media (max-width: 768px) {
        .hero-content h1 { font-size: 3rem; }
        .hero-banner { height: 60vh; }
    }
</style>
</head>
<body>
	
	<jsp:include page="header.jsp"/>

    <section class="hero-banner">
        <div class="hero-content">
            <h1>MEGA CLOTHING SALE</h1>
            <span>UP TO 70% OFF ON ALL CATEGORIES</span>
            <a href="product.jsp" class="shop-btn">SHOP NOW</a>
        </div>
    </section>

    <div class="container text-center mb-5">
        <h2 style="color:var(--primary); font-weight:bold; text-transform:uppercase;">Shop by Category</h2>
    </div>

    <section class="category-section">
        <a href="product.jsp?category=men" class="category-card">
            <img src="https://images.unsplash.com/photo-1617137984095-74e4e5e3613f?auto=format&fit=crop&q=80&w=800" alt="Men">
            <div class="category-label">MEN</div>
        </a>
        <a href="product.jsp?category=women" class="category-card">
            <img src="https://images.unsplash.com/photo-1483985988355-763728e1935b?auto=format&fit=crop&q=80&w=800" alt="Women">
            <div class="category-label">WOMEN</div>
        </a>
        <a href="product.jsp?category=kids" class="category-card">
            <img src="https://images.pexels.com/photos/1619697/pexels-photo-1619697.jpeg?auto=compress&cs=tinysrgb&w=800" alt="Kids">
            <div class="category-label">KIDS</div>
        </a>
    </section>

	<jsp:include page="footer.jsp"/>
    
    <!-- Scripts -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>