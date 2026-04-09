 <%@page import="com.dao.Dao"%>
<%@page import="com.model.ProductModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            grid-gap: 20px;
            padding: 20px;
        }
        .product {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        .product img {
            max-width: 100px;
            max-height: 100px;
        }
        .swd-button 
			{
				background: #f2db18;
				border: 1px solid white;
				border-radius: 5px;
				color: white;
				display: inline-block;
				font: bold 12px Arial, Helvetica, sans-serif;
				padding: 10px 15px;
				text-decoration: none;
				text-transform: uppercase;
				margin-top: 15px;
			}
        
    </style>
</head>
<body>


<jsp:include page="header.jsp"/>    

  <div class="container">
        <% 
        String categoryParam = request.getParameter("category");
        
        // Jo user e koi chokkas category click kari hoy
        if (categoryParam != null) {
            List<ProductModel> list = Dao.viewProductsByCategory(categoryParam);
        %>
            <h2 style="margin-top: 30px; text-transform: capitalize; border-bottom: 2px solid #f2db18; padding-bottom: 10px;">
                <%= categoryParam %> Fashion
            </h2>
            <div class="product-grid">
                <% if (list.isEmpty()) { %>
                    <p style="grid-column: 1 / -1; text-align: center; padding: 20px;">No products found in this category.</p>
                <% } else {
                    for (ProductModel m : list) { %>
                    <div class="product">
                        <img src="data:image/jpeg;base64,<%=m.getP_image()%>" width="150px" height="200px" />
                        <h3><%= m.getP_name() %></h3>
                        <p>Price: ₹<%= m.getP_price() %></p>
                        <% if(session.getAttribute("project")!=null) { %>
                            <form action="addtowishlist.jsp" style="display:inline;">
                                <input type="hidden" name="id" value="<%=m.getId()%>">
                                <input type="submit" class="swd-button" value="Wishlist">
                            </form>
                            <form action="addtocart.jsp" style="display:inline;">
                                <input type="hidden" name="id" value="<%=m.getId()%>">
                                <input type="submit" class="swd-button" value="Cart">
                            </form>
                        <% } %>
                    </div>
                <% } } %>
            </div>
        <% 
        } else { 
            // home page pr (badhi category alag-alag batavvi hoy)
            String[] categories = {"men", "women", "kids"};
            for (String cat : categories) {
                List<ProductModel> list = Dao.viewProductsByCategory(cat);
                if (!list.isEmpty()) {
        %>
                <h2 style="margin-top: 50px; text-transform: capitalize; background: #333; color: #fff; padding: 10px 20px; border-radius: 5px;">
                    <%= cat %> Collection
                </h2>
                <div class="product-grid">
                    <% for (ProductModel m : list) { %>
                        <div class="product">
                            <img src="data:image/jpeg;base64,<%=m.getP_image()%>" width="150px" height="200px" />
                            <h3><%= m.getP_name() %></h3>
                            <p>Price: ₹<%= m.getP_price() %></p>
                            <% if(session.getAttribute("project")!=null) { %>
                                <form action="addtowishlist.jsp" style="display:inline;">
                                    <input type="hidden" name="id" value="<%=m.getId()%>">
                                    <input type="submit" class="swd-button" value="Wishlist">
                                </form>
                                <form action="addtocart.jsp" style="display:inline;">
                                    <input type="hidden" name="id" value="<%=m.getId()%>">
                                    <input type="submit" class="swd-button" value="Cart">
                                </form>
                            <% } %>
                        </div>
                    <% } %>
                </div>
        <% 
                }
            } 
        } 
        %>
    </div>
<jsp:include page="footer.jsp"/>    

</body>
</html>