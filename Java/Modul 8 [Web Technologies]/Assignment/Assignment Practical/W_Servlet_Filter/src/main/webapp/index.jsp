<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if (errorMessage != null) { %>
	    <p style="color:red;"><%= errorMessage %></p>
	<% } %>
	
	<form action="SubmitFormServlet" method="post">
	    Username: <input type="text" name="username"><br><br>
	    Password: <input type="password" name="password"><br><br>
	    <input type="submit" value="Submit">
	</form>

</body>
</html>