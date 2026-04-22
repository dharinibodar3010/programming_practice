<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.Dao" %>
<%
    int cartCount = 0;
    if (session.getAttribute("userId") != null) {
        cartCount = Dao.getCartCount((Integer) session.getAttribute("userId"));
    }
    String currentPage = request.getRequestURI();
%>
<style>
.navbar { position: sticky; top: 0; z-index: 1000; background: rgba(15,15,15,0.9); backdrop-filter: blur(12px); border-bottom: 1px solid rgba(255,255,255,0.08); padding: 0 20px; }
.navbar .container { display: flex; align-items: center; justify-content: space-between; height: 68px; }
.nav-links { display: flex; align-items: center; gap: 4px; list-style: none; margin: 0; padding: 0; }
.nav-link { padding: 8px 14px; border-radius: 8px; color: var(--text-muted); font-weight: 500; font-size: 0.95rem; text-decoration: none; transition: all 0.2s; white-space: nowrap; }
.nav-link:hover, .nav-link.active { color: var(--primary); background: rgba(255,107,53,0.08); }
.nav-divider { width: 1px; height: 22px; background: rgba(255,255,255,0.1); margin: 0 6px; }
.cart-btn { position: relative; display: flex; align-items: center; gap: 8px; padding: 8px 16px; border-radius: 8px; color: var(--text-muted); font-weight: 500; font-size: 0.95rem; text-decoration: none; transition: all 0.2s; }
.cart-btn:hover { color: var(--primary); background: rgba(255,107,53,0.08); }
.cart-badge { position: absolute; top: 2px; right: 2px; background: var(--primary); color: white; font-size: 0.65rem; font-weight: 700; width: 18px; height: 18px; border-radius: 50%; display: flex; align-items: center; justify-content: center; border: 2px solid var(--bg-color); }
/* User dropdown */
.user-menu { position: relative; }
.user-trigger { display: flex; align-items: center; gap: 9px; padding: 6px 14px; border-radius: 8px; cursor: pointer; transition: all 0.2s; color: var(--text-dark); font-weight: 600; font-size: 0.95rem; }
.user-trigger:hover { background: rgba(255,107,53,0.08); }
.user-avatar { width: 32px; height: 32px; border-radius: 50%; background: linear-gradient(135deg, var(--primary), #ff9261); display: flex; align-items: center; justify-content: center; color: white; font-weight: 700; font-size: 0.85rem; flex-shrink: 0; }
.user-dropdown { position: absolute; top: calc(100% + 10px); right: 0; background: var(--surface-color); border: 1px solid rgba(255,255,255,0.1); border-radius: 12px; padding: 8px; min-width: 190px; box-shadow: 0 20px 40px rgba(0,0,0,0.4); opacity: 0; pointer-events: none; transform: translateY(-8px); transition: all 0.2s; z-index: 999; }
.user-menu:hover .user-dropdown, .user-menu.open .user-dropdown { opacity: 1; pointer-events: all; transform: translateY(0); }
.dropdown-item { display: flex; align-items: center; gap: 12px; padding: 10px 14px; border-radius: 8px; color: var(--text-muted); font-size: 0.9rem; text-decoration: none; transition: all 0.2s; }
.dropdown-item:hover { background: rgba(255,107,53,0.08); color: var(--primary); }
.dropdown-item.danger:hover { background: rgba(220,53,69,0.08); color: #dc3545; }
.dropdown-item i { width: 16px; }
.dropdown-divider { height: 1px; background: rgba(255,255,255,0.08); margin: 6px 0; }
/* Hamburger */
.hamburger { display: none; flex-direction: column; gap: 5px; cursor: pointer; padding: 6px; }
.hamburger span { width: 22px; height: 2px; background: var(--text-dark); border-radius: 2px; transition: all 0.3s; }
@media (max-width: 768px) {
    .hamburger { display: flex; }
    .nav-links { display: none; position: absolute; top: 68px; left: 0; right: 0; background: rgba(15,15,15,0.97); flex-direction: column; align-items: flex-start; padding: 16px; gap: 4px; border-bottom: 1px solid rgba(255,255,255,0.08); }
    .nav-links.open { display: flex; }
    .nav-divider { width: 100%; height: 1px; margin: 4px 0; }
    .user-dropdown { position: static; box-shadow: none; border: none; background: rgba(255,107,53,0.05); padding: 4px; margin-top: 4px; opacity: 1; pointer-events: all; transform: none; display: none; }
    .user-menu.open .user-dropdown { display: block; }
}
/* Search Box Styles */
.search-container { position: relative; flex: 1; max-width: 400px; margin: 0 30px; }
.search-input-wrapper { position: relative; width: 100%; }
.search-control { width: 100%; background: rgba(255,255,255,0.05); border: 1.5px solid rgba(255,255,255,0.1); border-radius: 20px; padding: 10px 20px 10px 45px; color: white; font-size: 0.9rem; outline: none; transition: all 0.3s; }
.search-control:focus { background: rgba(255,255,255,0.08); border-color: var(--primary); box-shadow: 0 0 0 4px rgba(255,107,53,0.1); }
.search-icon { position: absolute; left: 18px; top: 50%; transform: translateY(-50%); color: var(--text-muted); font-size: 0.9rem; }
.suggestions-box { position: absolute; top: calc(100% + 8px); left: 0; right: 0; background: var(--surface-color); border: 1px solid rgba(255,255,255,0.1); border-radius: 12px; box-shadow: 0 10px 30px rgba(0,0,0,0.5); z-index: 1001; overflow: hidden; display: none; }
.suggestion-item { padding: 12px 20px; cursor: pointer; transition: all 0.2s; color: var(--text-muted); font-size: 0.9rem; }
.suggestion-item:hover { background: rgba(255,107,53,0.1); color: var(--primary); }
@media (max-width: 900px) { .search-container { margin: 0 15px; } }
@media (max-width: 768px) { .search-container { display: none; } }
</style>

<nav class="navbar">
    <div class="container">
        <a href="index.jsp" class="logo">
            <i class="fa-solid fa-utensils"></i> Foodie
        </a>

        <!-- Search Bar -->
        <div class="search-container">
            <div class="search-input-wrapper">
                <i class="fa-solid fa-magnifying-glass search-icon"></i>
                <input type="text" id="searchInput" class="search-control" placeholder="Search for dishes..." onkeyup="showSuggestions(this.value)" autocomplete="off">
            </div>
            <div id="suggestions" class="suggestions-box"></div>
        </div>

        <div class="hamburger" id="hamburger" onclick="toggleMenu()">
            <span></span><span></span><span></span>
        </div>

        <ul class="nav-links" id="navLinks">
            <li><a href="index.jsp"        class="nav-link">Home</a></li>
            <li><a href="about.jsp"        class="nav-link">About Us</a></li>
            <li><a href="products.jsp"     class="nav-link">Products</a></li>
            <li><a href="contact.jsp"      class="nav-link">Contact</a></li>

            <% if (session.getAttribute("userName") != null && session.getAttribute("adminId") == null) { %>
                <%-- Logged in as CUSTOMER --%>
                <li><div class="nav-divider"></div></li>

                <li><a href="order-history.jsp" class="nav-link"><i class="fa-solid fa-clock-rotate-left" style="margin-right:6px;font-size:0.85em;"></i>Orders</a></li>

                <li>
                    <a href="cart.jsp" class="cart-btn">
                        <i class="fa-solid fa-bag-shopping"></i> Cart
                        <% if (cartCount > 0) { %>
                        <span class="cart-badge"><%= cartCount %></span>
                        <% } %>
                    </a>
                </li>

                <li>
                    <div class="user-menu" id="userMenu">
                        <div class="user-trigger" onclick="toggleUserMenu()">
                            <div class="user-avatar">
                                <%= session.getAttribute("userName").toString().substring(0,1).toUpperCase() %>
                            </div>
                            <%= session.getAttribute("userName") %>
                            <i class="fa-solid fa-chevron-down" style="font-size:0.7rem;color:var(--text-muted);"></i>
                        </div>
                        <div class="user-dropdown">
                            <a href="profile.jsp"       class="dropdown-item"><i class="fa-solid fa-user"></i> My Profile</a>
                            <a href="order-history.jsp" class="dropdown-item"><i class="fa-solid fa-receipt"></i> Order History</a>
                            <a href="cart.jsp"          class="dropdown-item"><i class="fa-solid fa-bag-shopping"></i> My Cart
                                <% if (cartCount > 0) { %><span style="margin-left:auto;background:var(--primary);color:white;font-size:0.7rem;padding:2px 7px;border-radius:10px;"><%= cartCount %></span><% } %>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a href="logout.jsp" class="dropdown-item danger"><i class="fa-solid fa-right-from-bracket"></i> Logout</a>
                        </div>
                    </div>
                </li>

            <% } else if (session.getAttribute("adminId") != null) { %>
                <%-- Logged in as ADMIN --%>
                <li><div class="nav-divider"></div></li>
                <li><a href="dashboard.jsp" class="nav-link" style="color:var(--primary);font-weight:600;"><i class="fa-solid fa-gauge" style="margin-right:6px;"></i>Dashboard</a></li>
                <li><a href="logout.jsp" class="btn btn-outline" style="padding:7px 18px;margin-left:6px;">Logout</a></li>

            <% } else { %>
                <%-- Not logged in --%>
                <li><div class="nav-divider"></div></li>
                <li><a href="register.jsp" class="nav-link" style="font-weight:600;">Register</a></li>
                <li><a href="login.jsp" class="btn btn-primary" style="padding:8px 20px;margin-left:4px;">Sign In</a></li>
            <% } %>
        </ul>
    </div>
</nav>

<script>
function toggleMenu() {
    document.getElementById('navLinks').classList.toggle('open');
}
function toggleUserMenu() {
    document.getElementById('userMenu').classList.toggle('open');
}
// Close dropdown when clicking outside
document.addEventListener('click', function(e) {
    var menu = document.getElementById('userMenu');
    if (menu && !menu.contains(e.target)) menu.classList.remove('open');
});
// AJAX Suggestions
function showSuggestions(query) {
    let suggestions = document.getElementById("suggestions");
    if (query.trim().length === 0) {
        suggestions.style.display = "none";
        return;
    }
    
    fetch("SearchServlet?q=" + encodeURIComponent(query))
        .then(response => response.text())
        .then(data => {
            if (data.trim().length > 0) {
                suggestions.innerHTML = data;
                suggestions.style.display = "block";
            } else {
                suggestions.style.display = "none";
            }
        });
}

function selectSuggestion(name) {
    document.getElementById("searchInput").value = name;
    document.getElementById("suggestions").style.display = "none";
    // Redirect to products page with the search query
    window.location.href = "products.jsp?q=" + encodeURIComponent(name);
}

// Close suggestions on click outside
document.addEventListener("click", function(e) {
    if (!document.querySelector(".search-container").contains(e.target)) {
        document.getElementById("suggestions").style.display = "none";
    }
});
</script>