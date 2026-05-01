<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String user = (String) session.getAttribute("user");
	
	if(user == null)
	{
		response.sendRedirect("login.jsp");
	}
	%>
	
	<h2>Welcome <%= user %></h2>
	
	<a href="theme.jsp">Set Theme</a><br><br>
	<a href="step1.jsp">Register (Hidden Field)</a><br><br>
	<a href="LogoutServlet">Logout</a>
	
	

</body>
</html>