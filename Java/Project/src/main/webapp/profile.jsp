<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"/>

<div class="container" style="margin-top: 100px; margin-bottom: 100px; max-width: 500px; background: #f9f9f9; padding: 30px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1);">
    <h3 class="text-center" style="color: #6a5af9; margin-bottom: 20px;">Edit Profile</h3>
    
    <form action="update_process.jsp" method="post">
        <div class="form-group">
            <label><b>Full Name:</b></label>
            <input type="text" name="fullname" class="form-control" value="<%= session.getAttribute("name") %>" required>
        </div>
        
        <div class="form-group">
            <label><b>Email (Read Only):</b></label>
            <input type="email" name="email" class="form-control" value="<%= session.getAttribute("email") %>" readonly style="background-color: #e9ecef;">
        </div>
        
        <div class="form-group">
            <label><b>Phone Number:</b></label>
            <input type="text" name="phone" class="form-control" value="<%= session.getAttribute("num") %>" required>
        </div>
        
        <div style="text-align: center; margin-top: 20px;">
            <button type="submit" class="btn btn-primary" style="background-color: #6a5af9; border: none; width: 100%;">Save Changes</button>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp"/>