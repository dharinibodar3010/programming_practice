<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.Dao" %>
<%@ page import="com.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    int userId = (Integer) session.getAttribute("userId");

    // Read filter params
    String filterDate  = request.getParameter("filterDate");   // YYYY-MM-DD (exact date)
    String filterMonth = request.getParameter("filterMonth");  // 1-12
    String filterYear  = request.getParameter("filterYear");   // e.g. 2025

    // Decide which data to load
    List<OrderModel> orders;
    boolean isFiltered = (filterDate != null && !filterDate.isEmpty())
                      || (filterMonth != null && !filterMonth.isEmpty())
                      || (filterYear  != null && !filterYear.isEmpty());
    if (isFiltered) {
        orders = Dao.getOrdersByFilter(userId, filterDate, filterMonth, filterYear);
    } else {
        orders = Dao.getUserOrders(userId);
    }

    // For year dropdown: show last 5 years
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    // Date formatter for display
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a");

    // Build a human-readable active filter label
    String[] monthNames = {"", "January","February","March","April","May","June",
                                "July","August","September","October","November","December"};
    StringBuilder activeLabel = new StringBuilder();
    if (filterDate != null && !filterDate.isEmpty()) {
        activeLabel.append("Date: ").append(filterDate);
    } else {
        if (filterMonth != null && !filterMonth.isEmpty()) {
            try { activeLabel.append(monthNames[Integer.parseInt(filterMonth)]); } catch(Exception ex) {}
        }
        if (filterYear != null && !filterYear.isEmpty()) {
            if (activeLabel.length() > 0) activeLabel.append(" ");
            activeLabel.append(filterYear);
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History - Foodie</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .history-container { padding: 50px 0; min-height: 70vh; }

        /* ── Filter Bar ── */
        .filter-bar {
            background: var(--surface-color);
            border: 1px solid rgba(255,255,255,0.08);
            border-radius: 16px;
            padding: 24px 28px;
            margin-bottom: 36px;
            display: flex;
            flex-wrap: wrap;
            gap: 16px;
            align-items: flex-end;
        }
        .filter-bar h4 {
            width: 100%;
            margin: 0 0 4px;
            font-size: 1rem;
            color: var(--text-muted);
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .filter-group {
            display: flex;
            flex-direction: column;
            gap: 6px;
            flex: 1;
            min-width: 140px;
        }
        .filter-group label {
            font-size: 0.78rem;
            font-weight: 600;
            color: var(--text-muted);
            text-transform: uppercase;
            letter-spacing: 0.06em;
        }
        .filter-group select,
        .filter-group input[type="date"] {
            background: var(--bg-color);
            border: 1.5px solid rgba(255,255,255,0.1);
            border-radius: 10px;
            padding: 10px 14px;
            color: var(--text-dark);
            font-size: 0.95rem;
            outline: none;
            transition: border-color 0.2s;
            appearance: none;
            -webkit-appearance: none;
            cursor: pointer;
            width: 100%;
        }
        .filter-group select:focus,
        .filter-group input[type="date"]:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(255,107,53,0.12);
        }
        .filter-divider {
            width: 1px;
            height: 44px;
            background: rgba(255,255,255,0.08);
            margin-bottom: 2px;
            align-self: flex-end;
        }
        .filter-actions {
            display: flex;
            gap: 10px;
            align-self: flex-end;
            flex-wrap: wrap;
        }
        .btn-filter {
            background: var(--primary);
            color: white;
            border: none;
            border-radius: 10px;
            padding: 10px 22px;
            font-size: 0.95rem;
            font-weight: 600;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 8px;
            transition: all 0.2s;
            white-space: nowrap;
        }
        .btn-filter:hover { opacity: 0.88; transform: translateY(-1px); }
        .btn-reset {
            background: transparent;
            color: var(--text-muted);
            border: 1.5px solid rgba(255,255,255,0.12);
            border-radius: 10px;
            padding: 10px 18px;
            font-size: 0.95rem;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.2s;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 7px;
            white-space: nowrap;
        }
        .btn-reset:hover { border-color: var(--primary); color: var(--primary); }

        /* ── Active filter badge ── */
        .active-filter-badge {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            background: rgba(255,107,53,0.1);
            color: var(--primary);
            border: 1px solid rgba(255,107,53,0.25);
            border-radius: 20px;
            padding: 5px 14px;
            font-size: 0.85rem;
            font-weight: 600;
            margin-bottom: 20px;
        }

        /* ── Order Cards ── */
        .order-card {
            background: var(--surface-color);
            border-radius: 14px;
            padding: 22px 26px;
            margin-bottom: 18px;
            box-shadow: var(--shadow-sm);
            display: flex;
            justify-content: space-between;
            align-items: center;
            transition: var(--transition);
            gap: 20px;
        }

        .order-info h3 { font-size: 1.1rem; margin-bottom: 6px; }
        .order-info p  { color: var(--text-muted); font-size: 0.9rem; margin-bottom: 3px; display: flex; align-items: center; gap: 7px; }

        .order-status { display: flex; flex-direction: column; align-items: flex-end; gap: 12px; flex-shrink: 0; }

        .order-amount { font-size: 1.3rem; font-weight: 700; color: var(--text-dark); }

        /* ── Summary strip ── */
        .summary-strip {
            display: flex;
            gap: 24px;
            flex-wrap: wrap;
            margin-bottom: 28px;
        }
        .summary-chip {
            background: var(--surface-color);
            border: 1px solid rgba(255,255,255,0.07);
            border-radius: 12px;
            padding: 14px 22px;
            text-align: center;
            flex: 1;
            min-width: 120px;
        }
        .summary-chip .chip-val { font-size: 1.6rem; font-weight: 700; color: var(--text-dark); }
        .summary-chip .chip-lbl { font-size: 0.8rem; color: var(--text-muted); margin-top: 2px; }

        /* Empty state */
        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: var(--text-muted);
            background: var(--surface-color);
            border-radius: 16px;
        }
        .empty-state i { font-size: 3.5rem; opacity: 0.18; margin-bottom: 18px; display: block; }

        @media(max-width: 768px) {
            .order-card   { flex-direction: column; align-items: flex-start; }
            .order-status { align-items: flex-start; flex-direction: row; flex-wrap: wrap; width: 100%; justify-content: space-between; }
            .filter-divider { display: none; }
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container history-container">
        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:28px; flex-wrap:wrap; gap:12px;">
            <div>
                <h2 style="font-size:1.8rem; margin-bottom:4px;">My Order History</h2>
                <p style="color:var(--text-muted); margin:0;">Track all your past Foodie orders</p>
            </div>
            <a href="products.jsp" class="btn btn-primary" style="padding:10px 22px;">
                <i class="fa-solid fa-plus" style="margin-right:7px;"></i>Order Again
            </a>
        </div>

        <!-- ══════════ FILTER BAR ══════════ -->
        <form method="get" action="order-history.jsp" id="filterForm">
            <div class="filter-bar">
                <h4><i class="fa-solid fa-filter"></i> Filter Orders</h4>

                <!-- Exact Date -->
                <div class="filter-group">
                    <label><i class="fa-regular fa-calendar-day"></i> Specific Date</label>
                    <input type="date" name="filterDate" id="filterDate"
                           value="<%= (filterDate != null ? filterDate : "") %>"
                           onchange="onDatePicked()">
                </div>

                <div class="filter-divider"></div>

                <!-- Month -->
                <div class="filter-group" id="grpMonth">
                    <label><i class="fa-regular fa-calendar"></i> Month</label>
                    <select name="filterMonth" id="filterMonth">
                        <option value="">All Months</option>
                        <% for (int m = 1; m <= 12; m++) {
                               boolean sel = (filterMonth != null && filterMonth.equals(String.valueOf(m)));%>
                        <option value="<%= m %>" <%= sel ? "selected" : "" %>><%= monthNames[m] %></option>
                        <% } %>
                    </select>
                </div>

                <!-- Year -->
                <div class="filter-group" id="grpYear">
                    <label><i class="fa-regular fa-calendar-check"></i> Year</label>
                    <select name="filterYear" id="filterYear">
                        <option value="">All Years</option>
                        <% for (int y = currentYear; y >= currentYear - 4; y--) {
                               boolean sel = (filterYear != null && filterYear.equals(String.valueOf(y)));%>
                        <option value="<%= y %>" <%= sel ? "selected" : "" %>><%= y %></option>
                        <% } %>
                    </select>
                </div>

                <div class="filter-actions">
                    <button type="submit" class="btn-filter">
                        <i class="fa-solid fa-magnifying-glass"></i> Search
                    </button>
                    <a href="order-history.jsp" class="btn-reset">
                        <i class="fa-solid fa-rotate-left"></i> Reset
                    </a>
                </div>
            </div>
        </form>

        <!-- Active filter badge -->
        <% if (isFiltered && activeLabel.length() > 0) { %>
        <div class="active-filter-badge">
            <i class="fa-solid fa-tag"></i>
            Showing orders for: <strong><%= activeLabel %></strong>
            &nbsp;&nbsp;<a href="order-history.jsp" style="color:inherit;text-decoration:none;" title="Clear filter"><i class="fa-solid fa-xmark"></i></a>
        </div>
        <% } %>

        <!-- ══════════ SUMMARY CHIPS ══════════ -->
        <%
            double totalSpent = 0;
            for (OrderModel o : orders) {
                totalSpent += o.getTotalAmount();
            }
        %>
        <div class="summary-strip">
            <div class="summary-chip">
                <div class="chip-val"><%= orders.size() %></div>
                <div class="chip-lbl">Total Orders</div>
            </div>
            <div class="summary-chip">
                <div class="chip-val">₹<%= (int) totalSpent %></div>
                <div class="chip-lbl">Amount Spent</div>
            </div>


        </div>

        <!-- ══════════ ORDER CARDS ══════════ -->
        <% if (orders != null && !orders.isEmpty()) {
               for (OrderModel o : orders) {

                   String dateDisplay = (o.getOrderDate() != null) ? sdf.format(o.getOrderDate()) : "—";
                   List<OrderItemModel> items = Dao.getItemsByOrderId(o.getId());
                   StringBuilder itemSummary = new StringBuilder();
                   for (OrderItemModel item : items) {
                       ProductModel p = Dao.getProductById(item.getProductId());
                       if (p != null) {
                           if (itemSummary.length() > 0) itemSummary.append(", ");
                           itemSummary.append(p.getName()).append(" ×").append(item.getQuantity());
                       }
                   }
        %>
        <div class="order-card">
            <div class="order-info">
                <h3>
                    <a href="order.jsp?id=<%= o.getId() %>" style="color:var(--text-dark);text-decoration:none;">
                        Order <span style="color:var(--primary);">#ORD-<%= o.getId() %></span>
                    </a>
                </h3>
                <p><i class="fa-regular fa-clock"></i> <%= dateDisplay %></p>
                <% if (itemSummary.length() > 0) { %>
                <p><i class="fa-solid fa-utensils"></i> <%= itemSummary %></p>
                <% } %>
                <% if (o.getPaymentMethod() != null && !o.getPaymentMethod().isEmpty()) { %>
                <p><i class="fa-solid fa-credit-card"></i> <%= o.getPaymentMethod() %></p>
                <% } %>
            </div>
            <div class="order-status">
                <div class="order-amount">₹<%= (int) o.getTotalAmount() %></div>
                <a href="order.jsp?id=<%= o.getId() %>" style="color:var(--primary);font-size:0.85rem;font-weight:600;text-decoration:none;">
                    View Details <i class="fa-solid fa-arrow-right" style="font-size:0.75em;"></i>
                </a>
            </div>
        </div>
        <% } } else { %>
        <div class="empty-state">
            <i class="fa-solid fa-box-open"></i>
            <% if (isFiltered) { %>
            <h3 style="color:var(--text-dark);margin-bottom:8px;">No orders found</h3>
            <p>No orders match your selected filter. <a href="order-history.jsp" style="color:var(--primary);font-weight:600;">Clear filter</a></p>
            <% } else { %>
            <h3 style="color:var(--text-dark);margin-bottom:8px;">No orders yet</h3>
            <p>You haven't placed any orders. Start exploring our delicious menu!</p>
            <a href="products.jsp" class="btn btn-primary" style="margin-top:18px;">Browse Menu</a>
            <% } %>
        </div>
        <% } %>
    </div>

    <%@ include file="footer.jsp" %>

    <script>
    // When exact date is picked, clear month/year (and vice-versa)
    function onDatePicked() {
        var d = document.getElementById('filterDate').value;
        if (d) {
            document.getElementById('filterMonth').value = '';
            document.getElementById('filterYear').value  = '';
        }
    }
    document.getElementById('filterMonth').addEventListener('change', function() {
        if (this.value) document.getElementById('filterDate').value = '';
    });
    document.getElementById('filterYear').addEventListener('change', function() {
        if (this.value) document.getElementById('filterDate').value = '';
    });
    </script>
</body>
</html>
