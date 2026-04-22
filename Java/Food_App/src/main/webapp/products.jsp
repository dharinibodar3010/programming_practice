<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products - Foodie Premium Delivery</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>
    
    <!-- Hero Section for Products -->
    <section class="about-hero" style="padding: 60px 0; background: url('https://images.unsplash.com/photo-1504674900247-0877df9cc836?q=80&w=2070&auto=format&fit=crop') center/cover no-repeat;">
        <div class="container about-hero-content">
            <h1 style="font-size: 3rem;">Our Menu</h1>
            <p style="font-size: 1.1rem;">Explore our premium selection of delicious meals.</p>
        </div>
    </section>

    <!-- Categories -->
    <section class="categories container">
        <h2 class="section-title">Categories</h2>
        <div class="category-flex">
            <a href="products.jsp?category=Pizza" class="category-card <%= "Pizza".equals(request.getParameter("category")) ? "active" : "" %>">
                <i class="fa-solid fa-pizza-slice"></i>
                <h3>Pizza</h3>
            </a>
            <a href="products.jsp?category=Burger" class="category-card <%= "Burger".equals(request.getParameter("category")) ? "active" : "" %>">
                <i class="fa-solid fa-burger"></i>
                <h3>Burger</h3>
            </a>
            <a href="products.jsp?category=Thali" class="category-card <%= "Thali".equals(request.getParameter("category")) ? "active" : "" %>">
                <i class="fa-solid fa-bowl-food"></i>
                <h3>Thali</h3>
            </a>
            <a href="products.jsp?category=Drinks" class="category-card <%= "Drinks".equalsIgnoreCase(request.getParameter("category")) ? "active" : "" %>">
                <i class="fa-solid fa-martini-glass-citrus"></i>
                <h3>Drinks</h3>
            </a>
            <a href="products.jsp?category=Dessert" class="category-card <%= "Dessert".equalsIgnoreCase(request.getParameter("category")) ? "active" : "" %>">
                <i class="fa-solid fa-ice-cream"></i>
                <h3>Dessert</h3>
            </a>
        </div>
    </section>

    <!-- Products -->
    <section class="products container" style="padding-top: 0;">
        <h2 class="section-title">
            <%
                String cat = request.getParameter("category");
                String query = request.getParameter("q");
                if (query != null && !query.isEmpty()) {
                    out.print("Search Results for \"" + query + "\"");
                } else if (cat != null && !cat.isEmpty()) {
                    out.print(cat + " Dishes");
                } else {
                    out.print("All Products");
                }
            %>
        </h2>
        <div class="product-grid">
            <%
                List<ProductModel> products;
                if (query != null && !query.isEmpty()) {
                    products = Dao.searchProducts(query);
                } else if (cat != null && !cat.isEmpty()) {
                    products = Dao.getProductsByCategory(cat);
                } else {
                    products = Dao.getAllProducts();
                }
                
                if (products != null && !products.isEmpty()) {
                    for (ProductModel p : products) {
            %>
                <div class="product-card">
                    <div class="product-img-wrapper">
                        <img src="<%= p.getImageUrl() %>" alt="<%= p.getName() %>" width="400" height="300">
                    </div>
                    <div class="product-info">
                        <h3 class="product-title"><%= p.getName() %></h3>

                        <p style="color: var(--text-muted); font-size: 0.9rem;"><%= p.getDescription() %></p>
                        <div class="product-footer">
                            <div class="product-price">₹<%= (int)p.getPrice() %></div>
                            <form action="cart.jsp" method="post">
                                <input type="hidden" name="productId" value="<%= p.getId() %>">
                                <button type="submit" name="action" value="add" class="btn btn-primary" style="padding: 8px 16px; font-size: 0.9rem;">
                                    <i class="fa-solid fa-plus"></i> Add
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            <%
                    }
                } else {
            %>
                <div style="grid-column: 1/-1; text-align: center; padding: 40px; color: var(--text-muted);">
                    <i class="fa-solid fa-utensils" style="font-size: 3rem; margin-bottom: 1rem; opacity: 0.2;"></i>
                    <p>No dishes available at the moment. Check back soon!</p>
                </div>
            <% } %>
        </div>
    </section>

    <script>
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
