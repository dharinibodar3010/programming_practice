<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>

	<h2>Login</h2>
	
	<form action="LoginServlet" method="post">
	Username: <input type="text" name="username"><br><br>
	Password: <input type="password" name="password"><br><br>
	
	<input type="submit" value="Login">
	</form>
	
	<%
	String msg=(String)request.getAttribute("errorMessage");
	if(msg!=null){
	%>
	
	<p style="color:red;"><%=msg%></p>
	
	<%
	}
	%>

</body>
</html>