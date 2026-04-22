<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Foodie - Premium Food Delivery</title>
    <link rel="stylesheet" href="css/style.css">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .search-container { position: relative; width: 100%; max-width: 400px; }
        .suggestions-box {
            position: absolute; top: 100%; left: 0; right: 0;
            background: white; border: 1px solid var(--border-color);
            border-radius: 0 0 8px 8px; box-shadow: var(--shadow-md);
            z-index: 1000; display: none; max-height: 200px; overflow-y: auto;
        }
        .suggestion-item {
            padding: 10px 15px; cursor: pointer; transition: background 0.2s;
            color: var(--text-dark); border-bottom: 1px solid #f0f0f0;
        }
        .suggestion-item:hover { background: #f8f9fa; color: var(--primary); }
        .suggestion-item:last-child { border-bottom: none; }
    </style>
</head>
<body>

    <!-- Sticky Navbar -->
    <%@ include file="header.jsp" %>

    <!-- Hero Section -->
    <header class="container hero">
        <div class="hero-content">
            <h1>It's not just food, It's an <span>Experience</span>.</h1>
            <p>Discover the best food & drinks in your city. Premium quality, lightning fast delivery, and top-tier service tailored just for you.</p>
            <div style="display: flex; gap: 15px;">
                <a href="products.jsp" class="btn btn-primary"><i class="fa-solid fa-bolt" style="margin-right: 8px;"></i> Order Now</a>
                <a href="#featured-dishes" class="btn btn-outline">Explore Menu</a>
            </div>
        </div>
        <div class="hero-image glass-panel" style="padding: 10px;">
            <img src="https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=1000&auto=format&fit=crop" alt="Delicious Pizza">
        </div>
    </header>

    <!-- Categories -->
    <section class="categories container">
        <h2 class="section-title">Eat what makes you happy</h2>
        <div class="category-flex">
            <a href="products.jsp?category=Pizza" class="category-card">
                <i class="fa-solid fa-pizza-slice"></i>
                <h3>Pizza</h3>
            </a>
            <a href="products.jsp?category=Burger" class="category-card">
                <i class="fa-solid fa-burger"></i>
                <h3>Burger</h3>
            </a>
            <a href="products.jsp?category=Thali" class="category-card">
                <i class="fa-solid fa-bowl-food"></i>
                <h3>Thali</h3>
            </a>
            <a href="products.jsp?category=Drinks" class="category-card">
                <i class="fa-solid fa-martini-glass-citrus"></i>
                <h3>Drinks</h3>
            </a>
            <a href="products.jsp?category=Dessert" class="category-card">
                <i class="fa-solid fa-ice-cream"></i>
                <h3>Dessert</h3>
            </a>
        </div>
    </section>

    <!-- Featured Products -->
    <section class="products container" id="featured-dishes">
        <h2 class="section-title">Trending Dishes</h2>
        <div class="product-grid">
            <%
                List<ProductModel> products = Dao.getAllProducts();
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
        function getSuggestions(query) {
            const suggestionsBox = document.getElementById('suggestions');
            if (query.length < 2) {
                suggestionsBox.style.display = 'none';
                return;
            }

            fetch('SearchServlet?q=' + encodeURIComponent(query))
                .then(response => response.text())
                .then(data => {
                    if (data.trim().length > 0) {
                        suggestionsBox.innerHTML = data;
                        suggestionsBox.style.display = 'block';
                    } else {
                        suggestionsBox.style.display = 'none';
                    }
                });
        }

        function selectSuggestion(name) {
            const searchInput = document.getElementById('searchInput');
            searchInput.value = name;
            document.getElementById('suggestions').style.display = 'none';
            // Optionally redirect or filter products
            window.location.href = 'products.jsp?search=' + encodeURIComponent(name);
        }

        // Close suggestions when clicking outside
        document.addEventListener('click', function(e) {
            if (!e.target.closest('.search-container')) {
                document.getElementById('suggestions').style.display = 'none';
            }
        });

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