<%@page import="com.dao.Dao"%>
<%@page import="com.model.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add to Wishlist</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <%
        // Check if user is logged in
        if(session.getAttribute("project") != null)
        {
            String id = request.getParameter("id");
            if(id != null) {
                int id2 = Integer.parseInt(id);
                // FIX: Fetch from Product table, not wishlist table
                ProductModel m = Dao.getwishlistindexwise(id2); 
    %>
            <br><br>
            <center>
                <h2><%= m.getP_name() %></h2>
                <h3>Price: <%= m.getP_price() %></h3>
                <p><%= m.getP_des() %></p>
                <img src="data:image/jpeg;base64, <%= m.getP_image() %>" width="350px" height="300px" />
                
                <form action="imageSave2" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="<%= m.getId() %>">
                    <input type="hidden" name="p_name" value="<%= m.getP_name() %>">
                    <input type="hidden" name="p_price" value="<%= m.getP_price() %>">
                    <input type="hidden" name="p_des" value="<%= m.getP_des() %>">
                    <input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
                    <input type="hidden" name="p_image" value="data:image/jpeg;base64,<%= m.getP_image() %>" /> 

                    <div class="form-button mt-3">
                        <button id="submit" type="submit" class="btn btn-primary">Confirm Add to Wishlist</button>
                    </div>
                </form>        
            </center>                
    <%
            }
        } else {
            // Optional: Redirect to login if session is null
            response.sendRedirect("signin.jsp");
        }
    %>        
<jsp:include page="footer.jsp"/>
</body>
</html>