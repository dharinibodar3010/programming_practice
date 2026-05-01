<%@page import="com.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>

<%
UserModel um = (UserModel) session.getAttribute("user");

// Important null check
if(um == null)
{
    response.sendRedirect("login.jsp");
    return;
}
%>

<h2>Welcome <%= um.getName() %></h2>

<form action="UpdateServlet" method="post">
    <input type="hidden" name="id" value="<%= um.getId() %>">

    Name: <input type="text" name="name" value="<%= um.getName() %>"><br><br>
    Email: <input type="text" name="email" value="<%= um.getEmail() %>"><br><br>
    Password: <input type="password" name="password" value="<%= um.getPassword() %>"><br><br>

    <input type="submit" value="Update">
</form>

<br>

<a href="DeleteServlet?id=<%= um.getId() %>">Delete Account</a>

<br><br>

<a href="LogoutServlet">Logout</a>

</body>
</html>